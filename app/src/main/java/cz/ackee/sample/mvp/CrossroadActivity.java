package cz.ackee.sample.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.ackee.sample.mvp.mvp.view.MVPLoginActivity;
import cz.ackee.sample.mvp.standard.LoginActivity;

/**
 * Activity which is crossroad between standard and mvp way to login
 * Created by David Bilik[david.bilik@ackee.cz] on {17/12/15}
 **/
public class CrossroadActivity extends Activity {
    public static final String TAG = CrossroadActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossroad);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_mvp)
    public void onMVPButtonClicked() {
        startActivity(new Intent(this, MVPLoginActivity.class));
    }

    @OnClick(R.id.btn_standard)
    public void onStandardButtonClicked() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
