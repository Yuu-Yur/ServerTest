package com.busanit501.practice.mapper;

import com.busanit501.practice.controller.dto.PageDTO;
import com.busanit501.practice.domain.TodoVO;

import java.util.List;

public interface TodoMapper {
    String getTime();
    int insert(TodoVO todoVO);
    List<TodoVO> selectAll();
    TodoVO selectOne(Long tno);
    void delete(Long tno);
    List<TodoVO> selectPage(PageDTO pageDTO);
    int count(PageDTO pageDTO);
}
