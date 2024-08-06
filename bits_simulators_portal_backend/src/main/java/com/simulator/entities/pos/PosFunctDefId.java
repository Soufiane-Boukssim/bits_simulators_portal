package com.simulator.entities.pos;

import com.simulator.entities.FunctDefId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Data
public class PosFunctDefId implements Serializable {

    @Column(name = "FCT_CODE", nullable = false,length = 3)
    private String fctCode;

    @Column(name = "FCT_PROTO",length = 2, nullable = false)
    private String  fctProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    public String getFctCode() {
        return fctCode;
    }

    public void setFctCode(String fctCode) {
        this.fctCode = fctCode;
    }


    public String getFctProtocol() {
        return fctProtocol;
    }

    public PosFunctDefId(String fctCode, String fctProtocol) {
        this.fctCode = fctCode;
        this.fctProtocol = fctProtocol;
    }
    public void setFctProtocol(String fctProtocol) {
        this.fctProtocol = fctProtocol;
    }








    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosFunctDefId that = (PosFunctDefId) o;
        return Objects.equals(fctCode, that.fctCode) && Objects.equals(fctProtocol, that.fctProtocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fctCode, fctProtocol);
    }

    public Character getFtcProtocol() {
        return null;
    }
}
