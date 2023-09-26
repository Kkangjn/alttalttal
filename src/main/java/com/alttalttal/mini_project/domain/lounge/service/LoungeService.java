package com.alttalttal.mini_project.domain.lounge.service;

import com.alttalttal.mini_project.domain.lounge.dto.LoungeRequestDto;
import com.alttalttal.mini_project.domain.lounge.dto.LoungeResponseDto;
import com.alttalttal.mini_project.domain.lounge.entity.LoungeEntity;
import com.alttalttal.mini_project.domain.user.entity.User;
import com.alttalttal.mini_project.global.jwt.JwtUtil;
import com.alttalttal.mini_project.domain.lounge.repository.LoungeRepository;
import com.alttalttal.mini_project.global.token.ServiceManagerImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alttalttal.mini_project.global.dto.MessageResponseDto;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LoungeService {
    private final LoungeRepository loungeRepository; // 생성자 주입
    private final JwtUtil jwtUtil;
    private final ServiceManagerImpl serviceManager;

    // 라운지 목록 조회
    // repository 에서 라운지 entity 를 꺼낸 다음, Dto 로 변환하고 변환된 객체를 list 에 담는다.
    public List<LoungeResponseDto> getfindAll() {
        return loungeRepository.findAll().stream().map(LoungeResponseDto::new).toList(); // findAll 을 사용해서 repository 에서 데이터를 가져올 때는 entity로 가져온다. // 가져온 entity를 Dto로 변환하고 List를 만들어서 반환
    }

    // repository 에 작성한 라운지를 저장한다.
    public ResponseEntity<MessageResponseDto> createLounge(LoungeRequestDto loungeRequestDto, String accessToken, String refreshToken, HttpServletResponse response) {
        if (!jwtUtil.validateAllToken(accessToken, refreshToken, response)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        User user = serviceManager.getUserFromToken(refreshToken);

        LoungeEntity loungeEntity = new LoungeEntity(loungeRequestDto, user);
        loungeRepository.save(loungeEntity);
        return new ResponseEntity<>(new MessageResponseDto("등록완료!" , HttpStatus.OK.toString()), HttpStatus.OK);
    }

    // 라운지 삭제
    public ResponseEntity<MessageResponseDto> deleteLounge(Long id, String accessToken, String refreshToken, HttpServletResponse response) {
        if (!jwtUtil.validateAllToken(accessToken, refreshToken, response)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        Long userId = serviceManager.getUserIdFromToken(refreshToken);

        LoungeEntity loungeEntity = loungeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 없습니다.")
        );

        if(loungeEntity.getUser().getId() != userId)
            throw new IllegalArgumentException("작성자가 아닙니다.");

        loungeRepository.delete(loungeEntity);
        return new ResponseEntity<>(new MessageResponseDto("삭제 완료!" , HttpStatus.OK.toString()), HttpStatus.OK);
    }

}

// 라운지 저장
// entity를 save 메서드로 넘겨준다.
// LoungeRepository 에 save 라는 메서드를 호출하게 되는데, 그래야 insert 를 db에 할 수 있다.
// save 라는 메서드는 기본적으로 매개변수를 entity 클래스 타입으로 받도록 되어있다.
// public abstract <S extends T> S save(     S entity )
//  Saves a given entity. Use the returned instance for further operations as the save operation might have changed the entity instance completely
// 받을 때도 entity 클래스 타입의 리턴, 반환할 때도 entity 클래스 타입의 리턴