package com.shishuishuia.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName orders
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private Integer orderId;

    private Integer buyerId;

    private Integer sellerId;

    private Integer phoneId;

    private Date createTime;

    private Date updateTime;

    private Date payTime;

    private Date deliverTime;

    private Date confirmTime;

    private Date cancelTime;

    private Date finishTime;

    private Integer orderState;

    private BigDecimal payment;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Orders other = (Orders) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
                && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
                && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
                && (this.getPhoneId() == null ? other.getPhoneId() == null : this.getPhoneId().equals(other.getPhoneId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
                && (this.getDeliverTime() == null ? other.getDeliverTime() == null : this.getDeliverTime().equals(other.getDeliverTime()))
                && (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime()))
                && (this.getCancelTime() == null ? other.getCancelTime() == null : this.getCancelTime().equals(other.getCancelTime()))
                && (this.getFinishTime() == null ? other.getFinishTime() == null : this.getFinishTime().equals(other.getFinishTime()))
                && (this.getOrderState() == null ? other.getOrderState() == null : this.getOrderState().equals(other.getOrderState()))
                && (this.getPayment() == null ? other.getPayment() == null : this.getPayment().equals(other.getPayment()))
                && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getPhoneId() == null) ? 0 : getPhoneId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getDeliverTime() == null) ? 0 : getDeliverTime().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getCancelTime() == null) ? 0 : getCancelTime().hashCode());
        result = prime * result + ((getFinishTime() == null) ? 0 : getFinishTime().hashCode());
        result = prime * result + ((getOrderState() == null) ? 0 : getOrderState().hashCode());
        result = prime * result + ((getPayment() == null) ? 0 : getPayment().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

}