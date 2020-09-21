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
	 * �����豸�����ϱ���¼
	 * @param equipFaultReport
	 * @return
	 */
	public boolean saveFaultReport(EquipFaultReport equipFaultReport) {
		//���ݱ�Ų�ѯ�Ƿ��и��豸
		Equipment equipment= new Equipment();
		equipment.setQrCode(equipFaultReport.getEquipNo());
		Equipment equipment2=equipmentService.getByQRCode(equipment);
		if (equipment2==null) {
			return false;
		}
		//�����������
		equipFaultReport.setEquipId(equipment2.getId());//�����豸Id
		equipFaultReport.setReportPerson(UserUtils.getUser().getName());//�����ϱ���
		equipFaultReport.setStatus("0001");//����״̬Ϊ�ϱ�
		equipFaultReport.setEquipLoc(lineService.get(equipFaultReport.getEquipLoc()).getLineNumber());
		//����
		this.save(equipFaultReport);
		return true;
	}
	/**
	 * �ж��Ƿ����ɹ�
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
	 * �ж��Ƿ��ѿ���
	 * @param equipFaultReport
	 * @return
	 */
	public String saveStartRepair(EquipFaultReport equipFaultReport) {
		System.out.println("\n***********************saveStartRepair******************************************\n");

		String message=null;
		EquipFaultReport report = this.get(equipFaultReport);
		if (report.getStatus().equals("0001")) {
			message="�豸�Ѿ����ޣ����ڵȴ��ɹ���";
		}else if (report.getStatus().equals("0003")) {
			message="�豸�Ѿ���ʼά�ޣ������ظ�������";
		}else if (report.getStatus().equals("0004")) {
			message="�豸�Ѿ��깤��";
		}else {
			report.setStatus("0003");
			this.save(report);
			message="���Ͽ�ʼά��";
		}
		System.out.println("\n***********************saveStartRepair  end******************************************\n"+message);

		return message;
	}
}
