package udacity.viktor.oursdklibrary;

import android.content.Context;
import android.support.annotation.NonNull;

public class AuthenticationSettings {

    private @NonNull
    String token;
    Context context;

    //creating class with provided settings
    public AuthenticationSettings(@NonNull String token, @NonNull Context context){
        this.token = token;
        this.context  = context;
    }

}
