package com.xnzn.graph.controller;

import com.xnzn.graph.base.entity.LorePointGraph;
import com.xnzn.graph.base.entity.MyLore;
import com.xnzn.graph.base.service.LorePointGraphService;
import com.xnzn.graph.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xn
 * @Date: 2019/9/29 11:25
 */
@RestController
public class LorePointGraphController {
    @Autowired
    private LorePointGraphService lorePointGraphService;

    @GetMapping("/lore-list/{subjectId}")
    public ResponseEntity selectBySubjectId(@PathVariable Integer subjectId) {
        List<LorePointGraph> lorePointGraphs = lorePointGraphService.selectBySubjectId(subjectId);
        return ResultUtil.getInstance().withItem(lorePointGraphs).ok();
    }

    @PostMapping("/lore/insert")
    public ResponseEntity insert(@RequestBody LorePointGraph entity) {
        lorePointGraphService.insert(entity);
        return ResultUtil.getInstance().withItem(entity).ok();
    }

    @PutMapping("/lore/update")
    public ResponseEntity update(@RequestBody LorePointGraph entity) {
        lorePointGraphService.updateByPrimaryKey(entity);
        return ResultUtil.getInstance().ok();
    }

    @DeleteMapping("/lore/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        LorePointGraph lorePointGraph = lorePointGraphService.selectById(id);
        lorePointGraphService.deleteByPrimaryKey(id);
        return ResultUtil.getInstance().withItem(lorePointGraph).ok();
    }

    @GetMapping("/lore-list/tiled/{subjectId}")
    public ResponseEntity selectTiledBySubjectId(@PathVariable Integer subjectId) {
        List<LorePointGraph> lorePointGraphs = lorePointGraphService.selectTiledBySubjectId(subjectId);
        return ResultUtil.getInstance().withItem(lorePointGraphs).ok();
    }

    @GetMapping("/lore-test/{subjectId}/{studentId}")
    public ResponseEntity test(@PathVariable Integer subjectId,@PathVariable Integer studentId) {
        Map<Integer, MyLore> results = lorePointGraphService.test(subjectId,studentId);
        Iterator<Map.Entry<Integer, MyLore>> iterator = results.entrySet().iterator();
        ArrayList<MyLore> lores = new ArrayList<>();
        while (iterator.hasNext()){
            Map.Entry<Integer, MyLore> entry = iterator.next();
            MyLore value = entry.getValue();
            lores.add(value);
        }
        return ResultUtil.getInstance().withItem(lores).ok();
    }

}
