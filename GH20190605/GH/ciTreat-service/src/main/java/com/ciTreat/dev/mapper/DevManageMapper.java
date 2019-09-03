package com.ciTreat.dev.mapper;

import java.util.List;

import com.ciTreat.dev.domain.DevManager;

/**
 * 设备管理
 * 
 * @author LIANYI
 */
public interface DevManageMapper
{
	
    public List<DevManager> selectDevManageList(DevManager devManager);

    public List<DevManager> selectDevCalibration(DevManager devManager);
    
    public DevManager selectDevManageById(DevManager devManager);
    
    public int updateDetailDevManage(DevManager devManager);
    
}
