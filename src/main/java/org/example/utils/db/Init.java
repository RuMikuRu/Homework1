package org.example.utils.db;

import java.sql.SQLException;

public class Init {
    public void init(){
        Connection connection = new Connection();

        try {
            connection.createConnection().execute("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
