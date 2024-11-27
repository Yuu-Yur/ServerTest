package com.busanit501.helloworld.food.service;

import com.busanit501.helloworld.food.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.food.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum FoodService {
    INSTANCE;

    // DAO 와 Mapper 를 사용
    private FoodDAO foodDAO;
    private ModelMapper modelMapper;

    // 초기화
    FoodService() {
        modelMapper = MapperUtil.INSTANCE.get();
        foodDAO = new FoodDAO();
    }

    // CRUD 설정(DAO)에서 가져옴 서블릿에선 DTO 로 받음, 서버랑 통신할 땐 VO
    public void register(FoodDTO foodDTO) throws SQLException {
        // DAO 작업할 때, 디비에 직접적인 영향을 주는 객체,
        // VO, 실제 비지니스 로직에서만 사용함.
        // 서블릿 > DTO 전달 받고, -> DAO 한테 전달할 때, 다시, VO 변환해야함.
        // 변환 하는 도구, 모델 매퍼
        // 도구를 사용안하면, 코드가 길어짐
        //              모델 매퍼.맵(받은 객체, 변환할 객체 클래스명.클래스)
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);

        foodDAO.insertFood(foodVO);
    }

    public List<FoodDTO> getAllFood() throws SQLException {
        List<FoodDTO> foodDTOList = foodDAO
                .readFoodVO().stream()
                .map(foodVO -> modelMapper.map(foodVO, FoodDTO.class))
                .collect(Collectors.toList());
        return foodDTOList;
    }

    public FoodDTO getFoodByFno(int fno) throws SQLException {
        if (foodDAO.selectByFno(fno) != null) {
            FoodDTO foodDTO = modelMapper.map(foodDAO.selectByFno(fno), FoodDTO.class);
            return foodDTO;
        }
        return null;
    }

    public void updateFood(FoodDTO foodDTO) throws SQLException {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodDAO.updateFood(foodVO);
    }

    public void deleteFood(FoodDTO foodDTO) throws SQLException {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodDAO.deleteFood(foodVO);
    }

    public FoodDTO lastAddedFood() throws SQLException {
        FoodDTO foodDTO = modelMapper.map(foodDAO.lastOne(), FoodDTO.class);
        return foodDTO;
    }
}
