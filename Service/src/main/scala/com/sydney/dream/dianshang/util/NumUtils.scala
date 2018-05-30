package com.sydney.dream.dianshang.util

//import java.math.BigDecimal

/**
  * 数字格式化的功能
  * 1，格式化小数 函数名： formatDouble
  */
object NumUtils {
    /**
      * 格式化小数
      * @param num 需要格式化的数字
      * @param scale 小数点后几位
      * @return 格式化后的小数
      */
    def formatDouble(num: Double, scale: Int) = {
        val decimal = new java.math.BigDecimal(num)
        decimal.setScale(scale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue
        val decimal1 = BigDecimal
    }
}
