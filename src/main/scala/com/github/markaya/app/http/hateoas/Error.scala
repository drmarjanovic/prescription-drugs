package com.github.markaya.app.http.hateoas

import zio.json._

final case class Error (status: String, title: String, detail: String)

object Error {
  implicit val encoder: JsonEncoder[Error] = DeriveJsonEncoder.gen[Error]
}
