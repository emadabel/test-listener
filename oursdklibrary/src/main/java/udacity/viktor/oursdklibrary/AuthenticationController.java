package udacity.viktor.oursdklibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AuthenticationController {

    private static AuthenticationController instance;
    private AuthenticationListener mListener;
    private AuthenticationSettings settings;

    private AuthenticationController() {

    }

    public static AuthenticationController getInstance() {

        if (instance == null) {
            instance = new AuthenticationController();
        }

        return instance;
    }

    //provide settings in this singleton class
    public void initialize(@NonNull AuthenticationSettings settings1, @Nullable AuthenticationListener listener) {
        this.settings = settings1;
        //save this listener to use it in all activities and notify MainActivity
        mListener = listener;
    }

    public void startAuthentication(@NonNull Context context) {
        if (settings == null) {
            throw new IllegalStateException("call IdenfyContoller.getInstance().initialize(...) first");
        } else {
            //Open our SDK activity
            Intent intent = new Intent(context, OurSDKActivity.class);
            context.startActivity(intent);
        }

    }

    public void setResultData(AuthenticationResult result) {
        if (mListener != null) mListener.onAuthentication(result);
    }

    public interface AuthenticationListener {
        void onAuthentication(AuthenticationResult result);
    }
}
