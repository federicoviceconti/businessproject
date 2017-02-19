package com.example.personale.businessproject.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.personale.businessproject.R;
import com.example.personale.businessproject.model.Student;
import com.example.personale.businessproject.view.EditCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by personale on 19/02/2017.
 */

public class AdapterStudent extends RecyclerView.Adapter<AdapterStudent.StudentVH> {

    public static final int SEND_INFO_REQUEST = 10;
    private Context context;
    private List<Student> dataSet;

    public AdapterStudent(Context c){
        this.context = c;
        dataSet = new ArrayList<>();
    }

    public void addStudent(Student s){
        dataSet.add(0, s);
        notifyItemInserted(0);
    }

    public void editStudent(Student s, int position){
        dataSet.set(position, s);
        notifyItemChanged(position);
    }

    public List<Student> getDataSet(){
        return this.dataSet;
    }

    @Override
    public AdapterStudent.StudentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_list, null);
        return new StudentVH(view);
    }

    @Override
    public void onBindViewHolder(final AdapterStudent.StudentVH holder, final int position) {
        Student s = dataSet.get(position);
        holder.nameTv.setText(s.getNome());
        holder.mailTv.setText(s.getEmail());
        holder.phoneTv.setText(s.getTelefono());
        holder.courseTv.setText(s.getCorso().toString());
        holder.menuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(holder.itemView.getContext(), holder.menuTv);
                menu.inflate(R.menu.menu_item_card);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_delete:
                                getDataSet().remove(position);
                                notifyItemRemoved(position);
                                break;
                        }

                        return true;
                    }
                });
                menu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class StudentVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTv, phoneTv, mailTv, courseTv, menuTv;
        Button btnInfo, btnSend, btnCall;

        public StudentVH(View itemView){
            super(itemView);
            nameTv  = (TextView) itemView.findViewById(R.id.student_user);
            mailTv  = (TextView) itemView.findViewById(R.id.student_mail);
            phoneTv = (TextView) itemView.findViewById(R.id.student_phone);
            courseTv = (TextView) itemView.findViewById(R.id.student_course);
            btnCall = (Button) itemView.findViewById(R.id.btn_call);
            btnInfo = (Button) itemView.findViewById(R.id.btn_info);
            btnSend = (Button) itemView.findViewById(R.id.btn_send);
            menuTv = (TextView) itemView.findViewById(R.id.menu);

            btnCall.setOnClickListener(this);
            btnInfo.setOnClickListener(this);
            btnSend.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i;
            int position = getAdapterPosition();

            switch(v.getId()){
                case R.id.btn_info:
                    i = new Intent(context, EditCard.class);
                    i.putExtra(Field.ID, position);
                    i.putExtra(Field.NAME, getDataSet().get(position).getNome());
                    i.putExtra(Field.MAIL, getDataSet().get(position).getEmail());
                    i.putExtra(Field.PHONE, getDataSet().get(position).getTelefono());
                    i.putExtra(Field.COURSE, getDataSet().get(position).getCorso().toString());
                    Activity activity = (Activity) context;
                    activity.startActivityForResult(i, SEND_INFO_REQUEST);
                    break;
                case R.id.btn_send:
                    i = new Intent(Intent.ACTION_SENDTO);
                    i.setData(Uri.parse("mailto:" + getDataSet().get(position).getEmail()));
                    context.startActivity(i);
                    break;
                case R.id.btn_call:
                    Uri uri = Uri.parse("tel:" + getDataSet().get(position).getTelefono());
                    i = new Intent(Intent.ACTION_DIAL);
                    i.setData(uri);
                    context.startActivity(i);
                    break;
            }
        }
    }
}
