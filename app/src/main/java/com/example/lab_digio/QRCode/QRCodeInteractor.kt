package com.example.lab_digio.QRCode

import com.example.lab_digio.Api.ApiModule
import com.example.lab_digio.Model.TableQRCodeRequest
import com.example.lab_digio.Model.TableQRCodeResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException


class QRCodeInteractor : QRContact.Interractor {

    var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()
    var view: QRContact.View? = null
    var presenter: QRContact.Presenter? = null


    override fun postQRCode(
        money: Double, request: TableQRCodeRequest,
        output: (isSuccess: Boolean, result: TableQRCodeResult?, msg: String) -> Unit?
    ) {
        view = QRCodeActivity()
        presenter = QRPresenter(view)
        val mapHeader = mutableMapOf<String, String>()

        mapHeader["Content-Type"] = "application/json"
        mCompositeDisposable?.add(
            ApiModule.QRcodeInterceptor().getQRcode(mapHeader, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    output(true, it, "Success")
                }, { e ->
                    e.printStackTrace()
                    if (e is HttpException) {
                        output(false, null, e.message())
                    } else {
                        output(false, null, e.message!!)
                        view?.showFailedResult(e.message!!)
                    }
                })
        )
    }


}