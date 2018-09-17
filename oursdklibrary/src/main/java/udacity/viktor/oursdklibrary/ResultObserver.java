package udacity.viktor.oursdklibrary;

/* This interface will be implemented on the Observer
* The Observer will update its views or member variables depending on the data comes from here
* Example for the Observer is the MainActivity
*/
public interface ResultObserver {
    void onResultUpdated(AuthenticationController authenticationResult);
}
