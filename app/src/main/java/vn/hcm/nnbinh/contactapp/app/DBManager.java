package vn.hcm.nnbinh.contactapp.app;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import vn.hcm.nnbinh.contactapp.db.Contact;

/**
 * Created by nguyenngocbinh on 5/18/17.
 */

public class DBManager {
    private static final String TAG = DBManager.class.getName();
    private static final long DB_VERSION = 0;
    private RealmConfiguration mRealmConfiguration;
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }

    public static DBManager get(Context context) {
        ContactApplication app = ContactApplication.get(context);
        if (app != null)
            return app.getDBManager();
        return null;
    }
    protected void init() {
        Realm.init(mContext);
        if (mRealmConfiguration == null) {
            mRealmConfiguration = new RealmConfiguration.Builder()
                    .schemaVersion(DB_VERSION)
                    .migration(migration)
                    .build();
        }
        Realm.setDefaultConfiguration(mRealmConfiguration);
    }
    
    /**
     * migration db
     */
    private RealmMigration migration = new RealmMigration() {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            Log.w(TAG, "DB migrate...oldVersion: " + oldVersion + ", newVersion: " + newVersion);
        }
    };

    public Realm getRealm() {
        init();
        Realm realm = Realm.getDefaultInstance();
        return realm;
    }

    /**
     * get contacts from db
     */
    public List<Contact> getContacts() {
        RealmResults<Contact> results = getRealm().where(Contact.class).findAllSorted("section", Sort.ASCENDING);
        return results;
    }

    /**
     * update db with this medias.
     * @param data: list of objecs that extend from RealmObject
     */
    public void save(final List<? extends RealmObject> data) {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(data);
            }
        });
        getRealm().close();
    }

}
