package com.xnzn.graph.base.service.impl;

import com.xnzn.graph.base.entity.LorePointGraph;
import com.xnzn.graph.base.entity.MyLore;
import com.xnzn.graph.base.entity.Point;
import com.xnzn.graph.base.mapper.LorePointGraphMapper;
import com.xnzn.graph.base.service.LorePointGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: xn
 * @Date: 2019/9/29 11:23
 */
@Service
public class LorePointGraphServiceImpl implements LorePointGraphService {

    @Autowired
    private LorePointGraphMapper lorePointGraphMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return lorePointGraphMapper.deleteByPrimaryKey(id);
    }

    @Override
    public LorePointGraph selectById(Integer id) {
        return lorePointGraphMapper.selectById(id);
    }

    @Override
    public List<LorePointGraph> selectTiledBySubjectId(Integer subjectId) {
        return lorePointGraphMapper.selectTiledBySubjectId(subjectId);
    }

    @Override
    public int insert(LorePointGraph entity) {
        if (entity.getPath() == null) {
            String path = lorePointGraphMapper.selectLastPathBySubjectId(entity);
            if (path != null) {
                int i = Integer.parseInt(path);
                entity.setPath(i + 1 + "");
            } else {
                entity.setPath("0");
            }
        }

        return lorePointGraphMapper.insert(entity);
    }

    @Override
    public List<LorePointGraph> selectBySubjectId(Integer subjectId) {
        List<LorePointGraph> lorePointGraphs = lorePointGraphMapper.selectBySubjectId(subjectId);
        ArrayList<LorePointGraph> results = new ArrayList<>();
        List<LorePointGraph> children = new ArrayList<>();
        List<LorePointGraph> children1 = new ArrayList<>();
        List<LorePointGraph> children2 = new ArrayList<>();
        List<LorePointGraph> children3 = new ArrayList<>();
        List<LorePointGraph> children4 = new ArrayList<>();
        List<LorePointGraph> children5 = new ArrayList<>();
        for (LorePointGraph lorePointGraph : lorePointGraphs) {
            if (lorePointGraph.getPath().length() == 1) {
                results.add(lorePointGraph);
            } else if (lorePointGraph.getPath().length() == 3) {
                results.get(results.size() - 1).getChildren().add(lorePointGraph);
            } else if (lorePointGraph.getPath().length() == 5) {
                children = results.get(results.size() - 1).getChildren();
                results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().add(lorePointGraph);
            } else if (lorePointGraph.getPath().length() == 7) {
                children1 = results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren();
                results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().get(children1.size() - 1).getChildren().add(lorePointGraph);
            } else if (lorePointGraph.getPath().length() == 9) {
                children2 = results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().get(children1.size() - 1).getChildren();
                results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().get(children1.size() - 1)
                        .getChildren().get(children2.size() - 1).getChildren().add(lorePointGraph);
            } else if (lorePointGraph.getPath().length() == 11) {
                children3 = results.get(results.size() - 1).getChildren().get(children.size() - 1)
                        .getChildren().get(children1.size() - 1).getChildren().get(children2.size() - 1).getChildren();
                results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().get(children1.size() - 1)
                        .getChildren().get(children2.size() - 1).getChildren().get(children3.size() - 1).getChildren().add(lorePointGraph);
            } else if (lorePointGraph.getPath().length() == 13) {
                children4 = results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().get(children1.size() - 1)
                        .getChildren().get(children2.size() - 1).getChildren().get(children3.size() - 1).getChildren();
                results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().get(children1.size() - 1)
                        .getChildren().get(children2.size() - 1).getChildren().get(children3.size() - 1).getChildren().
                        get(children4.size() - 1).getChildren().add(lorePointGraph);
            } else if (lorePointGraph.getPath().length() == 15) {
                children5 = results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().get(children1.size() - 1)
                        .getChildren().get(children2.size() - 1).getChildren().get(children3.size() - 1).getChildren().
                                get(children4.size() - 1).getChildren();
                results.get(results.size() - 1).getChildren().get(children.size() - 1).getChildren().get(children1.size() - 1)
                        .getChildren().get(children2.size() - 1).getChildren().get(children3.size() - 1).getChildren().
                        get(children4.size() - 1).getChildren().get(children5.size() - 1).getChildren().add(lorePointGraph);
            }
        }

        return results;
    }

    private void doDEMO() {

    }

    @Override
    public int updateByPrimaryKey(LorePointGraph entity) {
        LorePointGraph lorePointGraph = lorePointGraphMapper.selectById(entity.getId());
        if (lorePointGraph.getPath().length() == 1 || lorePointGraph.getPath().length() == 2) {
            entity.setAbsoluteDelta(entity.getDelta());
            entity.setAbsoluteCorner(entity.getCorner());
            lorePointGraphMapper.updateByPrimaryKey(entity);
        } else {
            Point absoluteDelta = new Point();
            Point absoluteCorner = new Point();
            absoluteDelta.setX(entity.getDelta().getX() + lorePointGraph.getParentAbsoluteDelta().getX());
            absoluteDelta.setY(entity.getDelta().getY() + lorePointGraph.getParentAbsoluteDelta().getY());
            absoluteCorner.setX(entity.getCorner().getX() + lorePointGraph.getParentAbsoluteDelta().getX());
            absoluteCorner.setY(entity.getCorner().getY() + lorePointGraph.getParentAbsoluteDelta().getY());
            entity.setAbsoluteDelta(absoluteDelta);
            entity.setAbsoluteCorner(absoluteCorner);
            lorePointGraphMapper.updateByPrimaryKey(entity);
        }
        String path = lorePointGraph.getPath();
        List<LorePointGraph> lorePointGraphList = lorePointGraphMapper.selectByPath(path);
        if (lorePointGraphList != null && lorePointGraphList.size() != 0) {
            for (LorePointGraph pointGraph : lorePointGraphList) {
                Point absoluteDelta = new Point();
                Point absoluteCorner = new Point();
                absoluteDelta.setX(pointGraph.getAbsoluteDelta().getX() + entity.getDelta().getX() - lorePointGraph.getDelta().getX());
                absoluteDelta.setY(pointGraph.getAbsoluteDelta().getY() + entity.getDelta().getY() - lorePointGraph.getDelta().getY());
                absoluteCorner.setX(pointGraph.getAbsoluteCorner().getX() + entity.getDelta().getX() - lorePointGraph.getDelta().getX());
                absoluteCorner.setY(pointGraph.getAbsoluteCorner().getY() + entity.getDelta().getY() - lorePointGraph.getDelta().getY());
                pointGraph.setAbsoluteDelta(absoluteDelta);
                pointGraph.setAbsoluteCorner(absoluteCorner);
                lorePointGraphMapper.updateByPrimaryKey(pointGraph);
            }
        }
//        lorePointGraphMapper.updatePointGraphList(lorePointGraphList,lorePointGraph.getParentAbsoluteDelta().getX(),lorePointGraph.getParentAbsoluteDelta().getY());
        return 1;
    }

    @Override
    public  Map<Integer, MyLore> test(Integer subjectId, Integer studentId) {
        List<MyLore> lores = lorePointGraphMapper.queryTestAccuary(subjectId, studentId);
        List<MyLore> loreLores = lorePointGraphMapper.queryTestAccuaryForList(lores);
        lores.addAll(loreLores);
        List<MyLore> loreLores1 = new ArrayList<>();

        while (loreLores1 != null) {
            loreLores1 = lorePointGraphMapper.queryTestAccuaryForList(loreLores);
            if (loreLores1.size() != 0) {
                lores.addAll(loreLores1);
                loreLores = loreLores1;
            } else {
                loreLores1 = null;
            }
        }
        Map<Integer, MyLore> map = new HashMap();
        for (MyLore lore : lores) {
            Integer key = lore.getId();
            if (map.containsKey(key)) {
                MyLore myLore = map.get(key);
                myLore.setAvgAccuracy(myLore.getAvgAccuracy() + lore.getAvgAccuracy());
            } else {
                map.put(key, lore);
            }
        }

        return map;
    }
}
