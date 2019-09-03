package com.golden.workflow.template.model;

import java.io.Serializable;

import com.golden.workflow.common.AbstractInfo;

public class TemplateInfo extends AbstractInfo implements Serializable {
	private static final long serialVersionUID = 5614464690637165257L;

	protected String templateId; // 模板Id号
	protected String templateName; // 模板名称
	protected String templateComment; // 模板的注解信息

	public String toString() {
		return "[TemplateInfo]: templateId=" + templateId + ",strTemplateName=" + templateName + ",strTemplateComment=" + templateComment;
	}

	public TemplateInfo() {
	}

	public TemplateInfo(String nTemplateId, String strTemplateName, String strTemplateComment) {
		try {
			this.templateId = nTemplateId;
			this.templateName = strTemplateName;
			this.templateComment = strTemplateComment;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return 返回 templateComment。
	 */
	public String getTemplateComment() {
		return templateComment;
	}

	/**
	 * @param templateComment
	 *            要设置的 templateComment。
	 */
	public void setTemplateComment(String templateComment) {
		this.templateComment = templateComment;
	}

	/**
	 * @return 返回 templateId。
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId
	 *            要设置的 templateId。
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return 返回 templateName。
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName
	 *            要设置的 templateName。
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}