package com.github.markaya.app.http

import com.github.markaya.app.config.AppConfig
import com.github.markaya.app.external.Sftp
import com.github.markaya.app.http.hateoas.AppInfo
import zhttp.http._
import zhttp.service.Server
import zio.RIO
import zio.blocking.Blocking
import zio.json._

import java.io.IOException

final case class Routes(config: AppConfig) {

  private val sftp = new Sftp(config.sftp)

  private val routes: HttpApp[Blocking, IOException] = HttpApp.collect {
    case Method.GET -> Root / "health"                       =>
      Response.jsonString(AppInfo.up.toJsonPretty)
    case Method.GET -> Root / "api" / "prescriptions" / name =>
      val content = HttpData.fromStream(sftp.read(name))
      Responses.file(name, content)
    case _ -> path                                           =>
      Responses.notFound(
        title = "Page not found",
        detail = s"The requested URL $path was not found on this server. That's all we know."
      )
  }

  val server: RIO[Blocking, Nothing] = Server.start(config.http.port, routes.silent)

}
