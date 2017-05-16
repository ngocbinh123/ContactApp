package vn.hcm.nnbinh.contactapp.db;

import android.net.Uri;

import java.util.List;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class Contact {
    private long id;
    private String name;
    private Uri thumbnail;
    private boolean isOnline = false;
    private int hasPhoneNumber;
    private List<String> phoneList;

    public Contact(long id, String name, Uri thumbnail, int hasPhoneNumber, List<String> phoneList) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.hasPhoneNumber = hasPhoneNumber;
        this.phoneList = phoneList;
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

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public Uri getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Uri thumbnail) {
        this.thumbnail = thumbnail;
    }
}
