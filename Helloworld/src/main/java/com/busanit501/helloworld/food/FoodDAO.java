package com.busanit501.helloworld.food;


import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.jdbcex.DAO.ConnectionUtil;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
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
        int i = preparedStatement.executeUpdate();
        if (i > 0) log.info("수정 성공");
        else log.info("수정 실패");
    }

    public void deleteFood(FoodVO foodVO) throws SQLException {
        sql = "DELETE FROM tbl_food WHERE fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, foodVO.getFno());
        int i = preparedStatement.executeUpdate();
        if (i > 0) log.info("삭제 성공");
        else log.info("삭제 실패");
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

    public FoodVO lastOne() throws SQLException {
        sql = "SELECT * FROM tbl_food ORDER BY fno DESC LIMIT 1";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            FoodVO foodVO = FoodVO.builder()
                    .fno(resultSet.getInt("fno"))
                    .title(resultSet.getString("title"))
                    .price(resultSet.getInt("price"))
                    .counter(resultSet.getInt("counter"))
                    .build();
            return foodVO;
        }
        return null;
    }
}
