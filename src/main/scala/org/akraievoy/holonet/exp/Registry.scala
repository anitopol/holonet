/*
 Copyright 2012 Anton Kraievoy akraievoy@gmail.com
 This file is part of Holonet.

 Holonet is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Holonet is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Holonet. If not, see <http://www.gnu.org/licenses/>.
 */

package org.akraievoy.holonet.exp

import annotation.tailrec
import store.{RunStore, ExperimentStore, RegistryStore, FileSystem}
import java.io.File
import org.slf4j.LoggerFactory

object Registry extends RegistryData {
  val log = LoggerFactory.getLogger(classOf[RegistryData])

  lazy val expByName = experiments.groupBy(_.name).mapValues {
    expSeq =>
      if (expSeq.size > 1) {
        throw new IllegalArgumentException(
          "multiple experiments with name '%s'".format(
            expSeq.head.name
          )
        )
      }
      expSeq.head
  }

  type ExpConfPair = (Experiment, Config)

  @tailrec
  private def addDependencies(
    pendingNames: Seq[String],
    resultChain: Seq[ExpConfPair],
    configFun: Experiment => Config
  ): Seq[ExpConfPair] = {
    if (resultChain.size + pendingNames.size > 128) {
      throw new IllegalStateException(
        "cyclic dependency of experiments"
      )
    } else if (pendingNames.isEmpty) {
      resultChain
    } else {
      val head = pendingNames.head
      val headExp = expByName.get(head).getOrElse {
        throw new IllegalArgumentException(
          "experiment with name '%s' not found".format(head)
        )
      }
      val newPending =
        pendingNames.tail ++ headExp.depends.filterNot {
          depName =>
            pendingNames.contains(depName)
        }.map {
          depName =>
            expByName.get(depName).getOrElse(
              throw new IllegalArgumentException(
                "experiment '%s' dependency '%s' not registered".format(
                  head, depName
                )
              )
            )
            depName
        }
      addDependencies(
        newPending,
        (headExp, configFun(headExp)) +: resultChain,
        configFun
      )
    }
  }

  private def dependencyChain(
    targetName: String,
    configFun: Experiment => Config
  ) = {
    addDependencies(
      Seq(targetName),
      Nil,
      configFun
    )
  }

  private def execute(expPairSeq: Seq[ExpConfPair]) = {
    val fs = new FileSystem(new File("data"))
    val registryStore = new RegistryStore(fs)

    //  TODO validate we have no param name/type collisions
    (1 to expPairSeq.length).toSeq.foldLeft(Seq.empty[ExperimentStore]){
      (runChain, length) =>
        val subchain = expPairSeq.take(length)
        val currentExpPair = expPairSeq(length - 1)
        val emptyParamSpace = Map(
          true -> Config.EMPTY_SPACE,
          false -> Config.EMPTY_SPACE
        )
        val paramSpace = subchain.zipWithIndex.map{
          case ((exp, conf), index) =>
            conf.paramSpace(index == length - 1, index)
        }.foldLeft(emptyParamSpace) {
          case (mapChained, mapCurrent) =>
            mapChained.map{
              case (parallelFlag, paramPosSeq) =>
                (
                  parallelFlag,
                  for (
                    chainedPoses <- paramPosSeq;
                    currentPoses <- mapCurrent.getOrElse(
                      parallelFlag,
                      Config.EMPTY_SPACE
                    )
                  ) yield {
                    val posSeq = chainedPoses ++ currentPoses
                    posSeq
                  }
                )
            }
        }

        log.info(
          "starting {} with conf {}",
          currentExpPair._1.name,
          currentExpPair._2.name
        )

        val currentUID = registryStore.registerRun(
          currentExpPair._1.name,
          currentExpPair._2.name
        )

        val expStore = new ExperimentStore(
          fs,
          currentUID,
          currentExpPair._1,
          currentExpPair._2,
          runChain
        )

        paramSpace.getOrElse(
          false,
          Config.EMPTY_SPACE
        ).foreach {
          sequentialPos =>
            paramSpace.getOrElse(
              true,
              Config.EMPTY_SPACE
            ).par.foreach {
              parallelPos =>
                val spacePos = sequentialPos ++ parallelPos
                log.debug("spacePos = " + spacePos)
                currentExpPair._1.executeFun(RunStore(expStore, spacePos))
            }
        }

        println("write shutdown for " + currentExpPair._1.name)

        expStore.writeShutdown()

        runChain :+ expStore
    }

    //  FIXME PROCEED on-completion triggers
    log.info("chain complete")

  }

  private def execute(targetExpName: String, configFun: Experiment => Config) {
    val chain = dependencyChain(targetExpName, configFun)
    execute(chain)
  }

  def execute(targetExpName: String, configMap: Map[String, String]) {
    execute(
      targetExpName,
      {
        exp =>
          exp.configs(
            configMap.getOrElse(exp.name, "default")
          )
      }
    )
  }
}