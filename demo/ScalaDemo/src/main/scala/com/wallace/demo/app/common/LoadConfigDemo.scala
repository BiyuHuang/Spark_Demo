/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wallace.demo.app.common

import java.util

import scala.collection.JavaConverters.collectionAsScalaIterableConverter

/**
  * Created by wallace on 2018/1/18.
  */
object LoadConfigDemo extends ProjectConfig {
  def main(args: Array[String]): Unit = {
    setConfigFiles("test.conf")

    val test2: util.List[String] = config.getStringList("test_2")

    test2.asScala.foreach {
      conf =>
        val temp = conf.split("=").map(_.trim)
        val key = temp.head
        val value = temp.last
        log.info(s"Key: $key, Value: $value")
    }
  }

}