package com.xnzn.graph.base.service;

import com.xnzn.graph.base.entity.LorePointGraph;
import com.xnzn.graph.base.entity.MyLore;

import java.util.List;
import java.util.Map;

/**
 * @Auther: xn
 * @Date: 2019/9/29 11:22
 */
public interface LorePointGraphService {
    int deleteByPrimaryKey(Integer id);

    int insert(LorePointGraph entity);

    List<LorePointGraph> selectBySubjectId(Integer subjectId);

    int updateByPrimaryKey(LorePointGraph entity);

    LorePointGraph selectById(Integer id);

    List<LorePointGraph> selectTiledBySubjectId(Integer subjectId);

    Map<Integer, MyLore> test(Integer subjectId, Integer studentId);
}
