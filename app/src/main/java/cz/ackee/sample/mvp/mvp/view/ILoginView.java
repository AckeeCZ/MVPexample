package cz.ackee.sample.mvp.mvp.view;

/**
 * View defining interface of login screen
 * Created by David Bilik[david.bilik@ackee.cz] on {17/12/15}
 **/
public interface ILoginView {
    public static final String TAG = ILoginView.class.getName();

    public void onLoginSuccess();

    public void onEmailError(String error);

    public void onPasswordError(String error);

    public void onGeneralError(String error);

    public void clearErrors();

    public void showProgress(boolean show);
}
