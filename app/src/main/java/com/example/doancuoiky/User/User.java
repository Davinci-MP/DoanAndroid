package com.example.doancuoiky.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doancuoiky.Dialog.NewClassDialog;
import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.AccountActivity;
import com.example.doancuoiky.activity.ListClassesActivity;
import com.example.doancuoiky.activity.ManageStudentsActivity;

public class User extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);
        findViewById(R.id.btnListClasses).setOnClickListener(this);
        findViewById(R.id.btnManageStudents).setOnClickListener(this);
        findViewById(R.id.btmngAccount).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.btnListClasses:
                Intent intent = new Intent(this, ListClassesActivity.class);
                startActivity(intent);

//                Toast.makeText(this,"Xem Danh Sach",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnManageStudents:
                Intent mngintent = new Intent(this, MngStdUser.class);
                startActivity(mngintent);
                break;
            case R.id.btmngAccount:
//                Intent accIntent = new Intent(this, AccountActivity.class);
//                startActivity(accIntent);
                break;
        }
    }
}