package udacity.viktor.testinglistener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import udacity.viktor.oursdklibrary.AuthenticationController;
import udacity.viktor.oursdklibrary.AuthenticationResult;
import udacity.viktor.oursdklibrary.AuthenticationSettings;

//MainActivity it is activity of apps, which use our SDK
public class MainActivity extends AppCompatActivity {

    private String TAG="MainActivityOtherApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AuthenticationSettings authenticationSettings = new AuthenticationSettings("token", this);
        AuthenticationController authenticationController = AuthenticationController.getInstance();

        authenticationController.initialize(authenticationSettings, new AuthenticationController.AuthenticationListener() {
            @Override
            public void onAuthentication(AuthenticationResult result) {
                switch (result) {
                    case ERROR:
                        Log.d(TAG, "ERROR: " + result.message);
                        break;
                    case CANCEL:
                        Log.d(TAG, "CANCEL");
                        break;
                    case SUCCESS:
                        Log.d(TAG, "SUCCESS");
                        break;
                }
            }});
        authenticationController.startAuthentication(this);
    }
}
