package mapper;

import com.busanit501.practice.controller.dto.FoodDTO;
import com.busanit501.practice.controller.dto.FoodVO;
import com.busanit501.practice.controller.dto.PageResponseDTO;
import com.busanit501.practice.controller.dto.PageRequestDTO;
import com.busanit501.practice.mapper.FoodMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.IntStream;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class FoodMapperTest {
    @Autowired(required = false)
    private FoodMapper foodMapper;

    @Test
    public void testListFood() {
        List<FoodVO> foodVOList = foodMapper.selectAll();
        log.info(foodVOList);
    }

    @Test
    public void testFindFoodById() {
        FoodVO foodVO = foodMapper.selectByFno(1L);
        log.info(foodVO);
    }

    @Test
    public void testDeleteFood() {
        foodMapper.delete(35L);
    }

    @Test
    public void testUpdateFood() {
        foodMapper.update(
                FoodVO.builder()
                        .fno(28L)
                        .title("밀면")
                        .price(8000)
                        .counter(3)
                        .build()
        );
    }

    @Test
    public void testAddFood() {
        IntStream.range(1,100).forEach(i -> {
            foodMapper.insert(
                    FoodVO.builder()
                            .title("샘플" + i)
                            .price(i)
                            .build()
            );
        });
    }

    @Test
    public void testLoadPage() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .pageSize(10)
                .build();
        log.info(pageRequestDTO.getSkip());
        log.info(foodMapper.selectByPage(pageRequestDTO));

    }

    @Test
    public void testCounter() {
        log.info(foodMapper.selectCount(PageRequestDTO.builder().page(2).page(3).build()));
    }
}
