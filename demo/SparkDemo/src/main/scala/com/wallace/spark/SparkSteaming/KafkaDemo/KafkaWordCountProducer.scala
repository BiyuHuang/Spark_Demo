package com.wallace.spark.SparkSteaming.KafkaDemo

import java.util
import java.util.{Timer, TimerTask}

import com.wallace.common.LogSupport
import com.wallace.common.TimeFormat.TimePara
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.io.Source

/**
  * Created by Wallace on 2016/5/5.
  */
object KafkaWordCountProducer extends LogSupport {
  def main(args: Array[String]) {
    //    if (args.length < 3) {
    //      System.err.println("Usage: KafkaWordCountProducer <metadataBrokerList> <topic> <messagesPerSec>")
    //      System.exit(1)
    //    }

    val (brokers, topic, messagesPerSec) = ("localhost:9092", "kafka-spark-demo", "1000")
    val timer = new Timer
    timer.schedule(new senderTIme(brokers, topic, messagesPerSec.toInt), 1000, 5000)
  }
}

class senderTIme(brokers: String, topic: String, messagesPerSec: Int) extends TimerTask with LogSupport {
  // Zookeeper connection properties
  val props = new util.HashMap[String, Object]()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](props)
  // Send some messages


  override def run(): Unit = {
    val file = Source.fromFile("demo/SparkDemo/data/DateProducer_2016-05-14_Test.csv", "UTF-8")
    val lines = file.getLines.toArray
    log.error(s"========== Start to send ${messagesPerSec * 5} message to Topic: [$topic] ==========")
    (1 to messagesPerSec * 5).foreach {
      messageNum =>
        val str: Array[String] = lines(scala.util.Random.nextInt(lines.length)).split(",", -1)
        try {
          val msg = s"""${TimePara.getCurrentDate},${str.drop(1).mkString(",")}"""
          val message = new ProducerRecord[String, String](topic, null, msg)
          producer.send(message)
        } catch {
          case e: Exception =>
            throw e
            log.error(e.getMessage)
        }
    }
    log.error(s"========== Send message to Topic : [$topic] has done ==========")
  }

}