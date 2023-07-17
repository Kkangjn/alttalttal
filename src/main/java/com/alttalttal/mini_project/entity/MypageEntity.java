//// 끝
//package com.alttalttal.mini_project.entity;
//
//import com.alttalttal.mini_project.Dto.MypageRequestDto;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//
//@Entity
//@Getter
//@NoArgsConstructor
//@Table(name = "mypage_table")
//public class MypageEntity extends Time {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @Column(name = "nickname", nullable = false)
//    private String nickname;
//
//
//
//
//
//    public MypageEntity(MypageRequestDto mypageRequestDto, String username) {
//        this.nickname = mypageRequestDto.getNickname();
//    }
//
//
//
//
//
//    public void update(MypageRequestDto mypageRequestDto) {
//        this.nickname = mypageRequestDto.getNickname();
//    }
//}
