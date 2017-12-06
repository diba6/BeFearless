package xyz.sudocoding.befearless.listeners;

/**
 * Created by HackRider on 02-12-2017.
 */

public interface DatabaseListener {
    public void onQuerySuccess(String result);
    public void onQueryFailed();
}
