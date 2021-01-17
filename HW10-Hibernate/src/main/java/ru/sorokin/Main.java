package ru.sorokin;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sorokin.hibernate.HibernateUtils;
import ru.sorokin.hibernate.dao.UserDao;
import ru.sorokin.hibernate.dao.UserDaoImpl;
import ru.sorokin.entity.AddressDataSet;
import ru.sorokin.entity.PhoneDataSet;
import ru.sorokin.entity.User;
import ru.sorokin.service.UserService;
import ru.sorokin.service.UserServiceImpl;
import ru.sorokin.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Optional;
import java.util.Set;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";


    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);
        logger.info("Start");
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration, User.class, PhoneDataSet.class, AddressDataSet.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoImpl(sessionManager);
        UserService userService = new UserServiceImpl(userDao);

        Set<PhoneDataSet> phoneDataSets = Set.of(new PhoneDataSet("8800553311"), new PhoneDataSet("8800771111"));
        User firstUser = new User();
        firstUser.setAddress(new AddressDataSet("Gagarina"));
        firstUser.setPhoneDataSet(phoneDataSets);
        long id =  userService.saveUser(firstUser);

        Optional<User> mayBeCreatedUser = userService.getUser(id);
        mayBeCreatedUser.ifPresentOrElse(user -> outputUser("Created firstUser", user),
                () -> logger.info("User not found"));

        Set<PhoneDataSet> alternativePhoneDataSets = Set.of(new PhoneDataSet("+7999555"), new PhoneDataSet("+788555"));
        id = userService.saveUser(new User(1,firstUser.getAddress(),alternativePhoneDataSets));
        Optional<User> mayBeUpdatedUser = userService.getUser(id);
        mayBeUpdatedUser.ifPresentOrElse(user -> outputUser("Updated firstUser", user),
                () -> logger.info("User not found"));

    }
    private static void outputUser(String header, User user) {
        logger.info("-----------------------------------------------------------");
        logger.info(header);
        logger.info("user:{}", user);
    }
}
