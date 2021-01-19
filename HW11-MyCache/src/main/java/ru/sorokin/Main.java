package ru.sorokin;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sorokin.api.mapper.EntityClassMetaData;
import ru.sorokin.api.mapper.JdbcMapper;
import ru.sorokin.cache.HwListener;
import ru.sorokin.entity.Account;
import ru.sorokin.impl.executor.DbExecutorImpl;
import ru.sorokin.impl.mapper.CustomORMJdbcMapper;
import ru.sorokin.impl.mapper.DataSourceDemo;
import ru.sorokin.impl.mapper.EntityClassMetaDataImpl;
import ru.sorokin.impl.mapper.EntitySQLMetaDataImpl;

import javax.sql.DataSource;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var dataSource = new DataSourceDemo();
        flywayMigrations(dataSource);

        EntityClassMetaData<Account> entityAccountMetaData = new EntityClassMetaDataImpl<>(Account.class);
        JdbcMapper<Account> jdbcAccountMapper = new CustomORMJdbcMapper<>(
                entityAccountMetaData,
                new EntitySQLMetaDataImpl<>(entityAccountMetaData),
                new DbExecutorImpl<>(),
                dataSource
        );



        Account firstAccount = new Account("Account1", "Private", 10.5F);
        Service<String,Account> clientServer = new Service<>(jdbcAccountMapper);
        clientServer.addListener(new HwListener<>() {
            @Override
            public void notify(String key, Account value, String action) {
                logger.info("key:{}, value:{}, action: {}", key, value, action);
            }
        });

        clientServer.save(firstAccount);
        logger.info("старт");
        clientServer.findById("Account1",Account.class);
        logger.info("финиш");
        logger.info("старт");
        clientServer.findById("Account1", Account.class);
        logger.info("финиш");
        logger.info("очистка кэша");
        clientServer.clearCache();
        logger.info("старт");
        clientServer.findById("Account1",Account.class);
        logger.info("финиш");
    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/migration")
                .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }
}
