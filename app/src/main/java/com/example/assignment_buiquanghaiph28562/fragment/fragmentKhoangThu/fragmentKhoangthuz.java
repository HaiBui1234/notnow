package com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.adapter.KhoangThuAdapterPager;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentKhoangthuz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentKhoangthuz extends Fragment {


    public fragmentKhoangthuz() {
        // Required empty public constructor
    }

    public static fragmentKhoangthuz newInstance(String param1, String param2) {
        fragmentKhoangthuz fragment = new fragmentKhoangthuz();
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
        return inflater.inflate(R.layout.fragment_khoangthuz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout=view.findViewById(R.id.id_tabLayout);
        ViewPager viewPager=view.findViewById(R.id.id_Viewpager);
        KhoangThuAdapterPager adapterPager = new KhoangThuAdapterPager(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapterPager);
        tabLayout.setupWithViewPager(viewPager);
    }
}