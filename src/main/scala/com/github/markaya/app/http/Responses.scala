package com.github.markaya.app.http

import com.github.markaya.app.http.hateoas.{ Error, ErrorResponse }
import zhttp.http._
import zio.Chunk
import zio.json._

object Responses {

  def notFound(title: String, detail: String): Response.HttpResponse[Any, Nothing] = {
    val res = ErrorResponse(Error(Status.NOT_FOUND.toJHttpStatus.code.toString, title, detail))

    Response.http(
      Status.NOT_FOUND,
      content = HttpData.CompleteData(Chunk.fromArray(res.toJsonPretty.getBytes(HTTP_CHARSET))),
      headers = List(Header.contentTypeJson)
    )
  }

}
