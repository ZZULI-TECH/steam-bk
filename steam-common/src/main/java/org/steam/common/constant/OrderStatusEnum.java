package org.steam.common.constant;

/**
 * 订单状态枚举类
 */

public enum OrderStatusEnum {
    /**
     * 订单创建完成的订单状态
     */
    CREATED(1,"订单生成"),
    /**
     * 订单已支付的订单状态
     */
    NEED_SHIPPING(2,"待发货"),
    /**
     * 订单已发货的订单状态
     */
    HAVE_SHIPPING(3,"已发货"),
    /**
     * 订单确认收获的订单状态
     */
    FINISHED(4,"订单完成"),
    /**
     * 订单取消后的订单状态
     */
    CANCLED(5,"订单取消");

    private Integer code;
    private String desc;

    OrderStatusEnum(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }

}
