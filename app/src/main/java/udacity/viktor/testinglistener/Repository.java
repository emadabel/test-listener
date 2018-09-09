package udacity.viktor.testinglistener;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import udacity.viktor.oursdklibrary.AuthenticationController.AuthenticationListener;
import udacity.viktor.oursdklibrary.AuthenticationResult;

public class Repository {

    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private AuthenticationListener mListener;

    private Repository() {
    }

    public static Repository getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository();
            }
        }
        return sInstance;
    }

    public LiveData<AuthenticationResult> getResult() {
        final MutableLiveData<AuthenticationResult> data = new MutableLiveData<>();
        mListener = new AuthenticationListener() {
            @Override
            public void onAuthentication(AuthenticationResult result) {
                data.setValue(result);
            }
        };
        return data;
    }

    public AuthenticationListener getListener() {
        return mListener;
    }
}
