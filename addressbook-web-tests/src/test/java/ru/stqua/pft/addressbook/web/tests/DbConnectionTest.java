package ru.stqua.pft.addressbook.web.tests;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.model.DataSet;
import ru.stqua.pft.addressbook.web.model.GroupData;


import java.sql.*;

/**
 * Created by Александр on 30.04.2017.
 */
public class DbConnectionTest {
    Logger logger = LoggerFactory.getLogger(DbConnectionTest.class);
    Connection conn = null;
    @Test
    public void testDbConnection(){
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            DataSet<GroupData> groups = new DataSet<>();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");
            while (rs.next()){
                groups.add(new GroupData()
                        .withId(rs.getInt("group_id"))
                        .withName(rs.getString("group_name"))
                        .withName(rs.getString("group_header"))
                        .withHeader(rs.getString("group_header"))
                        .withFooter(rs.getString("group_footer")));
            }
            rs.close();
            statement.close();
            conn.close();
            System.out.println(groups);
        } catch (SQLException ex) {
            // handle any errors
            logger.error("SQLException: " + ex.getMessage());
            logger.error("SQLState: " + ex.getSQLState());
            logger.error("VendorError: " + ex.getErrorCode());
        }
    }
}
