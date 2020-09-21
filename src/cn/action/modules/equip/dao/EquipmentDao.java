package cn.action.modules.equip.dao;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.equip.entity.Equipment;

public interface EquipmentDao extends CrudDao<Equipment>{
	//根据设备编号查询
	public Equipment getByQRCode(Equipment equipment);
}
