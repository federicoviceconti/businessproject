package com.example.personale.businessproject.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.personale.businessproject.R;
import com.example.personale.businessproject.controller.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by personale on 19/02/2017.
 */

public class EditCard extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    EditText nameEt, mailEt, phoneEt;
    Spinner courseSp;
    Button btnOk;
    private int id;
    private String name = "", mail = "", phone = "", corso = "";
    private String course;
    Intent i;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

        if((i = getIntent()) != null){
            id = i.getIntExtra(Field.ID, -1);
            name = i.getStringExtra(Field.NAME);
            mail = i.getStringExtra(Field.MAIL);
            phone = i.getStringExtra(Field.PHONE);
            course = i.getStringExtra(Field.COURSE);
            setTitle(name);
        }

        nameEt  = (EditText) findViewById(R.id.card_user_et);
        nameEt.setText(name);
        mailEt  = (EditText) findViewById(R.id.card_mail_et);
        mailEt.setText(mail);
        phoneEt = (EditText) findViewById(R.id.card_phone_et);
        phoneEt.setText(phone);
        courseSp = (Spinner) findViewById(R.id.card_course);
        courseSp.setSelection(selectCourse());

        btnOk = (Button) findViewById(R.id.card_btn_ok);

        nameEt.addTextChangedListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent send = new Intent();
        send.putExtra(Field.ID, id);
        send.putExtra(Field.NAME, nameEt.getText().toString());
        send.putExtra(Field.MAIL, mailEt.getText().toString());
        send.putExtra(Field.PHONE, phoneEt.getText().toString());
        send.putExtra(Field.COURSE, courseSp.getSelectedItem().toString());

        setResult(Activity.RESULT_OK, send);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length() < 20)
            setTitle(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public int selectCourse(){
        ArrayAdapter adapter = (ArrayAdapter)courseSp.getAdapter();
        return adapter.getPosition(course);
    }
}
