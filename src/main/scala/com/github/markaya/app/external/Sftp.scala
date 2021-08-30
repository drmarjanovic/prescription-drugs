package com.github.markaya.app.external

import com.github.markaya.app.config.SftpConfig
import zio.blocking.Blocking
import zio.ftp.SFtp.readFile
import zio.ftp.{ secure, FtpCredentials, SecureFtpSettings }
import zio.stream.ZStream

import java.io.IOException

final class Sftp(config: SftpConfig) {

  private val settings = SecureFtpSettings(config.host, config.port, FtpCredentials(config.username, config.password))

  def read(path: String): ZStream[Blocking, IOException, Byte] =
    readFile(path).provideSomeLayer(secure(settings))

}
