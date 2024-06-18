package org.example.Connection;

import org.example.Settings.AppSettings;
import org.example.Utils.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;

@RestController
@Transactional
public class SQL_Operations extends Http_Request_Error {
    static AppSettings appSettingsController = new AppSettings();

    public SQL_Operations() {

    }

    // Hibernate connection settup
    public Configuration getHibernateConfig() throws NullPointerException {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.url", "jdbc:mysql://" + appSettingsController.getCurrentAppSettings().getIpAddress() + ":" + appSettingsController.getCurrentAppSettings().getPort() + "/" + appSettingsController.getCurrentAppSettings().getDatabaseName())
                    .setProperty("hibernate.connection.username", appSettingsController.getCurrentAppSettings().getLogin())
                    .setProperty("hibernate.connection.password", appSettingsController.getCurrentAppSettings().getPassword())
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.jdbc.batch_versioned_data", "true")
                    .setProperty("hibernate.jdbc.batch_size", "500");

            configuration.addAnnotatedClass(Item.class);
            return configuration;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //returns connect session
    public SessionFactory getConnectionWithDatabase() throws ServiceException, NullPointerException {
        return appSettingsController.getCurrentAppSettings().getHibernateCfg().buildSessionFactory();
    }

    //drop 'steamitems' table
    public void dropSteamItemsTable() {
        Session session = getConnectionWithDatabase().openSession();
        Transaction txn = session.beginTransaction();
        String request = "CALL DropSteamItems";
        Query query = session.createSQLQuery(request);
        query.executeUpdate();
        txn.commit();
        session.close();
    }

    //Insert item
    public void sendSteamItemsIntoDatabase(ArrayList<Item> itemsToSendList) throws ServiceException, NullPointerException {
        Session session = getConnectionWithDatabase().openSession();
        Transaction txn = session.beginTransaction();
        for (Item item : itemsToSendList) {
            session.save(item);
        }
        txn.commit();
        session.close();
    }
}
