package org.example.Settings;

import org.example.Connection.Api_Connection;
import org.example.Connection.SQL_Operations;
import org.hibernate.cfg.Configuration;

public class AppSettings extends Api_Connection {

    static SQL_Operations databaseController = new SQL_Operations();

    private String ipAddress;
    private int port;
    private String databaseName;
    private String login;
    private String password;
    private int packageSize;
    private Configuration hibernateCfg;

    private static AppSettings currentAppSettings;

    private AppSettings(String ipAddress, int port, String databaseName, String login, String password,int packageSize,Configuration hibernateCfg) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.databaseName = databaseName;
        this.login = login;
        this.password = password;
        this.packageSize = packageSize;
        this.hibernateCfg = hibernateCfg;
    }

    public AppSettings()
    {
        //nothing
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPackageSize() {
        return this.packageSize;
    }

    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }

    public Configuration getHibernateCfg() {
        return this.hibernateCfg;
    }

    public void setHibernateCfg(Configuration hibernateCfg) {
        this.hibernateCfg = hibernateCfg;
    }

    public AppSettings getCurrentAppSettings() {
        if(currentAppSettings == null)
        {
            currentAppSettings = new AppSettings();
        }
        return currentAppSettings;
    }

    public void setAppSettings(String[] args){
        if(args.length == 6) {
            currentAppSettings = new AppSettings(args[0], Integer.parseInt(args[1]), args[2],args[3], args[4],Integer.parseInt(args[5]),null);
            currentAppSettings.setHibernateCfg(databaseController.getHibernateConfig());
        }
    }
}
