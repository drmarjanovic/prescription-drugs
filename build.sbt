import Dependencies._

lazy val info = List(
  name := "prescription-drugs",
  description := "Manages prescription drugs both through GRPC and HTTP API. Also, supports prescription retrieving using SFTP.",
  organization := "com.github.markaya",
  maintainer := "markoristic15@gmail.com",
  version := "0.0.1"
)

lazy val buildInfoSettings = List(
  buildInfoKeys := List[BuildInfoKey](name, description, version),
  buildInfoPackage := "com.github.markaya.app"
)

lazy val protoSettings = List(
  PB.protoSources in Compile += file("src/main/resources/protobuf").getAbsoluteFile,
  PB.deleteTargetDirectory := false,
  PB.targets in Compile := List(
    scalapb.gen(grpc = true)          -> (sourceManaged in Compile).value,
    scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value
  )
)

lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin, JavaAppPackaging)
  .settings(info: _*)
  .settings(buildInfoSettings: _*)
  .settings(protoSettings: _*)
  .settings {
    scalaVersion := "2.13.6"
    libraryDependencies ++= {
      val grpc = List(grpcNetty, scalapbRuntimeGrpc)
      val zio  = List(zioConfig, zioConfigMagnolia, zioConfigTypesafe, zioFtp, zioGrpc, zioHttp, zioJson)

      grpc ++ zio
    }
  }
