# Steam Items Importer:
It's a simple script to import steam items (CS2) into database. It's a 1 of 3 parts of my project where i created an application for traders on steam market. Main application was written in Android java. You can add items which you want to observe. Observed items prices update every 10minutes. It helps to buy and sell an items in optimal moments. 

# About:
- I created this app to make better decision's on steam market. In this code i used Spring Boot and Hibernate framework. I had to create my own database using MariaDB and phpmyadmin. This application imports items using Steam API and sends it into database. 

# How to use:
1. Create your database.
2. Modify database name and columne's names in code.
3. click 'code' and 'download ZIP'
4. Unpack ZIP folder and open it into Intellij.
5. Build an artifact.
6. Create bash script.
7. Run jar file with starting parameters in the Command Line.

# Example:
- starting parameters: 1.IP 2.Port 3.Database_name 4.Login 5.Password 6.Package-size

java -jar C:\Users\...\SteamItemsImporter.jar 192.168.0.11 3307 myDbName mylogin myPassword 500


![importing](https://github.com/AdamBan-Programmer/Steam_Items_Importer/assets/137770072/3b8eb2a4-a747-490e-b596-d65f38f8dbfb)

# Requirements:
- Windows 10.

- Java 18 or higher.

