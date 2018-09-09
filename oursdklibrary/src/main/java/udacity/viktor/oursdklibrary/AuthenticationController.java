package udacity.viktor.oursdklibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationController implements Subject {

    private static AuthenticationController instance;
    private AuthenticationSettings settings;
    private AuthenticationResult mResult;

    private List<ResultObserver> mObservers = new ArrayList<>();

    private AuthenticationController() {

    }

    public static AuthenticationController getInstance() {

        if (instance == null) {
            instance = new AuthenticationController();
        }

        return instance;
    }

    //provide settings in this singleton class
    public void initialize(@NonNull AuthenticationSettings settings1) {
        this.settings = settings1;
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

    @Override
    public void registerObserver(ResultObserver observer) {
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    @Override
    public void removeObserver(ResultObserver observer) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    // notify all observers for data change
    @Override
    public void notifyObservers() {
        for (ResultObserver observer : mObservers) {
            observer.onResultUpdated(mResult);
        }
    }

    public void setResultData(AuthenticationResult result) {
        mResult = result;
        notifyObservers();
    }
}
