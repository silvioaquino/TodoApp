package TodoApp;

import java.sql.Connection;
import util.connectionFactory;


public class Main {

    
    public static void main(String[] args) {
       
        Connection c = connectionFactory.getConnection();
        
        connectionFactory.closeConnection(c);
        
    }
    
}
