package com.busanit501.helloworld.jdbcex.service;


import com.busanit501.helloworld.food.util.MapperUtil;
import com.busanit501.helloworld.jdbcex.DAO.MemberDAO;
import com.busanit501.helloworld.jdbcex.DTO.MemberDTO;
import com.busanit501.helloworld.jdbcex.DTO.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO memberDAO;
    private ModelMapper modelMapper;

    MemberService() {
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(MemberDTO memberDTO) throws SQLException {
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);

        boolean check = memberDAO.insert(memberVO);
        if (check) log.info("서비스 등록 성공");
        else log.info("서비스 등록 실패");
    }

    public List<MemberDTO> getInfoList() throws SQLException {
        List<MemberDTO> memberDTOList = memberDAO
                .select().stream()
                .map(memberVO -> modelMapper.map(memberVO, MemberDTO.class))
                .collect(Collectors.toList());
        return memberDTOList;
    }

    public MemberDTO getMyInfo(int mno) throws SQLException {
        if (memberDAO.selectByMno(mno) != null) {
            MemberDTO memberDTO = modelMapper.map(memberDAO.selectByMno(mno), MemberDTO.class);
            return memberDTO;
        }
        return null;
    }

    public MemberDTO signIn(String id) throws SQLException {
        if (memberDAO.selectById(id) != null) {
            MemberDTO memberDTO = modelMapper.map(memberDAO.selectById(id), MemberDTO.class);
            return memberDTO;
        }
        return null;
    }

    public void edit(MemberDTO memberDTO) throws SQLException {
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        boolean check = memberDAO.update(memberVO);
        if (check) log.info("서비스 수정 성공");
        else log.info("서비스 수정 실패");
    }

    public void delete(MemberDTO memberDTO) throws SQLException {
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        boolean check = memberDAO.delete(memberVO);
        if (check) log.info("서비스 삭제 성공");
        else log.info("서비스 삭제 실패");
    }

    public MemberDTO lastAddedMember() throws SQLException {
        MemberDTO memberDTO = modelMapper.map(memberDAO.lastOne(), MemberDTO.class);
        return memberDTO;
    }

    public void updateUuid(String uuid, int mno) throws SQLException {
        memberDAO.updateUuid(uuid,mno);
    }

    public MemberDTO getAutoInfo(String uuid) throws SQLException {
        return modelMapper.map(memberDAO.selectByUuid(uuid), MemberDTO.class);
    }
}
