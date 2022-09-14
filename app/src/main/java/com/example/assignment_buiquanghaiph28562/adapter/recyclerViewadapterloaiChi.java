package com.example.assignment_buiquanghaiph28562.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_buiquanghaiph28562.R;
import com.example.assignment_buiquanghaiph28562.databaseAll.database;
import com.example.assignment_buiquanghaiph28562.field.loaithu_class;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.deleteListionner;

import java.util.ArrayList;

public class recyclerViewadapterloaiChi extends RecyclerView.Adapter<recyclerViewadapterloaiChi.logoViewHolder>  {
    private Context mContext;
    private ArrayList<loaithu_class> mArrayList;
    private database mDatabase;
    private deleteListionner listionner;

    public recyclerViewadapterloaiChi(Context mContext, deleteListionner listionner) {
        this.mContext = mContext;
        this.listionner = listionner;

    }
    public void setData(ArrayList<loaithu_class> mArrayList){
        this.mArrayList=mArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public logoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.khoanthuadapter,parent,false);
        return new logoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull logoViewHolder holder, int position) {
     loaithu_class thu=mArrayList.get(position);
     if(thu==null){
         return;
     }
     holder.tvname.setText(thu.getNameKhoanThu());
     holder.imgdelete.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             AlertDialog.Builder mBuilder=new AlertDialog.Builder(mContext);
             mBuilder.setTitle("DELETE");
             mBuilder.setMessage("Bn có chắc muốn xóa");
             mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     mDatabase=new database(mContext);
                     mDatabase.queryData("DELETE FROM loaichi WHERE id='"+thu.getId()+"'");
                     listionner.deletedatabase();
                 }
             });
             mBuilder.setNegativeButton("No",null);
             mBuilder.show();
         }
     });
     holder.imgedit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Dialog dialog1 = new Dialog(mContext);
             dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
             dialog1.setContentView(R.layout.addloaithu);
             TextView textView=dialog1.findViewById(R.id.tvbanner);
             EditText edtthu=dialog1.findViewById(R.id.tvaddkhoanThu);
             Button btnhuy=dialog1.findViewById(R.id.huykhoanThu);
             Button btnedit=dialog1.findViewById(R.id.btnaddkhoanThu);
             textView.setText("EDIT LOẠI CHI");
             btnedit.setText("Sửa");
             Window window=dialog1.getWindow();
             window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
             dialog1.show();
             btnhuy.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     dialog1.dismiss();
                 }
             });
             btnedit.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     AlertDialog.Builder mBuilder=new AlertDialog.Builder(mContext);
                     mBuilder.setTitle("EDIT");
                     mBuilder.setMessage("Bạn có chắc muốn sửa");
                     mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             if(edtthu.getText().toString().isEmpty()){
                                 Toast.makeText(mContext, "Không để trống", Toast.LENGTH_SHORT).show();
                             }else {
                                 String thu1=edtthu.getText().toString();
                                 mDatabase=new database(mContext);
                                 mDatabase.queryData("UPDATE loaichi SET name = '"+thu1+"' WHERE id='"+thu.getId()+"'");
                                 listionner.deletedatabase();
                                 dialog1.dismiss();
                             }
                         }
                     });
                     mBuilder.setNegativeButton("No",null);
                     mBuilder.show();
                 }
             });
         }
     });

    }
    @Override
    public int getItemCount() {
        if(mArrayList!=null){
            return mArrayList.size();
        }
        return 0;
    }

    public class logoViewHolder extends RecyclerView.ViewHolder{
        private TextView tvname;
        private ImageView imgedit,imgdelete;
        public logoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvkhoanThu);
            imgdelete=itemView.findViewById(R.id.imgKhoanThuDelete);
            imgedit=itemView.findViewById(R.id.imgKhoanThuEdit);
        }
    }
}
