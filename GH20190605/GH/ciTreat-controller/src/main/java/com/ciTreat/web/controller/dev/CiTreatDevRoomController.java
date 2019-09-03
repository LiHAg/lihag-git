package com.ciTreat.web.controller.dev;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciTreat.dev.domain.DevTreatmentRoom;
import com.ciTreat.dev.service.IDevRoomService;
import com.golden.common.annotation.Log;
import com.golden.common.base.AjaxResult;
import com.golden.common.enums.BusinessType;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.util.ShiroUtils;
import com.golden.framework.web.base.BaseController;

/**
 * 治疗室信息
 * 
 * @author LIANYI
 */
@Controller
@RequestMapping("/dev/devRoom")
public class CiTreatDevRoomController extends BaseController {

	private String prefix = "dev/devRoom";

	@Autowired
	private IDevRoomService devRoomserver;

	@GetMapping()
	public String devRoom() {
		return prefix + "/devRoom";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevTreatmentRoom devTreatmentRoom) {
		startPage();
		List<DevTreatmentRoom> selectDevRoomList = devRoomserver.selectDevRoomList(devTreatmentRoom);

		return getDataTable(selectDevRoomList);
	}

	/**
	 * 新增治疗室信息
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	@Log(title = "治疗室信息", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult insert(DevTreatmentRoom devTreatmentRoom) {

		devTreatmentRoom.setCreateBy(ShiroUtils.getLoginName());

		return toAjax(devRoomserver.insertDevRoom(devTreatmentRoom));
	}

	/**
	 * 修改治疗室信息
	 */
	@GetMapping("/edit/{treatMentId}")
	public String edit(@PathVariable("treatMentId") String treatMentId, ModelMap mmap) {
		DevTreatmentRoom selectDevRoomById = devRoomserver.selectDevRoomById(treatMentId);
		mmap.put("devTreatmentRoom", selectDevRoomById);
		return prefix + "/edit";
	}

	@Log(title = "治疗室信息", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(DevTreatmentRoom devTreatmentRoom) {

		devTreatmentRoom.setCreateBy(ShiroUtils.getLoginName());

		return toAjax(devRoomserver.updateDevRoom(devTreatmentRoom));
	}

	@Log(title = "治疗室信息", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{treatMentId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("treatMentId") String treatMentId) {
 
		return toAjax(devRoomserver.deleteDevRoomById(treatMentId));
	}

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData()
    {
        List<Map<String, Object>> tree = devRoomserver.selectDevRoomTree(new DevTreatmentRoom());
        return tree;
    }
    
}
