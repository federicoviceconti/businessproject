package com.example.personale.businessproject.controller;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.personale.businessproject.R;

/**
 * Created by personale on 19/02/2017.
 */

public class DisplayMessage {
    public final static String ERROR_MEX_EMPTY = "Please, fill all field!";

    public static void showSnackbar(View parent, String message, View.OnClickListener action){
        Snackbar.make(parent, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", action)
                .show();
    }

    public static AlertDialog.Builder showDialog(Context context, int layout, String title){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final View v = LayoutInflater.from(context).inflate(layout, null);

        alert.setView(v)
            .setTitle(title);

        return alert;
    }
}
