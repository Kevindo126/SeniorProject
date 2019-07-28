package com.example.adspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddProductActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


    }

    public void providerHome(View view)
    {
        String productName;
        String description;

        EditText ET1 = (EditText) findViewById(R.id.editText5);
        productName = ET1.getText().toString();

        EditText ET2 = (EditText) findViewById(R.id.editText6);
        description = ET2.getText().toString();

        if(!productName.equals("") && !description.equals("")){
            Intent providerIntent = new Intent(this, ProviderHomeActivity.class);
            startActivity(providerIntent);
        }

    }
}
