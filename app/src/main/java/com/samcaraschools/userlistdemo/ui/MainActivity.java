package com.samcaraschools.userlistdemo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.samcaraschools.userlistdemo.R;
import com.samcaraschools.userlistdemo.local.DatabaseClient;
import com.samcaraschools.userlistdemo.module.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAction{
    private UserViewModel mUserViewModel;
    private RecyclerView mRecyclerView;
    private TextView tvAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        DatabaseClient.getInstance(getApplicationContext())
                .getAppDatabase().userDao();

    }
    private void init() {
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mRecyclerView = findViewById(R.id.rvUserList);
        tvAddUser = findViewById(R.id.tvAddUser);
        tvAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUserFragment editUserFragment = EditUserFragment.newInstance(true,null);
                editUserFragment.show(getSupportFragmentManager(), "User Detail");
            }
        });
        getUserdata();
    }
    private void getUserdata() {
        mUserViewModel.getData(1);
        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                MainActivity.this.setAdapter(users);
            }
        });
    }

    private void setAdapter(List<User> users) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new UserAdapter(this,users,this));
    }


    @Override
    public void onUserDelete(User user) {
        mUserViewModel.removeUser(user);
    }

    @Override
    public void OnUserEdit(User user) {
        EditUserFragment editUserFragment = EditUserFragment.newInstance(false,user);
        editUserFragment.show(getSupportFragmentManager(), "User Detail");
    }

}
