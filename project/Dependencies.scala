import sbt._

object Dependencies {

  private object Versions {
    val Grpc: String    = "1.40.1"
    val ScalaPb: String = scalapb.compiler.Version.scalapbVersion
  }

  private object Zio {
    val Config = "1.0.6"
    val Ftp    = "0.3.3"
    val Grpc   = "0.5.1"
    val Http   = "1.0.0.0-RC17"
    val Json   = "0.1.5"
  }

  val grpcNetty: ModuleID          = "io.grpc"               % "grpc-netty"           % Versions.Grpc
  val scalapbRuntimeGrpc: ModuleID = "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % Versions.ScalaPb

  val zioConfig: ModuleID         = "dev.zio"                       %% "zio-config"          % Zio.Config
  val zioConfigMagnolia: ModuleID = "dev.zio"                       %% "zio-config-magnolia" % Zio.Config
  val zioConfigTypesafe: ModuleID = "dev.zio"                       %% "zio-config-typesafe" % Zio.Config
  val zioFtp: ModuleID            = "dev.zio"                       %% "zio-ftp"             % Zio.Ftp
  val zioGrpc: ModuleID           = "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen"    % Zio.Grpc
  val zioHttp: ModuleID           = "io.d11"                        %% "zhttp"               % Zio.Http
  val zioJson: ModuleID           = "dev.zio"                       %% "zio-json"            % Zio.Json

}
