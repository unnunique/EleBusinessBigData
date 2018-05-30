package com.sydney.dream.dianshang.util.test

import com.sydney.dream.dianshang.util.DateUtils
import org.apache.log4j.Logger
import org.scalatest.FunSuite

class DateUtilsSuite extends FunSuite{
    val LOG: Logger  = Logger.getLogger(DateUtilsSuite.this.getClass)
    test("com.sydney.dream.dianshang.util.DateUtils.before " +
        "2018-10-30 12:22:22 before ? 2018-10-30 12:22:21") {
        val time01 : String = "2018-10-30 12:22:22"
        val time02 : String = "2018-10-30 12:22:20"
        assert(DateUtils.before(time02, time01))
    }
}
