package com.zcy.mytool.base.core;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 返回形式
 */
public class BaseResponse {

    // 状态
    private Integer status;

    // 消息
    private String message;

    // 数据
    private Object data;

    // 时间
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date timestamp;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
