package com.qunar.fintech.plat.admin;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.qunar.fintech.plat.admin.query.vo.UrgeRepayReqVo;
import com.qunar.pay.finance.qf.commons.utils.base.CollectionUtils;
import com.qunar.pay.finance.qf.commons.utils.base.DateUtil;
import org.apache.commons.collections.MapUtils;
import sun.util.calendar.BaseCalendar;

import java.util.Date;
import java.util.Map;

public class TestMain {

	public static void main(String[] args) {
		String ext = "{\"msgUrl\":\"https://qnr.io/tomek9\",\"couponContent\":\"15天内借款使用有效。\", \"ruleTips\":\"去哪儿网拿去花交易满2元订单\"}";
		String ext1 = "{}";
		Map<String, Object> res = new Gson().fromJson(ext1, new TypeToken<Map<String, Object>>() {
		}.getType());
		System.out.println(res);
		System.out.println(MapUtils.isEmpty(res)?"1":res.get("ruleTips"));
		System.out.println(MapUtils.isEmpty(res)?"2":res.get("ruleTips111"));

		System.out.println(new Date());
		Date date= DateUtil.addHours(new Date(), 10);
		System.out.println(date);
		Date date1= DateUtil.addHours(new Date(), 5);
		System.out.println(date1);

	}

}
