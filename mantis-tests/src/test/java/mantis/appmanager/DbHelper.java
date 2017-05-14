package mantis.appmanager;

import mantis.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


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

    public List<User> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

//    public DataSet<GroupData> groups() {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        List<GroupData> groups = session.createQuery( "from GroupData" ).list();
//        session.getTransaction().commit();
//        session.close();
//        return new DataSet<>(groups);
//    }
//
//    public DataSet<ContactData> contacts() {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        List<ContactData> contacts = session.createQuery("from ContactData  where deprecated='0000-00-00 00:00:00'").list();
//        session.getTransaction().commit();
//        session.close();
//        return new DataSet<>(contacts);
//    }
//
//    public ContactData getContactWithLastName(String lastName) throws Exception {
//        for (ContactData data : contacts()){
//            if(data.getLastName().equals(lastName)){
//                return data;
//            }
//        }
//        throw new Exception();
//    }
}
