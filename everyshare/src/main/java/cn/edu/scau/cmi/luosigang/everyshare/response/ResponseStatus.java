package cn.edu.scau.cmi.luosigang.everyshare.response;

public enum ResponseStatus {
    // 响应正常
    SUCCESS(1000, "服务器响应正常"),
    RESPONSE_SUCCESS(1001, "服务器响应正常且数据操作成功"),
    RESPONSE_FAIL(1002, "服务器响应正常但数据操作失败"),
    USER_REGISTRATION_SUCCESS(1003,"用户注册成功"),
    USER_REGISTRATION_ERROR(1004,"用户注册发生错误"),
    USER_LOGOFF_SUCCESS(1005,"用户注销成功"),
    USER_LOGOFF_ERROR(1006,"用户注销发生错误"),
//    // 事务异常
//    TRANSACTION_ERROR(2000, "事务操作异常"),
//
//    // 服务器非正常
//    SERVER_ERROR(3000, "服务器异常"),

    // 用户非正常
    USER_AUTH_DENY(4000, "用户权限不足"),
    USER_PASSWORD_ERROR(4001, "用户密码错误"),
//    USER_ACCOUNT_EXPIRED(4002, "用户账号过期"),
//    USER_PASSWORD_EXPIRED(4003, "用户密码过期"),
    USER_ACCOUNT_DISABLE(4004, "用户账号不可用"),
    USER_ACCOUNT_LOCKED(4005, "用户账号锁定"),
    USER_ACCOUNT_NOT_EXIST(4006, "用户不存在"),
    USER_NOT_LOGIN(4007, "用户未登录"),
    USER_ACCOUNT_LOGOFF(4008,"用户账号已注销"),
    USERNAME_NOT_FOUNT(4009,"用户名未找到"),
    USERNAME_EXIST(5000, "用户名已存在"),

    UPLOAD_SUCCESS(6000,"上传成功"),
    UPLOAD_FAIL(6001,"上传失败"),
    REPEAT_OPERATION(6002,"重复操作"),
    ILLEGAL_OPERATION(6003,"非法操作"),
    ILLEGAL_PARAM(6004,"非法参数"),
    // 其他错误
    COMMON_ERROR(9000, "未知错误"),

    POST_CANNOT_COMMENT(8000,"该博文不允许评论"),
    POST_CANNOT_LIKE(8001,"该博文不允许点赞"),
    POST_CANNOT_COLLECT(8002,"该博文不允许收藏");
    private int statusCode;
    private String statusMsg;

    ResponseStatus(int statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public static String getMsgByCode(int statusCode) {
        for (ResponseStatus rs: ResponseStatus.values()) {
            if (rs.getStatusCode() == statusCode) return rs.getStatusMsg();
        }
        return "";
    }
}
