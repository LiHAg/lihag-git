package com.golden.workflow.template.service;

import java.util.List;

import com.golden.workflow.template.model.TemplateInfo;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
public interface TemplateService {
	public TemplateInfo getTemplateInfo(String templateId);
	public List<TemplateInfo> getTemplateInfoList();

}
