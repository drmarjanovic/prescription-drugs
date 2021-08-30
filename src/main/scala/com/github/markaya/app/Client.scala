package com.github.markaya.app

import com.github.markaya.app.grpc.drug.HasDrugRequest
import com.github.markaya.app.grpc.drug.ZioDrug.DrugServiceClient
import io.grpc.ManagedChannelBuilder
import scalapb.zio_grpc.ZManagedChannel
import zio.console._
import zio.Layer

object Client extends zio.App {
  val clientLayer: Layer[Throwable, DrugServiceClient] =
    DrugServiceClient.live(
      ZManagedChannel(
        ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext()
      )
    )

  def myAppLogic =
    for {
      r <- DrugServiceClient.hasDrug(HasDrugRequest(1, 3))
      _ <- putStrLn(r.hasDrug.toString)
    } yield ()

  final def run(args: List[String]) =
    myAppLogic.provideCustomLayer(clientLayer).exitCode
}