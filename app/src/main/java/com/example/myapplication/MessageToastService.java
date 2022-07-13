package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

public class MessageToastService {
    public static void MessageToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        return;
    }
}
