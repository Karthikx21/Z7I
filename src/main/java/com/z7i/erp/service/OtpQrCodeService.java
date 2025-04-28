package com.z7i.erp.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class OtpQrCodeService {

    private static final String QR_CODE_IMAGE_FORMAT = "PNG";

    public ResponseEntity<byte[]> generateQRCode(String username, String secretKey) {
        String otpAuthURL = getOtpAuthURL(username, secretKey);
        try {
            byte[] qrCodeImage = generateQRCodeImage(otpAuthURL, 250, 250);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/png");
            return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.OK);
        } catch (WriterException | IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getOtpAuthURL(String username, String secretKey) {
        String issuer = "Z7iBackendApp";
        return String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", issuer, username, secretKey, issuer);
    }

    private byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, QR_CODE_IMAGE_FORMAT, pngOutputStream);
            return pngOutputStream.toByteArray();
        }
    }
}
