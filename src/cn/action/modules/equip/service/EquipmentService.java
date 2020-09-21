package cn.action.modules.equip.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.equip.dao.EquipmentDao;
import cn.action.modules.equip.entity.Equipment;
@Service
@Transactional(readOnly=true)
public class EquipmentService extends CrudService<EquipmentDao, Equipment>{
	/**
	 * 根据设备编号查询
	 * @param equipment
	 * @return
	 */
	public Equipment getByQRCode(Equipment equipment) {
		return this.dao.getByQRCode(equipment);
	}
}
