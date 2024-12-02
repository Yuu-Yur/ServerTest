package mapper;

import com.busanit501.practice.mapper.TimeMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTest {
    //         인스턴스 없이도 주입이 가능하게 하는 옵션
    @Autowired(required = false)
    private TimeMapper timeMapper;

    @Test
    public void testGetTime() {
        log.info("getNow : " + timeMapper.getNow());
    }
}
