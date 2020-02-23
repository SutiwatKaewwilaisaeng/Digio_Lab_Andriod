package com.example.lab_digio.QRCode

import android.content.Intent
import android.os.Bundle
import android.util.Log.e
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_digio.Model.TableQRCodeRequest
import com.example.lab_digio.Model.TableQRCodeResult
import com.example.lab_digio.R
import com.example.lab_digio.ShowQRCode.ShowQRCodeActivity
import kerry.express.th.mobile.ketracking.dialog.progressbar.ProgressBarDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.net.UnknownHostException
import java.util.*
import kotlin.Exception
import kotlin.collections.ArrayList


class QRCodeActivity : AppCompatActivity(), QRContact.View {

    var presenter: QRPresenter? = null
    private var progressBarDialog: ProgressBarDialog? = ProgressBarDialog.newInstance()

    var detailQRcode: TableQRCodeRequest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        presenter = QRPresenter(this)
        val randomNumber = (0..10000).shuffled().random()

        buttonQRcode.setOnClickListener {
            try {
                if (editTextMoney.text.toString().isNullOrEmpty()) {
                    editTextMoney.error = "Please Press Money"
                } else {
                    detailQRcode = TableQRCodeRequest(
                        merchantId = "KB286232984133",
                        metadata = "ถุงผ้า 80.50, ดินสอ 20.00",
                        partnerId = "PTR7197407",
                        partnerSecret = "7c33daeacf294f28a51e0f784e5cb697",
                        partnerTxnUid = randomNumber.toString(),
                        qrType = "3",
                        reference1 = "INV001",
                        reference2 = "",
                        reference3 = "",
                        reference4 = "",
                        requestDt = "2020-02-20T22:54:00+07:00",
                        terminalId = "term1",
                        txnAmount = editTextMoney.text.toString().toDouble(),
                        txnCurrencyCode = "THB"
                    )
                    progressBarDialogShow()
                    presenter?.onSentGetQRCode(
                        detailQRcode!!,
                        editTextMoney.text.toString().toDouble()
                    )
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun progressBarDialogShow() {
        if (!progressBarDialog?.isVisible!!) {
            progressBarDialog?.show(supportFragmentManager, "")
        }
    }


    override fun showResultQRcode(qrList: TableQRCodeResult) {
        // result respond
        progressBarDialog?.dismiss()
        if (qrList.partnerTxnUid == null) {
            Toast.makeText(this, qrList.errorDesc.toString(), Toast.LENGTH_SHORT).show()
        } else {
            val intentBooking = Intent(this, ShowQRCodeActivity::class.java)
                .putExtra("QRcode", qrList.qrCode)
                .putExtra("Accountname", qrList.accountName)
            startActivity(intentBooking)
        }
    }


    override fun showFailedResult(msg: String) {
        //failed
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }


}
