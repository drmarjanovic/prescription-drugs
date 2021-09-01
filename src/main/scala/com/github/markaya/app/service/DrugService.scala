package com.github.markaya.app.service

import com.github.markaya.app.domain.Drug
import com.github.markaya.app.grpc.drug.ZioDrug.ZDrugService
import com.github.markaya.app.grpc.drug.{ HasDrugRequest, HasDrugResponse }
import io.grpc.Status
import zio.json._
import zio.{ ZEnv, ZIO }

import scala.collection.mutable
import scala.io.Source

object DrugService extends ZDrugService[ZEnv, Any] {

  private val drugs: mutable.MutableList[Drug] =
    Source
      .fromResource("db/drugs.json")
      .mkString
      .fromJson[List[Drug]]
      .toOption
      .fold[List[Drug]](Nil)(identity)
      .to[mutable.MutableList]

  override def hasDrug(request: HasDrugRequest): ZIO[ZEnv, Status, HasDrugResponse] = {
    val hasDrug = drugs
      .find(d => d.id == request.id && d.quantity >= request.quantity)
      .fold(false) { d =>
        drugs.update(drugs.indexOf(d), d.copy(quantity = d.quantity - request.quantity))
        true
      }

    ZIO.succeed(HasDrugResponse(hasDrug))
  }

}
