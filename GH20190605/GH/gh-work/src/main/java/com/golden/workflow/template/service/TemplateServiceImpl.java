package com.golden.workflow.template.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.template.mapper.TemplateInfoMapper;
import com.golden.workflow.template.model.TemplateInfo;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	TemplateInfoMapper templateInfoMapper;

	@Override
	public TemplateInfo getTemplateInfo(String templateId) {
		return templateInfoMapper.getTemplateInfo(templateId);
	}

	@Override
	public List<TemplateInfo> getTemplateInfoList() {
		return templateInfoMapper.getTemplateInfoList();
	}

}
