package com.example.assignment_buiquanghaiph28562.activityHello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment_buiquanghaiph28562.MainActivity;
import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.databaseAll.database;
import com.example.assignment_buiquanghaiph28562.field.loginAndReign;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    private EditText edttk, edtmk;
    private Button btnDangNhap, btnDangKy;
    public static database mDatabase;
    private CheckBox mCheckBox;
    private SharedPreferences mSharedPreferences;
    private ArrayList<loginAndReign> mArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        mDatabase = new database(this);
        mDatabase.queryData("CREATE TABLE IF NOT EXISTS login (tk NVARCHAR(20),mk NVARCHAR(20)) ");
//        mDatabase.queryData("INSERT INTO login VALUES ('Hai_Bui_1234','12345')");
        mSharedPreferences = getSharedPreferences("fileLogin", MODE_PRIVATE);
        boNhoTam();
        outPutData();
        //đăng nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outPutData();
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                String acout = edttk.getText().toString().trim();
                String passWord = edtmk.getText().toString().trim();
                for (loginAndReign infor : mArrayList) {
                    if (infor.getTk().trim().equalsIgnoreCase(acout) && infor.getMk().trim().equalsIgnoreCase(passWord)) {
                        if (mCheckBox.isChecked()) {
                            editor.putString("tk", acout);
                            editor.putString("mk", passWord);
                            editor.putBoolean("check", true);
                            editor.commit();
                        } else {
                            editor.remove("tk");
                            editor.remove("mk");
                            editor.commit();
                        }
                        startActivity(new Intent(login.this, MainActivity.class));
                        Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(login.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, singUp.class));
            }
        });

    }


    private void outPutData() {
        Cursor mCursor = mDatabase.getData("SELECT * FROM login");
        while (mCursor.moveToNext()) {
            String tk = mCursor.getString(0).trim();
            String mk = mCursor.getString(1);
            mArrayList.add(new loginAndReign(tk, mk));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        outPutData();
    }

    private void anhXa() {
        edttk = findViewById(R.id.edttk);
        edtmk = findViewById(R.id.edtmk);
        btnDangNhap = findViewById(R.id.btnlogin);
        btnDangKy = findViewById(R.id.btndk);
        mCheckBox = findViewById(R.id.ckghinho);
    }

    private void boNhoTam() {
        edttk.setText(mSharedPreferences.getString("tk", ""));
        edtmk.setText(mSharedPreferences.getString("mk", ""));
        mCheckBox.setChecked(mSharedPreferences.getBoolean("check", false));

    }
}