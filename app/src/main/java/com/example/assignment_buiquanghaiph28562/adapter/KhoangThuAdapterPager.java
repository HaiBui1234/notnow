package com.example.assignment_buiquanghaiph28562.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.khoanThuFragment;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.loaiThuFragment;

public class KhoangThuAdapterPager extends FragmentStatePagerAdapter {
    public KhoangThuAdapterPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new khoanThuFragment();
            case 1:
                return new loaiThuFragment();
            default:
                return new khoanThuFragment();
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
                title="Khoản Thu";
                break;
            case 1:
                title="Loại Thu";
                break;
        }
        return title;
    }
}
