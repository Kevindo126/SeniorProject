package com.example.adspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ChatBotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        WebView myWeb = (WebView) findViewById(R.id.ChatBot);
        WebSettings webSettings = myWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWeb.loadUrl("https://webchat.botframework.com/embed/my-qna-service2-bot?s=lUEGFT6F1oc.Dh-naOmHujoVnlVCpdxSekbbvgn3hcbSsNK-FZt_vP0");
    }
}
