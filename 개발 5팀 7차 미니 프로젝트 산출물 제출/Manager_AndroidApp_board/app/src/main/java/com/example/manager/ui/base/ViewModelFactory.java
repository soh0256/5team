package com.example.manager.ui.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.manager.network.MemberApi;
import com.example.manager.repository.MemberRepository;
import com.example.manager.ui.login.LoginViewModel;
import com.example.manager.ui.memberlist.MemberListViewModel;
import com.example.manager.ui.modify.ModifyViewModel;
import com.example.manager.ui.signup.SignUpViewModel;


public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MemberRepository memberRepository = new MemberRepository(MemberApi.getInstance().getMemberService());
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(memberRepository);
        } else if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
            return (T) new SignUpViewModel(memberRepository);
        } else if (modelClass.isAssignableFrom(ModifyViewModel.class)) {
            return (T) new ModifyViewModel(memberRepository);
        } else if (modelClass.isAssignableFrom(MemberListViewModel.class)) {
            return (T) new MemberListViewModel(memberRepository);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}