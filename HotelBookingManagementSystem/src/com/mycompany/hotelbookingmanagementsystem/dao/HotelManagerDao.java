package com.mycompany.hotelbookingmanagementsystem.dao;

import java.sql.SQLException;
import java.util.List;
import com.mycompany.hotelbookingmanagementsystem.model.HotelManager;

public interface HotelManagerDao {
    int book(HotelManager hotelManager) throws SQLException, ClassNotFoundException;
    
    int update(HotelManager hotelManager, int id) throws SQLException, ClassNotFoundException;
    
    int delete(int id) throws SQLException, ClassNotFoundException;
    
    HotelManager findOne(int id) throws SQLException, ClassNotFoundException;
    
    List<HotelManager> findAll() throws SQLException, ClassNotFoundException;
    
    List<HotelManager> search(String query) throws SQLException, ClassNotFoundException;
    
}

