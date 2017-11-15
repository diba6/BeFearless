package xyz.sudocoding.befearless.listeners;

/**
 * Firebase Authentication listener class
 */

public interface AuthenticationListener {
    public boolean signedUp(boolean isSuccessful);
    public boolean logIn(boolean isSuccessful);
    public boolean error();
}
