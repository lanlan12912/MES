package cn.action.modules.trace.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.bas.entity.*;
import cn.action.modules.tec.entity.Flow;
import cn.action.modules.tec.entity.FlowProcess;
import cn.action.modules.tec.entity.Process;

import java.io.InputStream;
import java.io.OutputStream;

public class TraceProcess extends DataEntity<TraceProcess> {
    private static final long serialVersionUID=1L;
    /**
     *Bom类
     */
    private String lotNumber;
    private Product product;//产品外键对象
    private WorkCell workCell;
    private WorkStationInfos workStationInfos;
    private Process process;
    private Flow flow;
    private FlowProcess flowProcess;
    private Employee employee;
    private TraceProcess traceProcess;
    private CellEmployee cellEmployee;

    public TraceProcess(){
        super();
        this.product=new Product();
        this.flowProcess =new FlowProcess();
        this.employee=new Employee();
        this.flow=new Flow();
        this.process=new Process();
        this.workCell=new WorkCell();
        this.workStationInfos=new WorkStationInfos();
        this.cellEmployee=new CellEmployee();
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public WorkCell getWorkCell() {
        return workCell;
    }

    public void setWorkCell(WorkCell workCell) {
        this.workCell = workCell;
    }

    public WorkStationInfos getWorkStationInfos() {
        return workStationInfos;
    }

    public void setWorkStationInfos(WorkStationInfos workStationInfos) {
        this.workStationInfos = workStationInfos;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public FlowProcess getFlowProcess() {
        return flowProcess;
    }

    public void setFlowProcess(FlowProcess flowProcess) {
        this.flowProcess = flowProcess;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TraceProcess getTraceProcess() {
        return traceProcess;
    }

    public void setTraceProcess(TraceProcess traceProcess) {
        this.traceProcess = traceProcess;
    }


    public CellEmployee getCellEmployee() {
        return cellEmployee;
    }

    public void setCellEmployee(CellEmployee cellEmployee) {
        this.cellEmployee = cellEmployee;
    }
}
