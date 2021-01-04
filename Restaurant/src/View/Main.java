/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.controllerFood;
import Utilities.dbConnection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author 1styrGroupC
 */
public class Main {
    
    public static void main(String[] args) throws SQLException {
        dbConnection db = new dbConnection(); // Initializing a db
        db.init(); 
        db.getMyConnection();
        
        Scanner input = new Scanner(System.in);
        controllerFood food = new controllerFood(); //Initializing a food 
        
        while (true){
            actions();
            System.out.print("What do you want to do? ");
            
            int command = input.nextInt();
            switch(command){ // Selection Control
                case 1: 
                    food.addFood(); // Calling the addFood method
                    break;
                case 2:
                    food.updateFood(); // Calling the updateFood method
                    break;
                case 3:
                    food.deleteFood(); // Calling the deteleFood method
                    break;
                case 4:
                    food.viewFood(); // Calling the viewFood method
                    break;
                case 5:
                    food.viewAllMenu(); // Calling the viewAllMenu method
                    break;
            }
        }
        
    }
    public static void actions(){ 
        System.out.println("            ===================================");
        System.out.println("          |                 ACTIONS              | ");
        System.out.println("          |                                      | ");  
        System.out.println("          |          1 - ADD MENU                | ");
        System.out.println("          |          2 - UPDATE MENU BY ID       |");
        System.out.println("          |          3 - DELETE MENU BY ID       |");
        System.out.println("          |          4 - VIEW MENU BY ID         |");
        System.out.println("          |          5 - VIEW ALL MENUS          |");
        System.out.println("          |                                      | ");  
        System.out.println("            ====================================");
    }
}
