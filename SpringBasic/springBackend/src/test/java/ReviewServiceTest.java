import com.busanit501.springbackend.dto.ReviewDTO;
import com.busanit501.springbackend.service.ReviewService;
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
public class ReviewServiceTest {
    @Autowired
    private ReviewService reviewService;

    @Test
    public void test1() {
        reviewService.register(ReviewDTO.builder()
                .title("Frostbite")
                .content("Service reg content")
                .userName("Service reg user")
                .build());
    }

    @Test
    public void test2() {
        reviewService.delete(3L);
    }

    @Test
    public void test3() {
        reviewService.update(
                ReviewDTO.builder()
                        .rid(20L)
                        .title("In the Shadows")
                        .content("Service upd content")
                        .userName("Service upd user")
                        .build()
        );
    }

    @Test
    public void test4() {
        List<ReviewDTO> result = reviewService.getReview("영화 1");
        log.info(result);
    }
}
