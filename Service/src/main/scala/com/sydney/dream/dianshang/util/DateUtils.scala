package com.sydney.dream.dianshang.util

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

/**
  * 日期相关的工具类，主要功能和函数名如下
  * 1, 判断yyyy-MM-dd HH:mm:ss 模式下 String类型的两个时间，谁在前面，
  *    也就是谁更靠近古代，哈哈，皮一下很开熏。函数名：before
  * 2， 取得两个时间的时间差，单位是秒  函数名：getSecondsOfTwoTime
  * 3， 获取当天的日期 函数名：getToday
  * 4， 获取当前的时间 函数名：getTime
  * 5,  获取昨天的日期  函数名：getYestoday
  * 6， 格式化时间  函数名：formatTime
  * 7， 格式化日期  函数名：formatDate
  * 8， 把字符串转成时间  函数名：getDate
  * 9， 判断两个时间，谁在后面 函数名： after
  */
object DateUtils {
    val time_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date_format = new SimpleDateFormat("yyyy-MM-dd")

    /**
      * 把字符串转换成时间
      * @param dateString  需要转换的字符串
      * @return 转换后的日期对象
      */
    def getDate(dateString: String): Date = {
        time_format.parse(dateString)
    }

    /**
      * 格式化日期
      * @param date Date 对象
      * @return yyyy-MM-dd
      */
    def formatDate(date: Date): String = {
        date_format.format(date)
    }

    /**
      *  格式化时间
      * @param date Date 对象
      * @return yyyy-MM-dd HH:mm:ss
      */
    def  formatTime(date: Date): String = {
        time_format.format(date)
    }

    /**
      * 判断yyyy-MM-dd HH:mm:ss 模式下 String类型的两个时间，谁在前面，
      *    也就是谁更靠近古代，哈哈，皮一下很开熏
      * @param time01 时间1，
      * @param time02 时间2，
      * @return  true 如果时间1比时间2早 else flase
      */
    def before(time01: String, time02: String) : Boolean = {
        try {
            val timeV1 = time_format.parse(time01)
            val timeV2 = time_format.parse(time02)
            if (timeV1.before(timeV2)) {
                true
            } else {
                false
            }
        }catch {
            case e: Exception =>
                e.printStackTrace()
                false
        }
    }

    /**
      * 判断两个时间，谁在后面
      * @param time1 时间一
      * @param time2 时间2
      * @return 如果时间1在时间2 后面，则true， 否则false
      */
    def after(time1: String, time2: String): Boolean = {
        before(time2, time1)
    }

    /**
      * 取得两个时间的时间差，也就是相差多少秒
      * @param time01 yyyy-MM-dd HH:mm:ss 格式的时间
      * @param time02 yyyy-MM-dd HH:mm:ss 格式的时间
      * @return 两个时间相差多少秒
      */
    def getSecondsOfTwoTime(time01: String, time02: String): Long = {
        try {
            val dateTime01 = date_format.parse(time01)
            val dateTime02 = date_format.parse(time02)
            if (dateTime01.before(dateTime02)) {
                dateTime02.getTime - dateTime01.getTime
            } else {
                dateTime01.getTime - dateTime02.getTime
            }
        } catch {
            case e: Exception =>
                e.printStackTrace()
                Long.MinValue
        }
    }

    /**
      * 获取当天的日期
      * @return 当天的日期 2018-02-02
      */
    def getToday: String = {
        date_format.format(new Date())
    }

    /**
      * 获取当前的时间 yyyy-MM-dd HH:mm:ss
      * @return
      */
    def getTime: String = {
        time_format.format(new Date())
    }

    /**
      * 获取昨天的日期
      * @return 左前的日期： yyyy-MM-dd
      */
    def getYestoday: String = {
        val cal = Calendar.getInstance()
        cal.setTime(new Date())
        cal.add(Calendar.DAY_OF_YEAR, -1)
        val date = cal.getTime
        date_format.format(date)
    }
}
