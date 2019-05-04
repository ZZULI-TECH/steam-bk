package org.steam.core.model.entity;

import java.math.BigDecimal;

import org.steam.common.model.VersionEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author biao
 * @since 2019-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_order")
public class Order extends VersionEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String orderSn;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 县区
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 快递单号
     */
    private String shippingCode;

    /**
     * 快递名称
     */
    private String shippingName;
    /**
     * 是否需要发货
     */
    private Boolean needShipping;

    /**
     * 支付号
     */
    private String payCode;

    /**
     * 支付方式名称
     */
    private String payName;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 应付款金额
     */
    private BigDecimal orderAmount;

    /**
     * 实付款
     */
    private BigDecimal totalAmount;

    private LocalDateTime shippingTime;

    private LocalDateTime confirmTime;

    private LocalDateTime payTime;



}
