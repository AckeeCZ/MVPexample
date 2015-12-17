package cz.ackee.sample.mvp.mvp.presenter;

import android.text.TextUtils;

import cz.ackee.sample.mvp.R;
import cz.ackee.sample.mvp.mvp.model.ApiInteractor;
import cz.ackee.sample.mvp.mvp.model.response.LoginResponse;
import cz.ackee.sample.mvp.mvp.view.ILoginView;


/**
 * Presenter responsible for dummy login behavior
 * Created by David Bilik[david.bilik@ackee.cz] on {17/12/15}
 **/
public class LoginPresenter implements ApiInteractor.ResultCallback<LoginResponse> {
    public static final String TAG = LoginPresenter.class.getName();
    private final ApiInteractor mApiInteractor;
    private ILoginView mView;
    private boolean mLogging;
    private String mError;
    private boolean mLoggingFinished;

    public LoginPresenter() {
        mApiInteractor = new ApiInteractor();
    }

    public void onViewAttached(ILoginView view) {
        mView = view;
        updateView();
    }


    public void onViewDettached() {
        mView = null;
    }

    private ILoginView getView() {
        return mView;
    }


    public void login(String email, String password) {
        mView.clearErrors();
        mError = null;
        mLoggingFinished = false;
        if (validate(email, password)) {
            getView().showProgress(true);
            performLogin(email, password);
        }
    }

    private void performLogin(String email, String password) {
        mLogging = true;
        mApiInteractor.login(email, password, this);
    }


    public boolean validate(String email, String password) {
        boolean success = true;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            getView().onPasswordError("Invalid password");
            success = false;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            getView().onEmailError("Empty email");
            success = false;
        }
        if (!isEmailValid(email)) {
            getView().onEmailError("Invalid email");
            success = false;
        }
        return success;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onSuccess(LoginResponse response) {
        mLogging = false;
        mLoggingFinished = true;
        updateView();
    }

    @Override
    public void onError(String reason) {
        mLogging = false;
        mError = reason;
        updateView();
    }

    private void updateView() {
        if(getView() == null) {
            return;
        }
        getView().showProgress(mLogging);
        if(mError != null) {
            getView().onGeneralError(mError);
        }
        if(mLoggingFinished) {
            getView().onLoginSuccess();
        }
    }

}
