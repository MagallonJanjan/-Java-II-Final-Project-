/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author 1styrGroupCo
 */
public class dbConnection {
    private Connection myConnection;  
    private String dbUrl = "jdbc:mysql://localhost:3306/managementsystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String dbUserName = "root";
    private String password = "";
    
    public dbConnection(){ 
        
    }
    public void init(){
        try{
            Class.forName(driver);
            myConnection = DriverManager.getConnection(dbUrl, dbUserName, password);  
            System.out.println("                  CONNECTION SUCCESSFULL");
        }
        catch(ClassNotFoundException | SQLException e){  // Catch if there is an error
            System.out.println("failed to have a connection");
            System.out.println(e);
        }
    }
    public Connection getMyConnection(){  // Getting the connection
        return this.myConnection;
    }
    public void close(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }
    public void close(java.sql.Statement stmt){
        if(stmt != null){
            try{
                stmt.close();
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }
    public void destroy(){  // Method for destroying the connection
        if(myConnection != null){
            try{
                myConnection.close();
            }
            catch(SQLException e ){
                System.out.println(e);
            }
        }
    }
}
