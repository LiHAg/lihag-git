package com.golden.monitor.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 所模拟的数据都可以通过Mybatis、Hibernate、Spring JDBC、Spring JPA
 * 生成多行的二维表格式：List<Map<String,Object>>
 * 生成单行的二维表格式：Map<String,Object>
 * @author jinma.zheng
 *
 */
@Service
//@Slf4j
public class DataService {
	/**
	 * 气象测试数据
	 * @return
	 */
	public List<Map<String,Object>> getWeatherData(){
		//模拟二维数据表数据-多行
		String jsonData="["
				+ "{\"y0:Current\":1,\"x:Time\":\"time1\"},"
				+ "{\"y0:Current\":1,\"x:Time\":\"time2\"},"
				+ "{\"y0:Current\":1,\"x:Time\":\"time3\"},"
				+ "{\"y0:Current\":1,\"x:Time\":\"time4\"},"
				+ "{\"y0:Current\":1,\"x:Time\":\"time5\"},"
				+ "{\"y0:Current\":1,\"x:Time\":\"time6\"}]";
		
		//jsonData="[{\"y\":10,\"x:\":\"2019-03-26 21:50:42\"}]";
		
		List<Map<String,Object>> mapList = JSON.parseObject(jsonData,new TypeReference<List<Map<String,Object>>>(){});
		
		//输出调试信息
		System.out.println("----------------测试数据开始-------------\n\n");
		System.out.println("y0:最低气温 \t y0:电流 \t x:日期");
		System.out.println("-----------------------------");
		mapList.forEach((Map<String,Object> map) ->{
			System.out.println(map.get("y0:最低气温")+" \t "+map.get("y0:电流")+" \t "+map.get("x:日期"));
		});
		System.out.println("\n\n----------------测试数据结束-------------");
		
		return mapList;
	}
	
	/**
	 * 饼状图测试数据
	 * @return
	 */
	public Map<String,Object> getPieData(){
		//模拟二维数据表数据-单行
		String jsonData="{\"视觉\":830,\"听觉\":110,\"嗅觉\":35,\"触觉\":15,\"味觉\":10}";
		Map<String,Object> map = JSON.parseObject(jsonData,new TypeReference<Map<String,Object>>(){});
		
		//输出调试信息
		System.out.println("----------------测试数据开始-------------\n\n");
		map.forEach((k,v) ->{
			System.out.println("列名："+k+";"+"列值："+v);
		});
		System.out.println("\n\n----------------测试数据结束-------------");
		return map;
	}
	
	/**
	 * 雷达图测试数据
	 * @return
	 */
	public List<Map<String,Object>> getRadarData(){
		List<Map<String,Object>> list = new ArrayList<>();
		//模拟二维数据表数据-多行
		Map<String,Object> map1 = new HashMap<>(16);
		map1.put("x:name", "销售（sales）");
		map1.put("x:max", 6500);
		map1.put("y:预算分配（Allocated Budget）",4300);
		map1.put("y:实际开销（Actual Spending）", 5000);
		list.add(map1);
		
		Map<String,Object> map2 = new HashMap<>(16);
		map2.put("x:name","管理（Administration）");
		map2.put("x:max",16000);
		map2.put("y:预算分配（Allocated Budget）",10000);
		map2.put("y:实际开销（Actual Spending）", 14000);
		list.add(map2);
		
		Map<String,Object> map3 = new HashMap<>(16);
		map3.put("x:name", "信息技术（Information Techology）");
		map3.put("x:max", 30000);
		map3.put("y:预算分配（Allocated Budget）",28000);
		map3.put("y:实际开销（Actual Spending）", 28000);
		list.add(map3);
		
		Map<String,Object> map4 = new HashMap<>(16);
		map4.put("x:name", "客服（Customer Support）");
		map4.put("x:max",38000);
		map4.put("y:预算分配（Allocated Budget）",35000);
		map4.put("y:实际开销（Actual Spending）", 31000);
		list.add(map4);
		
		Map<String,Object> map5 = new HashMap<>(16);
		map5.put("x:name", "研发（Development）");
		map5.put("x:max", 52000);
		map5.put("y:预算分配（Allocated Budget）",50000);
		map5.put("y:实际开销（Actual Spending）", 42000);
		list.add(map5);
		
		Map<String,Object> map6 = new HashMap<>(16);
		map6.put("x:name", "市场（Marketing）");
		map6.put("x:max", 25000);
		map6.put("y:预算分配（Allocated Budget）",19000);
		map6.put("y:实际开销（Actual Spending）", 21000);
		list.add(map6);
		
		return list;
	}
	
