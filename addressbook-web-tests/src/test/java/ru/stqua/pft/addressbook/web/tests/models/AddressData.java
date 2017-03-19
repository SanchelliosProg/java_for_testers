package ru.stqua.pft.addressbook.web.tests.models;

import ru.stqua.pft.addressbook.web.tests.utils.group.Groups;

import java.io.File;
import java.io.IOException;

/**
 * Created by Александр on 18.03.2017.
 */
public class AddressData {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String groupName;

    private AddressData(String firstName, String lastName, String address, String phone, String groupName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.groupName = groupName;
    }

    interface FirstNameStep{
        LastNameStep firstName(String name);
    }

    interface LastNameStep{
        AddressStep lastName(String lastName);
    }

    interface AddressStep{
        PhoneStep address(String address);
    }

    interface PhoneStep{
        EmailStep phone(String phone);
    }

    interface EmailStep{
        GroupStep email(String email);
    }

    interface GroupStep{
        BuildStep group(Groups group);
    }

    interface BuildStep{
        AddressData build();
    }

    public static FirstNameStep newBuilder(){
        return new AddressBuilder();
    }

    public static class AddressBuilder implements FirstNameStep, LastNameStep, AddressStep, PhoneStep, EmailStep, GroupStep, BuildStep{
        private String firstName;
        private String lastName;
        private String address;
        private String phone;
        private String email;
        private String groupName;

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
        public PhoneStep address(String address) {
            this.address = address;
            return this;
        }

        @Override
        public EmailStep phone(String phone) {
            this.phone = phone;
            return this;
        }

        @Override
        public GroupStep email(String email) {
            this.email = email;
            return this;
        }

        @Override
        public AddressData build() {
            return new AddressData(firstName, lastName, address, phone, groupName, email);
        }

        @Override
        public BuildStep group(Groups group) {
            groupName = group.getName();
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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroupName() {
        return groupName;
    }
}