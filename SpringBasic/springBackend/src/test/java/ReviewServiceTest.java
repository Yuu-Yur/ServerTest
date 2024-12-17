import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.dto.PageResponseDTO;
import com.busanit501.springbackend.dto.ReviewDTO;
import com.busanit501.springbackend.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        PageResponseDTO<ReviewDTO> result = reviewService.getPage(PageRequestDTO.builder()
                .page(1)
                .size(5)
                .types(new String[]{"u"})
                .keyword("vic")
                .build()
        );
        log.info(result);
    }
}
