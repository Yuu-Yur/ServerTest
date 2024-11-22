package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.DAO.TodoDAO;
import com.busanit501.helloworld.jdbcex.DTO.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

public class TodoDAOTest {
    private TodoDAO todoDAO;

    // BeforeEach는 각 단위테스트(method) 실행 전 먼저 실행함
    @BeforeEach
    public void setUp() {
        todoDAO = new TodoDAO();
    }

    @Test
    public void todoDAOTest() {
        todoDAO.getTime();
    }

    @Test
    public void insertTest() throws SQLException {
//        TodoVO todoVO = new TodoVO();
        //todoVO.setTitle = "제목" 뭐 이런식으로 하나하나 설정할 수도 있지만
        TodoVO todoVO1 = TodoVO.builder()
                .title("디비 샘플 생성 테스트")
                .dueDate(LocalDate.of(2024, 12, 31))
                .finished(false)
                .build();
        todoDAO.insert(todoVO1);
    }
}
