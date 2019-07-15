package com.example.adspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {


    public String userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        String username = message.substring(0, message.indexOf('.'));
        String password = message.substring((message.indexOf('.')+1));

        String master = "Username: " + username + ", Password: " + password;
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(master);

        String[][] userTable = new String[5][5];
        userTable[0][0] = "Provider.pp"; userTable[0][1] = "P";
        userTable[1][0] = "Consumer.cc"; userTable[1][1] = "C";
        userTable[2][0] = "MrHappy.mm"; userTable[2][1] = "C";
        userTable[3][0] = "MrSad.mm"; userTable[3][1] = "C";

        for (int i = 0; i <= 3; i = i + 1) //search the table
        {
            if (username.equals(userTable[i][0])) { //found user
                if(userTable[i][1].equals("P")){
                    userType = "P";
                }
                else{
                    userType = "C";
                }
            }
        }

    }
}
