package com.example.adspot;

import android.content.Context;
import android.util.Log;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;

import java.net.MalformedURLException;

public class AzureService {

    private static Context mCon;
    private static MobileServiceClient mClient;
    private static AzureService mInstance = null;


    private AzureService(Context context) {
        mCon = context;
        try {
            // Create the client instance, using the provided mobile app URL.
            mClient = new MobileServiceClient(
                    "https://adspotsjsu.azurewebsites.net",
                    mCon).withFilter(
                    new ServiceFilter() {
                        @Override
                        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilter) {
                            // Get the request contents
                            String url = request.getUrl();
                            String content = request.getContent();

                            if (url != null) {
                                Log.d("Request URL:", url);
                            }

                            if (content != null) {
                                Log.d("Request Content:", content);
                            }

                            // Execute the next service filter in the chain
                            ListenableFuture<ServiceFilterResponse> responseFuture = nextServiceFilter.onNext(request);

                            Futures.addCallback(responseFuture, new FutureCallback<ServiceFilterResponse>() {
                                @Override
                                public void onFailure(Throwable exception) {
                                    Log.d("Exception:", exception.getMessage());
                                }

                                @Override
                                public void onSuccess(ServiceFilterResponse response) {
                                    if (response != null && response.getContent() != null) {
                                        Log.d("Response Content:", response.getContent());
                                    }
                                }
                            });

                            return responseFuture;
                        }
                    }
            );
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static void initCon(Context context) {
        if (mInstance == null){
            mInstance = new AzureService(context);
        }
    }

    public static MobileServiceClient getClient() {
        return mClient;
    }

}
