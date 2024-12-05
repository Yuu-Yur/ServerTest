package mapper;

import com.busanit501.practice.controller.dto.PageRequestDTO;
import com.busanit501.practice.domain.TodoVO;
import com.busanit501.practice.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTest {
    //         인스턴스 없이도 주입이 가능하게 하는 옵션
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info("getTime : " + todoMapper.getTime());
    }

    @Test
    public void testSelectAll() {
        List<TodoVO> todoVOList = todoMapper.selectAll();
        log.info(todoVOList);
    }

    @Test
    public void testSelectOne() {
        TodoVO todoVO = todoMapper.selectOne(2L);
        log.info(todoVO);
    }

    @Test
    public void testDelete() {
        todoMapper.delete(2L);
    }

    @Test
    public void testPage() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(15)
                .build();
        List<TodoVO> todoVOList = todoMapper.selectPage(pageRequestDTO);
        log.info(todoVOList);
        int total = todoMapper.count(pageRequestDTO);
        log.info(total);
    }


}
