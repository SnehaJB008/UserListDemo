package com.samcaraschools.userlistdemo.ui;

import android.arch.lifecycle.ViewModel;

import com.samcaraschools.userlistdemo.module.User;

public class EditUserViewModel extends ViewModel {
    private UserRepository mRepository;
    public EditUserViewModel(){
        mRepository = new UserRepository();
    }

    public void editUser(User user){
        mRepository.updateUser(user);
    }

    public void addUser(User user){
        mRepository.insertUser(user);
    }
}
