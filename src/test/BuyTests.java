package src.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import src.main.StockType;
import src.main.TradeType;
import src.main.model.account.Account;
import src.main.model.account.Personal;
import src.main.model.account.TFSA;
import src.main.model.trade.Trade;

public class BuyTests {
    // test where they buy trade and they have the money for it
    @Test
    public void PersonalSuccessfulBuyTrade(){
        Account personal = new Personal(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
    }
    @Test
    public void TFSASuccessfulBuyTrade(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);

    }
    // test where they have insuffient funds
    @Test
    public void PersonalFailedBuyTrade(){
        Account personal = new Personal(200.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),false);

    }
    @Test
    public void TFSAFailedBuyTrade(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,200)),false);

    }
    // check personal balance is correct after a purchase
    @Test
    public void PersonalBalanceAfterBuyTrade(){
        Account personal = new Personal(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(personal.getFunds(),Double.valueOf(435.07140000000004));
    } 
    // check TFSA balance is correct after a purchase
    @Test
    public void TFSABalanceAfterBuyTrade(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        assertEquals(tfsa.getFunds(),Double.valueOf(419.42211399999997));

    }
    // check personal shares are correct after purchase
    @Test
    public void PersonalSharesAfterBuyTrade(){
        Account personal = new Personal(20000.0);
        StockType stock = StockType.AAPL;
        assertEquals(personal.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,200)),true);
        HashMap<StockType,Double> portfolio = personal.getPortofolio();
        assertEquals(portfolio.get(StockType.AAPL),Double.valueOf(200));
    }
    // check TFSA shares are correct after purchase
    @Test
    public void TFSASharesAfterBuyTrade(){
        Account tfsa = new TFSA(2000.0);
        StockType stock = StockType.AAPL;
        assertEquals(tfsa.makeTrade(new Trade(TradeType.MARKET_BUY,stock,15.649286,100)),true);
        HashMap<StockType,Double> portfolio = tfsa.getPortofolio();
        assertEquals(portfolio.get(StockType.AAPL),Double.valueOf(100));

    }
   
    




}
