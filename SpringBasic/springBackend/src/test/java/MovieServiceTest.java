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

import java.time.LocalDate;


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
        PageResponseDTO<MovieDTO> result = movieService.getPage(pageRequestDTO);
        log.info(result);
    }

    @Test
    public void testGetOneMovie() {
        MovieDTO result = movieService.getOne(1L);
        log.info(result);
    }

    @Test
    public void testUpdate() {
        movieService.update(MovieDTO.builder()
                .mid(50L)
                .title("service update test")
                .reservation(80)
                .releaseDate(LocalDate.now())
                .build());
    }

    @Test
    public void testDelete() {
        movieService.delete(50L);
    }

    @Test
    public void testRegister() {
        movieService.register(MovieDTO.builder()
                .title("service reg test")
                .reservation(99)
                .releaseDate(LocalDate.now())
                .build());
    }
}
