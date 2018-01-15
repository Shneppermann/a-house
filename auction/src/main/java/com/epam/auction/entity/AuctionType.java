package com.epam.auction.entity;

import java.util.Objects;

/**
 * Auction type entity
 */
public class AuctionType extends Entity {
    private static final long serialVersionUID = 3688765902570832150L;
    private String auctionType;

    public AuctionType() {
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    @Override
    public AuctionType clone() {
        return (AuctionType) super.clone();
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
        AuctionType that = (AuctionType) object;
        return Objects.equals(auctionType, that.auctionType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), auctionType);
    }

    @Override
    public String toString() {
        return "AuctionType{" +
                "auctionType='" + auctionType + '\'' +
                "} " + super.toString();
    }
}
