package com.github.markaya.app.http.hateoas

import zio.json._

final case class ErrorResponse (errors: List[Error])

object ErrorResponse {

  def apply(errors: Error*) = new ErrorResponse(errors.toList)

  implicit val encoder: JsonEncoder[ErrorResponse] = DeriveJsonEncoder.gen[ErrorResponse]
}
