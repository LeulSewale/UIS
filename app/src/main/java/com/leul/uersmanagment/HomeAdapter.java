package com.leul.uersmanagment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeHolder>  {
    ArrayList<Home> homeArrayList;
    Context context;
    public HomeAdapter(ArrayList<Home> homeArrayList, Context context) {
        this.homeArrayList = homeArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_user,null);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeHolder holder, final int position) {
        final Home home = homeArrayList.get(position);
        holder.fullname.setText(home.getFullname());

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void itemOnClickListener(View view, int postion) {
                Intent intent = new Intent(context, ViewDetail.class);
                String fullnameTitle = homeArrayList.get(postion).getFullname();
                String email = homeArrayList.get(postion).getEmail();
                String phone_num = homeArrayList.get(postion).getPhoneN();
                String username = homeArrayList.get(postion).getUsername();
                String gender = homeArrayList.get(postion).getGender();
                String password = homeArrayList.get(postion).getPassword();

                intent.putExtra("fullname", fullnameTitle);
                intent.putExtra("email",email);
                intent.putExtra("phonenum",phone_num);
                intent.putExtra("username",username);
                intent.putExtra("gender",gender);
                intent.putExtra("password",password);
                context.startActivity(intent);
            }
        });
        holder.setItemLongListner(new ItemLongListner() {
            @Override
            public void itemOnLongListener(View view, int postion) {
                removeItem(home);
            }
        });
    }

    private void removeItem(Home home) {
        int curPosition = homeArrayList.indexOf(home);
        homeArrayList.remove(curPosition);
        notifyItemRemoved(curPosition);
    }

    @Override
    public int getItemCount() {
        return homeArrayList.size();
    }

}

