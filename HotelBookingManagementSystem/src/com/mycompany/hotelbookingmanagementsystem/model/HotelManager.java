package com.mycompany.hotelbookingmanagementsystem.model;

public class HotelManager {
    
    private int id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private int roomNo;
    private int price;
    
    public HotelManager(){
    }
    
    public HotelManager(int id, String firstName, String lastName, Long phoneNumber, int roomNo, int price) {
        
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.roomNo=roomNo;
        this.price=price;
    }
    
    public HotelManager(String firstName, String lastName, Long phoneNumber, int roomNo, int price){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.roomNo=roomNo;
        this.price=price;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    
    public int getRoomNo(){
        return roomNo;
    }
    
    public void setRoomNo(int roomNo){
        this.roomNo=roomNo;
    }
    
    public Long getPhoneNumber(){
        return phoneNumber;
    }
    
    public void setPhoneNumber(Long phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int price){
        this.price=price;
    }
}
