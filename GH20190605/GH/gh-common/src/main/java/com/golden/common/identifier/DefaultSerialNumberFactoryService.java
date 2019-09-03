package com.golden.common.identifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
public class DefaultSerialNumberFactoryService implements SerialNumberFactoryService {
	//private static Logger logger = LoggerFactory.getLogger(DefaultSerialNumberFactoryService.class);
	
	@Autowired
	private SerialNumberMapper serialNumberMapper;
	/**
	 * 产生16位流水号
	 * 格式：当前日期+8位序号
	 * 例如：2018011910000019
	 */
	@Override
	@Transactional(readOnly = true)
	public SerialNumber getCurrentSerialNumber() {
		//return this.myBatisBasedDao.queryForObject(SQL_GET_SERIAL_NUMBER);
		return serialNumberMapper.selectSerialNumber("123");
	}
	

}
