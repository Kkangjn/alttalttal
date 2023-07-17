package com.alttalttal.mini_project.repository;

import com.alttalttal.mini_project.entity.LoungeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoungeRepository extends JpaRepository<LoungeEntity, Long> {
// LoungeRepository는 JpaRepository를 상속받는다. 상속받을 때 두 개의 인자(entity 클래스의 이름과 entity 클래스가 가지고 있는 pk의 타입을 적어준다.)를 < >안에 적어준다.
// 이 repository는 기본적으로 entity 클래스만 받아준다.
}