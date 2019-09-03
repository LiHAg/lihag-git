package com.ciTreat.dev.service;

import java.util.List;
import java.util.Map;

import com.ciTreat.dev.domain.DevTreatmentRoom;

/**
 * 治疗室管理 服务层
 * 
 * @author LIANYI
 */
public interface IDevRoomService
{
    /**
     * 查询治疗室管理数据
     * 
     * @param ApplicationContextProvider 治疗室信息
     * @return 治疗室信息集合
     */
    public List<DevTreatmentRoom> selectDevRoomList(DevTreatmentRoom devTreatmentRoom);


    /**
     * 查询治疗室数
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int selectDevRoomCount(DevTreatmentRoom devTreatmentRoom);

    /**
     * 删除治疗室管理信息
     * 
     * @param treatMentId 治疗室ID
     * @return 结果
     */
    public int deleteDevRoomById(String treatMentId);

    /**
     * 新增治疗室信息
     * 
     * @param ApplicationContextProvider 治疗室信息
     * @return 结果
     */
    public int insertDevRoom(DevTreatmentRoom devTreatmentRoom);

    /**
     * 修改治疗室信息
     * 
     * @param ApplicationContextProvider 治疗室信息
     * @return 结果
     */
    public int updateDevRoom(DevTreatmentRoom devTreatmentRoom);
    /**
     * 根据治疗室ID查询信息
     * 
     * @param treatMentId 治疗室ID
     * @return 治疗室信息
     */
    public DevTreatmentRoom selectDevRoomById(String treatMentId);
    
    
    /**
     * 加载治疗室树
     * @return
     */
    public List<Map<String, Object>> selectDevRoomTree(DevTreatmentRoom devTreatmentRoom);
}
