package com.mycompany.hotelbookingmanagementsystem.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.hotelbookingmanagementsystem.model.HotelManager;
import com.mycompany.hotelbookingmanagementsystem.dao.HotelManagerDao;

import static com.mycompany.hotelbookingmanagementsystem.connection.ConnectionFactory.getConnection;

public class HotelManagerImpl implements HotelManagerDao{
    
    @Override
    public int book(HotelManager hotelManager) throws SQLException, ClassNotFoundException{
        String insertSQL="insert into hotel_manager (id, first_name, last_name, phone_number, room_no, price) values(?,?,?,?,?,?)";
        PreparedStatement prepareStatement = getConnection().prepareStatement(insertSQL);
        prepareStatement.setInt(1,hotelManager.getId());
        prepareStatement.setString(2, hotelManager.getFirstName());
        prepareStatement.setString(3, hotelManager.getLastName());
        prepareStatement.setLong(4, hotelManager.getPhoneNumber());
        prepareStatement.setInt(5,hotelManager.getRoomNo());
        prepareStatement.setInt(6, hotelManager.getPrice());
        return prepareStatement.executeUpdate();
    }
    
    @Override
    public int update(HotelManager hotelManager, int id) throws SQLException, ClassNotFoundException {
        String updateSQL = "update hotel_manager set first_name=?, last_name=?, phone_number=?, room_no=?, price=? where id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(updateSQL);
        preparedStatement.setString(1, hotelManager.getFirstName());
        preparedStatement.setString(2, hotelManager.getLastName());
        preparedStatement.setLong(3, hotelManager.getPhoneNumber());
        preparedStatement.setInt(4, hotelManager.getRoomNo());
        preparedStatement.setInt(5, hotelManager.getPrice());
        preparedStatement.setInt(6, hotelManager.getId());
        return preparedStatement.executeUpdate();
    }
    
    @Override
    public HotelManager findOne(int id) throws SQLException, ClassNotFoundException{
        PreparedStatement preparedStatement = getConnection().prepareStatement("select * from hotel_manager where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        HotelManager hotelManager=new HotelManager();
        while (resultSet.next()){
            hotelManager.setId(resultSet.getInt("id"));
            hotelManager.setFirstName(resultSet.getString("first_name"));
            hotelManager.setLastName(resultSet.getString("last_name"));
            hotelManager.setPhoneNumber(resultSet.getLong("phone_number"));
            hotelManager.setRoomNo(resultSet.getInt("room_no"));
            hotelManager.setPrice(resultSet.getInt("price"));
        }
        return hotelManager;
    }
    
    @Override
    public int delete(int id) throws SQLException, ClassNotFoundException{
        String deleteSQL = "delete from hotel_manager where id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(deleteSQL);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }
    
    @Override
    public List<HotelManager> findAll() throws SQLException, ClassNotFoundException{
        List<HotelManager> hotelManagers = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select * from hotel_manager");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            HotelManager hotelManager = new HotelManager();
            hotelManager.setId(resultSet.getInt("id"));
            hotelManager.setFirstName(resultSet.getString("first_name"));
            hotelManager.setLastName(resultSet.getString("last_name"));
            hotelManager.setPhoneNumber(resultSet.getLong("phone_number"));
            hotelManager.setRoomNo(resultSet.getInt("room_no"));
            hotelManager.setPrice(resultSet.getInt("price"));
            hotelManagers.add(hotelManager);
        }
        return hotelManagers;
    }
    
    @Override
    public List<HotelManager> search(String query) throws SQLException, ClassNotFoundException {
        List<HotelManager> hotelManagers = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection().prepareStatement(
                "select * from hotel_manager where  id  like concat('%' ? '%')"
                +"or first_name like concat ('%' ? '%')"
                + "or  last_name like concat('%' ? '%') "
                + "or phone_number  like concat('%' ? '%') "
                + "or room_no  like concat('%' ? '%')"
                + "or price  like concat('%' ? '%')");
        
        preparedStatement.setString(1, query);
        preparedStatement.setString(2, query);
        preparedStatement.setString(3, query);
        preparedStatement.setString(4, query);
        preparedStatement.setString(5, query);
        preparedStatement.setString(6, query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            HotelManager hotelManager = new HotelManager();
            
            hotelManager.setId(resultSet.getInt("id"));
            hotelManager.setFirstName(resultSet.getString("first_name"));
            hotelManager.setLastName(resultSet.getString("last_name"));
            hotelManager.setPhoneNumber(resultSet.getLong("phone_number"));
            hotelManager.setRoomNo(resultSet.getInt("room_no"));
            hotelManager.setPrice(resultSet.getInt("price"));
            hotelManagers.add(hotelManager);
        }
        return hotelManagers;
    }
}
