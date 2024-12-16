import com.busanit501.springbackend.domain.ReviewVO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.mapper.ReviewMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class ReviewMapperTest {
    @Autowired(required = false)
    private ReviewMapper reviewMapper;

    @Test
    public void test1() {
        reviewMapper.insert(ReviewVO.builder()
                .userName("테스트")
                .title("영화 1")
                .content("리뷰매퍼 삽입 테스트중")
                .build());
    }

    @Test
    public void test2() {
        reviewMapper.update(ReviewVO.builder()
                .rid(2L)
                .userName("테스트2")
                .title("영화 2")
                .content("리뷰매퍼 수정 테스트중")
                .build());
    }

    @Test
    public void test3() {
        reviewMapper.delete(2L);
    }

    @Test
    public void test4() {
        int result = reviewMapper.count(PageRequestDTO.builder().page(5).pageSize(10).build());
        log.info(result);
    }

    @Test
    public void test5() {
        List<ReviewVO> result = reviewMapper.select(PageRequestDTO.builder().page(5).pageSize(10).build());
        log.info(result);
    }
}
