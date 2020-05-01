package cn.ckh2019.pawnshop.commons.bean;

import lombok.Data;

/**
 * @author Chen Kaihong
 * 2019-11-04 15:06
 */

@Data
public class Result {

    private int code;
    private boolean tag;
    private String msg;
    private Object obj;

    public Result() {
    }

    public Result(boolean tag) {
        this.tag = tag;
    }

    public Result(boolean tag, String msg) {
        this.tag = tag;
        this.msg = msg;
    }

    public Result(int code, boolean tag, String msg, Object obj) {
        this.code = code;
        this.tag = tag;
        this.msg = msg;
        this.obj = obj;
    }
}
