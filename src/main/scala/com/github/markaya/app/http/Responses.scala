package com.github.markaya.app.http

import com.github.markaya.app.http.hateoas.{ Error, ErrorResponse }
import zhttp.http._
import zio.Chunk
import zio.blocking.Blocking
import zio.json._
import io.netty.handler.codec.http.HttpHeaderNames.{
  CONTENT_TYPE => ContentType,
  CONTENT_DISPOSITION => ContentDisposition
}
import io.netty.handler.codec.http.HttpHeaderValues.{
  ATTACHMENT => Attachment,
  APPLICATION_OCTET_STREAM => ApplicationOctetStream
}

import java.io.IOException

object Responses {

  def file(name: String, content: HttpData[Blocking, IOException]): Response.HttpResponse[Blocking, IOException] =
    Response.http(
      content = content,
      headers = List(contentTypeApplicationOctetStream, contentDispositionAttachment(name))
    )

  def notFound(title: String, detail: String): Response.HttpResponse[Any, Nothing] = {
    val res = ErrorResponse(Error(Status.NOT_FOUND.toJHttpStatus.code.toString, title, detail))

    Response.http(
      Status.NOT_FOUND,
      content = HttpData.CompleteData(Chunk.fromArray(res.toJsonPretty.getBytes(HTTP_CHARSET))),
      headers = List(Header.contentTypeJson)
    )
  }

  private val contentTypeApplicationOctetStream          =
    Header(ContentType, ApplicationOctetStream)
  private def contentDispositionAttachment(name: String) =
    Header(ContentDisposition, s"""$Attachment; filename="$name"""")

}
