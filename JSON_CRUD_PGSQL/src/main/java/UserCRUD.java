import java.sql.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserCRUD 
{
    private static final String URL = "jdbc:postgresql://localhost:5432/assignment";
    private static final String USER = "postgres";
    private static final String PASSWORD = "tiger";

    public static void main(String[] args) 
    {
        try 
        {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            //createUser(connection, "Giri Prasath", 25, "giri@xyz.com");
            //createUser(connection, "Riya Sharma", 35, "riya@xyz.com");
            //createUser(connection, "Hari Ram", 27, "hari@xyz.com");
            //createUser(connection, "Rinku", 33, "rinku@xyz.com");
            
            readUsers(connection);
            //updateUserAge(connection, 1, 28);
            //deleteUser(connection, 2);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUser(Connection connection, String name, int age, String email) 
    {
        String sql = "INSERT INTO users (data) VALUES (?::jsonb)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
            
            String jsonString = String.format("{\"name\": \"%s\", \"age\": %d, \"email\": \"%s\"}", name, age, email);

            
            stmt.setString(1, jsonString);
            stmt.executeUpdate();
            System.out.println("User created successfully!");
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }


    public static void readUsers(Connection connection) 
    {
        String sql = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) 
        {

            while (resultSet.next()) 
            {
                int id = resultSet.getInt("id");
                String data = resultSet.getString("data");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(data);
                
                String name = jsonNode.has("name") ? jsonNode.get("name").asText() : "N/A";
                int age = jsonNode.has("age") ? jsonNode.get("age").asInt() : 0;
                String email = jsonNode.has("email") ? jsonNode.get("email").asText() : "N/A";

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Email: " + email);
                System.out.println("----------------");
            }
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    public static void updateUserAge(Connection connection, int id, int newAge) 
    {
        String sql = "UPDATE users SET data = jsonb_set(data, '{age}', ?::jsonb) WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
            
            String jsonAge = String.format("{\"age\": %d}", newAge);
            stmt.setString(1, jsonAge);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully!");
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(Connection connection, int id) 
    {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
