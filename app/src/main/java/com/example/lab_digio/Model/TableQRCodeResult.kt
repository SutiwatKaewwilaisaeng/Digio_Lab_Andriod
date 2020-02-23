package com.example.lab_digio.Model


import com.squareup.moshi.Json

data class TableQRCodeResult(
    @Json(name = "accountName")
    var accountName: String? = "",
    @Json(name = "errorCode")
    var errorCode: Any? = null,
    @Json(name = "errorDesc")
    var errorDesc: Any? = null,
    @Json(name = "partnerId")
    var partnerId: String? = "",
    @Json(name = "partnerTxnUid")
    var partnerTxnUid: Any? = 0,
    @Json(name = "qrCode")
    var qrCode: String? = "",
    @Json(name = "statusCode")
    var statusCode: String? = ""
)