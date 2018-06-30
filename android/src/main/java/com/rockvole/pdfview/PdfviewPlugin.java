package com.rockvole.pdfview;

import android.app.ProgressDialog;
import android.os.Environment;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.webviewtopdf.PdfView;

import java.io.File;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;


/**
 * PdfviewPlugin
 */
public class PdfviewPlugin implements MethodChannel.MethodCallHandler {


    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "pdfview");
        PdfviewPlugin instance = new PdfviewPlugin(registrar);
        channel.setMethodCallHandler(instance);
    }

    private final Registrar mRegistrar;

    private PdfviewPlugin(Registrar registrar) {
        this.mRegistrar = registrar;
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (call.method.equals("pdfview")) {
            if (!(call.arguments instanceof String)) {
                result.error("ARGUMENT_ERROR", "String argument expected", null);
                return;
            }
            final String text = (String) call.arguments;
            showReport(text);
            result.success(null);

        } else {
            result.notImplemented();
        }
    }

    private void showReport(String reportString) {
        final WebView webView = new WebView(mRegistrar.context());
        webView.loadDataWithBaseURL(null, reportString, "text/HTML", "UTF-8", null);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                generateAndOpenPDF(webView);

            }
        });

//        PrintManager printManager = (PrintManager) mRegistrar.activity().getSystemService(Context.PRINT_SERVICE);
//
//        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();
//
//        String jobName = "Pdf View Print Test";
//
//        PrintAttributes attrib = new PrintAttributes.Builder().
//                setMediaSize(PrintAttributes.MediaSize.NA_LETTER.asLandscape()).
//                setMinMargins(PrintAttributes.Margins.NO_MARGINS).
//                build();
//        printManager.print(jobName, printAdapter, attrib);
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        webView = findViewById(R.id.webView);
//        button_convert = findViewById(R.id.button_convert);
//
//        webView.loadUrl("http://www.annalytics.co.uk/android/pdf/2017/04/06/Save-PDF-From-An-Android-WebView/");
//
//
//        button_convert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {



    }

    private void generateAndOpenPDF(WebView webView) {
        String outputPath = mRegistrar.context().getApplicationInfo().dataDir;

        File path = Environment.getExternalStoragePublicDirectory(outputPath + "/pdf/");

        final String fileName = "Test.pdf";

        final ProgressDialog progressDialog = new ProgressDialog(mRegistrar.activity());
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        PdfView.createWebPrintJob(mRegistrar.activity(), webView, path, fileName, new PdfView.Callback() {

            @Override
            public void success(String path) {
                progressDialog.dismiss();
                PdfView.openPdfFile(mRegistrar.activity(), mRegistrar.context().getApplicationInfo().name, "Do you want to open the pdf file?" + fileName, path);
            }

            @Override
            public void failure() {
                progressDialog.dismiss();

            }
        });
    }
//        });

//    }
}
