package com.oring.oring_wm_ui_test.apkTest.model

import com.google.gson.annotations.SerializedName

data class TerminalBlockData(

        @SerializedName("Subject")
        var subject: String? = null,

        var member: MutableList<TerminalBlockMember>? = null

)

data class TerminalBlockMember(

        @SerializedName("Name")
        var name: String? = null,

        @SerializedName("Status")
        var status: String? = null,

        @SerializedName("Events")
        var event: String? = null,

        @SerializedName("Switch")
        var swithc: String? = null

)