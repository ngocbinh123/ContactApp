package vn.hcm.nnbinh.contactapp.db;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class Contact extends RealmObject{
    @PrimaryKey
    private long id;
    private String name;
    private String thumbnail;
    private boolean isOnline = false;
    private int hasPhoneNumber;
    private RealmList<PhoneNumber> phoneNumbers =new RealmList<PhoneNumber>();

    public Contact(long id, String name, String thumbnail, int hasPhoneNumber, RealmList<PhoneNumber> phoneNumbers) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.hasPhoneNumber = hasPhoneNumber;
        this.phoneNumbers = phoneNumbers;
    }

    public Contact() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getHasPhoneNumber() {
        return hasPhoneNumber;
    }

    public void setHasPhoneNumber(int hasPhoneNumber) {
        this.hasPhoneNumber = hasPhoneNumber;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public RealmList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(RealmList<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

}
