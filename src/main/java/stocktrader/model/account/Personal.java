package stocktrader.model.account;

public class Personal extends Account{
    
    public Personal(Double funds) {
        super(funds);
        sellFee = 0.05;
        buyFee = 0.00;
    }
    public Personal(Personal source) {
        super(source);
    }

    }    
     


