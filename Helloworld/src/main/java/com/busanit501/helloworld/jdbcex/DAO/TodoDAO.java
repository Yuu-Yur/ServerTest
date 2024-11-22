package com.busanit501.helloworld.jdbcex.DAO;

import com.busanit501.helloworld.jdbcex.DTO.TodoVO;
import lombok.Cleanup;

import java.sql.*;

public class TodoDAO {
    public String getTime(){
        String now = null;

        try (
                Connection conn = ConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select now()");
                ResultSet rs = pstmt.executeQuery()
                ){
            rs.next();
            now = rs.getString(1);
            System.out.println(now);
        } catch (Exception e){
            e.printStackTrace();
        }
        return now;
    }

    public void insert(TodoVO todoVO) throws SQLException {
        String sql = "insert into tbl_todo (title,dueDate,finished) values (?,?,?)" ;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3,todoVO.isFinished());
        preparedStatement.executeUpdate();
    }
}
