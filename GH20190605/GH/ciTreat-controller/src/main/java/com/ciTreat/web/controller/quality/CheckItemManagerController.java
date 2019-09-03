package com.ciTreat.web.controller.quality;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciTreat.dev.domain.DevTreatmentRoom;
import com.ciTreat.quality.domain.CheckItem;
import com.ciTreat.quality.service.CheckItemService;
import com.golden.common.annotation.Log;
import com.golden.common.base.AjaxResult;
import com.golden.common.enums.BusinessType;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.util.ShiroUtils;
import com.golden.framework.web.base.BaseController;

@Controller
@RequestMapping("/quality/checkManager")
public class CheckItemManagerController extends BaseController{
	private String prefix = "quality/checkManager";
	@Autowired
	private CheckItemService checkItemService;
	@GetMapping()
	public String checkItem1() {
		return prefix + "/checkManager";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CheckItem checkItem) {
		startPage();
		List<CheckItem> selectcheckitems=checkItemService.selectCheckItems(checkItem);
		return getDataTable(selectcheckitems);
		
	}
	@PostMapping("/list2")
	@ResponseBody
	public TableDataInfo list2(CheckItem checkItem) {
		startPage();
		List<CheckItem> selectcheckitems=checkItemService.selectCheckItemsPortion(checkItem);
		return getDataTable(selectcheckitems);
		
	}
	@GetMapping("/add")
	public String add() {
		return prefix+"/add";
	}
	
	@Log(title = "检查项信息", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult insert(CheckItem checkItem) {

		checkItem.setCreateBy(ShiroUtils.getLoginName());

		return toAjax(checkItemService.insertCheckItem(checkItem));
	}
	@GetMapping("/edit/{checkItemId}")
	public String edit(@PathVariable("checkItemId") Integer checkItemId,ModelMap mmap) {
		CheckItem item=checkItemService.selectCheckItem(checkItemId);
		mmap.put("checkItem", item);
		return prefix+"/edit";
	}
	@Log(title = "检查项信息", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(CheckItem checkItem) {
		checkItem.setCreateBy(ShiroUtils.getLoginName());

		return toAjax(checkItemService.updateCheckItem(checkItem));
	}
	@Log(title = "检查项信息", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{checkItemId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("checkItemId") int id) {
 
		return toAjax(checkItemService.deleteCheckItem(id));
	}
}
