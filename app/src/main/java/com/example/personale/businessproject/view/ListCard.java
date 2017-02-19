package com.example.personale.businessproject.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.personale.businessproject.R;
import com.example.personale.businessproject.controller.AdapterStudent;
import com.example.personale.businessproject.controller.DisplayMessage;
import com.example.personale.businessproject.controller.Field;
import com.example.personale.businessproject.model.Corso;
import com.example.personale.businessproject.model.Student;

/**
 * Created by personale on 19/02/2017.
 */

public class ListCard extends AppCompatActivity implements View.OnClickListener{

    RecyclerView listRv;
    LinearLayoutManager linearLayoutManager;
    FloatingActionButton addFab;
    AdapterStudent adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        addFab = (FloatingActionButton) findViewById(R.id.add_student);
        listRv = (RecyclerView) findViewById(R.id.recycler_list);

        initialiazeAll();
        addFab.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && data != null){
            if(requestCode == AdapterStudent.SEND_INFO_REQUEST){
                int id = data.getIntExtra(Field.ID, -1);

                if(id > -1){
                    String name = data.getStringExtra(Field.NAME);
                    String mail = data.getStringExtra(Field.MAIL);
                    String phone = data.getStringExtra(Field.PHONE);
                    Corso course = Corso.setCorso(data.getStringExtra(Field.COURSE));

                    adapter.editStudent(new Student(name, phone, mail, course), id);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        showAddDialog();
    }

    public void showAddDialog(){
        AlertDialog.Builder addAlert = new AlertDialog.Builder(this);
        final View v = LayoutInflater.from(this).inflate(R.layout.item_dialog_alert, null);

        final EditText nameEt = (EditText) v.findViewById(R.id.et_user);
        final EditText mailEt = (EditText) v.findViewById(R.id.et_mail);
        final EditText phoneEt = (EditText) v.findViewById(R.id.et_phone);
        final Spinner listCourse = (Spinner) v.findViewById(R.id.spinner_course);

        addAlert.setView(v)
                .setTitle("Insert new user")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameEt.getText().toString(), phone = phoneEt.getText().toString(), email = mailEt.getText().toString();
                        Corso c = choiceCourse(listCourse);

                        if(!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()){
                            Student s = new Student(name, phone, email, c);
                            adapter.addStudent(s);
                            listRv.scrollToPosition(0);
                        }
                        else{
                            DisplayMessage.showSnackbar(addFab, DisplayMessage.ERROR_MEX_EMPTY, null);
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        addAlert.create().show();
    }

    public void initialiazeAll(){
        if(adapter == null){
            adapter = new AdapterStudent(this);
        }

        if(linearLayoutManager == null){
            linearLayoutManager = new LinearLayoutManager(this);
        }

        if(listRv.getAdapter() == null){
            listRv.setAdapter(adapter);
        }

        if(listRv.getLayoutManager() == null){
            listRv.setLayoutManager(linearLayoutManager);
        }
    }

    public Corso choiceCourse(Spinner spinner){
        if(spinner.getSelectedItem().equals("LTM"))
            return Corso.LTM;
        else if(spinner.getSelectedItem().equals("ISA"))
            return Corso.ISA;
        else
            return Corso.SKILLZONE;
    }

}