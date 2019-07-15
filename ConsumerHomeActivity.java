package com.example.adspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConsumerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_home);

        TextView textView2 = findViewById(R.id.textView2);

        TextView ListTxt1 = findViewById(R.id.ListTxt1);
        TextView ListTxt2 = findViewById(R.id.ListTxt2);
        TextView ListTxt3 = findViewById(R.id.ListTxt3);
        TextView ListTxt4 = findViewById(R.id.ListTxt4);
        TextView ListTxt5 = findViewById(R.id.ListTxt5);

        ImageView ListImg1 = findViewById(R.id.ListImg1);
        ImageView ListImg2 = findViewById(R.id.ListImg2);
        ImageView ListImg3 = findViewById(R.id.ListImg3);
        ImageView ListImg4 = findViewById(R.id.ListImg4);
        ImageView ListImg5 = findViewById(R.id.ListImg5);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        String username = message.substring(0, message.indexOf('.'));
        String password = message.substring((message.indexOf('.')+1));

        String master = "Welcome, " + username + "!";

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        textView.setText(master);

        if (username.equals("MrHappy")) {

            ImageView product1 = findViewById(R.id.ListImg1);
            product1.setImageResource(R.drawable.happy);

            ImageView product2 = findViewById(R.id.ListImg2);
            product2.setImageResource(R.drawable.happy);

            ImageView product3 = findViewById(R.id.ListImg3);
            product3.setImageResource(R.drawable.happy);

            ImageView product4 = findViewById(R.id.ListImg4);
            product4.setImageResource(R.drawable.happy);

            ImageView product5 = findViewById(R.id.ListImg5);
            product5.setImageResource(R.drawable.happy);
        }
        else if (username.equals("MrSad")) {
            ImageView product1 = findViewById(R.id.ListImg1);
            product1.setImageResource(R.drawable.sad);

            ImageView product2 = findViewById(R.id.ListImg2);
            product2.setImageResource(R.drawable.sad);

            ImageView product3 = findViewById(R.id.ListImg3);
            product3.setImageResource(R.drawable.sad);
        }

    }
}
