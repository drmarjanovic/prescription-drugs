package com.github.markaya.app

import com.github.markaya.app.config.AppConfig
import zio.config.magnolia.DeriveConfigDescriptor.descriptor
import zio.config.getConfig
import com.github.markaya.app.http.Routes
import com.github.markaya.app.service.DrugService
import scalapb.zio_grpc.{ ServerMain, ServiceList }
import zio._
import zio.config.typesafe.TypesafeConfig

object Main extends ServerMain {

  private val configLayer = TypesafeConfig.fromDefaultLoader(descriptor[AppConfig])

  override def services: ServiceList[ZEnv] = ServiceList.add(DrugService)

  override def run(args: List[String]): URIO[ZEnv, ExitCode] = {
    val status = getConfig[AppConfig].flatMap { config =>
      val http = Routes(config)

      myAppLogic
      http.server
    }.provideCustomLayer(configLayer)

    status.exitCode
  }

}
