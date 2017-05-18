package vn.hcm.nnbinh.contactapp.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by nguyenngocbinh on 5/18/17.
 */

public class ContactApplication extends Application {
    private DBManager mDBManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initManagers();
    }

    public static ContactApplication get(Context context) {
        context = context.getApplicationContext();
        if (context != null && Application.class.isInstance(context))
            return (ContactApplication) context;
        return null;
    }

    public DBManager getDBManager() {
        return mDBManager;
    }


    private void initManagers() {
        mDBManager = new DBManager(this);
    }

    /***
     * remove managers when activity is destroy
     * */
    public void destroyManagers() {
        mDBManager = null;
    }
}
