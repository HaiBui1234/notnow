package com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangChi;

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
import com.example.assignment_buiquanghaiph28562.adapter.recyclerViewadapterloaiChi;
import com.example.assignment_buiquanghaiph28562.databaseAll.database;
import com.example.assignment_buiquanghaiph28562.field.loaithu_class;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.deleteListionner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loaiChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loaiChiFragment extends Fragment implements deleteListionner {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mButton;
    private ArrayList<loaithu_class> mArrayList;
    private database mDatabase;
    private recyclerViewadapterloaiChi mViewadapterloaiChi;

    public loaiChiFragment() {
        // Required empty public constructor
    }

    public static loaiChiFragment newInstance(String param1, String param2) {
        loaiChiFragment fragment = new loaiChiFragment();
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
        return inflater.inflate(R.layout.fragment_loai_chi, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=view.findViewById(R.id.id_recyloaichi);
        mButton=view.findViewById(R.id.id_actionButton3);
        mDatabase=new database(getActivity());
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mDatabase.queryData("CREATE TABLE IF NOT EXISTS loaichi(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVARCHAR(30) )");
//        mDatabase.queryData("INSERT INTO loaichi VALUES (null,'Tiền Nước')");
//        mDatabase.queryData("INSERT INTO loaichi VALUES (null,'Tiền Nước1')");
        deletedatabase();
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
                mView1.setText("Loại Chi");
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
                                Toast.makeText(getActivity(), "ĐÃ CÓ", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        if(edtaddloaiThu.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "Không để trống", Toast.LENGTH_SHORT).show();
                        }else {
                            mDatabase.queryData("INSERT INTO loaichi VALUES (null,'"+edtaddloaiThu.getText().toString()+"')");
                            Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                            deletedatabase();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void deletedatabase() {
        mArrayList=new ArrayList<>();
        Cursor mCursor=mDatabase.getData("SELECT * FROM loaichi");
        mArrayList.clear();
        while (mCursor.moveToNext()){
            int id=mCursor.getInt(0);
            String name=mCursor.getString(1);
            mArrayList.add(new loaithu_class(name,id));
        }
        mViewadapterloaiChi=new recyclerViewadapterloaiChi(getActivity(),this);
        mViewadapterloaiChi.setData(mArrayList);
        mRecyclerView.setAdapter(mViewadapterloaiChi);
    }

    @Override
    public void editdatabase() {

    }
}