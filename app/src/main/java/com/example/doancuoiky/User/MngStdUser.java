package com.example.doancuoiky.User;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.ClassesAdapter;
import com.example.doancuoiky.adapter.StudentsAdapter;
import com.example.doancuoiky.helper.DateTimeHelper;
import com.example.doancuoiky.model.Classes;
import com.example.doancuoiky.model.Student;
import com.example.doancuoiky.sqlite.ClassesDAO;
import com.example.doancuoiky.sqlite.StudentDAO;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MngStdUser extends AppCompatActivity implements View.OnClickListener {
    private EditText etStudentId, etName, etDob, etAddress;
    private Spinner spClasses;
    private List<Classes> classesList;

    private List<Student> studentList;
    private ListView lvStudents;
    private StudentsAdapter studentsAdapter;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);
        etStudentId = findViewById(R.id.etStudentsID);
        etName = findViewById(R.id.etName);
        etDob = findViewById(R.id.etDob);
        spClasses = findViewById(R.id.spClasses);
        //etAddress = findViewById(R.id.etAddress);

        lvStudents = findViewById(R.id.lvStudents);
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student std = studentList.get(i);
                etStudentId.setText(std.getId());
                etName.setText(std.getName());
                etDob.setText(DateTimeHelper.toString(std.getDob()));
                //etAddress.setText(std.getAdDress());
                isEdit = true;
            }
        });

        fillClassesToSpinner();

        findViewById(R.id.btSave).setOnClickListener(this);
        findViewById(R.id.btDelete).setOnClickListener(this);
    }
    private void fillClassesToSpinner(){
        ClassesDAO dao = new ClassesDAO(this);
        classesList = dao.getAll();
        ClassesAdapter classesAdapter = new ClassesAdapter(this,classesList);
        spClasses.setAdapter(classesAdapter);

        spClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //fillStudentsToListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void fillStudentsToListView(){
        StudentDAO dao = new StudentDAO(this);
        try {
            Classes cls = (Classes) spClasses.getSelectedItem();
            studentList = dao.getAllByClass(cls.getId());

            studentsAdapter = new StudentsAdapter(this, studentList);
            lvStudents.setAdapter(studentsAdapter);
        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view) {
        StudentDAO dao = new StudentDAO(this);
        switch (view.getId()){
            case R.id.btSave:
                try{
                    Student std = new Student();
                    std.setId(etStudentId.getText().toString());
                    std.setName(etName.getText().toString());
                    std.setDob(DateTimeHelper.toDate(etDob.getText().toString()));
                    //std.setAdDress(etAddress.getText().toString());

                    Classes cls = (Classes) spClasses.getSelectedItem();
                    std.setClassID(cls.getId());
                    String msg;
                    if(!isEdit){
                        dao.insert(std);
                        msg = "Học sinh đã được lưu!";
                    }else {
                        dao.update(std);
                        msg = "Học sinh đã được cập nhật!";
                    }


                    Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    clearInputFields();

                    isEdit = false;
                    fillStudentsToListView();

                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(this,"Error: " +
                            ex.getMessage(),Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btDelete:
                if (isEdit && !etStudentId.getText().toString().equals("")){
                    String id = etStudentId.getText().toString();
                    dao.delete(id);

                    fillStudentsToListView();
                    Snackbar.make(view, "Học sinh đã được xóa",
                            Snackbar.LENGTH_LONG).show();

                    clearInputFields();
                }
                break;
        }
    }

    private void clearInputFields() {
        etStudentId.setText("");
        etName.setText("");
        etDob.setText("");
        //etAddress.setText("");
    }
}
