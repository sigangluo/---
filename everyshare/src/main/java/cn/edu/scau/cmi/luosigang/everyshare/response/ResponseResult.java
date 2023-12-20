package cn.edu.scau.cmi.luosigang.everyshare.response;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int statusCode;
    private String statusMsg;
    private T data;

    public ResponseResult() {}

    public ResponseResult(ResponseStatus rs) {
        statusCode = rs.getStatusCode();
        statusMsg = rs.getStatusMsg();
    }

    public ResponseResult(ResponseStatus rs,T data) {
        statusCode = rs.getStatusCode();
        statusMsg = rs.getStatusMsg();
        this.data = data;
    }

    public ResponseResult(int statusCode, String s, T data) {
        this.statusCode = statusCode;
        statusMsg = ResponseStatus.getMsgByCode(statusCode);
        this.data = data;
    }

    public ResponseResult(int statusCode) {
        this.statusCode = statusCode;
        statusMsg = ResponseStatus.getMsgByCode(statusCode);
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        statusMsg = ResponseStatus.getMsgByCode(statusCode);
    }

    public void setStatus(ResponseStatus rs) {
        statusCode = rs.getStatusCode();
        statusMsg = rs.getStatusMsg();
    }

    public void setData(T data) {
        this.data = data;
    }
}