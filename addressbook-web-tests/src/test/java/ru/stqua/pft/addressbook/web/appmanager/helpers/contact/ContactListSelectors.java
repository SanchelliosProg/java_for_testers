package ru.stqua.pft.addressbook.web.appmanager.helpers.contact;

/**
 * Created by Александр on 07.05.2017.
 */
public interface ContactListSelectors {
    String BUTTON_DELETE_CSS = "input[onclick='DeleteSel()']";
    String BUTTON_DETAILED_CSS = "td:nth-child(7)";
    String BUTTON_EDIT_CSS = "td:nth-child(8)";
    String BUTTON_ADD_TO = "input[type='submit'][name='add']";
    String LIST_OF_ADDRESSES_CSS = "table tbody tr[name='entry']";
    String CHECKBOX_ROW_CSS = "input[type='checkbox']";
    String LABEL_LAST_NAME_CSS = "td:nth-child(2)";
    String LABEL_FIRST_NAME_CSS = "td:nth-child(3)";
    String LABEL_ADDRESS_CSS = "td:nth-child(4)";
    String LABEL_EMAIL_CSS = "td:nth-child(5)";
    String LABEL_PHONES_CSS = "td:nth-child(6)";
    String SELECT_GROUP = "div.right select[name='to_group']";
}
