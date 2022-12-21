package src.main.model.trade;
import src.main.StockType;
import  src.main.TradeType;
public class Trade {
    TradeType tradetype;
    StockType stock;
    Double stockPrice;
    int shares;

    public Trade(TradeType trade, StockType stock, Double stockPrice, int shares) {
        this.tradetype = trade;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.shares = shares;
    }
    public Trade(Trade source) {
        this.stock = source.stock;
        this.tradetype= source.tradetype;
        this.stockPrice = source.stockPrice;
        this.shares = source.shares;
    }

    public TradeType getTrade() {
        return this.tradetype;
    }

    public StockType getStock() {
        return this.stock;
    }

    public Double getStockPrice() {
        return this.stockPrice;
    }

    public int getShares() {
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
