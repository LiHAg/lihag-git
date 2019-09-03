package com.ciTreat.web.controller.citreat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golden.framework.web.base.BaseController;

/**
 * 治疗
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/ciTreat/doTreat")
public class CiTreatController extends BaseController {
	private String prefix = "/ciTreat/ciTwork";

	@GetMapping()
	public String doTreat(ModelMap mmap) throws Exception {
		return prefix + "/ciTtask";
	}


}
