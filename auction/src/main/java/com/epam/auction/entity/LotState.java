package com.epam.auction.entity;

import java.util.Objects;

/**
 * Lot state entity
 */
public class LotState extends Entity {

    private static final long serialVersionUID = 4968772900388647627L;
    private String lotState;

    public LotState() {
    }

    public String getLotState() {
        return lotState;
    }

    public void setLotState(String lotState) {
        this.lotState = lotState;
    }

    @Override
    public LotState clone() {
        return (LotState) super.clone();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        LotState lotState1 = (LotState) object;
        return Objects.equals(lotState, lotState1.lotState);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), lotState);
    }

    @Override
    public String toString() {
        return "LotState{" +
                "lotStrate='" + lotState + '\'' +
                "} " + super.toString();
    }
}
