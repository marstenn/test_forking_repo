package src.main.model.trade;
import src.main.StockType;
import  src.main.TradeType;
public class Trade {
    TradeType trade;
    StockType stock;
    Double stockPrice;
    Double shares;

    public Trade(TradeType trade, StockType stock, Double stockPrice, Double shares) {
        this.trade = trade;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.shares = shares;
    }
    public Trade(Trade source) {
        this.stock = source.stock;
        this.trade= source.trade;
        this.stockPrice = source.stockPrice;
        this.shares = source.shares;
    }

    public TradeType getTrade() {
        return this.trade;
    }

    public StockType getStock() {
        return this.stock;
    }

    public Double getStockPrice() {
        return this.stockPrice;
    }

    public Double getShares() {
        return this.shares;
    }

    @Override
    public String toString() {
        return "{" +
            " trade='" + getTrade() + "'" +
            ", stock='" + getStock() + "'" +
            ", stockPrice='" + getStockPrice() + "'" +
            ", shareAmount='" + getShares() + "'" +
            "}";
    }
    


    
}