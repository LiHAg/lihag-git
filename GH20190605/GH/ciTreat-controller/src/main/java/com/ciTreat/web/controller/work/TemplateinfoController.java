package com.ciTreat.web.controller.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.template.model.TemplateInfo;
import com.golden.workflow.template.service.TemplateService;

/**
 * 服务器监控
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/work/templateinfo")
public class TemplateinfoController extends BaseController {
	private String prefix = "work/templateinfo";
	@Autowired
    private TemplateService templateService;
    
	@GetMapping()
	public String ciTtask(ModelMap mmap) throws Exception {
		return prefix + "/templateinfo";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TemplateInfo obj) {
		startPage();
		List<TemplateInfo> list = templateService.getTemplateInfoList();
		return getDataTable(list);
	}

	/**
     * 查询字典详细
     */
    @GetMapping("/detail/{templateId}")
    public String detail(@PathVariable("templateId") String templateId, ModelMap mmap)
    {
        //mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        mmap.put("templateId", templateId);
        return "work/nodeInfo/nodeInfo";
    }
}
