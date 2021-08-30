addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.9.0")

addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.4")

addSbtPlugin("au.com.onegeek" % "sbt-dotenv" % "2.1.204")

libraryDependencies ++= List(
  "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen" % "0.5.1",
  "com.thesamet.scalapb" %% "compilerplugin" % "0.11.5"
)
