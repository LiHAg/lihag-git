package com.ciTreat.web.controller.quality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.ciTreat.quality.domain.CheckItem;
import com.ciTreat.quality.domain.DailyCheckFlowMeter;
import com.ciTreat.quality.service.CheckItemService;
import com.ciTreat.quality.service.DailyCheckFlowMeterService;
import com.golden.common.annotation.Log;
import com.golden.common.base.AjaxResult;
import com.golden.common.enums.BusinessType;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.util.ShiroUtils;
import com.golden.framework.web.base.BaseController;
@Controller
@RequestMapping("/quality/dailyCheck")
public class DailyCheckController extends BaseController{
	private String prefix = "quality/dailyCheck";
	@Autowired
	private IDevRoomService devRoomserver;
	@Autowired
	private DailyCheckFlowMeterService dailyCheckFlowMeterMapper;
	@Autowired
	private CheckItemService checkItemService;
	@GetMapping()
	public String dailyCheck1() {
		return prefix + "/dailyCheck";
	}
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DevTreatmentRoom devTreatmentRoom) {
		startPage();
		List<DevTreatmentRoom> selectDevRoomList = devRoomserver.selectDevRoomList(devTreatmentRoom);
		return getDataTable(selectDevRoomList);	
	}
	/**
     *每日检查-跳转 
     */
	@GetMapping("/dailycheckAction/{treatMentId}")
	public String dailyCheck(@PathVariable("treatMentId") String treatMentId, ModelMap mmap) throws Exception {
		mmap.put("treatMentId", treatMentId);
		return "quality/dailyCheck/dailycheckAction";
	}
	/**
     *检查明细 -跳转
     */
	@GetMapping("/flowmeter/{treatMentId}")
	public String flowmeter(@PathVariable("treatMentId") String treatMentId,ModelMap mmap) {
		mmap.put("treatMentId", treatMentId);
		return "quality/dailyCheck/flowmeter";
	}
    /**
     *检查明细-流水查询 
     */
	@PostMapping("/flowmeterlist/{treatMentId}")
	@ResponseBody
	public TableDataInfo flowmeterlist(@PathVariable("treatMentId") String treatMentId,DailyCheckFlowMeter flowmeter) {
		startPage();
		List<DailyCheckFlowMeter> selectflowmeter=dailyCheckFlowMeterMapper.selectFlowMeter(treatMentId);
		return getDataTable(selectflowmeter);
		
	}
    /**
     * 检查状态修改
     */
    @Log(title = "每日检查", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(CheckItem checkItem)
    {
    	checkItem.setCreateBy(ShiroUtils.getLoginName());
    	return toAjax(checkItemService.updateCheckItem(checkItem));
    }
 
	@Log(title = "每日检查完成-插入流水", businessType = BusinessType.INSERT)
	@PostMapping("/success/{treatMentId}")
	@ResponseBody
	public AjaxResult success(@PathVariable("treatMentId") String treatMentId , DailyCheckFlowMeter dailyCheckFlowMeter,DevTreatmentRoom devTreatmentRoom) {
		/*更新治疗室表的治疗室状态为已检查*/
		devTreatmentRoom.setTreatMentId(treatMentId);
		devTreatmentRoom.setCheckStatus(1);
		devRoomserver.updateDevRoom(devTreatmentRoom);
		dailyCheckFlowMeter.setCreateBy(ShiroUtils.getLoginName());
		/*向流水表插入流水*/
		int dom= new Random().nextInt(999999);
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat time = new SimpleDateFormat("HHmmss");
        String tradeDate = date.format(new Date());
        String tradeTime = time.format(new Date());
        String no = tradeDate+tradeTime;
        int radom = new Random().nextInt(9999);
		String num=String.valueOf(radom);
		dailyCheckFlowMeter.setCreateTime(new Date());
		dailyCheckFlowMeter.setFlowMeterId("170"+no+num);
		dailyCheckFlowMeter.setCheckStatus(1);
		dailyCheckFlowMeter.setTreatMentId(treatMentId);
		return toAjax(dailyCheckFlowMeterMapper.insertDailyCheckFlowMeter(dailyCheckFlowMeter));
	}
}
