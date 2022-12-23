package stocktrader.model.account;

public class TFSA  extends Account{
   
    public TFSA(Double funds) {
        super(funds);
        sellFee = 0.01;
        buyFee = 0.01;
    }
    
    public TFSA(TFSA source) {
        super(source);
    }
}
