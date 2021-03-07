package ru.sorokin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sorokin.entity.User;
import ru.sorokin.helpers.FileSystemHelper;
import ru.sorokin.hibernate.HibernateUtils;
import ru.sorokin.hibernate.dao.UserDaoImpl;
import ru.sorokin.hibernate.sessionmanager.SessionManagerHibernate;
import ru.sorokin.server.UsersWebServer;
import ru.sorokin.server.UsersWebServerWithBasicSecurity;
import ru.sorokin.service.UserService;
import ru.sorokin.service.UserServiceImpl;
import ru.sorokin.template.TemplateProcessor;
import ru.sorokin.template.TemplateProcessorImpl;

public class Main {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HASH_LOGIN_SERVICE_CONFIG_NAME = "realm.properties";
    private static final String REALM_NAME = "AnyRealm";
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration, User.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        ru.sorokin.hibernate.dao.UserDao userDao = new UserDaoImpl(sessionManager);
        UserService userService = new UserServiceImpl(userDao);


        User firstUser = new User("Ivan Sobaka", "Ivan", "123" );
        User secondUser = new User("George Eagle", "User", "456" );
        userService.saveUser(firstUser);
        userService.saveUser(secondUser);


        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        String hashLoginServiceConfigPath = FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);
        LoginService loginService = new HashLoginService(REALM_NAME, hashLoginServiceConfigPath);


        UsersWebServer usersWebServer = new UsersWebServerWithBasicSecurity(WEB_SERVER_PORT,
                loginService, userService, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
