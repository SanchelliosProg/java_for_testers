package ru.stqua.pft.addressbook.web.appmanager.helpers.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.DataSet;
import ru.stqua.pft.addressbook.web.model.GroupData;

import java.util.List;

/**
 * Created by Александр on 01.05.2017.
 */
public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public DataSet<GroupData> groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> groups = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new DataSet<>(groups);
    }

    public DataSet<ContactData> contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> contacts = session.createQuery("from ContactData  where deprecated='0000-00-00 00:00:00'").list();
        session.getTransaction().commit();
        session.close();
        return new DataSet<>(contacts);
    }
}
