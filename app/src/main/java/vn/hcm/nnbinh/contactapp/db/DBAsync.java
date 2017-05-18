package vn.hcm.nnbinh.contactapp.db;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import vn.hcm.nnbinh.contactapp.app.DBManager;

/**
 * Created by nguyenngocbinh on 5/18/17.
 */

public class DBAsync extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    private DBProvider mDBProvider;
    private ArrayList<Contact> mContactList;

    public DBAsync(Context context) {
        this.mContext = context;
        mDBProvider = DBProvider.get(mContext);
    }

    @Override
    protected Void doInBackground(Void... params) {
        mContactList = mDBProvider.getContacts();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        DBManager db =  DBManager.get(mContext);
        db.save(mContactList);
    }
}
