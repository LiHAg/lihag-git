package com.golden.common.constant;

/**
 * 业务级别状态
 * @author lianyi
 */
public class BusinessContants
{
	/** 业务初始状态-创建-0  */
    public static final String BUSINESS_EXECUTE_CREATE = "0";
    /** 业务执行中-1 */
    public static final String BUSINESS_EXECUTE_INTO = "1";
    /**  执行异常-3 */
    public static final String BUSINESS_EXECUTE_CANCEL = "3";
    /** 业务执行完成-16 */
    public static final String BUSINESS_EXECUTE_SUCCESS = "16";

    /** 治疗室状态-正常 */
    public static final String DEVROOM_NORMAL = "Y";
    /** 治疗室状态-异常 */
    public static final String DEVROOM_ABNORMAL = "N";
    
    /**设备标定状态-已标定*/
    public static final String DEVICE_STATUS_Y = "1";
    /**设备标定状态-未标定*/
    public static final String DEVICE_STATUS_N = "0";

    /**束流标定状态-未标定*/
    public static final String BEAM_STATUS_Y = "0";
    /**束流标定状态-已标定*/
    public static final String BEAM_STATUS_N = "0";
    
    /**治疗状态-等待治疗*/
    public static final String TREAT_STATUS_WAIT = "0";
    /**治疗状态-治疗未完成*/
    public static final String TREAT_STATUS_UNDONE = "1";
    /**治疗状态-治疗失败*/
    public static final String TREAT_STATUS_FAILD = "2";
    /**治疗状态-治疗完成*/
    public static final String TREAT_STATUS_FINISH = "3";
    
    /**患者-正常治疗*/
    public static final String TREAT_FLAG_NORMAL = "0";
    /**患者-模拟治疗*/
    public static final String TREAT_FLAG_TEST = "1";
}
