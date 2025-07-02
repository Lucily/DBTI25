package fhwedel.ETL;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ETL {

    /**
     * Die Hauptmethode der Klasse. Führt verschiedene Dinge aus...
     * 
     * @param args args
     * 
     * @throws SQLException Unbehandelt
     */
    public static void main(String[] args) throws SQLException {
        // 1. CONNECTION 
        Connection connection = connect();
        Statement statement = connection.createStatement();

        // AUFGABE 3

        // 1. Daten aus den Tabellen personal und gehaltszahlung extrahieren
        ResultSet resultSetPersonal = statement.executeQuery("SELECT * FROM " + "personal" + ";");
        ResultSet resultSetGehaltszahlung = statement.executeQuery("SELECT * FROM " + "gehaltszahlung" + ";");

        // 2. Daten transformieren
        while (resultSetPersonal.next()) {
            Integer pnr = resultSetPersonal.getInt("pnr");
            String vorname = resultSetPersonal.getString("vorname");
            String name = resultSetPersonal.getString("name");
            String geh_stufe = resultSetPersonal.getString("geh_stufe");
            String abt_nr = resultSetPersonal.getString("abt_nr");
            String krankenkasse = resultSetPersonal.getString("krankenkasse");

            statement.executeQuery("INSERT INTO dim_mitarbeiter (mitarbeiter_id, vorname, nachname, geh_stufe, abt_nr, krankenkasse) " +
                "VALUES (" + pnr + ", '" + vorname + "', '" + name + "', '" + geh_stufe + "', '" + abt_nr + "', '" + krankenkasse + "');");
        }

        
        

        statement.close();
        connection.close();
    }

    /**
     * 1. Stellt eine Verbindung zum laufenden MariaDB-Datenbankserver her.
     * 
     * @return Die hergestellte Verbindung
     * 
     * @throws SQLException Unbehandelt
     */
    private static Connection connect() throws SQLException {
        String user = "root";
        String password = "password";
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/firma", user, password);
    }
    
}
