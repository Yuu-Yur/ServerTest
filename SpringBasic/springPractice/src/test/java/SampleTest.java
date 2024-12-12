import com.busanit501.practice.sample.SampleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTest {
//  시스템에 등록된 빈을 연결
    @Autowired
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testService1() {
        log.info("testService1 : " + sampleService);
//         sampleService 는 SampleService()
//         그런데 SampleService 에 필드주입으로 SampleDAO를 주입하면
//         sampleService 는 SampleService(sampleDAO=com.busanit501.practice.sample.SampleDAO@55cff952)
//         'DAO까지 같이 호출 할 수 있다'
        Assertions.assertNotNull(sampleService);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        log.info("testConnection : " + conn);
        Assertions.assertNotNull(conn);
        conn.close();
    }
}
