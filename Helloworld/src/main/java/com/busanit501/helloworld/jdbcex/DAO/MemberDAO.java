package com.busanit501.helloworld.jdbcex.DAO;

import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.jdbcex.DTO.MemberVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MemberDAO {
    String sql;

    public boolean insert(MemberVO memberVO) throws SQLException {
        sql = "INSERT INTO tbl_member(id,pw) VALUES(?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getId());
        preparedStatement.setString(2, memberVO.getPw());
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            log.info("등록 성공");
            return true;
        } else {
            log.info("등록 실패");
            return false;
        }
    }

    public List<MemberVO> select() throws SQLException {
        sql = "SELECT * FROM tbl_member";
        List<MemberVO> memberList = new ArrayList<>();
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            memberList.add(MemberVO.builder()
                    .mno(resultSet.getInt("mno"))
                    .id(resultSet.getString("id"))
                    .pw(resultSet.getString("pw"))
                    .regdate(resultSet.getDate("regdate"))
                    .build());
        }
        return memberList;
    }

    public boolean update(MemberVO memberVO) throws SQLException {
        sql = "UPDATE tbl_member SET id = ?, pw = ? WHERE mno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getId());
        preparedStatement.setString(2, memberVO.getPw());
        preparedStatement.setInt(3, memberVO.getMno());
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            log.info("수정 성공");
            return true;
        } else {
            log.info("수정 실패");
            return false;
        }
    }

    public boolean delete(MemberVO memberVO) throws SQLException {
        sql = "DELETE FROM tbl_member WHERE mno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, memberVO.getMno());
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            log.info("삭제 성공");
            return true;
        } else {
            log.info("삭제 실패");
            return false;
        }
    }

    public MemberVO selectByMno(int mno) throws SQLException {
        sql = "SELECT * FROM tbl_member WHERE mno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, mno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return MemberVO.builder()
                    .mno(resultSet.getInt("mno"))
                    .id(resultSet.getString("id"))
                    .pw(resultSet.getString("pw"))
                    .regdate(resultSet.getDate("regdate"))
                    .build();
        }
        return null;
    }

    public MemberVO lastOne() throws SQLException {
        sql = "SELECT * FROM tbl_member ORDER BY mno DESC LIMIT 1";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            MemberVO memberVO = MemberVO.builder()
                    .mno(resultSet.getInt("mno"))
                    .id(resultSet.getString("id"))
                    .pw(resultSet.getString("pw"))
                    .regdate(resultSet.getDate("regdate"))
                    .build();
            return memberVO;
        }
        return null;
    }
}
