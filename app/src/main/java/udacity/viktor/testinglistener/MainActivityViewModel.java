package udacity.viktor.testinglistener;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import udacity.viktor.oursdklibrary.AuthenticationController.AuthenticationListener;
import udacity.viktor.oursdklibrary.AuthenticationResult;

public class MainActivityViewModel extends ViewModel {

    private Repository mRepository;
    private LiveData<AuthenticationResult> mResultLiveData;

    public MainActivityViewModel() {
        mRepository = Repository.getInstance();
        mResultLiveData = mRepository.getResult();
    }

    public LiveData<AuthenticationResult> getResult() {
        return mResultLiveData;
    }

    public AuthenticationListener getListener() {
        return mRepository.getListener();
    }
}
