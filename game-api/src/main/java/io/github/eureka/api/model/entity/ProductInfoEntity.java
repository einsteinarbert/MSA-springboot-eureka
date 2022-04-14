package io.github.eureka.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 13/04/2022<br/>
 */
@Entity
public class ProductInfoEntity {
    @JsonIgnoreProperties
    public static final String SQL =
            "select p.product_type, " +
                    "       p.id    as product_id, " +
                    "       p.name  as product_name, " +
                    "       p.end_date, " +
                    "       ip.item_id, " +
                    "       ip.item_type, " +
                    "       ipm.id  as item_payment_methods_id, " +
                    "       ipm.number, " +
                    "       ipm.price, " +
                    "       ipm.payment_method_id, " +
                    "       pm.name as pay_method_name, " +
                    "       pm.payment_type, " +
                    "       p.status " +
                    "from products p, " +
                    "     item_products ip, " +
                    "     item_payment_methods ipm, " +
                    "     payment_methods pm " +
                    "where p.end_date >= CURDATE() " +
                    "  and p.status = 1 " +
                    "  and ip.product_id = p.id " +
                    "  and ipm.item_product_id = ip.id " +
                    "  and pm.id = ipm.payment_method_id";
    private String productType;
    private long productId;
    private String productName;
    private Date endDate;
    private long itemId;
    private int itemType;
    private long itemPaymentMethodsId;
    private int number;
    private int price;
    private long paymentMethodId;
    private String payMethodName;
    private int paymentType;
    private int status;

    @Basic
    @Column(name = "product_type")
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Id
    @Basic
    @Column(name = "product_id")
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "item_id")
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "item_type")
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Basic
    @Column(name = "item_payment_methods_id")
    public long getItemPaymentMethodsId() {
        return itemPaymentMethodsId;
    }

    public void setItemPaymentMethodsId(long itemPaymentMethodsId) {
        this.itemPaymentMethodsId = itemPaymentMethodsId;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "payment_method_id")
    public long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Basic
    @Column(name = "pay_method_name")
    public String getPayMethodName() {
        return payMethodName;
    }

    public void setPayMethodName(String payMethodName) {
        this.payMethodName = payMethodName;
    }

    @Basic
    @Column(name = "payment_type")
    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
