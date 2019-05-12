package org.steam.core.model.vo;

import com.alipay.api.domain.AlipayTradePagePayModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PayPageVO {

    private AlipayTradePagePayModel model;

    private String returnUrl;

    private String notifyUrl;

}
