package com.ciTreat.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.quality.domain.CheckItem;
import com.ciTreat.quality.mapper.CheckItemMapper;
import com.ciTreat.quality.service.CheckItemService;

@Service
public class CheckItemServiceImpl implements CheckItemService {
	
	@Autowired
	private CheckItemMapper checkItemMapper;
	@Override
	public List<CheckItem> selectCheckItems(CheckItem checkItem) {
		// TODO Auto-generated method stub
		return checkItemMapper.selectCheckItems(checkItem);
	}

	@Override
	public CheckItem selectCheckItem(Integer checkItemId) {
		// TODO Auto-generated method stub
		return checkItemMapper.selectCheckItem(checkItemId);
	}

	@Override
	public int insertCheckItem(CheckItem checkItem) {
		// TODO Auto-generated method stub
		return checkItemMapper.insertCheckItem(checkItem);
	}

	@Override
	public int deleteCheckItem(Integer id) {
		// TODO Auto-generated method stub
		return checkItemMapper.deleteCheckItem(id);
	}

	@Override
	public int updateCheckItem(CheckItem checkItem) {
		// TODO Auto-generated method stub
		return checkItemMapper.updateCheckItem(checkItem);
	}
	@Override
	public List<CheckItem> selectCheckItemsPortion(CheckItem checkItem) {
		// TODO Auto-generated method stub
		return checkItemMapper.selectCheckItemsPortion(checkItem);
	}
}
