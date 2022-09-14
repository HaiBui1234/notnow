package com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangChi;

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
import com.example.assignment_buiquanghaiph28562.adapter.KhoangChiAdapterPager;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentKhoangChiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentKhoangChiz extends Fragment {

    public fragmentKhoangChiz() {
        // Required empty public constructor
    }

    public static fragmentKhoangChiz newInstance(String param1, String param2) {
        fragmentKhoangChiz fragment = new fragmentKhoangChiz();
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
        return inflater.inflate(R.layout.fragment_khoang_chiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout=view.findViewById(R.id.id_tabLayoutChi);
        ViewPager viewPager=view.findViewById(R.id.id_ViewpagerChi);
        KhoangChiAdapterPager adapterPager= new KhoangChiAdapterPager(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapterPager);
        tabLayout.setupWithViewPager(viewPager);
    }
}