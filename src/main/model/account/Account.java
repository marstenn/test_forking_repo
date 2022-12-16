package src.main.model.account;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import src.main.StockType;

import src.main.utils.Color;

public abstract class Account {
    Double funds;
    Double sellFee;
    Double buyFee;
    HashMap<StockType,Double> portfolio = new HashMap<StockType,Double>();
    public Account(Double funds) {
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
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }
    public Account(Account source) {
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
    
    public String toString() {
        return "\n  Stock\t\t"  + Color.RESET + "Shares" +
        "\n\n" + displayPortofolio() + Color.RESET +
        "\n  Funds Left\t" + Color.GREEN + "$" + Math.round(this.getFunds()) + Color.RESET;
    }




}
