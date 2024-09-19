package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseInitService {
    private static final String FOLDER_URL = "src/main/resources/sql/init_db.sql";
    public static void main(String[] args) {
        try {
            Connection connection = Database.getInstance().getConnection();
            List<String> sqlLines = Files.readAllLines(Paths.get(FOLDER_URL));
            String sqlRequest = String.join("\n", sqlLines);
            try(PreparedStatement statement = connection.prepareStatement(sqlRequest))
            {
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
