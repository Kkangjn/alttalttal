package com.alttalttal.mini_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Zzim")
@Getter
@NoArgsConstructor
public class Zzim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    @Column
    private Long userId;

    public Zzim(Recipe recipe, Long userId) {
        this.recipe = recipe;
        this.userId = userId;
    }
}
