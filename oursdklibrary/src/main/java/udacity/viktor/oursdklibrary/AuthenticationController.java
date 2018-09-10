package udacity.viktor.oursdklibrary;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.arch.lifecycle.Lifecycle.Event.ON_ANY;
import static android.arch.lifecycle.Lifecycle.Event.ON_CREATE;

public class AuthenticationController implements LifecycleObserver {

    private static AuthenticationController instance;
    private LifecycleOwner mLifecycleOwner;
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
    @OnLifecycleEvent(ON_ANY)
    public void initialize(LifecycleOwner lifecycleOwner, Event event, @NonNull AuthenticationSettings settings1, @Nullable AuthenticationListener listener) {
        // attach this as observer only one time
        if (event == ON_CREATE) {
            if (mLifecycleOwner == null) {
                mLifecycleOwner = lifecycleOwner;
                //mLifecycleOwner.getLifecycle().addObserver(this);
            }
            this.settings = settings1;
            //save this listener to use it in all activities and notify MainActivity
            mListener = listener;
        }
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
