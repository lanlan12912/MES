package cn.action.modules.qc.web;

import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.qc.entity.CutPieceYield;
import cn.action.modules.qc.service.CutPieceYieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="${adminPath}/qc/cutPieceYield")
public class CutPieceYieldController extends BaseController {
	@Autowired
	private CutPieceYieldService cutPieceYieldService;
	@RequestMapping(value= {"echarts",""})
	public String listData(CutPieceYield cutPieceYield, Model model) {
		//处理日期，加上时间
		if(StringUtils.isNotBlank(cutPieceYield.getStartTime())) {
			cutPieceYield.setStartTime(cutPieceYield.getStartTime()+" 00:00:00");
		}
		if(StringUtils.isNotBlank(cutPieceYield.getEndTime())) {
			cutPieceYield.setEndTime(cutPieceYield.getEndTime()+" 23:59:59");
		}
		//查询
		List<CutPieceYield> yields=cutPieceYieldService.findList(cutPieceYield);
		model.addAttribute("yieldList", yields);
		//清除两个时间
		cutPieceYield.setStartTime("");
		cutPieceYield.setEndTime("");
		model.addAttribute("cutPieceYield", cutPieceYield);
		return "modules/qc/cutPieceYieldEcharts";
	}
}
