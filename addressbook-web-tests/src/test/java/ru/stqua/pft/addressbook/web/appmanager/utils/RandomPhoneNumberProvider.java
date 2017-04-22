package ru.stqua.pft.addressbook.web.appmanager.utils;

import java.util.Random;

/**
 * Created by Александр on 21.04.2017.
 */
public class RandomPhoneNumberProvider {
    public static String generateRandomNumber(){
        Random rnd = new Random();
        int country = rnd.nextInt(10);
        int city = rnd.nextInt(1000 - 100) + 100;
        int phone = rnd.nextInt(10000000 - 1000000) + 1000000;
        return String.valueOf(country + "" + city + "" + phone);
    }
}
