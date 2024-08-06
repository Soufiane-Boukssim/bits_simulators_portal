package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@NoArgsConstructor
public class MerchantParamId implements Serializable {

    @Column(name = "MER_CODE", nullable = false)
    private String merCode;

    @Column(name = "MER_PROTO", length = 2 )
    private String merProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getMerProtocol() {
        return merProtocol;
    }

    public void setMerProtocol(String merProtocol) {
        this.merProtocol = merProtocol;
    }

    public MerchantParamId(String merCode, String merProtocol) {
        this.merCode = merCode;
        this.merProtocol = merProtocol;
    }

    public MerchantParamId(String merCode, String merProtocol, String bankCode) {
        this.merCode = merCode;
        this.merProtocol = merProtocol;
        this.bankCode = bankCode;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
