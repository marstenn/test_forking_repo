package stocktrader;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import stocktrader.model.account.Personal;
import stocktrader.model.account.TFSA;

public class TestAccountCreation {
   

    @Test
    public void getPersonalSellFee(){
        Personal account = new Personal(1000.0);
        
        assertTrue(account.getSellFee()==0.05);
    }
    @Test
    public void getPersonalBuyFee(){
        Personal account = new Personal(1000.0);
        
        assertTrue(account.getBuyFee()==0.00);
    }
    @Test
    public void getTFSABuyFee(){
        TFSA account = new TFSA(1000.0);
        
        assertTrue(account.getBuyFee()==0.01);
    }
    @Test
    public void getTFSASellFee(){
        TFSA account = new TFSA(1000.0);
        
        assertTrue(account.getSellFee()==0.01);
    }

    @Test
    public void checkAvailableBalance(){
        TFSA account = new TFSA(1000.0);
        assertTrue(account.getFunds()==1000.0);
        
    }

    
    
}
