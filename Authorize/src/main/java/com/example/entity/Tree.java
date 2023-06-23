package com.example.entity;

import lombok.Data;

import java.util.List;

@Data
public class Tree {
    private Integer id;
    private Integer pId;
    private String title;
    private List<Tree> children;
    private Boolean spread;
}
