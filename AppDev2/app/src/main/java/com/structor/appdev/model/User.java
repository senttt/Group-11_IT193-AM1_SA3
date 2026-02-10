package com.structor.appdev.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class User implements Parcelable {

    private int id;
    private String username;
    private String email;
    private int age;
    private String sex;
    private String breed;
    private String birthdate;
    private int photonum;
    private String description;

    public User() {
    }

    public User(String username, String email, int age, String birthdate,
                String breed, String sex, int photonum, String description) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.birthdate = birthdate;
        this.breed = breed;
        this.photonum = photonum;
        this.sex = sex;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getPhotonum() {
        return photonum;
    }

    public void setPhotonum(int photonum) {
        this.photonum = photonum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.age);
        hash = 79 * hash + Objects.hashCode(this.sex);
        hash = 79 * hash + Objects.hashCode(this.breed);
        hash = 79 * hash + Objects.hashCode(this.birthdate);
        hash = 79 * hash + Objects.hashCode(this.photonum);
        hash = 79 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.sex, other.sex)) {
            return false;
        }
        if (!Objects.equals(this.breed, other.breed)) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        if (!Objects.equals(this.photonum, other.photonum)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }

    @Override
    public String toString() {
        return username;
        //return "User{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + '}';
    }

    protected User(Parcel in) {
        id = in.readInt();
        username = in.readString();
        email = in.readString();
        age = in.readInt();
        sex = in.readString();
        breed = in.readString();
        birthdate = in.readString();
        photonum = in.readInt();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeInt(age);
        dest.writeString(sex);
        dest.writeString(breed);
        dest.writeString(birthdate);
        dest.writeInt(photonum);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
