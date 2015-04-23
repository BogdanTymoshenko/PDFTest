package ua.com.amicablesoft.pdftest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

import crl.android.pdfwriter.PDFWriter;
import crl.android.pdfwriter.PaperSize;
import crl.android.pdfwriter.StandardFonts;


public class MainActivity extends ActionBarActivity {

    private static final int PAGE_PADDING__TOP = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGeneratePdfClick(View view) {
        PDFWriter pdfWriter = new PDFWriter(PaperSize.A4_WIDTH, PaperSize.A4_HEIGHT);

        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        pdfWriter.addImage(
                (PaperSize.A4_WIDTH - imageBitmap.getWidth()) / 2,
                PaperSize.A4_HEIGHT - PAGE_PADDING__TOP - imageBitmap.getHeight(),
                imageBitmap);

        imageBitmap.recycle();
        imageBitmap = null;

        pdfWriter.setFont(StandardFonts.SUBTYPE, StandardFonts.HELVETICA, StandardFonts.WIN_ANSI_ENCODING);
        pdfWriter.addText(PaperSize.A4_WIDTH / 2 - 30, PAGE_PADDING__TOP + 18, 18, "Test");

        try {
            File pdfFile = new File(Environment.getExternalStorageDirectory(), "generated.pdf");
            pdfFile.createNewFile();
            pdfWriter.writeToFile(pdfFile);
        }
        catch (IOException ex) {
            Log.e("TAG", "Fail to write PDF to file", ex);
        }
    }
}
