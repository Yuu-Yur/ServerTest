package service;

import com.busanit501.practice.controller.dto.FoodDTO;
import com.busanit501.practice.controller.dto.PageRequestDTO;
import com.busanit501.practice.controller.dto.PageResponseDTO;
import com.busanit501.practice.service.FoodService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class FoodServiceTest {
    @Autowired
    private FoodService foodService;

    @Test
    public void testLoad() {
        foodService.loadList();
        log.info(foodService.loadList());
    }

    @Test
    public void delete() {
        foodService.delete(36L);
    }

    @Test
    public void editTest() {
        foodService.edit(FoodDTO.builder()
                .fno(28L)
                .title("밀면")
                .price(7500)
                .counter(2)
                .build());
    }

    @Test
    public void pageTest() {
        PageRequestDTO pDTO = PageRequestDTO.builder()
                .page(19)
                .size(5)
                .pageSize(10)
                .build();
        log.info(pDTO);
        PageResponseDTO<FoodDTO> pageResponseDTO = foodService.getListWithPage(pDTO);
        log.info(pageResponseDTO);
    }
}
