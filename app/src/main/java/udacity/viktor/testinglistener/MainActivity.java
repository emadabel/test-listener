package udacity.viktor.testinglistener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import udacity.viktor.oursdklibrary.AuthenticationController;
import udacity.viktor.oursdklibrary.AuthenticationResult;
import udacity.viktor.oursdklibrary.AuthenticationSettings;
import udacity.viktor.oursdklibrary.ResultObserver;

//MainActivity it is activity of apps, which use our SDK
public class MainActivity extends AppCompatActivity implements ResultObserver {

    private String TAG = "MainActivityOtherApp";
    private AuthenticationController authenticationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AuthenticationSettings authenticationSettings = new AuthenticationSettings("token", this);
        authenticationController = AuthenticationController.getInstance();

        authenticationController.registerObserver(this);
        authenticationController.initialize(authenticationSettings);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        authenticationController.removeObserver(this);
    }

    // get the result from the static instance of AuthenticationController
    // observer pattern with pull mechanism
    @Override
    public void onResultUpdated(AuthenticationController result) {
        switch (result.getResult()) {
            case ERROR:
                Log.d(TAG, "ERROR: " + result.getResult().message);
                break;
            case CANCEL:
                Log.d(TAG, "CANCEL");
                break;
            case SUCCESS:
                Log.d(TAG, "SUCCESS");
                break;
        }
    }

    public void Submit(View view) {
        authenticationController.startAuthentication(this);
    }
}
