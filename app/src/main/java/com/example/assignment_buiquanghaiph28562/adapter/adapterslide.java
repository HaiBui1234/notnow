package com.example.assignment_buiquanghaiph28562.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.field.slide;

import java.util.ArrayList;

public class adapterslide extends PagerAdapter {
   private Context mContext;
   private ArrayList<slide> mArrayList;

    public adapterslide(Context mContext, ArrayList<slide> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.slideshow,container,false);
        ImageView imgslide=view.findViewById(R.id.imgslide1);
        slide slide1=mArrayList.get(position);
        if (slide1!=null){
            Glide.with(mContext).load(slide1.getImg1()).into(imgslide);
        }
        container.addView(view);
        return view;

    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
