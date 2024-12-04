package com.busanit501.practice.service;

import com.busanit501.practice.controller.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
    List<TodoDTO> getAll();
    TodoDTO getOne(Long tno);
    void delete(Long tno);
}
