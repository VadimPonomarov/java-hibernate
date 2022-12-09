import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

        Metadata metadata =
                new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(User.class) /*!!!!!!! register class*/
                        .addAnnotatedClass(Passport.class) /*!!!!!!! register class*/
                        .addAnnotatedClass(Card.class) /*!!!!!!! register class*/
                        .getMetadataBuilder()
                        .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        /**/
        session.beginTransaction();
        session.save(new User("Petya", "Petrov", new Passport("322223", "СА")));
        session.save(new User("Vasya", "Vasiliev", new Passport("223223", "СС")));
        session.getTransaction().commit();
        /**/
        List<User> users = session.createQuery("select u from User u", User.class).list();
        for (User user : users) {
            System.out.println(user);
        }
        User user = session.find(User.class, 2);
        System.out.println(user.getPassport());

        Passport passport = session.find(Passport.class, 1);
        System.out.println(session.find(User.class, passport.getId()));

        Card card = new Card("1234 1234 1234 1234", user);
        session.save(card);

        session.close();
        sessionFactory.close();
        /**/

    }
}
