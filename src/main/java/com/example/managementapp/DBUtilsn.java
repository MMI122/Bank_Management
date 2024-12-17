package com.example.managementapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DBUtilsn {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/profilework"; // Replace with your database URL
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = ""; //password silo ekhane4

    public static JsonArray fetchDataFromDatabase() throws Exception {
        JsonArray jsonArray = new JsonArray();

        // Establish database connection
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement statement = connection.createStatement()) {

            // Query to fetch data
            String query = "SELECT * FROM dataforprofile";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set and convert to JSON
            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("username", resultSet.getString("username_profile"));
                jsonObject.addProperty("first_name", resultSet.getString("First_Name"));
                jsonObject.addProperty("last_name", resultSet.getString("Last_Name"));
                jsonObject.addProperty("email", resultSet.getString("email"));
                jsonObject.addProperty("address", resultSet.getString("address_profile"));
                jsonObject.addProperty("nid", resultSet.getString("nid_profile"));
                jsonObject.addProperty("nationality", resultSet.getString("nationality_profile"));
                jsonObject.addProperty("occupation", resultSet.getString("occupation_profile"));
                jsonObject.addProperty("creditor", resultSet.getString("creditor_profile"));
                jsonObject.addProperty("balance", resultSet.getDouble("balance")); // Handle balance as double
                jsonArray.add(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error fetching data from the database", e);
        }
        JsonUploader uploader = new JsonUploader();
        String jsonString = jsonArray.toString(); // Your JSON data
        uploader.uploadJson(jsonString);


        return jsonArray;

    }
    // I am adding it for deleting user.if you wanna delete it,just left out the last curly brace
    /**
     * Deletes a user from the database by matching all fields.
     *
     * @param username    The username of the user.
     * @param firstName   The first name of the user.
     * @param lastName    The last name of the user.
     * @param email       The email of the user.
     * @param address     The address of the user.
     * @param nid         The NID of the user.
     * @param nationality The nationality of the user.
     * @param occupation  The occupation of the user.
     * @param creditor    The creditor field value of the user.
     * @param balance     The balance of the user.
     * @return True if the user was deleted successfully, otherwise false.
     * @throws Exception If there is an error during the database operation.
     */
    public static boolean deleteUserFromDatabase(String username, String firstName, String lastName, String email,
                                                 String address, String nid, String nationality, String occupation,
                                                 String creditor, double balance) throws Exception {

        String deleteQuery = "DELETE FROM dataforprofile WHERE username_profile = ? AND First_Name = ? AND Last_Name = ? " +
                "AND email = ? AND address_profile = ? AND nid_profile = ? AND nationality_profile = ? " +
                "AND occupation_profile = ? AND creditor_profile = ? AND balance = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            // Set parameters for the query
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, nid);
            preparedStatement.setString(7, nationality);
            preparedStatement.setString(8, occupation);
            preparedStatement.setString(9, creditor);
            preparedStatement.setDouble(10, balance);

            // Execute the delete query
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was deleted
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error deleting user from the database", e);
        }
    }

}
