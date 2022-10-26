package com.example.doancuoiky.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.AccountAdapter;
import com.example.doancuoiky.model.NewAccount;
import com.example.doancuoiky.sqlite.AccountDAO;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lvaccount ;
    private List<NewAccount> list;
    private AccountAdapter accAdapter;
    private EditText txtIDAccount,txtNameAccount,txtUsernameAccount,txtPassWordAccount,txtRole;
    private boolean isEdit = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        txtIDAccount = findViewById(R.id.txtIDAccount);
        txtNameAccount = findViewById(R.id.txtNameAccount);
        txtUsernameAccount = findViewById(R.id.txtUsernameAccount);
        txtPassWordAccount = findViewById(R.id.txtPasswordAccount);
        txtRole = findViewById(R.id.txtRole);


        lvaccount = findViewById(R.id.lvaccount);



        fillAccountListView();

        lvaccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewAccount ncc = list.get(i);
                txtIDAccount.setText(ncc.getId());
                txtNameAccount.setText(ncc.getName());
                txtUsernameAccount.setText(ncc.getUsername());
                txtPassWordAccount.setText(ncc.getPassword());
                txtRole.setText(ncc.getRole());


                isEdit = true;
            }

        });
        findViewById(R.id.btnSaveAcc).setOnClickListener(this);
        findViewById(R.id.btnDeleteAcc).setOnClickListener(this);
        findViewById(R.id.buttonnew).setOnClickListener(this);
    }

    private void fillAccountListView() {
        AccountDAO dao= new AccountDAO(this);
        list = dao.getAll();

        accAdapter = new AccountAdapter(this,list);
        lvaccount.setAdapter(accAdapter);

    }




    private void ClearInputFields() {
        txtIDAccount.setText("");
        txtNameAccount.setText("");
        txtRole.setText("");
        txtPassWordAccount.setText("");
        txtUsernameAccount.setText("");
    }


    @Override
    public void onClick(View view) {
        AccountDAO dao=new AccountDAO(this);
        switch (view.getId())
        {
            case R.id.btnSaveAcc:
                try{

                    NewAccount newAccount= new NewAccount();
                    newAccount.setName(txtNameAccount.getText().toString());
                    newAccount.setUsername(txtUsernameAccount.getText().toString());
                    newAccount.setPassword(txtPassWordAccount.getText().toString());
                    newAccount.setRole(txtRole.getText().toString());


                    String msg;
                    if (!isEdit)
                    {
                        dao.insert(newAccount);
                        msg=" Đã lưu thành công";
                    }else{
                        newAccount.setId(txtIDAccount.getText().toString());
                        dao.update(newAccount);
                        msg=" Cập nhật thành công";
                    }


                    Snackbar snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_LONG);
                    snackbar.show();
                    ClearInputFields();

                    isEdit = false;
                    fillAccountListView();

                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(this, "lỗi chỗ này nè" +ex.getMessage(),   Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDeleteAcc:
                if(isEdit && !txtIDAccount.getText().toString().equals(""))
                {
                    String id  = txtIDAccount.getText().toString();
                    dao.delete(id);
                    fillAccountListView();
                    Snackbar.make(view,"Xóa thành công",Snackbar.LENGTH_LONG).show();
                    ClearInputFields();
                }
                break;
            case R.id.buttonnew:
                Toast.makeText(this, "Tạo mới", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
