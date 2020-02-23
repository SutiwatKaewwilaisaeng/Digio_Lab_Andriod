package com.example.lab_digio.Model


import com.squareup.moshi.Json

data class TableQRCodeRequest(
    @Json(name = "merchantId")
    var merchantId: String? = "",
    @Json(name = "metadata")
    var metadata: String? = "",
    @Json(name = "partnerId")
    var partnerId: String? = "",
    @Json(name = "partnerSecret")
    var partnerSecret: String? = "",
    @Json(name = "partnerTxnUid")
    var partnerTxnUid: String? = "",
    @Json(name = "qrType")
    var qrType: String? = "",
    @Json(name = "reference1")
    var reference1: String? = "",
    @Json(name = "reference2")
    var reference2: String? = "",
    @Json(name = "reference3")
    var reference3: String? = "",
    @Json(name = "reference4")
    var reference4: String? = "",
    @Json(name = "requestDt")
    var requestDt: String? = "",
    @Json(name = "terminalId")
    var terminalId: String? = "",
    @Json(name = "txnAmount")
    var txnAmount: Double? = 0.00,
    @Json(name = "txnCurrencyCode")
    var txnCurrencyCode: String? = ""
)
