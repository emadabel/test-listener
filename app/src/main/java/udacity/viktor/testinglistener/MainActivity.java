package udacity.viktor.testinglistener;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import udacity.viktor.oursdklibrary.AuthenticationController;
import udacity.viktor.oursdklibrary.AuthenticationResult;
import udacity.viktor.oursdklibrary.AuthenticationSettings;

//MainActivity it is activity of apps, which use our SDK
public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivityOtherApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getResult().observe(this, new Observer<AuthenticationResult>() {
            @Override
            public void onChanged(@Nullable AuthenticationResult result) {
                if (result != null) {
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
                }
            }
        });

        AuthenticationSettings authenticationSettings = new AuthenticationSettings("token", this);
        AuthenticationController authenticationController = AuthenticationController.getInstance();

        authenticationController.initialize(authenticationSettings, viewModel.getListener());
        authenticationController.startAuthentication(this);
    }
}
