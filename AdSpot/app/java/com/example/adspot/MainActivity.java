package com.example.adspot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static MobileServiceClient mClient;
    private static String globalReply = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String testerS = "some text";

        //you can now connect to our Azure server and get the client information with 2 lines of code!
        AzureService.initCon(this);
        mClient = AzureService.getClient();
        //Log.d("Hello: ", "test");

        //This should store a user. Not a 'real' function. Just for testing
        //enterTableVal();
        //getTableVal();
        //extractUserInfo(globalReply);

        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);



    //    ImageView background = findViewById(R.id.imageView3);
    //    background.setImageResource(R.drawable.background2);


    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ProviderHomeActivity.class);
        Intent intent2 = new Intent(this, ConsumerHomeActivity.class);

        //Send Username
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

        //Send Password
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String message2 = editText2.getText().toString();

        String master = message + "." + message2;

        int search = searchTable(master);

        if (search == 1) { //Provider
            Toast toast=Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,150);
            toast.show();
            intent.putExtra(EXTRA_MESSAGE, master);
            startActivity(intent);
        }
        else if (search == 2) { //Consumer
            Toast toast=Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,150);
            toast.show();
            intent2.putExtra(EXTRA_MESSAGE, master);
            startActivity(intent2);
        }
        else {
            Toast toast=Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,150);
            toast.show();
        }
    }

    public void createAcc(View view)
    {

        Intent intent = new Intent(this, CreateAccActivity.class);
        startActivity(intent);

        //Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(
        //        "https://webchat.botframework.com/embed/my-qna-service2-bot?s=lUEGFT6F1oc.Dh-naOmHujoVnlVCpdxSekbbvgn3hcbSsNK-FZt_vP0"));
        //startActivity(intent);
    }

    private int searchTable(String user)
    {
        String[][] userTable = new String[5][5];
        userTable[0][0] = "Provider.pp"; userTable[0][1] = "P";
        userTable[1][0] = "Consumer.cc"; userTable[1][1] = "C";
        userTable[2][0] = "MrHappy.mm"; userTable[2][1] = "C";
        userTable[3][0] = "MrSad.mm"; userTable[3][1] = "C";

        for (int i = 0; i <= 3; i = i + 1) //search the table
        {
            if (user.equals(userTable[i][0])) { //found user
                user = userTable[i][1];
                if (user.equals("P")) {return 1;}
                if (user.equals("C")) {return 2;}
            }
        }
        return 0;
    }

    public static void enterTableVal()
    {
        final MobileServiceTable<DummyTable> mDummyTable = mClient.getTable(DummyTable.class);
        final DummyTable item = new DummyTable("Whataburger", "Yum");

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mDummyTable.insert(item).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
        runAsyncTask(task);
    }

    public static void getTableVal() {
        final MobileServiceTable<DummyTable> mTable = mClient.getTable(DummyTable.class);

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            List<DummyTable> res = null;
            DummyTable obj = new DummyTable();

            @Override
            protected String doInBackground(Void... params) {
                String Result = "0";
                try {
                   res = mTable
                            .select("username")
                            .execute()
                            .get();

                    obj = res.get(0);
                    int i = 0;
                    while (obj != null){
                        obj = res.get(i);
                        Result = obj.getUsername();
                        Log.d("TASKREPLY0: ", Result);
                        if (Result.equals("Calvin")) {
                            break;
                        }
                        i = i + 1;
                    }


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return Result;
            }

            @Override
            protected void onPostExecute(String Result){
                getReply(Result);
            }
        };
        runAsyncTask2(task);

    }

    private static AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private static AsyncTask<Void, Void, String> runAsyncTask2(AsyncTask<Void, Void, String> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private static void getReply(String rep)
    {
        globalReply = rep;
        Log.d("GLOBALREPLY: ", globalReply); //proves that the username is found
    }

    private void extractUserInfo(String data){
        int a = data.indexOf("username");
        a = a + 11;
        String UserName = data.substring(a, 6);
        globalReply = UserName;

    }

}


