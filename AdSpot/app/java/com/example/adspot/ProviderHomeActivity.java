package com.example.adspot;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProviderHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        String username = message.substring(0, message.indexOf('.'));

        String master = "Welcome, " + username + "!";
        // Capture the layout's TextView and set the string as its text
        TextView textView4 = findViewById(R.id.textView4);
        textView4.setText(master);

        FloatingActionButton addProduct = findViewById(R.id.floatingActionButton);
        addProduct.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view){
              addProduct(view);
              showSnackbar(view, "dismiss", Snackbar.LENGTH_LONG);
          }
        });

    }
    public void addProduct(View view){
        Intent addProductIntent = new Intent(this, AddProductActivity.class);
        startActivity(addProductIntent);
    }
    public void showSnackbar(View view, String dismissMessage, int duration){
        final Snackbar providerSnackbar = Snackbar.make(view, "Product Uploaded!", Snackbar.LENGTH_LONG);
        providerSnackbar.setAction("DISMISS", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                providerSnackbar.dismiss();
            }
        });
        providerSnackbar.show();
    }

}
