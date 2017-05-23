package gigigo.com.template.presentation.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import gigigo.com.kretrofitmanager.IConnectivity;

/**
 * @author Juan Godínez Vera - 5/16/2017.
 */
public class Connectivity
        extends BroadcastReceiver
        implements IConnectivity {
    public static final int TYPE_NONE = -1;

    private Context mContext;

    public Connectivity(Context context) {
        mContext = context;
    }

    private NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo();
    }

    @Override
    public boolean isConnected() {
        NetworkInfo activeNetwork = getNetworkInfo(mContext);
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public int connectivityType() {
        NetworkInfo activeNetwork = getNetworkInfo(mContext);
        return activeNetwork != null ?
                activeNetwork.getType() :
                TYPE_NONE;
    }

    @Override
    public boolean isReachable(String host, int timeout) {
        if(!isConnected()) return false;

        boolean reachable;

        try {
            reachable = InetAddress.getByName(host).isReachable(timeout);
        } catch (UnknownHostException hostException) {
            hostException.printStackTrace();
            reachable = false;
        } catch (IOException exception) {
            exception.printStackTrace();
            reachable = false;
        }

        return reachable;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;

    }
}