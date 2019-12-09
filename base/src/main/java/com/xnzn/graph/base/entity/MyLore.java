package com.xnzn.graph.base.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: xn
 * @Date: 2019/10/31 17:21
 */
@Setter
@Getter
public class MyLore {
    private Integer id;

    private Short subjectId;

    private String name;

    private String path;

    private Integer parentId;

    private Integer isCustom;

    private Integer trueCount;

    private Integer total;

    private Integer avgAccuracy = 0;


}
