package com.example.assignment_buiquanghaiph28562.activityHello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment_buiquanghaiph28562.MainActivity;
import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.databaseAll.database;


public class singUp extends AppCompatActivity {
    private EditText edtTk,edtmk;
    private Button btndk;
//    private database mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        edtTk=findViewById(R.id.edttk1);
        edtmk=findViewById(R.id.edtmk1);
        btndk=findViewById(R.id.btndk1);
        login.mDatabase=new database(this);

        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acout=edtTk.getText().toString();
                String pass=edtmk.getText().toString();
                if (acout.isEmpty()||pass.isEmpty()){
                    Toast.makeText(singUp.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }else{
                    login.mDatabase.queryData("INSERT INTO login VALUES ('"+acout+"','"+pass+"')");
                    Toast.makeText(singUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(singUp.this,login.class));
                    finish();
                }
            }
        });
    }

}