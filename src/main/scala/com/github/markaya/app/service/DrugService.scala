package com.github.markaya.app.service

import com.github.markaya.app.grpc.drug.ZioDrug.ZDrugService
import zio.{ ZEnv, ZIO }
import com.github.markaya.app.grpc.drug.{ HasDrugRequest, HasDrugResponse }
import io.grpc.Status

object DrugService extends ZDrugService[ZEnv, Any] {

  override def hasDrug(request: HasDrugRequest): ZIO[ZEnv, Status, HasDrugResponse] = {
    println(s"Got request: $request")
    ZIO.succeed(HasDrugResponse(hasDrug = true))
  }

}
