/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

// Import model and other buil-in libraries 
import Models.model; 
import Utilities.dbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 1styrGroupC
 */
public class controllerFood {  

    Scanner input;
    ArrayList<model> foodModel; 
    dbConnection db; // database connection

    public controllerFood() {  //
        input = new Scanner(System.in);
        foodModel = new ArrayList<>();  // Creating an array used for storing the menus
        db = new dbConnection(); // Initializing a new db connection
        db.init();
    }

    public void addFood() { //Method to Add Menu
        
        // Ask user input to complete the menu
        System.out.print("Food Name: ");
        String foodname = input.nextLine();
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Price: ");
        int price = input.nextInt();
        input.nextLine();

        try {
            String query = "INSERT INTO foods (foodName, description, price) values (?, ?, ?)"; // SQL query to insert data into a table
            PreparedStatement pst = db.getMyConnection().prepareStatement(query); // used to execute a statement
            pst.setString(1, foodname); 
            pst.setString(2, description);
            pst.setInt(3, price);
            pst.execute();

            db.close(pst);  
            System.out.println("Successfully inserted menu to the database!");
        } catch (SQLException e) {    //Catch the error
            System.out.println("Failed to insert");
            System.out.println(e.getMessage());
        }
    }

    public void updateFood() { // Method to Update a menu
        System.out.print("Enter Menu ID: "); // Ask user an ID to update
        int id = input.nextInt();
        try {
            String idQuery = "SELECT id FROM foods"; // SQL query to update a data in a table
            Statement stmt = db.getMyConnection().createStatement(); 
            ResultSet rs = stmt.executeQuery(idQuery); // used to execute statements that returns tabular data
            int dbId = 0;
            boolean isPresent = false;
            while (rs.next()) {  

                if (id == rs.getInt("id")) { // Check if the Id enteres is equal to the existing Id in the database
                    dbId = rs.getInt("id");
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {  // Check if the ID entered doesn't exist
                System.out.println("Sorry, Id doesn't exist :(");

            } else {
                System.out.println("    ----UPDATE OPTIONS-----  ");
                System.out.println("|    1. Food Name            |");
                System.out.println("|    2. Description          |");
                System.out.println("|    3. Price                |");
                System.out.println("    -----------------------  ");
                 System.out.print("Please Choose: "); // Asking the user for the field that they want to update
                input.nextLine();
                String option = input.nextLine();
                switch (option) { // Selection Control
                    case "1":
                        System.out.print("Update Food Name: ");
                        String newFoodName = input.nextLine();
                        String foodQuery = "UPDATE foods SET foodName = ? WHERE id = ?";  //SQL query to update data in a table
                        PreparedStatement foodstmt = db.getMyConnection().prepareStatement(foodQuery);
                        foodstmt.setString(1, newFoodName);
                        foodstmt.setInt(2, dbId);
                        foodstmt.execute();

                        System.out.println("Name updated successfully!");
                        break;
                    case "2":
                        System.out.print("Update Description: ");
                        String newDescription = input.nextLine();
                        String descriptionQuery = "UPDATE foods SET description = ? WHERE id = ?";
                        PreparedStatement descriptionstmt = db.getMyConnection().prepareStatement(descriptionQuery);
                        descriptionstmt.setString(1, newDescription);
                        descriptionstmt.setInt(2, dbId);
                        descriptionstmt.execute();

                        System.out.println("Description updated successfully!");
                        break;
                    case "3":
                        System.out.print("Update Price: ");
                        String newPrice = input.nextLine();
                        String priceQuery = "UPDATE foods SET price = ? WHERE id = ?";
                        PreparedStatement pricestmt = db.getMyConnection().prepareStatement(priceQuery);
                        pricestmt.setString(1, newPrice);
                        pricestmt.setInt(2, dbId);
                        pricestmt.execute();

                        System.out.println("Price updated successfully!");
                        break;
                    default:
                        break;
                }
                
                db.close(stmt);
                db.close(rs);
            }

        } catch (SQLException e) {
            System.out.println("FAILED TO UPDATE");
            System.out.println(e.getMessage());
        }

    }

    public void deleteFood() {  //Method  to delete menu by ID
        System.out.print("Please Enter ID: "); // User input to enter ID of the menu that they want to remove
        int id = input.nextInt();

        try {
            boolean ispresent = false;
            int dbId = 0;

            String query = "SELECT id FROM foods"; // Retrieving ID from the database that will be used to compare the user's  ID input
            Statement stmt = db.getMyConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (id == rs.getInt("id")) {  //Check if the ID entered is equal to the ID that is in the database
                    dbId = rs.getInt("id");
                    ispresent = true;
                    break;
                }
            }
            if (!ispresent) { // Check of the ID doesn't exist
                System.out.println("Sorry, ID doesn't exist :( ");

            } else {
                String delete = "DELETE FROM foods WHERE id = ?";  //SQL query to delete data in the table
                PreparedStatement statemnt = db.getMyConnection().prepareStatement(delete); // used to execute a statement
                statemnt.setInt(1, id);

                statemnt.execute();
                System.out.println("Successfully deleted a menu :)");
            }
            db.close(stmt);

        } catch (SQLException e) { // Catching an error
            System.out.println("FAILED TO DELETE DATA");
            System.out.println(e.getMessage());
        }
    }

    public void viewFood() {  // Method to view menu by ID
        System.out.print("Please Input ID: ");
        int id = input.nextInt();
        try {
            String query = "SELECT * FROM foods WHERE id = ?"; // SQL query to select all the depends on the condition 
            PreparedStatement prpstmt = db.getMyConnection().prepareStatement(query);
            prpstmt.setInt(1, id);

            ResultSet rs = prpstmt.executeQuery();
            rs.next();
            System.out.println("Food ID: " + rs.getInt("id") + "\n" + "Food Name: " + rs.getString("foodName") + "\n"
                    + "Description: " + rs.getString("description") + "\n" + "Price: " + rs.getString("price"));
            prpstmt.execute();
            db.close(prpstmt);
        } catch (SQLException e) {
            System.out.println("Sorry, The Id you entered does not exist :(");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<model> viewAllMenu() { // Method to view all menu in the database

        try {
            String query = "SELECT * FROM foods";
            Statement stmt = db.getMyConnection().createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model model = new model();
                model.setID(rs.getInt("id"));
                model.setFoodName(rs.getString("foodName"));
                model.setDescription(rs.getString("description"));
                model.setPrice(rs.getInt("price"));
                foodModel.add(model);
            }
            System.out.println("FOOD MENU: \n");
            foodModel.forEach((food) -> {
                System.out.println("Food ID: " + food.getID() + "\n" + "Food Name: " + food.getFoodName() + "\n"
                        + "Description: " + food.getDescription() + "\n" + "Price: Php" + food.getPrice() + "\n");
            });
            db.close(stmt);
            System.out.println("Successfully loaded from the database");
        } catch (SQLException e) {
            System.out.println("FAILED TO LOAD DATA");
            System.out.println(e.getMessage());
        }

        return foodModel;
    }
}
