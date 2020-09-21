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
		//修改上报记录为完工
		EquipFaultReport equipFaultReport = equipFaultReportService.get(equipRepair.getMid());
		if (!"0003".equals(equipFaultReport.getStatus())) {
			return "维修报告添加失败，项目未开工";
		}
		equipFaultReport.setStatus("0004");
		equipFaultReportService.save(equipFaultReport);
		//保存维修报告
		this.save(equipRepair);
		return "维修报告添加成功";
	}
	public String uploadPic(MultipartFile picFile) {
		// TODO Auto-generated method stub
		try {
			System.out.println("service uploadPic");
            // 图片新名字
            String name = UUID.randomUUID().toString();
            // 图片原名字
            String oldName = picFile.getOriginalFilename();
            // 后缀名
            String exeName = oldName.substring(oldName.lastIndexOf("."));
            //保存图片路径为绝对路径
            File pic = new File("C:\\Users\\27088\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\action3\\static\\imgs\\" + name + exeName);
            // 保存图片到服务器
            picFile.transferTo(pic);
            return name+exeName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
}
