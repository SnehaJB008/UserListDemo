package com.samcaraschools.userlistdemo.ui;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.samcaraschools.userlistdemo.Application;
import com.samcaraschools.userlistdemo.local.UserDao;
import com.samcaraschools.userlistdemo.module.User;

import java.util.List;
import java.util.concurrent.Executor;

public class UserRepository {

    private LiveData<User> user;
    private UserDao userDao;
    private Executor executor;
    private LiveData<List<User>> users;

    UserRepository(){
        userDao = Application.getDatabase().getAppDatabase().userDao();
//        users = userDao.getAll();
    }

    public void insertUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDao.insert(user);
                return null;
            }
        }.execute();
    }

    public void deleteUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDao.delete(user);
                return null;
            }
        }.execute();
    }

    public void updateUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDao.updateUser(user);
                return null;
            }
        }.execute();
    }

    public void insertAllUser(final List<User> users) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDao.insertAll(users);
                return null;
            }
        }.execute();
    }

    public LiveData<List<User>> getAllUsers() {
        return userDao.getAll();
    }


}
