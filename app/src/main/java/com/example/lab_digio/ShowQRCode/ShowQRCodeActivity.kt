package com.example.lab_digio.ShowQRCode

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import com.example.lab_digio.QRCode.QRCodeActivity
import com.example.lab_digio.R
import com.example.lab_digio.Utilities.GeneratorLabelManager
import kotlinx.android.synthetic.main.activity_show_qrcode.*

class ShowQRCodeActivity : AppCompatActivity() {

    var qrCodeQRCodeBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_qrcode)
    }

    override fun onStart() {
        super.onStart()
        val getQRcode = intent.getStringExtra("QRcode")
        val getAccountname = intent.getStringExtra("Accountname")
        e("GGWP","QRCode : ${getQRcode} AccountName: ${getAccountname}")
        qrCodeQRCodeBitmap =
        GeneratorLabelManager.getInstance().encodeQRCodeAsBitmap("$getQRcode")
        imageViewQRCode.setImageBitmap(qrCodeQRCodeBitmap)
        textViewAccount.setText("$getAccountname")
        buttontoFirstPage.setOnClickListener{
            val intentBooking = Intent(this, QRCodeActivity::class.java)
            startActivity(intentBooking)

        }
    }

}
