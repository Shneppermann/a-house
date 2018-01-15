package com.epam.auction.entity.dto;


import java.math.BigDecimal;
import java.util.Objects;

/**
 * Lot DTO object
 */

public class LotDto extends AbstractDto {

    private static final long serialVersionUID = -1387839932856740873L;
    private int highBidId;
    private String lotName;
    private int lotOwnerId;
    private BigDecimal step;
    private int bidOwnerId;
    private BigDecimal bid;
    private String lotState;
    private String auctionType;

    public LotDto() {
    }

    public LotDto(int id, int highBidId, String lotName, int lotOwnerId, BigDecimal step,
                  int bidOwnerId, BigDecimal bid, String lotState, String auctionType) {
        super(id);
        this.highBidId = highBidId;
        this.lotName = lotName;
        this.lotOwnerId = lotOwnerId;
        this.step = step;
        this.bidOwnerId = bidOwnerId;
        this.bid = bid;
        this.lotState = lotState;
        this.auctionType = auctionType;
    }

    public int getHighBidId() {
        return highBidId;
    }

    public void setHighBidId(int highBidId) {
        this.highBidId = highBidId;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public int getLotOwnerId() {
        return lotOwnerId;
    }

    public void setLotOwnerId(int lotOwnerId) {
        this.lotOwnerId = lotOwnerId;
    }

    public BigDecimal getStep() {
        return step;
    }

    public void setStep(BigDecimal step) {
        this.step = step;
    }

    public int getBidOwnerId() {
        return bidOwnerId;
    }

    public void setBidOwnerId(int bidOwnerId) {
        this.bidOwnerId = bidOwnerId;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getLotState() {
        return lotState;
    }

    public void setLotState(String lotState) {
        this.lotState = lotState;
    }

    public String getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = auctionType;
    }

    @Override
    public LotDto clone() {
        return (LotDto) super.clone();
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
        LotDto lotDto = (LotDto) object;
        return highBidId == lotDto.highBidId &&
                lotOwnerId == lotDto.lotOwnerId &&
                bidOwnerId == lotDto.bidOwnerId &&
                Objects.equals(lotName, lotDto.lotName) &&
                Objects.equals(step, lotDto.step) &&
                Objects.equals(bid, lotDto.bid) &&
                Objects.equals(lotState, lotDto.lotState) &&
                Objects.equals(auctionType, lotDto.auctionType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), highBidId, lotName, lotOwnerId, step, bidOwnerId, bid, lotState, auctionType);
    }

    @Override
    public String toString() {
        return "LotDto{" +
                "highBidId=" + highBidId +
                ", lotName='" + lotName + '\'' +
                ", lotOwnerId=" + lotOwnerId +
                ", step=" + step +
                ", bidOwnerId=" + bidOwnerId +
                ", bid=" + bid +
                ", lotState='" + lotState + '\'' +
                ", auctionType='" + auctionType + '\'' +
                "} " + super.toString();
    }
}
