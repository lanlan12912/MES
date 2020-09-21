package cn.action.modules.qc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.entity.Employee;
import cn.action.modules.bas.service.EmployeeService;
import cn.action.modules.qc.dao.ViolactionDao;
import cn.action.modules.qc.entity.Violaction;
import cn.action.modules.tec.entity.ProcessStation;
import cn.action.modules.tec.service.ProcessStationService;

@Service
@Transactional(readOnly = true)
public class ViolactionService extends CrudService<ViolactionDao, Violaction> {
	@Autowired
	private ProcessStationService processStationService;
	@Autowired
	private EmployeeService employeeService;

	/*
	 * 保存
	 */
	public boolean saveViolaction(Violaction violaction) {
		// 将违规记录中的工站对应的工序对象添加到该对象中
		ProcessStation processStation = processStationService
				.findProcessStationByStation(violaction.getWorkStationInfos());
		Employee employee = employeeService.get(violaction.getUserName());
		// 若为空，说明没有匹配的工序，不能添加违规记录
		if (processStation == null) {
			return false;
		}
		violaction.setProcess(processStation.getProcess());
		violaction.setUserName(employee.getEmployeeName());
		this.save(violaction);
		return true;

	}

	/**
	 * 查询某年某月每个员工的违规次数
	 * 
	 * @param violaction
	 * @return
	 */
	public List<Violaction> findTimes(Violaction violaction) {
		// TODO Auto-generated method stub
		System.out.print(violaction.getViolationTime());
		return this.dao.findTimes(violaction);
	}
}
