
package models;

import java.math.BigInteger;

public class StockDTO {
    
    private String symbol;
    private Float ask;
    private Float bid;
    private Integer asize;
    private Integer bsize;
    private BigInteger timestamp;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getAsk() {
        return ask;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Integer getAsize() {
        return asize;
    }

    public void setAsize(Integer asize) {
        this.asize = asize;
    }

    public Integer getBsize() {
        return bsize;
    }

    public void setBsize(Integer bsize) {
        this.bsize = bsize;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }
    
}
