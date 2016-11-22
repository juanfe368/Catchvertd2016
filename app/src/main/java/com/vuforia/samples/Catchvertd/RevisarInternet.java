package com.vuforia.samples.Catchvertd;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by foxcar on 15/11/16.
 */

public class RevisarInternet {

    Context mContext;

    public RevisarInternet(Context mContext) {

        /*
        this.mContext = mContext;


        Log.e("netHabilitada", Boolean.toString(isNetDisponible()));
        Log.e("accInternet", Boolean.toString(isOnlineNet()));
*/

        //ConexionDisponible(mContext);


    }


    public static boolean ConexionDisponible(Context context) {

        Log.e("Net Habilitada: ", Boolean.toString(isNetDisponible(context)));
        Log.e("Acceso a Internet: ", Boolean.toString(isOnlineNet()));

        if(isNetDisponible(context)){
            if(isOnlineNet()){
                return true;
            }
        }

        return false;
    }


    /**
     * comprovar si la Network esta habilitada
     *
     * @return
     */
    private static boolean isNetDisponible(Context mContext) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    /**
     * comprovar si hay acceso a internet
     *
     * @return
     */
    public static Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}
