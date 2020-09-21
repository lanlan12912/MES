package cn.action.modules.equip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.common.utils.UserUtils;
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.equip.dao.EquipFaultReportDao;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.entity.Equipment;
@Service
@Transactional(readOnly=true)
public class EquipFaultReportService extends CrudService<EquipFaultReportDao, EquipFaultReport>{
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private LineService lineService;
	/**
	 * 保存设备故障上报记录
	 * @param equipFaultReport
	 * @return
	 */
	public boolean saveFaultReport(EquipFaultReport equipFaultReport) {
		//根据编号查询是否有该设备
		Equipment equipment= new Equipment();
		equipment.setQrCode(equipFaultReport.getEquipNo());
		Equipment equipment2=equipmentService.getByQRCode(equipment);
		if (equipment2==null) {
			return false;
		}
		//设置相关属性
		equipFaultReport.setEquipId(equipment2.getId());//设置设备Id
		equipFaultReport.setReportPerson(UserUtils.getUser().getName());//设置上报人
		equipFaultReport.setStatus("0001");//设置状态为上报
		equipFaultReport.setEquipLoc(lineService.get(equipFaultReport.getEquipLoc()).getLineNumber());
		//保存
		this.save(equipFaultReport);
		return true;
	}
	/**
	 * 判断是否已派工
	 * @param equipFaultReport
	 * @return
	 */
	public boolean isAssign(EquipFaultReport equipFaultReport) {
		EquipFaultReport report = this.get(equipFaultReport);
		if (!report.getStatus().equals("0001")) {
			return false;
		}
		return true;
	}
	/**
	 * 判断是否已开工
	 * @param equipFaultReport
	 * @return
	 */
	public String saveStartRepair(EquipFaultReport equipFaultReport) {
		System.out.println("\n***********************saveStartRepair******************************************\n");

		String message=null;
		EquipFaultReport report = this.get(equipFaultReport);
		if (report.getStatus().equals("0001")) {
			message="设备已经报修，正在等待派工！";
		}else if (report.getStatus().equals("0003")) {
			message="设备已经开始维修，不能重复开工！";
		}else if (report.getStatus().equals("0004")) {
			message="设备已经完工！";
		}else {
			report.setStatus("0003");
			this.save(report);
			message="故障开始维修";
		}
		System.out.println("\n***********************saveStartRepair  end******************************************\n"+message);

		return message;
	}
}
