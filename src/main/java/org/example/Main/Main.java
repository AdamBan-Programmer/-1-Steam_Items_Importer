package org.example.Main;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.Connection.Api_Connection;
import org.example.Connection.Http_Request_Error;
import org.example.Connection.SQL_Operations;
import org.example.Utils.Item;
import org.example.Settings.AppSettings;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

@SpringBootApplication
public class Main extends SQL_Operations {

    static AppSettings settingsController = new AppSettings();
    static SQL_Operations databaseController = new SQL_Operations();
    static Http_Request_Error requestErrorController = new Http_Request_Error();
    static Api_Connection steamApiController = new Api_Connection();

    // 1. IP    2. port  3.database_Name   4. login   5.password    6.package size
    public static void main(String[] args) {
        try {
            settingsController.getCurrentAppSettings().setAppSettings(args);
            databaseController.dropSteamItemsTable();
            startImportingSteamItems();
        } catch (Exception e) {
            System.out.println("Error while running! Program stopped!");
        } finally {
            System.exit(0);
        }
    }

    // imports items and sends to database in packages
    private static void startImportingSteamItems() throws JsonProcessingException, InterruptedException {
        int steamItemsAmount = steamApiController.getSteamItemsAmount();
        int packageSize = settingsController.getCurrentAppSettings().getPackageSize();
        System.out.println("Start, " + steamItemsAmount + " records to import in: " + ((steamItemsAmount / packageSize) + 1) + " packages");

        for (int i = 0; i < (steamItemsAmount / packageSize) + 1; i++) {
            try {
                ArrayList<Item> packageToSend = getNewItemsPackage(packageSize,steamItemsAmount,i);
                databaseController.sendSteamItemsIntoDatabase(packageToSend);
            } catch (HttpClientErrorException e) {
                i = requestErrorController.httpRequestError(i);
            }
        }
    }


    private static ArrayList<Item> getNewItemsPackage(int packageSize, int itemsAmount, int index) throws JsonProcessingException {
        ArrayList<Item> steamItems = new ArrayList<>();
        int itemsImported = 0;

        while (steamItems.size() < packageSize) {
            ArrayList<Item> newSteamItems = steamApiController.importSteamItems(itemsImported, itemsAmount - itemsImported);
            steamItems.addAll(newSteamItems);
            itemsImported = index * packageSize + steamItems.size();
            System.out.println("Items Imported:" + itemsImported + "  Package: " + (index+1));

            if (itemsImported == itemsAmount) {
                break;
            } else {
                makeInterval();
            }
        }
        return steamItems;
    }

    // sleep
    private static void makeInterval() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Error While waiting");
        }
    }
}