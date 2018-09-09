package udacity.viktor.oursdklibrary;

/**
 * The AuthenticationController class will implement this interface
 * to be updated with the active Observers and notify active observers with updated data
 */
public interface Subject {
    void registerObserver(ResultObserver observer);
    void removeObserver(ResultObserver observer);
    void notifyObservers();
}
