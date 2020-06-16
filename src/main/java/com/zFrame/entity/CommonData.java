package com.zFrame.entity;

import java.math.BigDecimal;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：CommonData    
 * 类描述：消费记录    
 * 创建人：Gz    
 * 创建时间：2019年12月10日 下午4:54:41    
 * 修改人：Gz    
 * 修改时间：2019年12月10日 下午4:54:41    
 * 修改备注：    
 * @version     
 *
 */
public class CommonData {

    private String id;

    private String value;

    // 消费前总金额
    private BigDecimal total;

    // 消费后总金额
    private BigDecimal front;

    private Integer version;

    public BigDecimal getTotal() {
        return total;
    }

    public CommonData(String id, String value, BigDecimal total, BigDecimal front, Integer version) {
        super();
        this.id = id;
        this.value = value;
        this.total = total;
        this.front = front;
        this.version = version;
    }

    public CommonData() {
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getFront() {
        return front;
    }

    public void setFront(BigDecimal front) {
        this.front = front;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
