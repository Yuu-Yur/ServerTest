package service;

import com.busanit501.practice.controller.dto.TodoDTO;
import com.busanit501.practice.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTest {
    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("serviceTest")
                .dueDate(LocalDate.now())
                .finished(false)
                .writer("하청빈").build();
        todoService.register(todoDTO);
        log.info(todoDTO);
    }

    @Test
    public void testGetAll() {
        List<TodoDTO> todoDTOList = todoService.getAll();
        for (TodoDTO todoDTO : todoDTOList) {
            log.info(todoDTO);
        }
    }

    @Test
    public void testGetOne() {
        TodoDTO  todoDTO= todoService.getOne(2L);
        log.info(todoDTO);
    }

    @Test
    public void testDelete() {
        todoService.delete(3L);
    }
}
