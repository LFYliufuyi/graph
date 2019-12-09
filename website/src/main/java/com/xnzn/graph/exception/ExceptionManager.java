package com.xnzn.graph.exception;

import com.xnzn.graph.utils.ErrorEnum;
import com.xnzn.graph.utils.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @Auther: xn
 * @Date: 2019/9/29 14:34
 */
@RestControllerAdvice
public class ExceptionManager {
    /**
     * 异常/IO
     *
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        System.out.println(e);
        return ResultUtil.getInstance().withError(ErrorEnum.SYSTEM_ERROR).serverInternalError();
    }
}
