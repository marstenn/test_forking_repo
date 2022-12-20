package src.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.HashMap;

import org.junit.Test;

import src.main.StockType;
import src.main.TradeType;
import src.main.model.account.Account;
import src.main.model.account.Personal;
import src.main.model.account.TFSA;
import src.main.model.trade.Trade;

public class SellTests {

    

    // test where they sell trade and they have the shares for it
    @Test
    public void PersonalSuccessfulSellTrade(){
        
        Account personal = new Personal(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        
           
    }
    @Test
    public void TFSASuccessfulSellTrade(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
       
           
    }
    // test where they have insuffient shares
    @Test
    public void PersonalInsuffientShares(){
        Account personal = new Personal(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,10)),true);
        assertFalse(personal.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)));
       
           
    }
    @Test
    public void TFSAInsuffientShares(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,10)),true);
        assertFalse(tfsa.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)));
        
    }
   
    // check personal balance is correct after sell
    @Test
    public void PersonalCorrectFundAfterSell(){
        Account personal = new Personal(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        assertEquals(personal.getFunds(),Double.valueOf(1742.6106734999998));
           
    }
    // check TFSA balance is correct after a sell
    @Test
    public void TFSACorrectFundAfterSell(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        assertEquals(tfsa.getFunds(),Double.valueOf(1782.0156726999999));
           
           
    }
    // check personal shares are correct after sell
    @Test
    public void PersonalCorrectSharesAfterSell(){
        Account personal = new Personal(20000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,200)),true);
        personal.getFunds();
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        HashMap<StockType,Double> portfolio = personal.getPortofolio();
        assertEquals(portfolio.get(StockType.AAPL),110);
           
    }
    // check TFSA shares are correct after sell
    @Test
    public void TFSACorrectSharesAfterSell(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        tfsa.getFunds();
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        HashMap<StockType,Double> portfolio = tfsa.getPortofolio();
        assertEquals(portfolio.get(StockType.AAPL),10);
           
    }
    
   

    


    

}
