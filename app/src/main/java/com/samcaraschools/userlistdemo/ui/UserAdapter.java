package com.samcaraschools.userlistdemo.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.samcaraschools.userlistdemo.R;
import com.samcaraschools.userlistdemo.module.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<User> mUserList ;
    private Context mContext;
    private ItemAction mListener;


    UserAdapter(Context context , List<User> userList, ItemAction listener){
        this.mContext = context;
        this.mUserList = (ArrayList<User>) userList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatTextView tvName,tvUserEmail;
        private AppCompatImageView ivDelete,ivEdit,ivUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUser= itemView.findViewById(R.id.ivUser);
            tvName =itemView.findViewById(R.id.tvUserName);
            tvUserEmail = itemView.findViewById(R.id.tvUserEmail);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }

        public void bind(int position){
            User user = mUserList.get(position);
            tvName.setText(user.getFirstName() + " " +user.getLastName());
            tvUserEmail.setText(user.getEmail());
            ivDelete.setOnClickListener(this);
            ivEdit.setOnClickListener(this);
            if(user.getAvatar()!= null){
                Glide
                        .with(mContext)
                        .load(user.getAvatar())
                        .into(ivUser);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ivDelete:
                    mListener.onUserDelete(mUserList.get(getAdapterPosition()));
                    break;
                case R.id.ivEdit:
                    mListener.OnUserEdit(mUserList.get(getAdapterPosition()));
                    break;
            }
        }
    }
}
