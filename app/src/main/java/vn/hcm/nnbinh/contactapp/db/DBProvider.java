package vn.hcm.nnbinh.contactapp.db;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.util.Log;
import java.util.ArrayList;
import io.realm.RealmList;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class DBProvider {
    private static final String TAG = DBProvider.class.getName();
    private ContentResolver mResolver;

    public DBProvider(Context context) {
        mResolver = context.getContentResolver();
    }

    public static DBProvider get(Context context) {
        return new DBProvider(context);
    }

    /**
     * get contact list
     * */
    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> conList = new ArrayList<>();
        Cursor cursor = mResolver.query(Contacts.CONTENT_URI, null, null, null, Contacts.DISPLAY_NAME + " ASC");

        if (cursor != null && cursor.moveToFirst()) {
            do {

                int colId = cursor.getColumnIndex(Contacts._ID);
                int colName = cursor.getColumnIndex(Contacts.DISPLAY_NAME);
                int colHasPhoneNumber = cursor.getColumnIndex(Contacts.HAS_PHONE_NUMBER);

                long id = cursor.getLong(colId);
                String name = cursor.getString(colName);
                int hasPhoneNumber = cursor.getInt(colHasPhoneNumber);
                Contact contact = new Contact(id, name, getThumbnail(id), hasPhoneNumber, null);
                if (hasPhoneNumber > 0)
                    contact.setPhoneNumbers(getPhoneNumber(id));
                conList.add(contact);
            } while (cursor.moveToNext());
        }

        return conList;
    }

    /**
     * get phone number of contact by id
     * */
    public RealmList<PhoneNumber> getPhoneNumber(long id) {
        RealmList<PhoneNumber>  phoneNumbers = new RealmList<>();
        Cursor cursor = mResolver.query(CommonDataKinds.Phone.CONTENT_URI,null,
                CommonDataKinds.Phone.CONTACT_ID +" = ?",
                new String[]{ String.valueOf(id) }, null);
       if (cursor != null && cursor.moveToFirst())
           do {
               int colType = cursor.getColumnIndex(CommonDataKinds.Phone.TYPE);
               int colNumber = cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER);

               int type = cursor.getInt(colType);
               String number = cursor.getString(colNumber);
               phoneNumbers.add(new PhoneNumber(type, number));
           }while (cursor.moveToNext());

        return phoneNumbers;
    }

    /**
     * get thumbnail of contact by id
     * */
    public String getThumbnail(long id) {
        try {
            String selection =  Data.CONTACT_ID + "=" + id + " AND " +
                    Data.MIMETYPE + "='" + CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'";
            Cursor cur = mResolver.query(Data.CONTENT_URI, null, selection, null, null);
            if (cur == null || !cur.moveToFirst())
                return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
        Uri person = ContentUris.withAppendedId(
                ContactsContract.Contacts.CONTENT_URI, id);
        return Uri.withAppendedPath(person,
                ContactsContract.Contacts.Photo.CONTENT_DIRECTORY).toString();
    }
}
