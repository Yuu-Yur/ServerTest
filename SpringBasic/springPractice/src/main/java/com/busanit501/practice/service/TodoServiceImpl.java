package com.busanit501.practice.service;

import com.busanit501.practice.controller.dto.TodoDTO;
import com.busanit501.practice.domain.TodoVO;
import com.busanit501.practice.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.insert(todoVO);
    }

    @Override
    public List<TodoDTO> getAll() {
        return todoMapper.selectAll().stream().map(vo -> modelMapper.map(vo , TodoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDTO getOne(Long tno) {
        return modelMapper.map(todoMapper.selectOne(tno),TodoDTO.class);
    }

    @Override
    public void delete(Long tno) {
        todoMapper.delete(tno);
    }

}
