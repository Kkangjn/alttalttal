package com.alttalttal.mini_project.dto;


import com.alttalttal.mini_project.entity.Timestamped;
import com.alttalttal.mini_project.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DB의 테이블 역할을 하는 클래스. spring data jpa 에서 필수로 사용. db와 직접적인 연관.
@Entity
@Getter
@Table(name = "lounge_table")
@NoArgsConstructor
public class LoungeEntity extends Timestamped { // LoungeEntity 가 TimeEntity 를 상속받는다.
    @Id // pk 컬럼을 지정한다. 필수.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mySQL 기준으로 auto.increment 를 할 수 있다.
    private Long id;

    @Column(nullable = false)
    private String base;

    @Column(nullable = false) // 기본 Colunm이라서 default 255, 내용 빈칸 불허
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LoungeEntity(LoungeRequestDto loungeRequestDto, User user) {
        this.base = loungeRequestDto.getBase();
        this.content = loungeRequestDto.getContent();
        this.user = user;
    }


    private void setContent(String content) {
    }

    private void setBase(String base) {
    }
}






