package com.github.markaya.app.http

import com.github.markaya.app.config.AppConfig
import com.github.markaya.app.external.Sftp
import zhttp.http._
import zhttp.service.Server
import zio.RIO
import zio.blocking.Blocking
import zio.json._

import java.io.IOException

final case class Routes(config: AppConfig) {

  private val sftp = new Sftp(config.sftp)

  private val routes: HttpApp[Blocking, IOException] = HttpApp.collect {
    case Method.GET -> Root / "health"             =>
      Response.jsonString(AppInfo.up.toJsonPretty)
    case Method.GET -> Root / "api" / "prescriptions" / id =>
      val content = HttpData.fromStream(sftp.read(id))
      Response.http(content = content)
  }

  val server: RIO[Blocking, Nothing] = Server.start(config.http.port, routes.silent)

}