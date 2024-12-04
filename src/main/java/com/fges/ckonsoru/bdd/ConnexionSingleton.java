package com.fges.ckonsoru.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.fges.ckonsoru.ConfigLoader;

public class ConnexionSingleton {

    private static ConnexionSingleton  maConnexion = null;
    protected Connection maCo = null;
   
    private ConnexionSingleton(){
        
        ConfigLoader maConf = new ConfigLoader();
        Properties prop = maConf.getProperties();

        String url = prop.getProperty("bdd.url");
        String login = prop.getProperty("bdd.login");
        String mdp = prop.getProperty("bdd.mdp");
        try {
        
            this.maCo = DriverManager.getConnection( url , login,mdp);
        } catch (SQLException e) {
         
            e.printStackTrace();
        }
        
    }

    public static ConnexionSingleton getInstance() throws SQLException {
        
        if (maConnexion == null) {
            maConnexion = new  ConnexionSingleton();
        }
        return maConnexion;
    }  

    public Connection getConnection(){
        return this.maCo;
    }
    
}
