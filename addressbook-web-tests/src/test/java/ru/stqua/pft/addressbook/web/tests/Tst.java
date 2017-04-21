package ru.stqua.pft.addressbook.web.tests;

import org.junit.Test;
import ru.stqua.pft.addressbook.web.appmanager.utils.RandomPhoneNumberProvider;

/**
 * Created by Александр on 21.04.2017.
 */
public class Tst {
    @Test
    public void tst(){
        System.out.println(RandomPhoneNumberProvider.generateRandomNumber());
    }
}
