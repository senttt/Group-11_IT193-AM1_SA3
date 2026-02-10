package com.structor.appdev.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DummyUser implements Parcelable {

    private String username;
    private String email;

    public DummyUser() {
    }

    public DummyUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    protected DummyUser(Parcel in) {
        username = in.readString();
        email = in.readString();
    }

    public static final Creator<DummyUser> CREATOR = new Creator<DummyUser>() {
        @Override
        public DummyUser createFromParcel(Parcel in) {
            return new DummyUser(in);
        }

        @Override
        public DummyUser[] newArray(int size) {
            return new DummyUser[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
    }
}
