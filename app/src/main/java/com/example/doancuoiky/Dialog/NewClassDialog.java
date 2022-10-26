package com.example.doancuoiky.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.doancuoiky.R;
import com.example.doancuoiky.model.Classes;
import com.example.doancuoiky.sqlite.ClassesDAO;

public class NewClassDialog extends Dialog implements View.OnClickListener{
    private Context context;
    private EditText etP,etQ;
    public NewClassDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_class);

        etP = findViewById(R.id.etP);
        etQ = findViewById(R.id.etQ);


        findViewById(R.id.btSave).setOnClickListener(this);
        findViewById(R.id.btClose).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btSave:
                Classes cls = new Classes();
                cls.setName(etQ.getText().toString());

                ClassesDAO dao = new ClassesDAO(context);
                dao.insert(cls);
                Toast.makeText(context,"Phường Đã Được Lưu",Toast.LENGTH_SHORT).show();
                dismiss();
            break;

            case R.id.btClose:
                dismiss();
                break;
        }

    }
}
