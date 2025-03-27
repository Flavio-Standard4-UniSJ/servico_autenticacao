
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CriaConexao {
    public static Connection getConexao() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Conectando ao banco");
            return DriverManager.getConnection("jdbc:postgresql:aplicationFoo","postgres","aulatads");
        }catch(ClassNotFoundException e){
            throw new SQLException(e.getMessage());
        }      
    }

}
