package udacity.viktor.oursdklibrary;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AuthenticationController {

    private static AuthenticationController instance;
    private Observer<AuthenticationListener> mObserver;
    private MutableLiveData<AuthenticationListener> mListener = new MutableLiveData<>();
    private static AuthenticationResult sResult;
    private AuthenticationSettings settings;

    private AuthenticationController() {
        mObserver = new Observer<AuthenticationListener>() {
            @Override
            public void onChanged(@Nullable AuthenticationListener listener) {
                notifyDataChanged();
            }
        };
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
        mListener.setValue(listener);
        mListener.observeForever(mObserver);
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
        sResult = result;
        notifyDataChanged();
    }

    private void notifyDataChanged() {
        AuthenticationListener listener = mListener.getValue();
        if (listener != null && sResult != null) listener.onAuthentication(sResult);
    }

    // considering adding this to the activity destroy
    public void updateObserver() {
        mListener.setValue(null);
    }

    public interface AuthenticationListener {
        void onAuthentication(AuthenticationResult result);
    }
}
