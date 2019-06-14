package com.huawei.l00379880.entity;

import lombok.Data;

import java.io.Serializable;

/***********************************************************
 * @note      : 订单实体类
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/6/14 10:26
 ***********************************************************/
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -2724110603750721693L;
    private String id;
    private String name;
    /**
     * 存储消息发送的唯一标志
     */
    private String messageId;
}
