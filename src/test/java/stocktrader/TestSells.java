package stocktrader;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import stocktrader.model.account.Account;
import stocktrader.model.account.Personal;
import stocktrader.model.account.TFSA;
import stocktrader.model.trade.Trade;

import java.util.HashMap;

import org.junit.jupiter.api.Test;


public class TestSells {

    

    // test where they sell trade and they have the shares for it
    @Test
    public void successfulSellTradePersonal(){
        
        Account personal = new Personal(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        
           
    }
    @Test
    public void successfulSellTradeTFSA(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
       
           
    }
    // test where they have insuffient shares
    @Test
    public void insuffientSharesPersonal(){
        Account personal = new Personal(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,10)),true);
        assertFalse(personal.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)));
       
           
    }
    @Test
    public void insuffientSharesTFSA(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,10)),true);
        assertFalse(tfsa.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)));
        
    }
   
    // check personal balance is correct after sell
    @Test
    public void correctFundAfterSellPersonal(){
        Account personal = new Personal(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        assertEquals(personal.getFunds(),Double.valueOf(1742.6106734999998));
           
    }
    // check TFSA balance is correct after a sell
    @Test
    public void correctFundAfterSellTFSA(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        assertEquals(tfsa.getFunds(),Double.valueOf(1782.0156726999999));
           
           
    }
    // check personal shares are correct after sell
    @Test
    public void correctSharesAfterSellPersonal(){
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
    public void correctSharesAfterSellTFSA(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        tfsa.getFunds();
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_SELL,StockType.AAPL,15.292857,90)),true);
        HashMap<StockType,Double> portfolio = tfsa.getPortofolio();
        assertEquals(portfolio.get(StockType.AAPL),10);
           
    }
    
   

    


   

}
