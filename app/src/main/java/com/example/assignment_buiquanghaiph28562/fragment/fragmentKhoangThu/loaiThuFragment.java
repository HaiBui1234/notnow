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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.adapter.recyclerViewadapterloaiThu;
import com.example.assignment_buiquanghaiph28562.databaseAll.database;
import com.example.assignment_buiquanghaiph28562.field.loaithu_class;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loaiThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loaiThuFragment extends Fragment implements deleteListionner {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mButton;
    private database mDatabase;
    private ArrayList<loaithu_class> mArrayList;
    private recyclerViewadapterloaiThu mThu;


    public loaiThuFragment() {
        // Required empty public constructor
    }


    public static loaiThuFragment newInstance(String param1, String param2) {
        loaiThuFragment fragment = new loaiThuFragment();

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
        return inflater.inflate(R.layout.fragment_loai_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=view.findViewById(R.id.recyLoaiThu);
        mButton=view.findViewById(R.id.id_actionButton1);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mDatabase=new database(getActivity());
        mDatabase.queryData("CREATE TABLE IF NOT EXISTS loaithu1 (id INTEGER PRIMARY KEY AUTOINCREMENT, thu NVARCHAR(40))");
//        mDatabase.queryData("INSERT INTO loaithu1 VALUES (null,'Ti???n ??i???n')");
//        mDatabase.queryData("INSERT INTO loaithu1 VALUES (null,'Ti???n N?????c')");
//        mDatabase.queryData("INSERT INTO loaithu1 VALUES (null,'Ti???n N?????c2')");
        getdatabase();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog =new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.addloaithu);
                TextView mView1=dialog.findViewById(R.id.tvbanner);
                EditText edtaddloaiThu=dialog.findViewById(R.id.tvaddkhoanThu);
                Button btnhuy=dialog.findViewById(R.id.huykhoanThu);
                Button btnadd=dialog.findViewById(R.id.btnaddkhoanThu);
                mView1.setText("Lo???i Thu");
                Window window=dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (loaithu_class t: mArrayList
                             ) {
                            if(edtaddloaiThu.getText().toString().equals(t.getNameKhoanThu())){
                                Toast.makeText(getActivity(), "???? C??", Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                Toast.makeText(getActivity(),String.valueOf(t.getId()), Toast.LENGTH_SHORT).show();
                            }
                        }
                       if(edtaddloaiThu.getText().toString().isEmpty()){
                           Toast.makeText(getActivity(), "Kh??ng ????? tr???ng", Toast.LENGTH_SHORT).show();
                       }else {
                           mDatabase.queryData("INSERT INTO loaithu1 VALUES (null,'"+edtaddloaiThu.getText().toString()+"')");
                           Toast.makeText(getActivity(), "Th??nh c??ng", Toast.LENGTH_SHORT).show();
                           getdatabase();
                           dialog.dismiss();
                       }
                    }
                });
            }
        });
    }

    private void getdatabase() {
        mArrayList=new ArrayList<>();
        Cursor mCursor=mDatabase.getData("SELECT *FROM loaithu1");
        mArrayList.clear();
        while (mCursor.moveToNext()){
            int id=mCursor.getInt(0);
            String name=mCursor.getString(1);
            mArrayList.add(new loaithu_class(name,id));
        }
        mThu=new recyclerViewadapterloaiThu(getActivity(),this);
        mThu.setData(mArrayList);
        mRecyclerView.setAdapter(mThu);
    }

    @Override
    public void deletedatabase() {
        mArrayList=new ArrayList<>();
        Cursor mCursor=mDatabase.getData("SELECT *FROM loaithu1");
        mArrayList.clear();
        while (mCursor.moveToNext()){
            int id=mCursor.getInt(0);
            String name=mCursor.getString(1);
            mArrayList.add(new loaithu_class(name,id));
        }
        mThu=new recyclerViewadapterloaiThu(getActivity(),this);
        mThu.setData(mArrayList);
        mRecyclerView.setAdapter(mThu);
    }

    @Override
    public void editdatabase() {

    }
}