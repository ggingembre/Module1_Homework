package Classes;

import java.io.*;
import java.sql.*;

import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * Created by guillaume on 6/12/17.
 */
public class Utils {

    public static boolean checkDBExists(String dbName){

        try{

            Connection conn = getConnection();

            ResultSet resultSet = conn.getMetaData().getCatalogs();

            while (resultSet.next()) {

                String databaseName = resultSet.getString(1);
                if(databaseName.equals(dbName)){
                    return true;
                }
            }
            resultSet.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    public static void createDB() throws ClassNotFoundException,
            SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
                        "?serverTimezone=UTC" +
                        "&autoReconnect=true&useSSL=false",
                "root","1");



        String aSQLScriptFilePath = "/home/guillaume/Programs/IdeaProjects/goitjsbc/src/main/resources/InitDB.sql";

        Statement stmt = null;

        try {
            // Initialize object for ScripRunner
            ScriptRunner sr = new ScriptRunner(con);

            // Give the input file to Reader
            Reader reader = new BufferedReader(
                    new FileReader(aSQLScriptFilePath));

            // Execute script
            sr.runScript(reader);

        } catch (Exception e) {
            System.err.println("Failed to Execute" + aSQLScriptFilePath
                    + " The error is " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/homework11" +
                        "?serverTimezone=UTC" +
                        "&autoReconnect=true&useSSL=false",
                "root","1");

    }

    public static void pause(){

        System.out.println("Please press any key to go back to the main menu");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = "";
        try {
            text = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


