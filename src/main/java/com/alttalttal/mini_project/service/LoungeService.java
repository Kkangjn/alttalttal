package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.LoungeRequestDto;
import com.alttalttal.mini_project.dto.LoungeResponseDto;
import com.alttalttal.mini_project.entity.LoungeEntity;
import com.alttalttal.mini_project.entity.User;
import com.alttalttal.mini_project.jwt.JwtUtil;
import com.alttalttal.mini_project.repository.LoungeRepository;
import com.alttalttal.mini_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LoungeService {
    private final LoungeRepository loungeRepository; // 생성자 주입
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public LoungeService(LoungeRepository loungeRepository, JwtUtil jwtUtil, UserRepository userRepository){
        this.loungeRepository = loungeRepository;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }


    // repository 에 작성한 라운지를 저장한다.
    public void createLounge(LoungeRequestDto loungeRequestDto, HttpServletRequest request, HttpServletResponse response) {
        if (!jwtUtil.validateAllToken(request, response)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        String refresh = jwtUtil.substringToken(jwtUtil.getTokenFromRequest("Refresh", request));
        String email = jwtUtil.getUserInfoFromToken(refresh).getSubject();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("유저가 없습니다.")
        );
        LoungeEntity loungeEntity = new LoungeEntity(loungeRequestDto, user);
//        loungeEntity.toSaveEntity(loungeRequestDto);
        loungeRepository.save(loungeEntity);
    }

    // 라운지 목록 조회
    // repository 에서 라운지 entity 를 꺼낸 다음, Dto 로 변환하고 변환된 객체를 list 에 담는다.
    public List<LoungeResponseDto> getfindAll() {
        List<LoungeEntity> loungeEntityList = loungeRepository.findAll(); // findAll 을 사용해서 repository 에서 데이터를 가져올 때는 entity로 가져온다.
        List<LoungeResponseDto> loungeResponseDtoList = new ArrayList<>(); // 리턴할 객체를 선언한다.
        for(LoungeEntity loungeEntity: loungeEntityList) { // LoungeEntityList 에 담긴 것을 LoungeRequestDtoList에 옮겨 담는다. 반복문을 돌린다.
            loungeResponseDtoList.add(new LoungeResponseDto(loungeEntity));
        }
        return loungeResponseDtoList;
    }

    // 라운지 삭제
    public void deleteLounge(Long id, HttpServletRequest request, HttpServletResponse response) {
        if (!jwtUtil.validateAllToken(request, response)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        String refresh = jwtUtil.substringToken(jwtUtil.getTokenFromRequest("Refresh", request));
        String email = jwtUtil.getUserInfoFromToken(refresh).getSubject();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("유저가 없습니다.")
        );

        LoungeEntity loungeEntity = loungeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 없습니다.")
        );
        if(loungeEntity.getUser().getId() != id)
            throw new IllegalArgumentException("작성자가 아닙니다.");

        loungeRepository.delete(loungeEntity);
    }

}

// 라운지 저장
// entity를 save 메서드로 넘겨준다.
// LoungeRepository 에 save 라는 메서드를 호출하게 되는데, 그래야 insert 를 db에 할 수 있다.
// save 라는 메서드는 기본적으로 매개변수를 entity 클래스 타입으로 받도록 되어있다.
// public abstract <S extends T> S save(     S entity )
//  Saves a given entity. Use the returned instance for further operations as the save operation might have changed the entity instance completely
// 받을 때도 entity 클래스 타입의 리턴, 반환할 때도 entity 클래스 타입의 리턴