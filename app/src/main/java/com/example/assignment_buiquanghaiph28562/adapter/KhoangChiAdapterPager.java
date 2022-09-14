package com.example.assignment_buiquanghaiph28562.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangChi.khoanChiFragment;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangChi.loaiChiFragment;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.khoanThuFragment;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.loaiThuFragment;

public class KhoangChiAdapterPager extends FragmentStatePagerAdapter {
    public KhoangChiAdapterPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new khoanChiFragment();
            case 1:
                return new loaiChiFragment();
            default:
                return new khoanChiFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Khoản Chi";
                break;
            case 1:
                title="Loại Chi";
                break;
        }
        return title;
    }
}
