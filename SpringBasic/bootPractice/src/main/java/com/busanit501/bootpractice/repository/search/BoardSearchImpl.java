package com.busanit501.bootpractice.repository.search;

import com.busanit501.bootpractice.domain.Board;
import com.busanit501.bootpractice.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

// interface 이름 + impl 은 고정 QRS 상속 , 당연히 BoardSearch 구현
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    // superClass 생성자는 class 를 parameter 로 가지기에 이 class 의 기본 생성자도 class parameter 필요
    // 사용하는 Board class
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search(Pageable pageable) {
        QBoard board = QBoard.board; // Q 도메인의 필드로 들어가 있는 (자동) 엔티티 클래스를 가져옴
        JPQLQuery<Board> query = from(board); // select * from board 의 결과와 같음
        // 즉 해당 DB 의 instance 전체를 가져와서 JPQLQuery 라는 class 형태로 할당함
        // JPQLQuery 는 다양한 쿼리 조건이 내장되어 있는 클래스
        // ex) where, groupby, join, pagination

        // 예를들어 where 을 쓰면 query 가 해당 조건으로 설정됨
        query.where(board.title.contains("샘"));
        // 제목, 작성자 검색 조건 추가 같이 여러 조건을 쓸 땐 BooleanBuilder 이용
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(board.title.contains("샘플"));
        booleanBuilder.or(board.content.contains("7"));
        query.where(booleanBuilder);
        // 페이징 조건 적용
        this.getQuerydsl().applyPagination(pageable, query);
        // 설정된 조건의 query 를 담음 (DB 즉 entity class 의 instance 를 담음)
        List<Board> boards = query.fetch();
        // 설정된 query 의 수를 담음
        Long count = query.fetchCount();
        return null;
    }
    // types 가 "t" "c" "tc" 일때
    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c" :
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "a" :
                        booleanBuilder.or(board.author.contains(keyword));
                }
            }
            query.where(booleanBuilder);
        }
        query.where(board.bno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();
        Long count = query.fetchCount();
        Page<Board> result = new PageImpl<Board>(list, pageable, count);
        return result;
    }
}
