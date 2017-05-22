package vn.hcm.nnbinh.contactapp.db;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by nguyenngocbinh on 5/18/17.
 */

public class PhoneNumber extends RealmObject{
    private int type;
    private String phoneNumber;

    public PhoneNumber(int type, String phoneNumber) {
        this.type = type;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
