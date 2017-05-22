package vn.hcm.nnbinh.contactapp.db;

import java.util.List;
import java.util.Random;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import vn.hcm.nnbinh.contactapp.utils.CommonUtils;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class Contact extends RealmObject{
    @PrimaryKey
    private long id;
    private String section;
    private String name;
    private String thumbnail;
    private boolean isOnline = false;
    private int hasPhoneNumber;
    private RealmList<PhoneNumber> phoneNumbers =new RealmList<PhoneNumber>();
    public Contact(long id, String name, String thumbnail, int hasPhoneNumber, RealmList<PhoneNumber> phoneNumbers) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.hasPhoneNumber = hasPhoneNumber;
        this.phoneNumbers = phoneNumbers;
        isOnline = (new Random()).nextBoolean();
        setName(name);
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
        section = CommonUtils.getSection(name);
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


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
