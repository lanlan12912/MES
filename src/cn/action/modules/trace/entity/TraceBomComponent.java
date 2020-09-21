package cn.action.modules.trace.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.bas.entity.Bom;
import cn.action.modules.bas.entity.BomDetail;
import cn.action.modules.bas.entity.Product;

public class TraceBomComponent extends DataEntity<TraceBomComponent> {
    private static final long serialVersionUID=1L;
    /**
     *Bom类
     */
    private BomDetail bomDetail;
    private String bomName;
    private String bomVersion;
    private String lotNumber;
    private String mNum;
    private String mType;
    private String unit;
    private String productName;
    private Product product;//产品外键对象
    private String status;
    public TraceBomComponent(){
        this.product=new Product();
        this.bomDetail=new BomDetail();
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getmNum() {
        return mNum;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBomName() {
        return bomName;
    }

    public void setBomName(String bomName) {
        this.bomName = bomName;
    }

    public String getBomVersion() {
        return bomVersion;
    }

    public void setBomVersion(String bomVersion) {
        this.bomVersion = bomVersion;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BomDetail getBomDetail() {
        return bomDetail;
    }

    public void setBomDetail(BomDetail bomDetail) {
        this.bomDetail = bomDetail;
    }
}

