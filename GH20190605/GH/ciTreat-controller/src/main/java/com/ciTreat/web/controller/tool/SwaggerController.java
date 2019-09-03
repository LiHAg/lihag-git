package com.ciTreat.web.controller.tool;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golden.framework.web.base.BaseController;
import com.golden.framework.web.domain.Server;

/**
 * swagger 接口
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController {

	private String prefix = "tool/swagger";

	@GetMapping()
	public String server(ModelMap mmap) throws Exception {
		//Server server = new Server();
		//server.copyTo();
		//mmap.put("server", server);
		return prefix + "/swagger";
	}

}
