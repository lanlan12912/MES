package cn.action.modules.equip.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import cn.action.common.service.CrudService;
import cn.action.modules.equip.dao.EquipRepairDao;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.entity.EquipRepair;
@Service
@Transactional(readOnly=true)
public class EquipRepairService extends CrudService<EquipRepairDao, EquipRepair>{
	@Autowired
	private EquipFaultReportService equipFaultReportService;
	public String saveRepair(EquipRepair equipRepair) {
		//�޸��ϱ���¼Ϊ�깤
		EquipFaultReport equipFaultReport = equipFaultReportService.get(equipRepair.getMid());
		if (!"0003".equals(equipFaultReport.getStatus())) {
			return "ά�ޱ������ʧ�ܣ���Ŀδ����";
		}
		equipFaultReport.setStatus("0004");
		equipFaultReportService.save(equipFaultReport);
		//����ά�ޱ���
		this.save(equipRepair);
		return "ά�ޱ�����ӳɹ�";
	}
	public String uploadPic(MultipartFile picFile) {
		// TODO Auto-generated method stub
		try {
			System.out.println("service uploadPic");
            // ͼƬ������
            String name = UUID.randomUUID().toString();
            // ͼƬԭ����
            String oldName = picFile.getOriginalFilename();
            // ��׺��
            String exeName = oldName.substring(oldName.lastIndexOf("."));
            //����ͼƬ·��Ϊ����·��
            File pic = new File("C:\\Users\\27088\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\action3\\static\\imgs\\" + name + exeName);
            // ����ͼƬ��������
            picFile.transferTo(pic);
            return name+exeName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
}
