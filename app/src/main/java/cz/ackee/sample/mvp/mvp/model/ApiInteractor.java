package cz.ackee.sample.mvp.mvp.model;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Random;

import cz.ackee.sample.mvp.R;
import cz.ackee.sample.mvp.mvp.model.response.LoginResponse;

/**
 * Interactor that performs api calls
 * Created by David Bilik[david.bilik@ackee.cz] on {17/12/15}
 **/
public class ApiInteractor {
    public static final String TAG = ApiInteractor.class.getName();


    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    /**
     * Very simple interface that representes api call result callback
     * @param <T>
     */
    public interface ResultCallback<T> {
        void onSuccess(T response);

        void onError(String reason);
    }

    public void login(String email, String password, ResultCallback<LoginResponse> callback) {
        new LoginTask(email, password, callback).execute();
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public static class LoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final WeakReference<ResultCallback<LoginResponse>> mCallback;

        public LoginTask(String email, String password, ResultCallback<LoginResponse> callback) {
            mEmail = email;
            mPassword = password;
            this.mCallback = new WeakReference<>(callback);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if(mCallback.get() == null) {
                return;
            }
            if(success) {
                mCallback.get().onSuccess(new LoginResponse(new Random().nextInt()));
            }else {
                mCallback.get().onError("Not existing user");
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}
