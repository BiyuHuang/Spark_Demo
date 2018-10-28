/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wallace.demo.app.algorithmdemo

import scala.reflect.ClassTag

/**
  * Created by wallace on 2018/8/26.
  */
object AlgDemo {

  def trailingZerosV2(n: Long, factor: Double = Math.log(5)): Long = {
    if (n <= 5) {
      0L
    } else {
      (Math.log(n) / factor).toLong + trailingZerosV2(n - 1)
    }
  }

  def compareFunc[T](a: T, b: T)(implicit v: T => Comparable[T]): T = {
    if (a.compareTo(b) > 0) a else b
  }

  def biggerFunc[T](a: T, b: T)(implicit v: T => Ordered[T]): T = {
    if (a.compareTo(b) > 0) a else b
  }

  def bigger[T: Ordering](first: T, second: T)(implicit ordered: Ordering[T]): T = {
    if (ordered.compare(first, second) > 0) first else second
  }

  def createArray[T: ClassTag](elem: T*): Array[T] = {
    Array(elem: _*)
  }

  def func(x: Long): Long = {
    if (x == 1) {
      1
    } else {
      x * func(x - 1)
    }
  }


  def trailingZeros(n: Long): Long = {

    val temp: Long = n / 5
    if (temp == 0) {
      0L
    } else {
      temp + trailingZeros(temp)
    }
  }

  def main(args: Array[String]): Unit = {

  }

}