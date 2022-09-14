package com.example.assignment_buiquanghaiph28562.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
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
import com.example.assignment_buiquanghaiph28562.field.khoanchi_class;
import com.example.assignment_buiquanghaiph28562.field.loaithu_class;
import com.example.assignment_buiquanghaiph28562.fragment.fragmentKhoangThu.deleteListionner;

import java.util.ArrayList;

public class recyclerViewadapterkhoanchi extends RecyclerView.Adapter<recyclerViewadapterkhoanchi.logoViewHolder>  {
    private Context mContext;
    private ArrayList<khoanchi_class> mArrayList;
    private database mDatabase;
    private deleteListionner listionner;

    public recyclerViewadapterkhoanchi(Context mContext, deleteListionner listionner) {
        this.mContext = mContext;
        this.listionner = listionner;

    }
    public void setData(ArrayList<khoanchi_class> mArrayList){
        this.mArrayList=mArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public logoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.loaithuadapter,parent,false);
        return new logoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull logoViewHolder holder, int position) {
     khoanchi_class thu=mArrayList.get(position);
     if(thu==null){
         return;
     }

     mDatabase=new database(mContext);
//        Cursor cursor=mDatabase.getData("SELECT * FROM loaichi WHERE id='"+thu.getId()+"'");
//        cursor.moveToFirst();
//        String namekhoanthu1=cursor.getString(1);
//        holder.tvnamethat.setText(namekhoanthu1);
        holder.tvnamethat.setText(String.valueOf(thu.getId()));
        holder.tvname.setText(String.valueOf(thu.getChi()));
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
                     mDatabase.queryData("DELETE FROM khoanchi WHERE GD='"+thu.getGD()+"'");
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
             textView.setText("EDIT KHOẢN CHI");
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
                     mBuilder.setMessage("Bn có chắc muốn sửa");
                     mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             try {
                                 if(edtthu.getText().toString().isEmpty()){
                                     Toast.makeText(mContext, "Không để trống", Toast.LENGTH_SHORT).show();
                                 }else{
                                     float chi= Float.parseFloat(edtthu.getText().toString());
                                     mDatabase=new database(mContext);
                                     mDatabase.queryData("UPDATE khoanchi SET chi = '"+chi+"' WHERE GD='"+thu.getGD()+"'");
                                     listionner.deletedatabase();
                                     dialog1.dismiss();
                                 }
                         }catch (Exception e){
                             Toast.makeText(mContext, "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
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
        private TextView tvname,tvnamethat;
        private ImageView imgedit,imgdelete;
        public logoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvloaithu);
            imgdelete=itemView.findViewById(R.id.imgloaithuDelete1);
            imgedit=itemView.findViewById(R.id.imgloaithuEdit1);
            tvnamethat=itemView.findViewById(R.id.namethat);
        }
    }
}
