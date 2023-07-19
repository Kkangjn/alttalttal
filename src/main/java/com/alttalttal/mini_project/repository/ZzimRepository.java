//package com.alttalttal.mini_project.repository;
//
//import com.alttalttal.mini_project.entity.Zzim;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.Optional;
//
//public interface ZzimRepository extends JpaRepository<Zzim, Long> {
//    Boolean existsByRecipeIdAndUserId(Long id, Long userId);
//
//    Optional<Zzim> findByRecipeIdAndUserId(Long id, Long userId);
//    Integer countByRecipeId(Long recipeId);
//}
