package com.ciTreat.web.controller.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciTreat.dev.domain.DevManager;
import com.ciTreat.dev.domain.DevTreatmentRoom;
import com.ciTreat.dev.service.IDevManageService;
import com.ciTreat.dev.service.IDevRoomService;
import com.golden.common.annotation.Log;
import com.golden.common.base.AjaxResult;
import com.golden.common.enums.BusinessType;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.util.ShiroUtils;
import com.golden.framework.web.base.BaseController;

/**
 * 设备管理
 * 
 * @author LIANYI
 */
@Controller
@RequestMapping("/dev/devManage")
public class CiTreatDevManageController extends BaseController {

	private String prefix = "dev/devManage";

	@Autowired
	private IDevManageService devManageService;

	@GetMapping()
	public String devRoom() {
		return prefix + "/devManage";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevManager devManager) {
		startPage();
		List<DevManager> selectDevManageList = devManageService.selectDevCalibration(devManager);

		return getDataTable(selectDevManageList);
	}
	
}
