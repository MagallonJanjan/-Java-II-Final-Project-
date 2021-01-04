/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author 1styrGroupC
 */
public class model {
    
    public model(){
    }

    //Initializing varibales needed for the Menu
    private int id;  
    private String foodName;
    private String description;
    private int price;

    
    // Getters and Setters for the variables above
    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getFoodName(){
        return foodName;
    }
    public void setFoodName(String foodName){
        this.foodName = foodName;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
}
