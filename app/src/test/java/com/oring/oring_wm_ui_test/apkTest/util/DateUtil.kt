package com.oring.oring_wm_ui_test.apkTest.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    fun getCurrentDate(): String? =
            LocalDateTime.now()
                    .format(DateTimeFormatter
                            .ofPattern("yyyyMMddHHmm"))
}