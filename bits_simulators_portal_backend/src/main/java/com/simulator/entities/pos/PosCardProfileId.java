package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosCardProfileId implements Serializable {

    @Column(name = "CARD_NO", nullable = false,length = 20)
    private String cardNo;

    @Column(name = "CARD_PROTO",length = 2, nullable = false)
    private String cardProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public PosCardProfileId(String cardNumber) {
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardProtocol() {
        return cardProtocol;
    }

    public void setCardProtocol(String cardProtocol) {
        this.cardProtocol = cardProtocol;
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
