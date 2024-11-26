package com.busanit501.helloworld.food;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.dto.FoodVO;
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

    public void insertFood(FoodVO foodVO) throws SQLException {
        sql = "INSERT INTO tbl_food(title,price) VALUES(?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setInt(2, foodVO.getPrice());
        preparedStatement.executeUpdate();
    }

    public List<FoodVO> readFoodVO() throws SQLException {
        sql = "SELECT * FROM tbl_food";
        List<FoodVO> foodList = new ArrayList<>();
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            foodList.add(FoodVO.builder()
                    .fno(resultSet.getInt("fno"))
                    .title(resultSet.getString("title"))
                    .price(resultSet.getInt("price"))
                    .counter(resultSet.getInt("counter"))
                    .build());
        }
        return foodList;
    }

    public void updateFood(FoodVO foodVO) throws SQLException {
        sql = "UPDATE tbl_food SET title = ?, price = ?, counter = ? WHERE fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setInt(2, foodVO.getPrice());
        preparedStatement.setInt(3, foodVO.getCounter());
        preparedStatement.setInt(4, foodVO.getFno());
        preparedStatement.executeUpdate();
    }

    public void deleteFood(FoodVO foodVO) throws SQLException {
        sql = "DELETE FROM tbl_food WHERE fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, foodVO.getFno());
        preparedStatement.executeUpdate();
    }

    public List<FoodVO> selectByPrice(int price) throws SQLException {
        sql = "SELECT * FROM tbl_food WHERE price <= ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, price);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        List<FoodVO> foodList = new ArrayList<>();
        while (resultSet.next()) {
            foodList.add(FoodVO.builder()
                    .fno(resultSet.getInt("fno"))
                    .title(resultSet.getString("title"))
                    .price(resultSet.getInt("price"))
                    .counter(resultSet.getInt("counter"))
                    .build());
        }
        return foodList;
    }

    public FoodVO selectByFno(int fno) throws SQLException {
        sql = "SELECT * FROM tbl_food WHERE fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, fno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return FoodVO.builder()
                    .fno(resultSet.getInt("fno"))
                    .title(resultSet.getString("title"))
                    .price(resultSet.getInt("price"))
                    .counter(resultSet.getInt("counter"))
                    .build();
        }
        return null;
    }

    public int getFavorite() throws SQLException {
        List<FoodVO> foodVOList = this.readFoodVO();
        int favorite = 0;
        int favoriteFno = 0;
        for (FoodVO foodVO : foodVOList) {
            if (foodVO.getCounter() > favorite) {
                favorite = foodVO.getCounter();
                favoriteFno = foodVO.getFno();
            }
        }
        return favoriteFno;
    }
}
