package com.simulator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "card_profile")
public class CardProfile {

    @EmbeddedId
    private CardProfileId id;

    @Column(name = "CARD_TYPE",length = 2)
    private String cardType;

    @Column(name = "ISSUER_NAME",length = 40)
    private String issuerName;

    @Column(name = "CARD_DESC",length = 40)
    private String cardDesc;

    @Column(name = "ISSUER_CODE",length = 40)
    private String issuerCode;

    @Column(name = "BIN",length = 12)
    private String bin;

    @Column(name = "ACCOUNT_CURR",length = 3)
    private String accountCurr;

    @Column(name = "ACCOUNT_TYPE",length = 3)
    private String accountType;

    @Column(name = "ACC_ACCMOUNT")
    private double accAmount;

    @Column(name = "AVAILABLE")
    private double available;

    @Column(name = "CARD_EXPIRY",length = 8)
    private String cardExpiry;

    @Column(name = "CVN_VAL",length = 3)
    private String cvnVal;

    @Column(name = "CVN2_VAL",length = 7)
    private String cvn2Val;

    @Column(name = "PIN",length = 12)
    private String PIN;

    @Column(name = "CF_TYPE",length = 2)
    private String cfType;

    @Column(name = "CF_NO",length = 32)
    private String cfNo;

    @Column(name = "FIRST_NAME",length = 50)
    private String firstName;

    @Column(name = "LAST_NAME",length = 50)
    private String lastName;

    @Column(name = "MOBILE",length = 20)
    private String mobile;

    @Column(name = "TRACK1",length = 79)
    private String track1;

    @Column(name = "TRACK2",length = 37)
    private String track2;

    @Column(name = "TRACK3",length = 104)
    private String track3;

    @Column(name = "IC_AID",length = 20)
    private String icAid;

    @Column(name = "IC_PROFILE",length = 4)
    private String icProfile;

    @Column(name = "IC_MK_AC",length = 128)
    private String icMkAc;

    @Column(name = "IC_MK_SMC",length = 128)
    private String icMkSmc;

    @Column(name = "IC_MK_SMI",length = 128)
    private String icMkSmi;



    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getAccountCurr() {
        return accountCurr;
    }

    public void setAccountCurr(String accountCurr) {
        this.accountCurr = accountCurr;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAccAmount() {
        return accAmount;
    }

    public void setAccAmount(double accAmount) {
        this.accAmount = accAmount;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double availbal) {
        this.available = availbal;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCvnVal() {
        return cvnVal;
    }

    public void setCvnVal(String cvnVal) {
        this.cvnVal = cvnVal;
    }

    public String getCvn2Val() {
        return cvn2Val;
    }

    public void setCvn2Val(String cvn2Val) {
        this.cvn2Val = cvn2Val;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getCfType() {
        return cfType;
    }

    public void setCfType(String cfType) {
        this.cfType = cfType;
    }

    public String getCfNo() {
        return cfNo;
    }

    public void setCfNo(String cfNo) {
        this.cfNo = cfNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTrack1() {
        return track1;
    }

    public void setTrack1(String track1) {
        this.track1 = track1;
    }

    public String getTrack2() {
        return track2;
    }

    public void setTrack2(String track2) {
        this.track2 = track2;
    }

    public String getTrack3() { return track3; }

    public void setTrack3(String track3) { this.track3 = track3; }
//
//    public String getTRACK3() {
//        return track3;
//    }
//
//    public void setTRACK3(String TRACK3) {
//        this.track3 = TRACK3;
//    }

    public String getIcAid() {
        return icAid;
    }

    public void setIcAid(String icAid) {
        this.icAid = icAid;
    }

    public String getIcProfile() {
        return icProfile;
    }

    public void setIcProfile(String icProfile) {
        this.icProfile = icProfile;
    }

    public String getIcMkAc() {
        return icMkAc;
    }

    public void setIcMkAc(String icMkAc) {
        this.icMkAc = icMkAc;
    }

    public String getIcMkSmc() {
        return icMkSmc;
    }

    public void setIcMkSmc(String icMkSmc) {
        this.icMkSmc = icMkSmc;
    }

    public String getIcMkSmi() {
        return icMkSmi;
    }

    public void setIcMkSmi(String icMkSmi) {
        this.icMkSmi = icMkSmi;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }
    public CardProfileId getId() {
        return id;
    }

    public void setId(CardProfileId id) {
        this.id = id;
    }

    public CardProfile(CardProfileId id, String cardType, String issuerName, String cardDesc, String issuerCode, String bin, String accountCurr, Character cardProtocol, String accountType, double accAmount, double available, String cardExpiry, String cvnVal, String cvn2Val, String PIN, String cfType, String cfNo, String firstName, String lastName, String mobile, String track1, String track2, String TRACK3, String icAid, String icProfile, String icMkAc, String icMkSmc, String icMkSmi) {
        this.id = id;
        this.cardType = cardType;
        this.issuerName = issuerName;
        this.cardDesc = cardDesc;
        this.issuerCode = issuerCode;
        this.bin = bin;
        this.accountCurr = accountCurr;
        this.accountType = accountType;
        this.accAmount = accAmount;
        this.available = available;
        this.cardExpiry = cardExpiry;
        this.cvnVal = cvnVal;
        this.cvn2Val = cvn2Val;
        this.PIN = PIN;
        this.cfType = cfType;
        this.cfNo = cfNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.track1 = track1;
        this.track2 = track2;
        this.track3 = TRACK3;
        this.icAid = icAid;
        this.icProfile = icProfile;
        this.icMkAc = icMkAc;
        this.icMkSmc = icMkSmc;
        this.icMkSmi = icMkSmi;
    }

    public CardProfile() {
    }

    @Override
    public String toString() {
        return "CardProfile{" +
                "id='" + id + '\'' +
                ", cardType='" + cardType + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", cardDesc='" + cardDesc + '\'' +
                ", issuerCode='" + issuerCode + '\'' +
                ", bin='" + bin + '\'' +
                ", accountCurr='" + accountCurr + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accAmount=" + accAmount +
                ", available=" + available +
                ", cardExpiry='" + cardExpiry + '\'' +
                ", cvnVal='" + cvnVal + '\'' +
                ", cvn2Val='" + cvn2Val + '\'' +
                ", PIN='" + PIN + '\'' +
                ", cfType='" + cfType + '\'' +
                ", cfNo='" + cfNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", track1='" + track1 + '\'' +
                ", track2='" + track2 + '\'' +
                ", TRACK3='" + track3 + '\'' +
                ", icAid='" + icAid + '\'' +
                ", icProfile='" + icProfile + '\'' +
                ", icMkAc='" + icMkAc + '\'' +
                ", icMkSmc='" + icMkSmc + '\'' +
                ", icMkSmi='" + icMkSmi + '\'' +
                '}';
    }

}