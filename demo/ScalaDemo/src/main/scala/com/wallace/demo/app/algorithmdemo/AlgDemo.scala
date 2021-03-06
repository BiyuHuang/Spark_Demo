/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wallace.demo.app.algorithmdemo

import com.wallace.demo.app.common.LogSupport

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
  * Created by wallace on 2018/8/26.
  */
object AlgDemo extends LogSupport {

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

  def triangleCount(s: Array[Int]): Int = {
    (0 until s.length - 2).flatMap {
      i =>
        (i + 1 until s.length - 1).flatMap {
          j =>
            (j + 1 until s.length).map {
              k =>
                if (s(i) + s(j) > s(k) && s(i) + s(k) > s(k) && s(j) + s(k) > s(i)) {
                  println(s"Triangle: ${s(i)} ${s(j)} ${s(k)}")
                  1
                } else {
                  0
                }
            }
        }
    }.sum
  }

  def main(args: Array[String]): Unit = {
    //TODO 1 TriangleCount
    val tCnt: Int = triangleCount(Array(3, 4, 6, 7, 8, 9))
    log.info(s"Triangle Count: $tCnt")

    //TODO 2 Two Sum: (1, 3)
    twoSum(Array(11, 2, 1, 7, 15), 9)

    //TODO 3 Reverse Int Value
    println("-123 reverse: " + reverseIntValue(-123))
    println("210 reverse: " + reverseIntValue(210))

    //TODO 4 Roman to Int
    println("MCMXCIV: " + romanToInt("MCMXCIV"))

    //TODO 5 Find all subsets
    val allSubsets = findAllSubSet(Array(1, 2, 3, 4, 5))
    allSubsets.foreach(x => println("[" + x.mkString("\t") + "]"))
  }

  def twoSum(d: Array[Int], target: Int): Unit = {
    val hMap: mutable.HashMap[Int, Int] = new mutable.HashMap[Int, Int]()
    d.zipWithIndex.foreach {
      x =>
        if (hMap.contains(x._1)) {
          println(s"${hMap(x._1)}, ${x._2}")
        } else {
          hMap.put(target - x._1, x._2)
        }
    }
  }

  def reverseIntValue(x: Int): Int = {
    //    val isNegative: Boolean = (x & Int.MinValue) == Int.MinValue
    val tmp: Int = Math.abs(x).toString.reverse.toInt
    if (x < 0) -tmp else tmp
    //    var tmp: Int = x
    //    var res: Int = 0
    //    while (tmp != 0) {
    //      res = res * 10 + tmp % 10
    //      tmp = tmp / 10
    //    }
    //    res
  }

  def romanToInt(x: String): Int = {
    val reflectMap: Map[Char, Int] = Map('I' -> 1, 'V' -> 5, 'X' -> 10, 'L' -> 50, 'C' -> 100, 'D' -> 500, 'M' -> 1000)
    reflectMap(x.last) + x.reverse.tail.zip(x.reverse).map {
      elem =>
        if (reflectMap(elem._1) >= reflectMap(elem._2)) {
          reflectMap(elem._1)
        } else {
          -reflectMap(elem._1)
        }
    }.sum
  }

  def findPublicPrefix(xs: Array[String]): String = {
    val cMap = new mutable.HashMap[Char, Int]()
    val tmp = xs.map(_.toCharArray)
    //    tmp.reduce
    ???
  }


  def findAllSubSet[T: ClassTag](arr: Array[T]): Array[Array[T]] = {
    val max: BigInt = BigDecimal.double2bigDecimal(math.pow(2, arr.length)).toBigInt
    val res: ArrayBuffer[Array[T]] = new ArrayBuffer[Array[T]]()
    (BigInt(0) until max).foreach {
      i =>
        var flag: BigInt = i
        var shift: Int = 0
        val tmp: ArrayBuffer[T] = new ArrayBuffer[T]()
        while (flag > 0) {
          if ((flag & BigInt(1)) == 1) tmp.append(arr(shift))
          shift += 1
          flag >>= 1
        }
        res.append(tmp.result().toArray[T])
    }
    res.result().toArray[Array[T]]
  }
}
