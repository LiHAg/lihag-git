package com.ciTreat.web.controller.reports;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.golden.common.charts.EchartModel;
import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.core.EchartsCore;

/**
 * @author jinma.zheng
 * @date 2019年4月18日
 */
@Controller
@RequestMapping("/doseReports")
public class DoseReportsController {
	private String prefix = "reports";
	@GetMapping("/doseReport")
	public String doseReport() {
		return prefix + "/DoseReport";
	}
	
	@PostMapping("/doseReportView")
	public @ResponseBody String doseReportView(@RequestBody EchartsConfig echartsConfig){
		System.out.println("----------------doseReportView-------------------");
		List<EchartModel> data = new ArrayList<EchartModel>();
		EchartModel em1 = new EchartModel("2019-04-10",100);
		EchartModel em2 = new EchartModel("2019-04-11",100);
		EchartModel em3 = new EchartModel("2019-04-12",10);
		EchartModel em4 = new EchartModel("2019-04-13",105);
		EchartModel em5 = new EchartModel("2019-04-14",173);
		EchartModel em6 = new EchartModel("2019-04-15",50);
		EchartModel em7 = new EchartModel("2019-04-16",160);
		EchartModel em8 = new EchartModel("2019-04-17",20);
		EchartModel em9 = new EchartModel("2019-04-18",130);
		EchartModel em10 = new EchartModel("2019-04-19",70);
		EchartModel em11 = new EchartModel("2019-04-20",90);
		data.add(em1);
		data.add(em2);
		data.add(em3);
		data.add(em4);
		data.add(em5);
		data.add(em6);
		data.add(em7);
		data.add(em8);
		data.add(em9);
		data.add(em10);
		data.add(em11);
		
		String gsonOption =  EchartsCore.getInstance().adapterEasyLineAndBar(echartsConfig,data).toString();
		System.out.println("--->" + gsonOption);
		return gsonOption;
	}
	
}
