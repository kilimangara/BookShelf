package com.killkompany.bookshelf.mvp.viewholders;

import android.view.View;
import android.widget.Button;

import com.killkompany.bookshelf.R;

import butterknife.BindView;

public class LoginViewHolder extends BaseViewHolder {

    @BindView(R.id.button_login_as)
    public Button loginAs;

    @BindView(R.id.button_login_by)
    public Button loginBy;

    @BindView(R.id.button_register)
    public Button register;

    public LoginViewHolder(View rootView) {
        super(rootView);
    }
}
