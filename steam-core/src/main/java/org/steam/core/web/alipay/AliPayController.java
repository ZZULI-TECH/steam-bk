package org.steam.core.web.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jpay.alipay.AliPayApi;
import com.jpay.alipay.AliPayApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.steam.common.exception.ServerException;
import org.steam.common.model.ResultModel;
import org.steam.core.model.entity.AliPayBean;
import org.steam.core.model.entity.Order;
import org.steam.core.model.entity.OrderGame;
import org.steam.core.model.entity.Token;
import org.steam.core.service.IOrderGameService;
import org.steam.core.service.IOrderService;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;


@Controller
@RequestMapping("/alipay")
public class AliPayController extends AliPayApiController {
    private static final Logger log = LoggerFactory.getLogger(AliPayController.class);

	@Autowired
	private AliPayBean aliPayBean;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderGameService orderGameService;


	@Override
	public AliPayApiConfig getApiConfig() {
		return AliPayApiConfig.New()
				.setAppId(aliPayBean.getAppId())
				.setAlipayPublicKey(aliPayBean.getPublicKey())
				.setCharset("UTF-8")
				.setPrivateKey(aliPayBean.getPrivateKey())
				.setServiceUrl(aliPayBean.getServerUrl())
				.setSignType("RSA2")
				.build();
	}
	
	@RequestMapping("")
	@ResponseBody
	public String index() {
		return "欢迎使用IJPay 中的支付宝支付 -By Javen";
	}

	/**
	 * PC支付
	 */
	@RequestMapping("/pay/{orderId}")
	@ResponseBody
	public void pcPay(HttpServletResponse response, @PathVariable String orderId){
		ResultModel<Token> models;
		Order order = orderService.getById(orderId);
		if(order.getOrderAmount().equals(BigDecimal.ZERO)){
			log.error("订单金额为0");
			models = ResultModel.fail(10005,"订单金额为0" );
			throw new ServerException(models, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<OrderGame> orderGames = orderGameService.list(new QueryWrapper<OrderGame>().eq("order_id", orderId));
		try {
			String totalAmount = order.getOrderAmount().toString();
			String outTradeNo =orderId+IdWorker.getMillisecond().substring(11);
			log.info("pc outTradeNo>"+outTradeNo);
			String returnUrl = aliPayBean.getDomain() + "/api/pay/payOrder";
			String notifyUrl = aliPayBean.getDomain() + "/api/pay/notify";
			AlipayTradePagePayModel model = new AlipayTradePagePayModel();
			model.setOutTradeNo(outTradeNo);
			model.setProductCode("FAST_INSTANT_TRADE_PAY");
			model.setTotalAmount(totalAmount);
			if(orderGames.size()>0){
				model.setSubject( orderGames.get(0).getGameName()+"等游戏购买");
			}else{
				model.setSubject("游戏购买");
			}
			model.setBody("游戏购买支付");
			model.setPassbackParams("passback_params");
			AliPayApi.tradePage(response,model , notifyUrl, returnUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 交易查询
	 */
	@RequestMapping(value = "/tradeQuery")
	@ResponseBody
	public boolean tradeQuery() {
		boolean isSuccess = false;
		try {
			AlipayTradeQueryModel model = new AlipayTradeQueryModel();
			model.setOutTradeNo("081014283315023");
			model.setTradeNo("2017081021001004200200273870");

			isSuccess = AliPayApi.isTradeQuery(model);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

}
