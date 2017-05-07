package ru.stqua.pft.addressbook.web.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;

import javax.persistence.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 18.03.2017.
 */
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id;
    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public ContactData() {
    }

    private ContactData(String firstName, String lastName, String address, String homePhone, String mobilePhone,
                        String workPhone, Set<GroupData> groups, String email, File photo) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.email = email;
        this.groups = groups;
        if (photo != null) {
            this.photo = photo.getPath();
        }

    }

    public ContactData(int id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public File getPhoto() {
        if (photo != null){
            return new File(photo);
        } else {
            return null;
        }

    }

    public void setPhoto(File photo) {
        this.photo = photo.getPath();
    }

    public int getId() {
        return id;
    }

    public void removeGroup(GroupData data) {
        groups.remove(data);
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
        PhotoStep group(GroupLabels... group);
        PhotoStep noGroup();
    }

    public interface PhotoStep{
        BuildStep photo(File photo);
        BuildStep noPhoto();
    }

    public interface BuildStep{
        ContactData build();
    }

    public void addGroup(GroupData groupData){
        groups.add(groupData);
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
        private Set<GroupData> groups = new HashSet<>();
        private String photo;

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
            File photoFile;
            try {
                photoFile = new File(photo);
            }catch (NullPointerException ex){
                photoFile = null;
            }

            return new ContactData(firstName, lastName, address, homePhone, mobilePhone, workPhone, groups, email, photoFile);
        }

        @Override
        public PhotoStep group(GroupLabels... groups) {
            for (GroupLabels label : groups) {
                this.groups.add(GroupProvider.get(label));
            }
            return this;
        }

        @Override
        public PhotoStep noGroup() {
            return this;
        }

        @Override
        public BuildStep photo(File photo) {
            this.photo = photo.getPath();
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

    public DataSet<GroupData> getGroups() {
        return new DataSet<>(groups);
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
