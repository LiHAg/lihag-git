package com.golden.workflow.template.mapper;

import java.util.List;

import com.golden.workflow.template.model.TemplateInfo;

/**
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public interface TemplateInfoMapper {
	TemplateInfo getTemplateInfo(String templateId);
	List<TemplateInfo> getTemplateInfoList();

}
