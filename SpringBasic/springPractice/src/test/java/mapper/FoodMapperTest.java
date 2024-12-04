package mapper;

import com.busanit501.practice.controller.dto.FoodVO;
import com.busanit501.practice.mapper.FoodMapper;
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
}