	/**
	 * 双对象测试数据
	 * @return
	 */
	public Map<String,List<Object[]>> getObjectNumLineData(){
		
		Map<String,List<Object[]>> dataMap = new HashMap<>(16);
		
		List<Object[]> data1 = new ArrayList<Object[]>();
		Object[] obj0= new Object[2];obj0[0]="2017-06-01";obj0[1]=38.8;data1.add(obj0);
		Object[] obj1= new Object[2];obj1[0]="2017-06-02";obj1[1]=129.6;data1.add(obj1);
		Object[] obj2= new Object[2];obj2[0]="2017-06-03";obj2[1]=259.9;data1.add(obj2);
		Object[] obj3= new Object[2];obj3[0]="2017-06-04";obj3[1]=447.1;data1.add(obj3);
		Object[] obj4= new Object[2];obj4[0]="2017-06-05";obj4[1]=702.5;data1.add(obj4);
		dataMap.put("y0:H82-2.0MW功率曲线", data1);
		
		List<Object[]> data2 = new ArrayList<Object[]>();
		Object[] obj00= new Object[2];obj00[0]="2017-06-01";obj00[1]=8.2;data2.add(obj00);
		Object[] obj11= new Object[2];obj11[0]="2017-06-02";obj11[1]=68.8;data2.add(obj11);
		Object[] obj22= new Object[2];obj22[0]="2017-06-03";obj22[1]=187.3;data2.add(obj22);
		Object[] obj33= new Object[2];obj33[0]="2017-06-04";obj33[1]=355.8;data2.add(obj33);
		Object[] obj44= new Object[2];obj44[0]="2017-06-05";obj44[1]=975.2;data2.add(obj44);
		dataMap.put("y1:H93-2.0MW功率模拟曲线", data2);
		
		return dataMap;
	}
	
	/**
	 * 双数值测试数据
	 * @return
	 */
	public Map<String,List<Object[]>> getDoubleNumLineData(){
		Map<String,List<Object[]>> dataMap = new HashMap<>(16);

		List<Object[]> data1 = new ArrayList<Object[]>();
		Object[] obj0= new Object[2];obj0[0]=4;obj0[1]=38.8;data1.add(obj0);
		Object[] obj1= new Object[2];obj1[0]=5;obj1[1]=129.6;data1.add(obj1);
		Object[] obj2= new Object[2];obj2[0]=6;obj2[1]=259.9;data1.add(obj2);
		Object[] obj3= new Object[2];obj3[0]=7;obj3[1]=447.1;data1.add(obj3);
		Object[] obj4= new Object[2];obj4[0]=8;obj4[1]=702.5;data1.add(obj4);
		dataMap.put("H82-2.0MW功率曲线", data1);

		List<Object[]> data2 = new ArrayList<Object[]>();
		Object[] obj00= new Object[2];obj00[0]=3;obj00[1]=8.2;data2.add(obj00);
		Object[] obj11= new Object[2];obj11[0]=4;obj11[1]=68.8;data2.add(obj11);
		Object[] obj22= new Object[2];obj22[0]=5;obj22[1]=187.3;data2.add(obj22);
		Object[] obj33= new Object[2];obj33[0]=6;obj33[1]=355.8;data2.add(obj33);
		Object[] obj44= new Object[2];obj44[0]=7;obj44[1]=975.2;data2.add(obj44);
		dataMap.put("H93-2.0MW功率曲线", data2);
		return dataMap;
	}
	
	/**
	 * 日历图测试数据
	 * @return
	 */
	public  List<Map<String,Object>> getCalendarData(){
		int maxDaysOfMonth = 31;
		List<Map<String,Object>> list = new ArrayList<>();
		for(int i=1;i<=maxDaysOfMonth;i++){
			String dateDay="";
			if(i<10){
				dateDay = "0"+i;
			}else{
				dateDay = ""+i;
			}
			Map<String,Object> map = new HashMap<>(16);
			map.put("echartsDate", "2017-05-"+dateDay);
			map.put("工作", randomData()/100);
			map.put("睡觉", randomData()/100);
			map.put("娱乐", randomData()/100);
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 热力图测试数据
	 * @return
	 */
	public List<Map<String,Object>> getHeatMap(){
		int startDays = 10;
		int maxDaysOfMonth = 31;
		List<Map<String,Object>> list = new ArrayList<>();
		for(int i=startDays;i<=maxDaysOfMonth;i++){
			Map<String,Object> map1 = new HashMap<String,Object>(16);
			map1.put("x", "2017-06-"+i);
			map1.put("y", "工作票");
			map1.put("z", (int)(randomData()/100));
			list.add(map1);
			
			Map<String,Object> map2 = new HashMap<>(16);
			map2.put("x", "2017-06-"+i);
			map2.put("y", "操作票");
			map2.put("z", (int)(randomData()/100));
			list.add(map2);
			
			Map<String,Object> map3 = new HashMap<>(16);
			map3.put("x", "2017-06-"+i);
			map3.put("y", "缺陷");
			map3.put("z", (int)(randomData()/100));
			list.add(map3);
			
			Map<String,Object> map4 = new HashMap<>(16);
			map4.put("x", "2017-06-"+i);
			map4.put("y", "工单");
			map4.put("z", (int)(randomData()/100));
			list.add(map4);
			
			Map<String,Object> map5 = new HashMap<>(16);
			map5.put("x", "2017-06-"+i);
			map5.put("y", "故障");
			map5.put("z", (int)(randomData()/100));
			list.add(map5);
		}
		return list;
	}
	
	private double randomData() {
	    return Math.round(Math.random()*1000);
	}
}
