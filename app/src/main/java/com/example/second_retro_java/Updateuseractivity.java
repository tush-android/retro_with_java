package com.example.second_retro_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.second_retro_java.databinding.ActivityUpdateuseractivityBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Updateuseractivity extends AppCompatActivity {
    private ActivityUpdateuseractivityBinding binding;
    int userId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUpdateuseractivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        if(intent != null && intent.hasExtra("empid") ){
            userId=intent.getIntExtra("empid",-1);
            String name=intent.getStringExtra("name");
            String email=intent.getStringExtra("email");
            String mobile=intent.getStringExtra("mobile");

            binding.e11.setText(name);
            binding.e22.setText(email);
            binding.e33.setText(mobile);
        }
        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newname=binding.e11.getText().toString();
                String newemail=binding.e22.getText().toString();
                String newmobil=binding.e33.getText().toString();
                if(!newname.isEmpty() && !newmobil.isEmpty() && !newemail.isEmpty()){
                    RetrofitClient.getInstance().updateu(userId,newname,newemail,newmobil).enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if(response.isSuccessful() && response.body() != null){
                                Toast.makeText(Updateuseractivity.this,"Data Updated Successfully...!",Toast.LENGTH_LONG).show();
                                finish();
                            }
                            else {
                                Toast.makeText(Updateuseractivity.this,"Failed To Update!!!",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Toast.makeText(Updateuseractivity.this,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Toast.makeText(Updateuseractivity.this,"Fill All the Field First",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}