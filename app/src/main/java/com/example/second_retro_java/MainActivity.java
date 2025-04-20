package com.example.second_retro_java;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.second_retro_java.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserAdapter userAdapter;
    private List<Users> usersList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Addnoteactivity.class);
                startActivity(intent);
            }
        });
        binding.l1.setLayoutManager(new LinearLayoutManager(this));
        fetchUsers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchUsers();
        //Log.d("RAW_RESPONSE", new Gson().toJson(response.body()));
    }

    private void fetchUsers() {
        RetrofitClient.getInstance().getAllUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.isSuccessful() && response.body() !=null ){
                    List<Users> user=response.body();
                    for (Users u : user) {
                        Log.d("API_DATA", "ID: " + u.getId() + " Name: " + u.getName() + " Email: " + u.getEmail() + " Mobile: " + u.getMobile());
                    }
                    userAdapter=new UserAdapter(MainActivity.this);
                    binding.l1.setAdapter(userAdapter);
                    userAdapter.updatedat(new ArrayList<>(user));
                }
                else{
                    Toast.makeText(MainActivity.this,"Failed To Load Users....!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}