package cn.action.modules.bas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.WorkOrderDao;
import cn.action.modules.bas.entity.Bom;
import cn.action.modules.bas.entity.BomDetail;
import cn.action.modules.bas.entity.Material;
import cn.action.modules.bas.entity.Order;
import cn.action.modules.bas.entity.WorkOrder;

@Service
@Transactional(readOnly=true)
public class WorkOrderService extends CrudService<WorkOrderDao,WorkOrder>{
	@Autowired
	private BomService bomService;
	@Autowired
	private BomDetailService bomDetailService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MaterialService materialService;
	/**
	 * ���湤��
	 * @param workOrder
	 * @return
	 */
	public boolean saveAll(WorkOrder workOrder){
		boolean flag=true;
		//�ж����޸Ļ�����ӵı���
		if(workOrder.getIsNewRecord()) {
			//1.����ж�
			flag=this.stockProcessing(workOrder);
			if(!flag) {
				return flag;
			}
			//2.������ʱ���޸Ĺ�����Ӧ�Ķ���״̬ΪworkOrder
			Order order=orderService.get(workOrder.getOrder().getId());
			order.setStatus("workOrder");
			orderService.save(order);
		}
		//3.���湤��
		if(workOrder.getActStartTime().equals("")){
			workOrder.setActStartTime(null);
		}
		System.out.println("------"+workOrder.getState());
		this.save(workOrder);
		return flag;
	}
	
	//ģ��wmsϵͳ�Ŀ�洦���߼�
	private boolean stockProcessing(WorkOrder workOrder)
	{
		Bom tempBom=new Bom();
		tempBom.setProduct(workOrder.getProduct());
		List<Bom>boms=bomService.findList(tempBom);
		for(int i=0;i<boms.size();i++)
		{
			System.out.println("$$$$$$"+boms.get(i).getBomName());
		}
		BomDetail tempDetail=new BomDetail();
		tempDetail.setBom(boms.get(0));
		List<BomDetail> bomdetailList =bomDetailService.findList(tempDetail);
		int allNums=workOrder.getAmount()*24;
		double nums=0;
		double curSum=0;
		for(BomDetail detail:bomdetailList) {
			Material m=new Material();
			m.setmType(detail.getmType());
			System.out.println("############"+detail.getmType());
			curSum=materialService.findSum(m);
			List<Material> curMaterialList=materialService.findList(m);
			if(detail.getmType().indexOf("bag")>-1||detail.getmType().indexOf("fresh")>-1)
			{
				nums=allNums*detail.getmNum();
			}
			if(curSum<nums)
			{
				return false;
			}
			for(Material temp:curMaterialList) {
				if(temp.getQuantity()>=nums) {
					temp.setQuantity(temp.getQuantity()-nums);
					materialService.save(temp);
					break;
				}
				nums=nums-temp.getQuantity();
				temp.setQuantity(0);
				materialService.save(temp);
			}
			return true;
		}
		return false;
	}
	/**
	 * ɾ������
	 * @param workOrder
	 * @return
	 */
	public boolean deleteOrder(WorkOrder workOrder) {
		//״̬�ǡ�δ�������Ĺ�������ɾ��
		//�жϹ�����״̬
		if(!workOrder.getState().equals("�ѷ���")) {
			//ɾ��
			this.delete(workOrder);
			//�޸Ķ�Ӧ�Ķ���״̬λ���¶�����new Order
			Order tempOrder = orderService.get(workOrder.getOrder());
			tempOrder.setStatus("newOrder");
			orderService.save(tempOrder);
			return true;
		}
		return false;
	}
}
