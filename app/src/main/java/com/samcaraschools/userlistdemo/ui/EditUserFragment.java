package com.samcaraschools.userlistdemo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.samcaraschools.userlistdemo.R;
import com.samcaraschools.userlistdemo.module.User;

public class EditUserFragment extends DialogFragment implements View.OnClickListener{
    private static final String IS_NEW = "is_new";
    private static final String USER = "user";

    private  boolean isNew;
    private User user;
    private EditText etFName , etLName, etEmail;
    private TextView tvAddUser;
    private EditUserViewModel mViewModel;

    public EditUserFragment() {

    }

    public static EditUserFragment newInstance(Boolean isNew, User user) {
        EditUserFragment fragment = new EditUserFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_NEW, isNew);
        args.putSerializable(USER,user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isNew = getArguments().getBoolean(IS_NEW);
            if(!isNew){
                user = (User)getArguments().getSerializable(USER);
            }
        }

        mViewModel = ViewModelProviders.of(getActivity()).get(EditUserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_edit_user, container, false);
        etEmail = view.findViewById(R.id.etEmail);
        etFName = view.findViewById(R.id.etFName);
        etLName = view.findViewById(R.id.etLName);
        tvAddUser = view.findViewById(R.id.tvAddUser);


        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvAddUser.setOnClickListener(this);
        if(!isNew){
            tvAddUser.setText(getResources().getString(R.string.btn_edt_user));
            setData();
        }

    }

    private void setData() {
        etEmail.setText(user.getEmail());
        etFName.setText(user.getFirstName());
        etLName.setText(user.getLastName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tvAddUser){
            User mUser  = new User();
            mUser.setEmail(etEmail.getText().toString());
            mUser.setLastName(etLName.getText().toString());
            mUser.setFirstName(etFName.getText().toString());
            user = mUser;
            if(isNew){
                mViewModel.addUser(user);
            }else{
                mViewModel.editUser(user);
            }
            dismiss();
        }
    }
}
