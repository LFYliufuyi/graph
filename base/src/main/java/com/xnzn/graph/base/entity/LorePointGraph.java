package com.xnzn.graph.base.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class LorePointGraph {

    private Integer id;

    private Short subjectId;

    private String name;

    private String path;

    private Integer parentId;

    private Double accuracy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Point parentAbsoluteDelta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Point parentAbsoluteCorner;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Point delta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Point absoluteDelta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Point corner;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Point absoluteCorner;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer index;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer rotate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LorePointGraph> children = new ArrayList<>();



    public boolean getExpanded(){
        if (this.children.size()!=0) {
            return true;
        }
        return false;
    }

}