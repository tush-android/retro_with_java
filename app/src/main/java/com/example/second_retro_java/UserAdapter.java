package com.example.second_retro_java;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<Users> user=new ArrayList<>();
    public UserAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_lists,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users u=user.get(position);
        holder.name.setText(u.getName());
        holder.email.setText(u.getEmail());
        holder.mob.setText(u.getMobile());
        holder.e.setOnClickListener(v -> {
            Intent intent=new Intent(holder.itemView.getContext(),Updateuseractivity.class);
            intent.putExtra("empid",u.getId());
            intent.putExtra("name",u.getName());
            intent.putExtra("email",u.getEmail());
            intent.putExtra("mobile",u.getMobile());
            context.startActivity(intent);
        });
        holder.d.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Are You Sure You Want To Delete This Particular User...!")
                    .setPositiveButton("Yes",(dialog, which) -> {
                        deleteuser(u.getId(),position);
                    })
                    .setNegativeButton("No",null)
                    .show();
        });
    }

    private void deleteuser(int id, int position) {
        RetrofitClient.getInstance().deleteu(id).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful() && response.body() != null && response.body().isSuccess()){
                    Toast.makeText(context,"User Deleted Successfully....!",Toast.LENGTH_LONG).show();
                    Users deleteuser=user.get(position);
                    Iterator<Users> iterator=user.iterator();
                    while (iterator.hasNext()){
                        if(iterator.next().getId() == deleteuser.getId()){
                            iterator.remove();
                            break;
                        }
                    }
                    notifyItemRemoved(position);
                }
                else {
                    Toast.makeText(context,"Failed To Delete",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(context,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView name,email,mob;
        ImageView e,d;
        public  UserViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            mob=itemView.findViewById(R.id.mob);
            e=itemView.findViewById(R.id.u1);
            d=itemView.findViewById(R.id.d1);
        }
    }
    public void updatedat(List<Users> newusers){
        user.clear();
        user.addAll(newusers);
        notifyDataSetChanged();
    }

}
