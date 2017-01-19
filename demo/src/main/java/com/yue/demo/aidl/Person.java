package com.yue.demo.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import com.yue.demo.util.LogUtil;

public class Person implements Parcelable {

    private Integer id;
    private String name;
    private String pass;

    public Person() {

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        LogUtil.d("name.hashCode() ： " + name.hashCode()
                + ";   pass.hashCode() : " + pass.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((name == null) ? 0 : pass.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Person other = (Person) obj;
        if (name == null) {

            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (pass == null) {

            if (other.pass != null)
                return false;
        } else if (!pass.equals(other.pass))
            return false;
        return true;
    }

    public Person(Integer id, String name, String pass) {
        super();
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 把该对象所包含的数据写到Parcel
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(pass);

    }

    public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>() {

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }

        @Override
        public Person createFromParcel(Parcel source) {
            // 从Parcel中读取数据，返回Person对象
            return new Person(source.readInt(), source.readString(),
                    source.readString());
        }
    };
}
