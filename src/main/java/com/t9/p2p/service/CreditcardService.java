package com.t9.p2p.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.p2p.dao.CreditcardDao;
import com.t9.system.service.FastBaseService;
import com.t9.system.service.FastServiceInvoke;

@Service
@SuppressWarnings({ "unchecked","rawtypes"})
public class CreditcardService extends FastBaseService{
	
	@Autowired
	CreditcardDao creditcardDao;

	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
	
	@Override
	public List<Map> queryList(FastServiceInvoke serviceInvoke) {
		List<Map> list=super.queryList(serviceInvoke);
		for (int i = 0; i < list.size(); i++) {
			Map map=list.get(i);
			String BANK_NAME=(String)map.get("BANK_NAME");
			String BILL_DAY=(String)map.get("BILL_DAY");
			int day=Integer.parseInt(BILL_DAY);
			
			Calendar calendar=Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, day);
			
			calendar.add(Calendar.MONTH, 1);
			String nextMonthDay=dateFormat.format(calendar.getTime());			
			calendar.add(Calendar.MONTH, -1);
			String currMonthDay=dateFormat.format(calendar.getTime());			
			calendar.add(Calendar.MONTH, -1);
			String lastMonthDay=dateFormat.format(calendar.getTime());
			

			double money=creditcardDao.queryBill(BANK_NAME, nextMonthDay);
			map.put("NEXT_BILL", money<0?""+money:"已还");
			
			money=creditcardDao.queryBill(BANK_NAME, currMonthDay);
			map.put("CURRENT_BILL", money<0?""+money:"已还");
			money=creditcardDao.queryBill(BANK_NAME, lastMonthDay);
			map.put("LAST_BILL", money<0?""+money:"已还");
			
		}
		return list;
	}
}
