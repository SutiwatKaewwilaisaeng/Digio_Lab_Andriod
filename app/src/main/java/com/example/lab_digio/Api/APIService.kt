package com.example.lab_digio.Api

import com.example.lab_digio.Model.TableQRCodeRequest
import com.example.lab_digio.Model.TableQRCodeResult
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import java.util.*

interface APIService {

    @POST("/pos/qr_request")
    fun getQRcode(
        @HeaderMap headers:Map<String,String>,@Body body:TableQRCodeRequest
    ):Observable<TableQRCodeResult>

}