/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hackerforfuture.codeprototypes.dataloader.source

import java.io.{File, FileOutputStream, OutputStream}

import com.hackerforfuture.codeprototypes.dataloader.common.source.SourceReader

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by wallace on 2018/7/10.
  */
class FtpSourceReader extends SourceReader[OutputStream] {

  override def read(): Future[OutputStream] = Future {
    new FileOutputStream(new File(""))
  }
}
