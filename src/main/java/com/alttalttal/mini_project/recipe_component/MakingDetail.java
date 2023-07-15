package com.alttalttal.mini_project.recipe_component;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class MakingDetail {
    private String making;
}
