package com.github.markaya.app.http

import com.github.markaya.app.config.AppConfig
import com.github.markaya.app.external.Sftp
import com.github.markaya.app.http.hateoas.AppInfo
import zhttp.http._
import zhttp.service.Server
import zio.RIO
import zio.blocking.Blocking
import zio.json._
import zio._
import zio.stream.ZStream

final case class Routes(config: AppConfig) {

  private val sftp = new Sftp(config.sftp)

  private val routes = HttpApp.collectM {
    case Method.GET -> Root / "health"                       =>
      UIO.succeed(Response.jsonString(AppInfo.up.toJsonPretty))
    case Method.GET -> Root / "api" / "prescriptions" / name =>
      sftp
        .read(name)
        .runCollect
        .map(chunk => Responses.file(name, HttpData.fromStream(ZStream.fromChunk(chunk))))
        .orElseSucceed {
          Responses.notFound(
            title = "File not found",
            detail = s"The requested resource '$name' does not exist."
          )
        }
    case _ -> path                                           =>
      UIO.succeed(
        Responses.notFound(
          title = "Page not found",
          detail = s"The requested URL $path was not found on this server. That's all we know."
        )
      )
  }

  val server: RIO[Blocking, Nothing] = Server.start(config.http.port, routes.silent)

}
