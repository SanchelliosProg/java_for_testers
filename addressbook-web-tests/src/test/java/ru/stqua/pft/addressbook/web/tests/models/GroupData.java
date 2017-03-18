package ru.stqua.pft.addressbook.web.tests.models;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupData {
    private String name;
    private String header;
    private String footer;

    public GroupData(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }
}
