package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabasePopulateService {
    private static final String FOLDER_URL = "src/main/resources/sql/populate_db.sql";
    public static void main(String[] args) {
        try
        {
            Connection connection = Database.getInstance().getConnection();
            List<String> sqlLines = Files.readAllLines(Paths.get(FOLDER_URL));
            String sqlRequest = String.join("\n", sqlLines);
            try(PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
                statement.execute();
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
