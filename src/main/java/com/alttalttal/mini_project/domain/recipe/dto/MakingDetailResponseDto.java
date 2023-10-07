package com.alttalttal.mini_project.domain.recipe.dto;

import com.alttalttal.mini_project.domain.recipe.entity.recipe_component.MakingDetail;
import lombok.Getter;

@Getter
public class MakingDetailResponseDto {
    private String making;
    public MakingDetailResponseDto(MakingDetail makingDetail){
        this.making = makingDetail.getMaking();
    }
}
