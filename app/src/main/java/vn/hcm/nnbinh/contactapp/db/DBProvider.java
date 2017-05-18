package vn.hcm.nnbinh.contactapp.db;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.realm.RealmList;
import vn.hcm.nnbinh.contactapp.utils.Cons;

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
        Cursor cursor = mResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int colId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                int colName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                int colHasPhoneNumber = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);

                long id = cursor.getLong(colId);
                String name = cursor.getString(colName);
                int hasPhoneNumber = cursor.getInt(colHasPhoneNumber);
                Contact contact = new Contact(id, name, getThumbnail(id),hasPhoneNumber, null);
                if (hasPhoneNumber > 0)
                    contact.setPhoneNumbers(getPhoneNumber(id));
                conList.add(contact);
            } while (cursor.moveToNext());
        }

        Collections.sort(conList, new Comparator<Contact>() {
            @Override
            public int compare(Contact lhs, Contact rhs) {
                return lhs.getName().compareToIgnoreCase(rhs.getName());
            }
        });
        return conList;
    }

    /**
     * get phone number of contact by id
     * */
    public RealmList<PhoneNumber> getPhoneNumber(long id) {
        RealmList<PhoneNumber>  phoneNumbers = new RealmList<>();
        Cursor cursor = mResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                new String[]{ String.valueOf(id) }, null);
       if (cursor != null && cursor.moveToFirst())
           do {
               int colNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
               String number = cursor.getString(colNumber);
               phoneNumbers.add(new PhoneNumber(Cons.PHONE_NUM_TYPE.HOME.name(), number));
           }while (cursor.moveToNext());

        return phoneNumbers;
    }

    /**
     * get thumbnail of contact by id
     * */
    public String getThumbnail(long id) {
        try {
            Cursor cur = mResolver.query(ContactsContract.Data.CONTENT_URI, null,
                            ContactsContract.Data.CONTACT_ID
                                    + "="
                                    + id
                                    + " AND "
                                    + ContactsContract.Data.MIMETYPE
                                    + "='"
                                    + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE
                                    + "'", null, null);
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
