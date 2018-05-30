package com.sydney.dream.dianshang.create_data

import java.util.UUID

import com.sydney.dream.dianshang.util.{DateUtils, StringUtil}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types._

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * 创建一些测试数据
  */
object InitSomeData {
    // 用户进行search,click,order,pay 操作
    val SEARCH = "search"
    val CLICK = "click"
    val ORDER = "order"
    val PAY = "pay"


    def main(args: Array[String]): Unit = {
        val spark = SparkSession
            .builder
            .appName("InitSomeData")
            .master("local[*]")
            .getOrCreate()
        val sc = spark.sparkContext
        val sqlC = spark.sqlContext

        // 模拟用户搜索关键词
        val searchKeyWords = Array("火锅", "蛋糕", "重庆辣子鸡", "重庆小面",
            "呷哺呷哺", "新辣道鱼火锅", "国贸大厦", "太古商场", "日本料理", "温泉")

        // 用户操作类型
        val actions = Array(SEARCH, CLICK, ORDER, PAY)

        // 获取当天时间
        val date = DateUtils.getToday
        // 用来生成随机数
        val random = new Random
        // 存储用户访问的每一行数据
        val rows = ArrayBuffer[(String, Int, Int, String, String, Long, Long, String, String, String, String, String)]()

        // 创造数据
        var i = 0
        while (i < 100) {
            var  j = 0
            val userId = random.nextInt(100)
            while (j < 10) {
                var k = 0
                //每次访问的记录id
                val sessionId = UUID.randomUUID().toString.replace("-", "")
                //打开网站时间
                val baseTime = date + random.nextInt(24)
                while (k < random.nextInt(100)) {
                    // 访问了那一页
                    val pageId = random.nextInt(10)
                    // 进行操作的时间
                    val actionTime = baseTime + ":" +
                        StringUtil.fillWithZero(random.nextInt(60).toString) + ":" +
                        StringUtil.fillWithZero(random.nextInt(60).toString)
                    // 搜索关键字
                    var searchKeyWord: String = null
                    // 点击的类别Id
                    var clickCategoryId: Long  = Long.MinValue
                    // 点击的产品Id
                    var clickProductId: Long   = Long.MinValue
                    // 下订单的类别Id
                    var orderCategoryIds: String = null
                    // 下订单的产品Id
                    var orderProdectIds: String = null
                    // 支付的类别Id
                    var payCategoryIds: String = null
                    // 支付的产品Id
                    var payProductIds: String  = null

                    val action = actions(random.nextInt(4))
                    if (SEARCH.equals(action)) {
                        searchKeyWord = searchKeyWords(random.nextInt(10))
                    } else  if (CLICK.equals(action)) {
                        clickCategoryId = random.nextInt(100)
                        clickProductId = random.nextInt(100)
                    } else  if(ORDER.equals(action)) {
                        orderCategoryIds = random.nextInt(100).toString
                        orderProdectIds = random.nextInt(100).toString
                    } else if (PAY.equals(action)) {
                        payCategoryIds = random.nextInt(100).toString
                        payProductIds = random.nextInt(100).toString
                    }
                    val row = (sessionId, userId, pageId, actionTime, searchKeyWord,
                        clickCategoryId, clickProductId, orderCategoryIds, orderProdectIds,
                        payCategoryIds, payProductIds, date)
                    rows += row
                }
                j = j + 1
            }
            i = i + 1
        }

        val schema = Array(
            StructField("session_id",StringType, nullable = false),
            StructField("user_id", LongType, nullable = false),
            StructField("page_id", LongType, nullable = false),
            StructField("action_time", StringType, nullable = false),
            StructField("click_category_id", LongType, nullable = true),
            StructField("click_procduct_id", LongType, nullable = true),
            StructField("order_category_ids", StringType, nullable = true),
            StructField("order_product_ids", StringType, nullable = true),
            StructField("pay_category_ids", StringType, nullable = true),
            StructField("pay_product_ids", StringType, nullable = true),
            StructField("date", StringType, nullable = false)
        )

        val arrs = rows.toArray[(String, Int, Int, String, String, Long, Long, String, String, String, String, String)]
        arrs.foreach(println)
        println("========================================" + arrs.size)
        val rdd = sc.parallelize(arrs)
        rdd.foreach(println)
//        val rdd = sc.parallelize(rows.toSeq)
//        rdd.foreach(println)

//        val sessionInfo = spark.createDataFrame(sc.parallelize(rows).map(arr => Row(arr(0), arr(1), arr(2),
//            arr(3), arr(4), arr(5), arr(6), arr(7), arr(8), arr(9), arr(10))), StructType(schema))
//
//        sessionInfo.show()


    }
}
