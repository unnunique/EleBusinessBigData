package com.sydney.dream.dianshang.util


/**
  * 主要是进行参数的获取
  * 1， 获取任务id,函数名：getTaskIdFromParams
  * 2, 从jsonObject 中提取任务运行参数， 函数名：getParams
  */
object ParamUtils {
    /**
      * 从命令行中获取任务id
      * @param params main 函数，即spark 程序命令行参数
      * @return 任务id
      */
    def getTaskIdFromParams(params: Array[String]): Long = {
        try {
            if (params != null && params.size > 0) {
                params(0).toLong
            } else {
                Long.MinValue
            }
        } catch {
            case e: Exception =>
                e.printStackTrace
                Long.MinValue
        }
    }

//    /**
//      * 从jsonObject 中提取任务运行参数
//      * @param jsonObject json 对象
//      * @param field 需要提取的内容
//      * @return 提取得到的命令行参数
//      */
//    def getParams(jsonObject: JSONObject, field: String): String = {
//        if (jsonObject == null || field == null) {
//            null
//        } else {
//            val jsonArray = jsonObject.getJSONArray(field)
//            if (jsonArray == null && jsonArray.size() == 0) {
//                null
//            } else {
//                jsonArray.getString(0)
//            }
//        }
//    }
}
