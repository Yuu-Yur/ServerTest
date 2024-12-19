import com.busanit501.springbackend.domain.ReviewVO;
import com.busanit501.springbackend.mapper.ReviewMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.IntStream;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class ReviewMapperTest {
    @Autowired(required = false)
    private ReviewMapper reviewMapper;

    @Test
    public void test1() {
        IntStream.range(1, 31).forEach(i -> {
            reviewMapper.insert(ReviewVO.builder()
                    .userName("테스트")
                    .title("The Future Begins")
                    .content("리뷰매퍼 삽입 테스트중" + i)
                    .build());
        });
    }

    @Test
    public void test2() {
        reviewMapper.update(ReviewVO.builder()
                .rid(1L)
                .userName("테스트2")
                .title("영화 1")
                .content("리뷰매퍼 수정 테스트중")
                .build());
    }

    @Test
    public void test3() {
        reviewMapper.delete(30L);
    }

    @Test
    public void test5() {
        List<ReviewVO> result = reviewMapper.select("영화 1");
        if (result.size() == 0) {log.info("List 없음");}
        log.info(result);
    }
}
