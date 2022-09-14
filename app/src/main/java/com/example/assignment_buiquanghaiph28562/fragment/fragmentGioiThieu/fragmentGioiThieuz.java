package com.example.assignment_buiquanghaiph28562.fragment.fragmentGioiThieu;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.adapter.adapterslide;
import com.example.assignment_buiquanghaiph28562.field.slide;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentGioiThieuz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentGioiThieuz extends Fragment {
    private ViewPager mPager;
    private CircleIndicator mIndicator;
    private ArrayList<slide> list;
    private adapterslide mAdapterslide;
    private Handler handler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mPager.getCurrentItem() == (list.size() - 1)) {
                mPager.setCurrentItem(0);
            } else {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            }

        }
    };


    public fragmentGioiThieuz() {
        // Required empty public constructor
    }
    public static fragmentGioiThieuz newInstance(String param1, String param2) {
        fragmentGioiThieuz fragment = new fragmentGioiThieuz();
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
        return inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPager = view.findViewById(R.id.viewPagerslide);
        mIndicator = view.findViewById(R.id.cirslide);
        mAdapterslide = new adapterslide(getActivity(), getListphoto());
        mPager.setAdapter(mAdapterslide);
        mIndicator.setViewPager(mPager);
        mAdapterslide.registerDataSetObserver(mIndicator.getDataSetObserver());
        handler.postDelayed(mRunnable, 2000);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(mRunnable);
                handler.postDelayed(mRunnable, 2000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private ArrayList<slide> getListphoto() {
        list = new ArrayList<>();
        list.add(new slide(R.drawable.slide));
        list.add(new slide(R.drawable.slide3));
        list.add(new slide(R.drawable.slide4));
        list.add(new slide(R.drawable.sloide2));
        return list;
    }
}