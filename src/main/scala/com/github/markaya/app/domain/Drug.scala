package com.github.markaya.app.domain

import zio.json._

final case class Drug(id: Long, name: String, quantity: Long)

object Drug {
  implicit val decoder: JsonDecoder[Drug] = DeriveJsonDecoder.gen[Drug]
}
