import com.busanit501.springbackend.dto.MovieDTO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.dto.PageResponseDTO;
import com.busanit501.springbackend.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class MovieServiceTest {
    @Autowired
    private MovieService movieService;

    @Test
    public void testNow() {
        PageRequestDTO pageRequestDTO = PageRequestDTO
                .builder()
                .page(1)
                .size(8)
                .pageSize(5)
                .released(false)
                .types(new String[]{"t"})
                .keyword("50")
                .build();
        PageResponseDTO<MovieDTO> result = movieService.getList(pageRequestDTO);
        log.info(result);
    }
}
