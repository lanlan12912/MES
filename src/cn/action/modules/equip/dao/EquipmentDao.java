package cn.action.modules.equip.dao;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.equip.entity.Equipment;

public interface EquipmentDao extends CrudDao<Equipment>{
	//�����豸��Ų�ѯ
	public Equipment getByQRCode(Equipment equipment);
}
