package com.xnzn.graph.base.mapper;

import com.xnzn.graph.base.entity.LorePointGraph;
import com.xnzn.graph.base.entity.MyLore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LorePointGraphMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LorePointGraph entity);

    List<LorePointGraph> selectBySubjectId(Integer subjectId);

    int updateByPrimaryKey(LorePointGraph entity);

    String selectLastPathBySubjectId(LorePointGraph entity);

    LorePointGraph selectById(Integer id);

    List<LorePointGraph> selectTiledBySubjectId(Integer subjectId);

    List<LorePointGraph> selectByPath(String path);

    void updatePointGraphList(@Param("list")List<LorePointGraph> lorePointGraphList, @Param("x")Integer x,@Param("y")Integer y);

    List<MyLore> queryTestAccuary(@Param("subjectId")Integer subjectId,@Param("studentId") Integer studentId);

    List<MyLore> queryTestAccuaryForList(@Param("list")List<MyLore> lores);
}