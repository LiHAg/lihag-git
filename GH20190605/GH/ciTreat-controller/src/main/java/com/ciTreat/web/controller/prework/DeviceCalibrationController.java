package com.ciTreat.web.controller.prework;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ciTreat.dev.domain.DevManager;
import com.ciTreat.dev.service.IDevManageService;
import com.golden.common.base.AjaxResult;
import com.golden.common.constant.BusinessContants;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.util.Transformer;

/**
 * 设备标定
 * @author Lianyi
 */
@Controller
@RequestMapping("/prework/device")
public class DeviceCalibrationController extends BaseController {
	
	@Autowired
	private IDevManageService devManageService;
	
	private String prefix = "prework/device";
	
	private String prefix_d = "prework/devDetail";
	
	@GetMapping()
	public String deviceCalibration() {
		return prefix + "/device";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevManager devManager) {
		startPage();
		List<DevManager> selectDevManageList = devManageService.selectDevCalibration(devManager);
		return getDataTable(selectDevManageList);
	}
	
	@GetMapping("/calibration")
	public String calibration(DevManager devManager,ModelMap mmap) {
		
		DevManager selectDevManageById = devManageService.selectDevManageById(devManager);
		String deviceId = selectDevManageById.getDeviceId();
		String deviceName = selectDevManageById.getDeviceName();
		//根据 设备ID 拿到该标定页面
		String page = selectDevManageById.getPage();
		mmap.put("deviceId",deviceId);
		mmap.put("deviceName",deviceName);
		return page;
	}
	
	@GetMapping("/detail")
	public String detail(DevManager devManager,ModelMap mmap) {
		
		DevManager selectDevManageById = devManageService.selectDevManageById(devManager);
		//根据 设备ID 拿到该标定页面
		String page = selectDevManageById.getPage();
		String details = selectDevManageById.getDetails();
		Map map = (Map) JSON.parse(details);
		for(Object obj : map.keySet()) {
			mmap.put((String) obj, map.get(obj));
		}
		return page;
	}
	
	@PostMapping("/commitDevCalibration")
	@ResponseBody
	public AjaxResult commitDevCalibration(@RequestBody DeviceCalibrationModel model) {
		
		System.out.println("----------设备标定----------DeviceId="+model.getDeviceId());
		System.out.println("----------设备标定----------DeviceId="+model.getFormData());
		DevManager devManager = new DevManager();
		devManager.setDeviceId(model.getDeviceId());
//		if("".equals(model.getFormData())) {
//			
//		}
		devManager.setDetails(Transformer.linkedHashmapToJsonString(model.getFormData()));
		//修改 标定状态status
		devManager.setStatus(BusinessContants.DEVICE_STATUS_Y);
		//存储大字段到 表dve_deviceconfig 
		devManageService.updateDetailDevManage(devManager);
		AjaxResult ajax = AjaxResult.success();	
		return ajax;
	}
	
	
	//另一种提交 	MultiValueMap接收
	@PostMapping("/commitDevCalibration1")
	@ResponseBody
	public AjaxResult commitDevCalibration1(@RequestBody MultiValueMap<String,String> json) {
		
		System.out.println("========================="+json);
		List<String> list = json.get("preDose");
		for(String s : list) {
			System.out.println("+++++++++=========="+s);
		}
		AjaxResult ajax = AjaxResult.success();	
		return ajax;
	}
}
