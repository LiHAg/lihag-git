package com.ciTreat.quality.service;

import java.util.List;

import com.ciTreat.quality.domain.CheckItem;
/**
 * 检查项管理      服务层
 * 
 * @author LQY
 */
public interface CheckItemService {
	/**查询所有检查项*/
	public List<CheckItem> selectCheckItems(CheckItem checkItem);
	/**查询检查项by id*/
	public CheckItem selectCheckItem(Integer checkItemId);
	/**增加检查项*/
	public int insertCheckItem(CheckItem checkItem);
	/**删除检查项*/
	public int deleteCheckItem(Integer id);
	/**修改检查项*/
	public int updateCheckItem(CheckItem checkItem);
	/**查询所有检查状态为不合格或者未检查的检查项*/
	public List<CheckItem> selectCheckItemsPortion(CheckItem checkItem);
}
