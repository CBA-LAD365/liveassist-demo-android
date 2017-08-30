package com.cafex.liveassist_android_demo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cafex.liveassist.LiveAssistChatStyle;
import com.cafex.liveassist.LiveAssistConfig;
import com.cafex.liveassist.LiveAssistView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onCreateLiveAssist();
        onCreateWebView();
    }

    private void onCreateLiveAssist(){
        LiveAssistView liveAssistView = (LiveAssistView) findViewById(R.id.live_assist);
        liveAssistView.loadWithConfig(this.getLiveAssistConfig());
    }

    private LiveAssistConfig getLiveAssistConfig() {
        int accountId = getAccountIdentifier();
        String[] sections = {""};
        LiveAssistChatStyle chatStyle = LiveAssistChatStyle.AUTO;
        return new LiveAssistConfig(accountId,sections,chatStyle);
    }

    private Integer getAccountIdentifier()
    {
        Integer accountNumber = getResources().getInteger(R.integer.account_identifier);
        if(accountNumber == 0){
            showNoAccountIdentifierAlert();
        }
        return accountNumber;
    }

    private void showNoAccountIdentifierAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(getString(R.string.dialog_header));
        alertDialog.setMessage(getString(R.string.no_account_id_message));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void onCreateWebView(){
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(getString(R.string.weburl));

    }
}
