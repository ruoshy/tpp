package cn.cwc.api.model;

public enum Action {
    OK(200, null),
    FAIL(400, "请求失败!");

    private int status;
    private String msg;

    Action(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
