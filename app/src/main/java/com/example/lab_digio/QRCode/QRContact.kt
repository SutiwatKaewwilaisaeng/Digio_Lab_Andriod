package com.example.lab_digio.QRCode

import com.example.lab_digio.Model.TableQRCodeRequest
import com.example.lab_digio.Model.TableQRCodeResult


object QRContact {
    interface  View
    {
        fun showResultQRcode(qrList:TableQRCodeResult)
        fun showFailedResult(msg: String)
    }
    interface Presenter{
        fun onSentGetQRCode(list:TableQRCodeRequest,money:Double)

    }

    interface Interractor{
        fun postQRCode(
            money: Double,request: TableQRCodeRequest,
            output: (isSuccess: Boolean,result:TableQRCodeResult?, msg:String) -> Unit?
        )
    }

    interface Router{

    }


}