package com.sydney.dream.dianshang.util

/**
  * 字符串相关的功能
  */
object StringUtil {
    /**
      * 字符串为空
      * @param str 需要判断的字符串
      * @return true 为空 else false
      */
    def isEmpty(str: String): Boolean = {
        str == null || "".equals(str)
    }

    /**
      * 字符换非空
      * @param str 需要判断的字符串
      * @return true 非空 elase false
      */
    def isNotEmpty(str: String): Boolean = {
        str != null && !"".equals(str)
    }

    /**
      * 截取字符串两端的逗号
      * @param str 需要截取的字符串
      * @return 截取掉两端逗号后的字符串
      */
    def trimComma(str: String): String = {
        if (isEmpty(str)){
            str
        } else {
            var strTmp = str
            if (strTmp.startsWith(",")) {
                strTmp = str.substring(1)
            }
            if (strTmp.endsWith(",")) {
                strTmp = strTmp.substring(0, strTmp.length - 1)
            }
            strTmp
        }
    }

    /**
      * 补全两位数
      * @param str 需要补全的内容
      * @return 补全的两位数
      */
    def fillWithZero(str: String): String = {
        if (isEmpty(str)) {
            str
        } else {
            if(str.length == 2) {
                str
            } else if(str.length < 2) {
                "0" + str
            } else {
                str
            }

        }
    }

    private val SPLIT_STR = "="
    /**
      * 从拼接的字符串中截取所需的内容
      * @param str 拼接的字符串
      * @param delimeter 分割符
      * @param field 需要取的字段
      * @return 取到的字段
      */
    def getFieldFromConcatString(str: String, delimeter: String, field: String): String = {
        // TODO 更为精确的应该用正则保证字符串的格式
        if(isEmpty(str)) {
            str
        } else {
            val arr = str.split(delimeter)
            var i = 0
            var result: String = null
            var boolean : Boolean = true
            while (i < arr.length && boolean) {
                val fieldName = arr(i).split(SPLIT_STR)(0)
                val fieldValue = arr(i).split(SPLIT_STR)(1)
                if (fieldName != null && fieldName.equals(field)) {
                    result = fieldValue
                    boolean = false
                }
                i = i + 1
            }
            result
        }
    }

    private val SPLIT_STR_NEW = "|"

    /**
      * 替换字符砖中的某个值
      * @param str 需要替换的字符串
      * @param delimeter 原始字符串的分割符
      * @param field 需要替换的字段
      * @param value 替换的值
      * @return 替换后的字符串
      */
    def setFieldInConcatString(str: String,delimeter: String,
                               field: String, value: String ): String ={
        // TODO 更为精确的应该用正则保证字符串的格式
        if (isEmpty(str)) {
            str
        } else {
            val arr = str.split(delimeter)
            var i = 0
            var result: String = null
            var boolean : Boolean = true
            while (i < arr.length && boolean) {
                val fieldName = arr(i).split(SPLIT_STR)(0)
                val fieldValue = arr(i).split(SPLIT_STR)(1)
                if (fieldName != null && fieldName.equals(field)) {
                    arr(i) = fieldName + SPLIT_STR + value
                    boolean = false
                }
                i = i + 1
            }
            var finalStr = new StringBuffer()
            i = 0
            while (i < arr.length) {
                finalStr.append(arr(i))
                if (i < arr.length - 1) {
                    finalStr.append("|")
                }
                i = i + 1
            }
            finalStr.toString
        }
    }
}
