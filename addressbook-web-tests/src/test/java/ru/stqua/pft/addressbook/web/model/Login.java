package ru.stqua.pft.addressbook.web.model;

/**
 * Created by Александр on 18.03.2017.
 */
public class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
