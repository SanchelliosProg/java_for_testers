package ru.stqua.pft.addressbook.web.model;

import com.google.gson.annotations.Expose;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;

import java.io.File;

/**
 * Created by Александр on 18.03.2017.
 */
public class ContactData {
    private int id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String address;
    @Expose
    private String homePhone;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    @Expose
    private String email;
    @Expose
    private String groupName;
    @Expose
    private File photo;

    private ContactData(String firstName, String lastName, String address, String homePhone, String mobilePhone,
                        String workPhone, String groupName, String email, File photo) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.email = email;
        this.groupName = groupName;
        this.photo = photo;
    }

    public ContactData(int id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public File getPhoto() {
        return photo;
    }

    public interface FirstNameStep{
        LastNameStep firstName(String name);
    }

    public interface LastNameStep{
        AddressStep lastName(String lastName);
    }

    public interface AddressStep{
        HomePhoneStep address(String address);
    }

    public interface HomePhoneStep {
        EmailStep homePhoneOnly(String phone);
        EmailStep homeAndMobile(String home, String mobile);
        EmailStep noPhone();
        MobilePhoneStep homePhone(String phone);
    }

    public interface MobilePhoneStep {
        WorkPhoneStep mobilePhone(String phone);
    }

    public interface WorkPhoneStep {
        EmailStep workPhone(String phone);
    }

    public interface EmailStep{
        GroupStep email(String email);
    }

    public interface GroupStep{
        PhotoStep group(GroupLabels group);
        PhotoStep noGroup();
    }

    public interface PhotoStep{
        BuildStep photo(File photo);
        BuildStep noPhoto();
    }

    public interface BuildStep{
        ContactData build();
    }

    public void setId(int id) {
        this.id = id;
    }

    public static FirstNameStep newBuilder(){
        return new AddressBuilder();
    }

    public static class AddressBuilder implements FirstNameStep, LastNameStep, AddressStep, HomePhoneStep,
            MobilePhoneStep, WorkPhoneStep, EmailStep, GroupStep, PhotoStep, BuildStep{
        private String firstName;
        private String lastName;
        private String address;
        private String homePhone;
        private String mobilePhone;
        private String workPhone;
        private String email;
        private String groupName;
        private File photo;

        public AddressBuilder() {
        }

        @Override
        public LastNameStep firstName(String name) {
            this.firstName = name;
            return this;
        }

        @Override
        public AddressStep lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        @Override
        public HomePhoneStep address(String address) {
            this.address = address;
            return this;
        }

        @Override
        public EmailStep homePhoneOnly(String phone) {
            this.homePhone = phone;
            this.mobilePhone = "";
            this.workPhone = "";
            return this;
        }

        @Override
        public EmailStep homeAndMobile(String home, String mobile) {
            this.homePhone = home;
            this.mobilePhone = mobile;
            this.workPhone = "";
            return this;
        }

        @Override
        public EmailStep noPhone() {
            this.homePhone = "";
            this.mobilePhone = "";
            this.workPhone = "";
            return this;
        }

        @Override
        public MobilePhoneStep homePhone(String phone) {
            this.homePhone = phone;
            return this;
        }

        @Override
        public WorkPhoneStep mobilePhone(String phone) {
            mobilePhone = phone;
            return this;
        }

        @Override
        public EmailStep workPhone(String phone) {
            workPhone = phone;
            return this;
        }

        @Override
        public GroupStep email(String email) {
            this.email = email;
            return this;
        }

        @Override
        public ContactData build() {
            return new ContactData(firstName, lastName, address, homePhone, mobilePhone, workPhone, groupName, email, photo);
        }

        @Override
        public PhotoStep group(GroupLabels group) {
            groupName = group.getName();
            return this;
        }

        @Override
        public PhotoStep noGroup() {
            groupName = "";
            return this;
        }

        @Override
        public BuildStep photo(File photo) {
            this.photo = photo;
            return this;
        }

        @Override
        public BuildStep noPhoto() {
            this.photo = null;
            return this;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData data = (ContactData) o;

        if (firstName != null ? !firstName.equals(data.firstName) : data.firstName != null) return false;
        return lastName != null ? lastName.equals(data.lastName) : data.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
