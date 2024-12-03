package com.busanit501.practice.mapper;

import com.busanit501.practice.domain.TodoVO;

public interface TodoMapper {
    String getTime();
    int insert(TodoVO todoVO);
}
