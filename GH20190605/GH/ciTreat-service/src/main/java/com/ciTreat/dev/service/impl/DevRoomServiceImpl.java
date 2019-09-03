package com.ciTreat.dev.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.dev.domain.DevTreatmentRoom;
import com.ciTreat.dev.mapper.DevRoomMapper;
import com.ciTreat.dev.service.IDevRoomService;
import com.ciTreat.system.domain.SysDept;
import com.golden.common.annotation.DataScope;
import com.golden.common.constant.BusinessContants;
import com.golden.common.constant.UserConstants;

/**
 * 治疗室信息 服务实现
 * 
 * @author LIANYI
 */
@Service
public class DevRoomServiceImpl implements IDevRoomService {
	
	@Autowired
	private DevRoomMapper devRoomMapper; 

	@Override
	@DataScope(tableAlias = "d")
	public List<DevTreatmentRoom> selectDevRoomList(DevTreatmentRoom devTreatmentRoom) {
		
		return devRoomMapper.selectDevRoomList(devTreatmentRoom);
	}

	@Override
	public int selectDevRoomCount(DevTreatmentRoom devTreatmentRoom) {
		
		return devRoomMapper.selectDevRoomCount(devTreatmentRoom);
	}

	@Override
	public int deleteDevRoomById(String treatMentId) {
		
		return devRoomMapper.deleteDevRoomById(treatMentId);
	}

	@Override
	public int insertDevRoom(DevTreatmentRoom devTreatmentRoom) {
		
		return devRoomMapper.insertDevRoom(devTreatmentRoom);
	}

	@Override
	public int updateDevRoom(DevTreatmentRoom devTreatmentRoom) {
		
		return devRoomMapper.updateDevRoom(devTreatmentRoom);
	}

	@Override
	public DevTreatmentRoom selectDevRoomById(String treatMentId) {
		
		return devRoomMapper.selectDevRoomById(treatMentId);
	}

	/**
	 * 查询治疗室树
	 */
	@Override
	@DataScope(tableAlias = "d")
	public List<Map<String, Object>> selectDevRoomTree(DevTreatmentRoom devTreatmentRoom) {
		
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<DevTreatmentRoom> selectDevRoomList = devRoomMapper.selectDevRoomList(devTreatmentRoom);
		trees = getTrees(selectDevRoomList, false, null);
		return trees;
	}
	
	 /**
     * 对象转治疗室树
     *
     * @param selectDevRoomList 治疗室列表
     * @param isCheck 是否需要选中
     * @param roleDeptList 治疗室已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<DevTreatmentRoom> selectDevRoomList, boolean isCheck, List<String> roleDeptList)
    {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (DevTreatmentRoom devTreatmentRoom : selectDevRoomList)
        {
            if (BusinessContants.DEVROOM_NORMAL.equals(devTreatmentRoom.getValidFg()))
            {
                Map<String, Object> devRoomtMap = new HashMap<String, Object>();
                devRoomtMap.put("id", devTreatmentRoom.getTreatMentId());
                devRoomtMap.put("name", devTreatmentRoom.getTreatMentName());
                devRoomtMap.put("description", devTreatmentRoom.getDescription());
                if (isCheck)
                {
                	devRoomtMap.put("checked", roleDeptList.contains(devTreatmentRoom.getTreatMentId() + devTreatmentRoom.getTreatMentName()));
                }
                else
                {
                	devRoomtMap.put("checked", false);
                }
                trees.add(devRoomtMap);
            }
        }
        return trees;
    }

}
