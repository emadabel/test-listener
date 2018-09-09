package udacity.viktor.oursdklibrary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.emadabel.oursdklibrary.R;

// First activity which opens after app calls initialize(this)
public class OurSDKActivity extends AppCompatActivity {

    private static final String TAG = "OurSdk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdk);

        AuthenticationResult authenticationResult = AuthenticationResult.ERROR;
        authenticationResult.message ="result";
        // delivering response, mListener is null after system kills.. how to solve that?

        AuthenticationController.getInstance().setResultData(authenticationResult);
    }
}
