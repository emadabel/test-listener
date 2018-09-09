package udacity.viktor.oursdklibrary;

import android.support.annotation.Nullable;

public enum AuthenticationResult {
    SUCCESS,
    ERROR,
    LOADING,
    CANCEL;

    public @Nullable
    String message = null;
}
