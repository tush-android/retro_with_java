package com.example.second_retro_java;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.second_retro_java.databinding.ActivityAddnoteactivityBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Addnoteactivity extends AppCompatActivity {
    private ActivityAddnoteactivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityAddnoteactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.e1.getText().toString();
                String email=binding.e2.getText().toString();
                String mob=binding.e3.getText().toString();
                if(!name.isEmpty() && !email.isEmpty() && !mob.isEmpty()){
                    ApiService apiService=RetrofitClient.getInstance();
                    Call<ResponseModel> call=apiService.insetem(name,email,mob);
                    call.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if(response.isSuccessful() && response.body() != null && response.body().isSuccess()){
                                Toast.makeText(Addnoteactivity.this,"Employee inserted successfully",Toast.LENGTH_LONG).show();
                                finish();
                            }
                            else {
                                Toast.makeText(Addnoteactivity.this,"Insert Failed...!",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Toast.makeText(Addnoteactivity.this,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Toast.makeText(Addnoteactivity.this,"Please Fill All Fields First.....!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}