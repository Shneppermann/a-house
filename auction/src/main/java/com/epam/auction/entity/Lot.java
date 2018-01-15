package com.epam.auction.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Lot entity
 */
public class Lot extends Entity{

    private static final long serialVersionUID = -7747497940659745497L;
    private int lotState;
    private int ownerId;
    private int auctionType;
    private String lotName;
    private BigDecimal step;
    private BigDecimal startPrice;

    public Lot(){}


    public Lot(int lotState, int ownerId, int auctionType, String lotName, BigDecimal step, BigDecimal startPrice) {
        this.lotState = lotState;
        this.ownerId = ownerId;
        this.auctionType = auctionType;
        this.lotName = lotName;
        this.step = step;
        this.startPrice = startPrice;
    }

    public int getLotState() {
        return lotState;
    }

    public void setLotState(int lotState) {
        this.lotState = lotState;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(int auctionType) {
        this.auctionType = auctionType;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public BigDecimal getStep() {
        return step;
    }

    public void setStep(BigDecimal step) {
        this.step = step;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }


    @Override
    public Lot clone(){
       return (Lot) super.clone();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        if (!super.equals(object)){
            return false;
        }
        Lot lot = (Lot) object;
        return lotState == lot.lotState &&
                ownerId == lot.ownerId &&
                auctionType == lot.auctionType &&
                Objects.equals(lotName, lot.lotName) &&
                Objects.equals(step, lot.step) &&
                Objects.equals(startPrice, lot.startPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), lotState, ownerId, auctionType, lotName, step, startPrice);
    }

    @Override
    public String toString() {
        return "Lot{" +
                "lotState=" + lotState +
                ", ownerId=" + ownerId +
                ", auctionType=" + auctionType +
                ", lotName='" + lotName + '\'' +
                ", step=" + step +
                ", startPrice=" + startPrice +
                "} " + super.toString();
    }
}
