package com.epam.auction.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Bid entity
 */
public class Bid extends Entity {
    private static final long serialVersionUID = 4986220024851057709L;
    private int ownerId;
    private int lotId;
    private BigDecimal bid;

    public Bid() {
    }

    public Bid(int ownerId, int lotId, BigDecimal bid) {
        this.ownerId = ownerId;
        this.lotId = lotId;
        this.bid = bid;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    @Override
    public Bid clone() {
        return (Bid) super.clone();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Bid bid1 = (Bid) object;
        return ownerId == bid1.ownerId &&
                lotId == bid1.lotId &&
                Objects.equals(bid, bid1.bid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ownerId, lotId, bid);
    }

    @Override
    public String toString() {
        return "Bid{" +
                "ownerId=" + ownerId +
                ", lotId=" + lotId +
                ", bid=" + bid +
                "} " + super.toString();
    }
}
