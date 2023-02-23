package com.example.akkar2.QrCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class CodeGenerator {

    public static BufferedImage generateQRCode(String urlText) throws Exception {// create or manipulate 2Dimage
        QRCodeWriter qrCodeWriter = new QRCodeWriter();//genereate Qrcode
        BitMatrix bitMatrix = qrCodeWriter.encode(urlText, BarcodeFormat.QR_CODE, 200, 200);//stores the black and white squares of a Qr cOde

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
