package com.wallace.spark

import com.wallace.common.{FuncRuntimeDur, LogSupport}
import org.apache.spark.sql.SparkSession

/**
  * Created by Wallace on 2016/11/10.
  */
trait CreateSparkSession extends FuncRuntimeDur with LogSupport {
  def createSparkSession(): SparkSession = {
    val warehouseLocation = System.getProperty("user.dir") + "/" + "spark-warehouse"
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("RddConvertToDataFrame")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()

    spark
  }
}