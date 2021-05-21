package com.oring.oring_wm_ui_test.apkTest.model

import com.google.gson.annotations.SerializedName

data class DeviceInfoData(


    @SerializedName("SerialNumber")
    var serialNumber: String? = null,

    @SerializedName("BluToothUUID")
    var bleUUID: String? = null,
    @SerializedName("MCUUUID")
    var mcuUUID: String? = null,
    @SerializedName("NB_RSSI")
    var nbRSSI: String? = null,
    @SerializedName("NB_RSRQ")
    var nbRSRQ: String? = null,
    @SerializedName("NB_RSRP")
    var nbRSRP: String? = null,
    @SerializedName("NB_SINR")
    var bnSINR: String? = null

)