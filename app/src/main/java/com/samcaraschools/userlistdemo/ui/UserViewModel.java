package com.samcaraschools.userlistdemo.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.samcaraschools.userlistdemo.module.User;
import com.samcaraschools.userlistdemo.module.UserData;
import com.samcaraschools.userlistdemo.network.RestClient;
import com.samcaraschools.userlistdemo.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private static final String TAG = UserViewModel.class.getSimpleName();
    private UserRepository mRepository;
    RestClient client;
    final MutableLiveData<UserData> data = new MutableLiveData<>();

    public UserViewModel(){
        client = RetrofitClientInstance.getRestClient();
        mRepository = new UserRepository();
    }

    public LiveData<UserData> getData(int pageNo) {
        client.getUsers(pageNo).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                data.setValue(response.body());
                addUsers(response.body().getData());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d(TAG,"severError");
            }
        });
        return data;
    }

    public void addUsers(List<User> users){
        mRepository.insertAllUser(users);
    }

    public LiveData<List<User>> getAllUsers(){
        return mRepository.getAllUsers();
    }
    public void removeUser(User users){
        mRepository.deleteUser(users);
    }

    public void editUser(User users){
        mRepository.updateUser(users);
    }


    public void addUser(User users){
        mRepository.insertUser(users);
    }

}
