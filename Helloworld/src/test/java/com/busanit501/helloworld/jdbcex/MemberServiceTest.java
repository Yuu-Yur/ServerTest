package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.DTO.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

@Log4j2
public class MemberServiceTest {
    // db에 아래 sql로 테이블만 만들면 테스트 가능
//    create table tbl_member(
//        mno int auto_increment primary key ,
//        id varchar(100) not null ,
//        pw varchar(100) not null ,
//        regdate date default current_date
//    );

    @Test
    public void test() throws SQLException {
        MemberDTO dto = MemberDTO.builder()
                .id("test")
                .pw("tpw")
                .build();
        // 등록
        MemberService.INSTANCE.register(dto);
        MemberService.INSTANCE.register(dto);
        // 등록된 정보 확인
        log.info("등록된 정보 확인"+MemberService.INSTANCE.lastAddedMember());
        // 조회
        List<MemberDTO> memberDTOList = MemberService.INSTANCE.getInfoList();
        // 조회된 정보 확인
        log.info("조회된 정보 확인"+memberDTOList);
        // 하나 조회
        MemberDTO memberDTO = MemberService.INSTANCE.getMyInfo(1);
        // 하나 조회 확인
        log.info("하나 조회 확인"+memberDTO);
        // 등록한것을 가져와서 수정
        memberDTO = MemberService.INSTANCE.lastAddedMember();
        memberDTO.setId("변경된 아이디");
        MemberService.INSTANCE.edit(memberDTO);
        // 수정된 정보 확인
        log.info("수정된 정보 확인"+MemberService.INSTANCE.lastAddedMember());
        // 삭제
        MemberService.INSTANCE.delete(memberDTO);
        // 삭제 확인
        if (MemberService.INSTANCE.lastAddedMember().equals(MemberService.INSTANCE.getMyInfo(1))) {
            log.info("서비스 테스트 성공");
        }
    }
}
