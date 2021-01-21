package com.jurepinjuh.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Table(name="PURCHASE")
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPURCHASE")
    private int id;
    @NotEmpty(message ="validation.purchase.billingName.notEmpty")
    @Column(name="BILLINGNAME")
    private String billingName;
    @Column(name="BILLINGSURNAME")
    private String billingSurname;
    @NotEmpty(message = "validation.purchase.billingPhone.notEmpty")
    @Column(name="BILLINGPHONE")
    private String billingPhone;
    @Column(name="BILLINGADRESS")
    @NotEmpty(message = "validation.purchase.billingAddress.notEmpty")
    private String billingAddress;
    @NotNull(message="validation.purchase.userid.notNull")
    @Column(name="USERID")
    private int userId;
    @NotNull(message = "validation.purchase.dateOfPurchase")
    @Column(name="DATEOFPURCHASE")
    private Date dateOfPurchase;
    @NotNull(message="validation.article.price.notNull")
    @Column(name="TOTALPRICE")
    private double price;
    @Column(name="DELIVERYNAME")
    @NotEmpty(message = "validation.purchase.deliveryName.notEmpty")
    private String deliveryName;
    @Column(name="DELIVERYSURNAME")
    private String deliverySurname;
    @NotEmpty(message = "validation.purchase.deliveryEmail.notEmpty")
    @Column(name="DELIVERYEMAIL")
    private String deliveryEmail;
    @Column(name="DELIVERYADRESS")
    @NotEmpty(message = "validation.purchase.deliveryAdress.notEmpty")
    private String deliveryAddress;
    @Column(name="REMARK")
    private String remark;

    public Purchase(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingSurname() {
        return billingSurname;
    }

    public void setBillingSurname(String billingSurname) {
        this.billingSurname = billingSurname;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliverySurname() {
        return deliverySurname;
    }

    public void setDeliverySurname(String deliverySurname) {
        this.deliverySurname = deliverySurname;
    }

    public String getDeliveryEmail() {
        return deliveryEmail;
    }

    public void setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
