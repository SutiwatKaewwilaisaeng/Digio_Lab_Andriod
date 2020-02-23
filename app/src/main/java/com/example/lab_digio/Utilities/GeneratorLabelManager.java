package com.example.lab_digio.Utilities;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class GeneratorLabelManager {

    private final static int white = 0xFFFFFFFF;
    private final static int black = 0xFF000000;
    private final static int WIDTH = 500;
    private final static int HEIGHT = 100;

    private static GeneratorLabelManager instance;

    public static GeneratorLabelManager getInstance() {
        if (instance == null)
            instance = new GeneratorLabelManager();
        return instance;
    }

    private Context mContext;

    private GeneratorLabelManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public Bitmap encodeQRCodeAsBitmap(String str) throws WriterException {

        Integer hw = 700;
        BitMatrix result;
        Bitmap bitmap = null;
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, hw, hw, hints);
            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? black : white;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, hw, 0, 0, w, h);
        } catch (Exception iae) {
            iae.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public Bitmap encodeBarcodeAsBitmap(String str){
        BitMatrix result;
        Bitmap bitmap = null;
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.CODE_128, WIDTH, HEIGHT, hints);
            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? black : white;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, WIDTH, 0, 0, w, h);
        } catch (Exception iae) {
            iae.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public Bitmap createBarCode (String codeData, BarcodeFormat barcodeFormat, int codeHeight, int codeWidth) {

        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put (EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            Writer codeWriter;
            if (barcodeFormat == BarcodeFormat.QR_CODE) {
                codeWriter = new QRCodeWriter();
            } else if (barcodeFormat == BarcodeFormat.CODE_128) {
                codeWriter = new Code128Writer();
            } else {
                throw new RuntimeException("Format Not supported.");
            }

            BitMatrix byteMatrix = codeWriter.encode (
                    codeData,
                    barcodeFormat,
                    codeWidth,
                    codeHeight,
                    hintMap
            );

            int width   = byteMatrix.getWidth ();
            int height  = byteMatrix.getHeight ();

            Bitmap imageBitmap = Bitmap.createBitmap (width, height, Bitmap.Config.ARGB_8888);

            for (int i = 0; i < width; i ++) {
                for (int j = 0; j < height; j ++) {
                    imageBitmap.setPixel (i, j, byteMatrix.get (i, j) ? Color.BLACK: Color.WHITE);
                }
            }

            return imageBitmap;

        } catch (WriterException e) {
            e.printStackTrace ();
            return null;
        }
    }

}