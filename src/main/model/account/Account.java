package src.main.model.account;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import src.main.StockType;
import src.main.TradeType;
import src.main.model.trade.Trade;
import src.main.utils.Color;

public abstract class Account {
    Double funds;
    Double sellFee;
    Double buyFee;
    Trade[] tradeHistory;
    HashMap<StockType,Double> portfolio = new HashMap<StockType,Double>();
    protected Account(Double funds) {
        this.funds = funds;
        
        createPortoflio();

    }
    
    private void createPortoflio(){
        for (StockType stock : StockType.values()) {
            this.portfolio.put(stock, 0.0);
        }

    }
    private Map<StockType, Double> copyMap(Map<StockType, Double> map) {
        return map.entrySet().stream()
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
    protected Account(Account source) {
        this.portfolio = (HashMap<StockType, Double>) copyMap(source.portfolio);
        this.funds = source.funds;
    }
    public String displayPortofolio(){
        return portfolio.toString();
    }
    
    public Double getFunds(){
        return this.funds;

    }
    public Double getSellFee(){
        return sellFee;
    } 
    public Double getBuyFee(){
        return buyFee;
    }
    public  HashMap<StockType,Double> getPortofolio(){
        return this.portfolio;
    }
    public String toString() {
        return "\n  Stock\t\t"  + Color.RESET + "Shares" +
        "\n\n" + displayPortofolio() + Color.RESET +
        "\n  Funds Left\t" + Color.GREEN + "$" + Math.round(this.getFunds()) + Color.RESET;
    }

    public boolean makeTrade(Trade trade){
        return (trade.getTrade().equals(TradeType.MARKET_BUY)? makeBuyTrade(trade): makeSellTrade(trade));
           
        
        
    }
    public boolean makeBuyTrade(Trade trade){

        Double fundBalance = this.funds-((trade.getStockPrice()*trade.getShares())+(trade.getStockPrice()*trade.getShares())*getBuyFee());
        if(fundBalance>=0){
            this.funds = fundBalance;
            portfolio.replace(trade.getStock(), (portfolio.get(trade.getStock())+trade.getShares()));



            return true;
        }else{
            return false;
        }

    }
    public boolean makeSellTrade(Trade trade){
   
        Double stockShares = portfolio.get(trade.getStock());
        Double newStockShares = stockShares - trade.getShares();
        if (newStockShares>=0){
            this.funds = this.funds + ((trade.getStockPrice()*trade.getShares())-(trade.getStockPrice()*trade.getShares())*getSellFee());
            portfolio.replace(trade.getStock(), (portfolio.get(trade.getStock())-trade.getShares()));

            return true;
        }else{
            return false;
        }

    }



}
