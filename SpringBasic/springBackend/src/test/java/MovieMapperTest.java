import com.busanit501.springbackend.domain.MovieVO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.mapper.MovieMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class MovieMapperTest {

    @Autowired(required = false)
    private MovieMapper movieMapper;

    @Test
    public void selectPagetest() {
        PageRequestDTO pageRequestDTO = PageRequestDTO
                .builder()
                .page(1)
                .size(8)
                .pageSize(5)
                .released(false)
                .types(new String[]{"t"})
                .keyword("10")
                .build();
        List<MovieVO> result = movieMapper.selectPage(pageRequestDTO);
        log.info(result);
        int count = movieMapper.count(pageRequestDTO);
        log.info(count);
    }

    @Test
    public void selectPage() {
        MovieVO mvo= movieMapper.selectById(10L);
        log.info(mvo);
    }

    @Test
    public void insert() {
        Random random = new Random();
        IntStream.range(1, 501).forEach(i -> {
            movieMapper.insert(MovieVO.builder()
                    .title("영화 " + i)
                    .reservation(random.nextInt(100) + 1)
                    .releaseDate(LocalDate.now().minusYears(1).plusDays(random.nextInt(800)))
                    .build());
        });
    }
    @Test
    public void update() {
        MovieVO mvo = MovieVO.builder()
                .mid(1004L)
                .title("수정 확인")
                .reservation(400)
                .releaseDate(LocalDate.now().minusYears(2))
                .build();
        movieMapper.update(mvo);
    }
    @Test
    public void delete() {
        movieMapper.deleteById(1004L);
    }
}
