package com.xnzn.graph.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xn
 * @Date: 2019/9/29 14:22
 */
public class ResultUtil<T> {

    /**
     * 状态码
     */
    private Integer code;

    private HttpStatus status;

    private String message;

    /**
     * 单数据
     */
    private T item;

    /**
     * 多数据
     */
    private Map<String, Object> data = new HashMap<String, Object>(3);

    /**
     * 分页
     */
    private T page;

    private Map<String, Object> resultMap = new HashMap<String, Object>(3);

    private ResultUtil() {

    }

    private static final ThreadLocal<ResultUtil> THREAD_LOCAL = new ThreadLocal<ResultUtil>() {
        @Override
        protected ResultUtil initialValue() {
            return new ResultUtil();
        }
    };


    public static ResultUtil getInstance() {
        return THREAD_LOCAL.get().clear();
    }

    public ResultUtil clear() {
        this.code = null;
        this.status = null;
        this.message = null;
        this.item = null;
        this.success = true;
        this.data.clear();
        this.page = null;
        this.resultMap.clear();
        return this;
    }

    public ResultUtil withCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResultUtil withStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ResultUtil withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 有异常为false,成功为true
     */
    private Boolean success = true;

    public void unlockError(ErrorEnum info) {
        this.success = info.getSuccess();
        this.code = info.getCode();
        this.message = info.getMessage();
    }

    public ResultUtil withError(ErrorEnum info) {
        this.unlockError(info);
        return this;
    }

    public ResultUtil withItem(T item) {
        this.item = item;
        return this;
    }

    public ResultUtil withItem(T item,Boolean success) {
        this.success = success;
        this.item = item;
        return this;
    }

    public ResultUtil withData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ResultUtil putData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public ResultUtil withPage(T page) {
        this.page = page;
        return this;
    }

    /**
     * Spring JSON响应
     */
    public ResponseEntity<Map<String, Object>> respondJson() {
        return new ResponseEntity(this.builderData(), this.status);
    }

    /**
     * 验证 JSON响应
     *
     * @param message
     * @return
     */
    public ResponseEntity<Map<String, Object>> validate(String message) {
        this.message = message;
        return this.badRequest();
    }


    /**
     * 普通 JSON响应
     */
    public void respondJson(HttpServletResponse response) {
        response.setStatus(this.status.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(this.builderData()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 生成数据
     */
    private Map<String, Object> builderData() {
        if (!StringUtils.isEmpty(this.success)) {
            resultMap.put("success", this.success);
        }
        if (!StringUtils.isEmpty(this.code)) {
            resultMap.put("code", this.code);
        }
        if (!StringUtils.isEmpty(this.message)) {
            resultMap.put("message", this.message);
        }
        if (null != this.page) {
            resultMap.put("page", this.page);
        }
        if (!StringUtils.isEmpty(this.item)) {
            resultMap.put("data", this.item);
        } else if (!ResultUtil.isEmpty(this.data)) {
            resultMap.put("data", this.data);
        }
        return (resultMap.size() == 0) ? null : resultMap;
    }

    private static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * 200成功
     */
    public ResponseEntity<Map<String, Object>> ok() {
        this.status = HttpStatus.OK;
        return this.respondJson();
    }

    /**
     * 返回400
     */
    public ResponseEntity<Map<String, Object>> badRequest() {
        this.status = HttpStatus.BAD_REQUEST;
        return this.respondJson();
    }

    /**
     * 返回500
     */
    public ResponseEntity<Map<String, Object>> serverInternalError() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.respondJson();
    }
}
