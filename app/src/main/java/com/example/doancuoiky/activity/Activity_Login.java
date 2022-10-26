package com.example.doancuoiky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doancuoiky.Menu_activity;
import com.example.doancuoiky.R;
import com.example.doancuoiky.User.User;
import com.example.doancuoiky.sqlite.DbHelper;

public class Activity_Login extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin,btnSignUp;
    CheckBox chk;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        chk = findViewById(R.id.chk);
        DB = new DbHelper(this);

    }
    public void  rememberUP(String u,String p, boolean status)
    {
        SharedPreferences sPef = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = sPef.edit();
        if(status == false)
        {
            editor.clear();
        }
        else
        {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);

        }
        editor.commit();
    }
    public int isLogin(String u, String p)
    {
            if (u.equals("admin") && p.equals("admin"))
            {
                return 1;
            }
            else
            if (u.equals("dong") && p.equals("12345"))
            {
                return 0;
            }
            else {
                return -1;
                }
    }

    String strU,strP;

    public void checkLogin(View view){
         strU = edUserName.getText().toString();
         strP = edPassword.getText().toString();
         if (strU.isEmpty() || strP.isEmpty()){
             Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được để trống",
                     Toast.LENGTH_LONG).show();
         }
         else
         {
             if(isLogin(strU,strP)>0){
                 Toast.makeText(getApplicationContext(), "Đăng nhập thành công",
                         Toast.LENGTH_LONG).show();
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                        startActivity(new Intent(Activity_Login.this, Menu_activity.class));
                     }
                 },2000);

             }
             else
             {
                 if (isLogin(strU,strP)==0){
                     Toast.makeText(getApplicationContext(), "Đăng nhập thành công (User)",
                             Toast.LENGTH_LONG).show();
                     new Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             startActivity(new Intent(Activity_Login.this, User.class));
                         }
                     },2000);

                     }
                 }
//                 Toast.makeText(getApplicationContext(), "Sai thông tin đăng nhập",
//                         Toast.LENGTH_LONG).show();
             }
         }

//    public void saveUP(View view){
//        String u = edUserName.getText().toString();
//        String p = edPassword.getText().toString();
//        boolean status = chk.isChecked();
//        rememberUP(u,p,status);
//    }

    private void saveAutoLogin(){
        SharedPreferences preferences = getSharedPreferences("Login",MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();

        editor.putString("username",edUserName.getText().toString());
        editor.putString("password",edPassword.getText().toString());
        editor.commit();
    }
}