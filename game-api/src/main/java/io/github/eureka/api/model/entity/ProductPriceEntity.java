package io.github.eureka.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 04/04/2022<br/>
 * Time: 11:34<br/>
 */
@Entity
public class ProductPriceEntity {
    @JsonIgnoreProperties
    public static final String SQL =
            "select ip.product_id, ip.item_id, p.name, p.product_type,\n" +
            "       ipm.payment_method_id, ipm.number, ipm.price\n" +
            "from item_products ip,\n" +
            "     products p,\n" +
            "     item_payment_methods ipm\n" +
            "where ip.item_id = :item_id\n" +
            "  and p.id = ip.product_id\n" +
            "  and ipm.number = :number\n" +
            "  and ip.id = ipm.item_product_id";
    private long productId;
    private long itemId;
    private String name;
    private String productType;
    private long paymentMethodId;
    private int number;
    private int price;

    @Id
    @Column(name = "product_id", nullable = false)
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "item_id", nullable = false)
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "product_type", nullable = false, length = 255)
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Basic
    @Column(name = "payment_method_id", nullable = false)
    public long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

  }
