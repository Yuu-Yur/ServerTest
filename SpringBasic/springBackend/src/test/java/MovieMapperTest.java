import com.busanit501.springbackend.domain.MovieVO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.mapper.MovieMapper;
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
public class MovieMapperTest {

    @Autowired(required = false)
    private MovieMapper movieMapper;

    @Test
    public void selectPageNowtest() {
        PageRequestDTO pageRequestDTO = PageRequestDTO
                .builder()
                .page(1)
                .size(8)
                .pageSize(5)
                .build();
        List<MovieVO> result = movieMapper.selectPageNow(pageRequestDTO);
        log.info(result);
        List<MovieVO> result2 = movieMapper.selectPageFuture(pageRequestDTO);
        log.info(result2);
        int count = movieMapper.count(pageRequestDTO);
        log.info(count);
    }

}
