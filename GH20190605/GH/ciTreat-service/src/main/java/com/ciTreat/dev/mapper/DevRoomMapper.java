package com.ciTreat.dev.mapper;

import java.util.List;

import com.ciTreat.dev.domain.DevTreatmentRoom;

/**
 * 治疗室信息 数据层
 * 
 * @author LIANYI
 */
public interface DevRoomMapper
{
    /**
     * 查询治疗室数
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int selectDevRoomCount(DevTreatmentRoom devTreatmentRoom);

    /**
     * 查询治疗室管理数据
     * 
     * @param ApplicationContextProvider 治疗室信息
     * @return 治疗室信息集合
     */
    public List<DevTreatmentRoom> selectDevRoomList(DevTreatmentRoom devTreatmentRoom);

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



}
