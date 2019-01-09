package com.ruubypay.demo;

/**
 * @author LiangYang
 * @time 2019/1/9 下午5:26
 * @class describe
 */
public class MessageWrap {

    public final String message;

    public static MessageWrap getInstance(String message) {
        return new MessageWrap(message);
    }

    private MessageWrap(String message) {
        this.message = message;
    }
}
