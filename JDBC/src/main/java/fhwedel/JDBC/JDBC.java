package fhwedel.JDBC;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {

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

        // 2. CREATE
        String tableCreate = "personal";
        String valuesCreate = "417, 'Henrik', 'Krause', 'it1', 'd13', 'tkk'";
        create(statement, tableCreate, valuesCreate);

        // 3. READ
        String tableRead = "personal";
        read(statement, tableRead);

        // 4. UPDATE
        String tableUpdate = "gehalt";
        String modificationUpdate = "betrag = betrag * 1.1";
        String conditionUpdate = "geh_stufe = 'it1'";
        update(statement, tableUpdate, modificationUpdate, conditionUpdate);

        // 5. DELETE 
        String tableDelete = "personal";
        String conditionDelete = "pnr = 135";
        delete(statement, tableDelete, conditionDelete);

        miarbeiterInVerkauf(statement);

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

    /**
     * 2. CREATE: Erzeugt einen neuen Datensatz in einer Tabelle der Datenbank.
     * 
     * @param statement Das zugehörige Statement zur Connection
     * @param table Die zugehörige Tabelle, aus der gelesen werden soll
     * @param values Die Values, die hinzugefügt werden sollen
     * 
     * @throws SQLException Unbehandelt
     */
    private static void create(Statement statement, String table, String values) throws SQLException{
        statement.executeUpdate("INSERT INTO " + table + " VALUES " + "(" + values + ");");
    }

    /**
     * 3. READ: Zeigt alle Datensätze einer Tabelle der Datenbank an.
     * 
     * @param statement Das zugehörige Statement zur Connection
     * @param table Die zugehörige Tabelle aus der gelesen werden soll
     * 
     * @throws SQLException Unbehandelt
     */
    private static void read(Statement statement, String table) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table + ";");
        while (resultSet.next()) {
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int index = 1; index <= columnCount; index++) {
                String data = resultSet.getString(index);
                System.out.print(data + " ");
            }
            System.out.println("");
        }
    }

    /**
     * 3. READ: Zeigt alle Datensätze einer Tabelle der Datenbank an.
     * 
     * @param statement Das zugehörige Statement zur Connection
     * @param table Die zugehörige Tabelle aus der gelesen werden soll
     * @param condition Condition
     * 
     * @throws SQLException Unbehandelt
     */
    private static void read(Statement statement, String table, String condition) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table + " WHERE " +  condition + ";");
        while (resultSet.next()) {
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int index = 1; index <= columnCount; index++) {
                String data = resultSet.getString(index);
                System.out.print(data + " ");
            }
            System.out.println("");
        }
    }

    /**
     * 4. UPDATE: Ändert einen Eintrag in einer Tabelle der Datenbank.
     * 
     * @param statement Das zugehörige Statement zur Connection
     * @param table Die zugehörige Tabelle, die geupdated werden soll
     * @param modification Die Änderung an der Column
     * @param condition Die Bedingung, zu der die modification ausgeführt wird
     * 
     * @throws SQLException Unbehandelt
     */
    private static void update(Statement statement, String table, String modification, String condition) throws SQLException{
        statement.executeUpdate("UPDATE " + table + " SET " +  modification + " WHERE " + condition + ";");
    }

    /**
     * 5. DELETE: Löscht einen Datensatz einer Tabelle der Datenbank.
     * 
     * @param statement Das zugehörige Statement zur Connection
     * @param table Die zugehörige table, die verändert werden soll
     * @param condition Die zugehörige Condition
     * 
     * @throws SQLException Unbehandelt
     */
    private static void delete(Statement statement, String table, String condition) throws SQLException{
        statement.executeUpdate("DELETE FROM " + table + " WHERE " + condition + ";");
    }

    /**
     * 5. DELETE: Löscht einen Datensatz einer Tabelle der Datenbank.
     * 
     * @param statement Das zugehörige Statement zur Connection
     * 
     * @throws SQLException Unbehandelt
     */
    private static void miarbeiterInVerkauf(Statement statement) throws SQLException{
        read(statement, "personal", "abt_nr = 'd15'");;
    }
    
}
