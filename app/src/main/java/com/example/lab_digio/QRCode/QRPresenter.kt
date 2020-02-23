package com.example.lab_digio.QRCode

import android.util.Log.e
import com.example.lab_digio.Model.TableQRCodeRequest
import com.example.lab_digio.Model.TableQRCodeResult

class QRPresenter(var view: QRContact.View?) : QRContact.Presenter {

    var interactor: QRContact.Interractor? = QRCodeInteractor()

    override fun onSentGetQRCode(list: TableQRCodeRequest, money: Double) {
        try {
            interactor?.postQRCode(money, list) { isSuccess, result, msg ->
                when (isSuccess) {
                    true -> view?.showResultQRcode(result!!)
                    false -> view?.showFailedResult(msg)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            view?.showFailedResult(ex.toString())
        }
    }


}