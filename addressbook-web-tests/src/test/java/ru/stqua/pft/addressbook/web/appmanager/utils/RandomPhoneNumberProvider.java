package ru.stqua.pft.addressbook.web.appmanager.utils;

import java.util.Random;

/**
 * Created by Александр on 21.04.2017.
 */
public class RandomPhoneNumberProvider {
    public static String generateRandomNumber(){
        Random rnd = new Random();
        int country = rnd.nextInt(10);
        int city = rnd.nextInt(900) + 100;
        int firstThree = rnd.nextInt(900) + 100;
        int firstTwo = rnd.nextInt(90) + 10;
        int secondTwo = rnd.nextInt(90) + 10;

        return String.valueOf("+" + country + " (" + city + ") " + firstThree + "-" + firstTwo + "-" + secondTwo);
    }

    public static String generateRandomNumber(int country){
        Random rnd = new Random();
        int city = rnd.nextInt(900) + 100;
        int firstThree = rnd.nextInt(900) + 100;
        int firstTwo = rnd.nextInt(90) + 10;
        int secondTwo = rnd.nextInt(90) + 10;

        return String.valueOf("+" + country + " (" + city + ") " + firstThree + "-" + firstTwo + "-" + secondTwo);
    }
}
