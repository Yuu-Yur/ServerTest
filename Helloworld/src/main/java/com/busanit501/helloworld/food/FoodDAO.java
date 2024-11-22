package com.busanit501.helloworld.food;

import com.busanit501.helloworld.jdbcex.DAO.ConnectionUtil;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    String sql;
    public void insertFood(FoodDTO foodDTO) throws SQLException {
        sql = "INSERT INTO tbl_food(title,price) VALUES(?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodDTO.getTitle());
        preparedStatement.setInt(2, foodDTO.getPrice());
        preparedStatement.executeUpdate();
    }

    public List<FoodDTO> readFood() throws SQLException {
        sql = "SELECT * FROM tbl_food";
        List<FoodDTO> foodList = new ArrayList<>();
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            foodList.add(new FoodDTO(resultSet.getString("title"), resultSet.getInt("price")));
        }
        return foodList;
    }

    public void updateFood(FoodVO foodVO) throws SQLException {
        sql = "UPDATE tbl_food SET title = ?, price = ? WHERE fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setInt(2, foodVO.getPrice());
        preparedStatement.setInt(3,foodVO.getFno());
        preparedStatement.executeUpdate();
    }

    public void deleteFood(FoodVO foodVO) throws SQLException {
        sql = "DELETE FROM tbl_food WHERE fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, foodVO.getFno());
        preparedStatement.executeUpdate();
    }
}
