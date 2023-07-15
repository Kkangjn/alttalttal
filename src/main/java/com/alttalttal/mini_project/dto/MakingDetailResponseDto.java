package com.alttalttal.mini_project.dto;

import com.alttalttal.mini_project.recipe_component.MakingDetail;
import lombok.Getter;

@Getter
public class MakingDetailResponseDto {
    private String making;
    public MakingDetailResponseDto(MakingDetail makingDetail){
        this.making = makingDetail.getMaking();
    }
}
