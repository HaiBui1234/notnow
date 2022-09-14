package com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.adapter.recyclerViewadapterKhoanThu;
import com.example.assignment_buiquanghaiph28562.databaseAll.database;
import com.example.assignment_buiquanghaiph28562.field.khoanchi_class;
import com.example.assignment_buiquanghaiph28562.field.loaithu_class;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link khoanThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class khoanThuFragment extends Fragment implements deleteListionner {
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;
    private ArrayList<khoanchi_class> mArrayList = new ArrayList<>();
    ArrayList<loaithu_class> mlist;
    private recyclerViewadapterKhoanThu adapter;
    private database mDatabase;
    private TextView tvloaithu;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public khoanThuFragment() {
        // Required empty public constructor
    }

    public static khoanThuFragment newInstance(String param1, String param2) {
        khoanThuFragment fragment = new khoanThuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khoan_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionButton = view.findViewById(R.id.id_actionButton);
        recyclerView = view.findViewById(R.id.id_khoanthuRecy);
        mDatabase = new database(getActivity());
        mDatabase.queryData("CREATE TABLE IF NOT EXISTS khoanthu (GD INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name float,id INTEGER REFERENCES loaithu1(id) )");
        mDatabase.queryData("INSERT INTO khoanthu VALUES (null,'20000',1)");
        mDatabase.queryData("INSERT INTO khoanthu VALUES (null,'30000',2)");
        mDatabase.queryData("INSERT INTO khoanthu VALUES (null,'40000',3)");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        getdatabase();
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.addkhoanthuthu);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
                EditText edtaddKhoanThu = dialog.findViewById(R.id.tvaddkhoanThu1);
                Button btnhuy = dialog.findViewById(R.id.huykhoanThu1);
                Button btnadd = dialog.findViewById(R.id.btnaddkhoanThu1);
                EditText edtngay = dialog.findViewById(R.id.tvaddngaythang);
                Spinner spinner = dialog.findViewById(R.id.spinnerkhoanthu);
                getthuspinner();
                ArrayAdapter<loaithu_class> adapter1 = new ArrayAdapter(getContext(),
                        android.R.layout.simple_spinner_dropdown_item, mlist);
                spinner.setAdapter(adapter1);
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            if (edtaddKhoanThu.getText().toString().isEmpty()) {
                                Toast.makeText(getActivity(), "Không để trống", Toast.LENGTH_SHORT).show();
                            } else {
                                mlist = new ArrayList<>();
                                float thu = Float.parseFloat(edtaddKhoanThu.getText().toString());
                                loaithu_class aClass = (loaithu_class) spinner.getSelectedItem();
                                int id1 = aClass.getId();
                                mDatabase.queryData("INSERT INTO khoanthu VALUES(null,'" + thu + "'," + id1 + ")");
//                                   tvloaithu.setText(String.valueOf(spinner.getSelectedItem()));
                                Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                                getdatabase();
                                dialog.dismiss();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

//        mArrayList.add(new khoanThu_class("ok",1));
//        mArrayList.add(new khoanThu_class("ok",1));


    }

    public void getdatabase() {
        Cursor cursor = mDatabase.getData("SELECT * FROM khoanthu");
        mArrayList.clear();
        while (cursor.moveToNext()) {
            int GD = cursor.getInt(0);
            float name = cursor.getFloat(1);
            int id = cursor.getInt(2);
            mArrayList.add(new khoanchi_class(id, GD, name));
        }
        adapter = new recyclerViewadapterKhoanThu(getActivity(), this);
        adapter.setData(mArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void deletedatabase() {
       /* mDatabase=new database(mContext);
        mDatabase.queryData("DELETE FROM khoanthu WHERE id='"+thu.getId()+"'");
        setData(mArrayList)*/
//        Toast.makeText(getActivity(), "da vao day", Toast.LENGTH_SHORT).show();
        Cursor cursor = mDatabase.getData("SELECT * FROM khoanthu");
        mArrayList.clear();
        while (cursor.moveToNext()) {
            int GD = cursor.getInt(0);
            int id = cursor.getInt(2);
            float name = cursor.getFloat(1);
            mArrayList.add(new khoanchi_class(id, GD, name));
        }
        adapter = new recyclerViewadapterKhoanThu(getActivity(), this);
        adapter.setData(mArrayList);
        recyclerView.setAdapter(adapter);
    }

    public void getthuspinner() {
        mlist = new ArrayList<>();
        Cursor mCursor = mDatabase.getData("SELECT *FROM loaithu1");
        mlist.clear();
        while (mCursor.moveToNext()) {
            int id = mCursor.getInt(0);
            String name = mCursor.getString(1);
            mlist.add(new loaithu_class(name, id));
        }
    }

    @Override
    public void editdatabase() {

    }
}