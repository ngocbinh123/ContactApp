package vn.hcm.nnbinh.contactapp.db;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by nguyenngocbinh on 5/18/17.
 */

public class PhoneNumber extends RealmObject{
    private String type;
    private String phoneNumber;

    public PhoneNumber(String type, String phoneNumber) {
        this.type = type;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
