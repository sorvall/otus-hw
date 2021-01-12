package ru.sorokin;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sorokin.api.mapper.EntityClassMetaData;
import ru.sorokin.api.mapper.JdbcMapper;
import ru.sorokin.entity.Account;
import ru.sorokin.entity.Client;
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

        Client firstClient = new Client(1, "Ivan", 10);
        Client secondClient = new Client(1, "Mike", 69);

        Account firstAccount = new Account("Account1", "Private", 10.5F);
        Account secondAccount = new Account("Account1", "Public", 22.5F);

        EntityClassMetaData<Account> entityAccountMetaData = new EntityClassMetaDataImpl<>(Account.class);
        JdbcMapper<Account> jdbcAccountMapper = new CustomORMJdbcMapper<>(
                entityAccountMetaData,
                new EntitySQLMetaDataImpl<>(entityAccountMetaData),
                new DbExecutorImpl<>(),
                dataSource
        );
        jdbcAccountMapper.insert(firstAccount);
        System.out.println("Вернулся аккаунт: " + jdbcAccountMapper.findById("Account1", Account.class));
        jdbcAccountMapper.update(secondAccount);
        System.out.println("Вернулся аккаунт: " + jdbcAccountMapper.findById("Account1", Account.class));

        EntityClassMetaData<Client> entityClientMetaData = new EntityClassMetaDataImpl<>(Client.class);
        JdbcMapper<Client> jdbcClientMapper = new CustomORMJdbcMapper<>(
                entityClientMetaData,
                new EntitySQLMetaDataImpl<>(entityClientMetaData),
                new DbExecutorImpl<>(),
                dataSource
        );
        jdbcClientMapper.insert(firstClient);
        System.out.println("Вернулся клиент: " + jdbcClientMapper.findById(1, Client.class));
        jdbcClientMapper.update(secondClient);
        System.out.println("Вернулся клиент: " + jdbcClientMapper.findById(1, Client.class));

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
