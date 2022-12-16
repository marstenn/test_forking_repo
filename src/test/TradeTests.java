package src.test;
/**
 * Trade types
 * invalid amount
 * 
 */
import src.main.StockType;
import  src.main.TradeType;
import org.junit.Test;

import src.main.model.trade.Trade;

public class TradeTests {
    @Test
    public void checkValidTrade(){
        Trade trade = new Trade(TradeType.MARKET_BUY,StockType.AAPL,15.00,1.0);

    }

    
}
