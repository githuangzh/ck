/**
 * 2010(c) Copyright Oceansoft Information System Co.,LTD. All rights reserved.
 * <p>
 * Compile: JDK 1.6+
 * <p>
 * 版权所有(C)：江苏欧索软件有限公司
 * <p>
 * 公司名称：江苏欧索软件有限公司
 * <p>
 * 公司地址：中国苏州科技城青山路1号
 * <p>
 * 网址: http://www.oceansoft.com.cn
 * <p>
 * 作者: 090922(陈伟)
 * <p>
 * 文件名: com.oceansoft.emobile.biz.common.entity.Result.java
 * <p>
 * 类产生时间: 2014/4/19 23:13
 * <p>
 * 负责人: 090922(陈伟)
 * <p>
 * Email:javacspring@gmail.com
 * <p>
 * 所在组 : 掌上公安应用平台
 * <p>
 * 所在部门: 开发部--手持技术部
 * <p>
 * <p>
 */
package com.will.utility;

/**
 * 响应结果信息类
 *
 * @author: chenw
 * @time: 2014/4/19 23:13
 */
public class Result {

    public static final boolean SUCC = true;
    public static final boolean FAILURE = false;

    private boolean succ = SUCC;
    private int statusCode = 200;
    private String msg;
    private Object data;
    private long time = System.currentTimeMillis();

    public Result() {
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(boolean succ) {
        this.succ = succ;
    }

    public Result(boolean succ, String msg) {
        this.succ = succ;
        this.msg = msg;
    }

    public Result(boolean succ, Object data) {
        this(succ, succ ? 200 : 400, data);
    }

    public Result(boolean succ, Object data, String msg) {
        this(succ, msg);
        this.data = data;
    }

    public Result(int statusCode, Object data) {
        this(200 == statusCode ? SUCC : FAILURE, statusCode, data);
    }

    public Result(boolean succ, int statusCode, Object data) {
        this.succ = succ;
        this.statusCode = statusCode;
        this.data = data;
    }	
    public Result(boolean succ, int statusCode, String msg, Object data) {
		super();
		this.succ = succ;
		this.statusCode = statusCode;
		this.msg = msg;
		this.data = data;
	}

	public Result(Object data) {
        this.data = data;
    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
