package com.alttalttal.mini_project.entity;

import com.alttalttal.mini_project.Dto.LoungeRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DB의 테이블 역할을 하는 클래스. spring data jpa 에서 필수로 사용. db와 직접적인 연관.
@Entity
@Getter
@Table(name = "lounge_table")
@NoArgsConstructor
public class LoungeEntity extends Time { // LoungeEntity 가 TimeEntity 를 상속받는다.
    @Id // pk 컬럼을 지정한다. 필수.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mySQL 기준으로 auto.increment 를 할 수 있다.
    private Long id;

    @Column(nullable = false)
    private String base;

    @Column(nullable = false) // 기본 Colunm이라서 default 255, 내용 빈칸 불허
    private String content;

    public LoungeEntity(LoungeRequestDto loungeRequestDto) {
        this.base = loungeRequestDto.getBase();
        this.content = loungeRequestDto.getContent();
    }


    private void setContent(String content) {
    }

    private void setBase(String base) {
    }
}



//@Entity
//@Builder
//@Getter
//@Table(name = "comments")
//@NoArgsConstructor
//@AllArgsConstructor // 자동으로 생성된다
//lounges extends Time{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // columnDefinition 컬럼을 text로 설정해 데이터 추출
//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String lounge; // 댓글 내용
//
//    @ManyToOne
//    @JoinColumn(name = "lounge_id")
//    private Lounge lounge;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    public lounges(LoungeRequestDto loungeRequestDto, Lounge lounge, User user) {
//        this.lounge = loungeRequestDto.getlounge();
//        this.lounge = lounge;
//        this.user = user;
//    }
//
//    // 댓글 수정 메서드
//    public void update(LoungeRequestDto requestDto){
//        this.lounge =requestDto.getlounge();
//    }
//}




