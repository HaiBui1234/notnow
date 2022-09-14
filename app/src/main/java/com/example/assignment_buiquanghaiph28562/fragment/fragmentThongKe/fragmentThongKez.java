package com.example.assignment_buiquanghaiph28562.fragment.fragmentThongKe;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.databaseAll.database;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentThongKez#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentThongKez extends Fragment {
    private ListView mListView;
    private database mDatabase;
    private SimpleAdapter mSimpleAdapter;
    private ArrayList<HashMap<String,Float>> data;
    public fragmentThongKez() {
        // Required empty public constructor
    }
    public static fragmentThongKez newInstance(String param1, String param2) {
        fragmentThongKez fragment = new fragmentThongKez();
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
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView=view.findViewById(R.id.listView);
        mDatabase=new database(getActivity());
        Cursor cursor=mDatabase.getData("SELECT SUM(name) FROM khoanthu WHERE name>0 ");
        cursor.moveToFirst();
        float tongthu=cursor.getFloat(0);
        Cursor cursor1=mDatabase.getData("SELECT SUM(chi) FROM khoanchi WHERE chi>0 ");
        cursor1.moveToFirst();
        float tongchi=cursor1.getFloat(0);
        data=new ArrayList<>();
        HashMap<String,Float> hashMap=new HashMap<>();
        hashMap.put("tongthu",tongthu);
        data.add(hashMap);
        HashMap<String,Float> hashMap1=new HashMap<>();
        hashMap1.put("tongchi",tongchi);
        data.add(hashMap1);
        HashMap<String,Float> hashMap2=new HashMap<>();
        hashMap2.put("hieu",(tongthu-tongchi));
        data.add(hashMap2);
        String from[]={"tongthu","tongchi","hieu"};
        int to[]={R.id.tongthu,R.id.tongchi,R.id.hieu};
        mSimpleAdapter=new SimpleAdapter(getActivity(),data,R.layout.thongkelayoutitem,from,to);
        mListView.setAdapter(mSimpleAdapter);

    }

}