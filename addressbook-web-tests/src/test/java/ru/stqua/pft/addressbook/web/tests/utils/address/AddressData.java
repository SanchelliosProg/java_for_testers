package ru.stqua.pft.addressbook.web.tests.utils.address;

import java.io.File;
import java.io.IOException;

/**
 * Created by Александр on 18.03.2017.
 */
public class AddressData {
    private String firstName;
    private String lastName;
    private String photo;
    private String address;
    private String phone;
    private String email;

    private AddressData(String firstName, String lastName, String photo, String address, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    interface FirstNameStep{
        LastNameStep firstName(String name);
    }

    interface LastNameStep{
        PhotoStep lastName(String lastName);
    }

    interface PhotoStep{
        AddressStep photo(String fileName);
    }

    interface AddressStep{
        PhoneStep address(String address);
    }

    interface PhoneStep{
        EmailStep phone(String phone);
    }

    interface EmailStep{
        BuildStep email(String email);
    }

    interface BuildStep{
        AddressData build();
    }

    public static FirstNameStep newBuilder(){
        return new AddressBuilder();
    }

    private static class AddressBuilder implements FirstNameStep, LastNameStep, PhotoStep, AddressStep, PhoneStep, EmailStep, BuildStep{
        private String firstName;
        private String lastName;
        private String photo;
        private String address;
        private String phone;
        private String email;

        @Override
        public LastNameStep firstName(String name) {
            this.firstName = name;
            return this;
        }

        @Override
        public PhotoStep lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        @Override
        public AddressStep photo(String fileName) {
            File pic = new File(".\\..\\resources\\" + fileName);
            try {
                this.photo = pic.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        public BuildStep email(String email) {
            this.email = email;
            return this;
        }

        @Override
        public AddressData build() {
            return new AddressData(firstName, lastName, photo, address, phone, email);
        }
    }    
}
