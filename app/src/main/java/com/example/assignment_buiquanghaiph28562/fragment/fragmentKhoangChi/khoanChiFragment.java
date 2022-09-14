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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.adapter.recyclerViewadapterkhoanchi;
import com.example.assignment_buiquanghaiph28562.databaseAll.database;
import com.example.assignment_buiquanghaiph28562.field.khoanchi_class;
import com.example.assignment_buiquanghaiph28562.field.loaithu_class;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.deleteListionner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link khoanChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class khoanChiFragment extends Fragment implements deleteListionner {
    private RecyclerView mView;
    private FloatingActionButton mButton;
    private ArrayList<khoanchi_class> mArrayList;
    private database mDatabase;
    ArrayList<loaithu_class> mlist;
    private recyclerViewadapterkhoanchi mViewadapterkhoanchi;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public khoanChiFragment() {
        // Required empty public constructor
    }

    public static khoanChiFragment newInstance(String param1, String param2) {
        khoanChiFragment fragment = new khoanChiFragment();
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
        return inflater.inflate(R.layout.fragment_khoan_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView=view.findViewById(R.id.id_recykhoanchi);
        mButton=view.findViewById(R.id.id_actionButton2);
        mDatabase=new database(getActivity());
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        mView.setLayoutManager(manager);
        mDatabase.queryData("CREATE TABLE IF NOT EXISTS khoanchi (GD INTEGER PRIMARY KEY AUTOINCREMENT, chi float,id INTEGER REFERENCES loaithu1(id) )");
//        mDatabase.queryData("INSERT INTO khoanchi VALUES (null,3000.0,1)");
//        mDatabase.queryData("INSERT INTO khoanchi VALUES (null,4000.0,2)");
        deletedatabase();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.addkhoanthuthu);
                TextView mView1=dialog.findViewById(R.id.tvbanner1);
                EditText edtaddkhoanchi=dialog.findViewById(R.id.tvaddkhoanThu1);
                Button btnhuy=dialog.findViewById(R.id.huykhoanThu1);
                Button btnadd=dialog.findViewById(R.id.btnaddkhoanThu1);
                EditText edtngay=dialog.findViewById(R.id.tvaddngaythang);
                Spinner spinner=dialog.findViewById(R.id.spinnerkhoanthu);
                mView1.setText("Khoản Chi");
                Window window=dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
                getthuspinner();
                ArrayAdapter<loaithu_class> adapter1=new ArrayAdapter(getContext(),
                        android.R.layout.simple_spinner_dropdown_item,mlist);
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
                            if(edtaddkhoanchi.getText().toString().isEmpty()){
                                Toast.makeText(getActivity(), "Không để trống", Toast.LENGTH_SHORT).show();
                            }else{
                                float chi= Float.parseFloat(edtaddkhoanchi.getText().toString());
                                loaithu_class aClass= (loaithu_class) spinner.getSelectedItem();
                                int id1=aClass.getId();
                                mDatabase.queryData("INSERT INTO khoanchi VALUES (null,'"+chi+"',"+id1+")");
                                Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                                deletedatabase();
                                dialog.dismiss();
                            }
                        }catch (Exception e){
                            Toast.makeText(getActivity(), "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void deletedatabase() {
        mArrayList=new ArrayList<>();
        Cursor mCursor=mDatabase.getData("SELECT * FROM khoanchi");
        mArrayList.clear();
        while (mCursor.moveToNext()){
            int GD=mCursor.getInt(0);
            int id=mCursor.getInt(2);
            float chi=mCursor.getFloat(1);
            mArrayList.add(new khoanchi_class(id,GD,chi));
        }
        mViewadapterkhoanchi=new recyclerViewadapterkhoanchi(getActivity(),this);
        mViewadapterkhoanchi.setData(mArrayList);
        mView.setAdapter(mViewadapterkhoanchi);


    }
    public void getthuspinner(){
        mlist=new ArrayList<>();
        Cursor mCursor=mDatabase.getData("SELECT *FROM loaichi");
        mlist.clear();
        while (mCursor.moveToNext()){
            int id=mCursor.getInt(0);
            String name=mCursor.getString(1);
            mlist.add(new loaithu_class(name,id));
        }
    }
    @Override
    public void editdatabase() {

    }
}