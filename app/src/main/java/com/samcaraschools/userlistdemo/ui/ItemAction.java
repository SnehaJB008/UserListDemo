package com.samcaraschools.userlistdemo.ui;

import com.samcaraschools.userlistdemo.module.User;

public interface ItemAction {
    void onUserDelete(User user);
    void OnUserEdit(User user);
}
