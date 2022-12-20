package src.main;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import src.main.model.account.Account;
import src.main.model.account.Personal;
import src.main.model.account.TFSA;
import src.main.model.trade.Trade;
import src.main.utils.Color;

public class Main {

    static Account account; 
    static final double INITIAL_DEPOSIT = 4000;
    static Scanner scanner = new Scanner(System.in);
  
    public static void main(String[] args) { 
            
            traderIntro();   
            explainApp();
            account = createAccount();
            initialBalance();
        
            for (int day = 1; day <= 2160; day++) {
                System.out.println("\n----------- Day"+ day+" -----------");
                
                System.out.println("\n"+account.toString());
                System.out.println("\n");
                displayPrices(day);
                String choice = buyOrSell();
                StockType stock = chooseStock();
                int shares = numShares(choice);
                String result = account.makeTrade(new Trade((choice.equals("buy") ? TradeType.MARKET_BUY : TradeType.MARKET_SELL),stock, Double.parseDouble(getPrice(stock,day)),shares))?"success":"failure";
                System.out.println(result);
            }
            
      
    }
    public static Account createAccount(){
        String choice = accountChoice();
        Double incomingFunds = initialfunds();
        
        while (Boolean.FALSE.equals(accountConfirmation(choice, incomingFunds))){
            choice = accountChoice();
            incomingFunds = initialfunds();
        }
         
        if (choice.equalsIgnoreCase("a")){
            return new Personal(incomingFunds);
        }else{
            return new TFSA(incomingFunds);
            
        }
        
    }
 
    public static Boolean accountConfirmation(String account, Double fund){
        System.out.print("\nPlease confirm your selection:");
        System.out.print("\nAccount:" + Color.YELLOW + (account.equalsIgnoreCase("a")?"Personal Account":"TFSA Account") + Color.RESET );
        System.out.print("\nInitial fund :" + Color.YELLOW + fund + Color.RESET +"\n");
        System.out.print("\nType 'y' to confirm or 'n' to  decline ");
        scanner.nextLine();
        String confirmation = scanner.nextLine();
        while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n")) {
            System.out.print("\n Type 'y' to confirm or 'n' to  decline ");
            confirmation = scanner.nextLine();
        }
        return confirmation.equalsIgnoreCase("y");

    }
    public static Double initialfunds(){
        Double funds = 0.0;
        while( funds <= 0){
            System.out.print("\nHow much would you like to put into your account ?\n");
            if (scanner.hasNextDouble()) {
            funds = scanner.nextDouble();
            }else{
                scanner.nextLine();
                System.out.println(("\nInvalid Input. Please try again."));
            }
        }
        return funds;
    }
    public static void traderIntro(){
        System.out.print(Color.GREEN + "\n ---------------------  STOCK TRADING SIMULATOR  ---------------------");
        System.out.print(Color.GREEN + "\n                              Good Morning.");
        System.out.print(Color.GREEN + "\n                  In this simulator you will trade stocks.");
        System.out.print(Color.GREEN + "\n               There are two types of accounts to choose from.");
        System.out.print(Color.GREEN + "\n ----------------------------------------------------------------------  ");

    }

    public static void explainApp() {
        System.out.print(Color.BLUE + "\n - PERSONAL: ");
        System.out.println(Color.YELLOW + "Every sale made in a personal account is charged a 5% fee.");
        System.out.print(Color.BLUE + "\n - TFSA: ");
        System.out.println(Color.YELLOW + "Every trade (buy/sell) made from a TFSA is charged a 1% fee.\n");
        System.out.println(Color.BLUE + " - Neither account has a limit on the amount of trades that can be made." + Color.RESET);
    }
    
    public static void initialBalance() {
        System.out.print("\n\n  You created a " + Color.YELLOW + account.getClass().getSimpleName() + Color.RESET + " account.");
        System.out.println(" Your account balance is " + Color.GREEN + "$" + account.getFunds() + Color.RESET);
        System.out.print("\n  Enter anything to start trading: ");
        scanner.nextLine();
    }


    public static String accountChoice() {
        System.out.print("\n  Respectively, type 'a' or 'b' to create a Personal account or TFSA: ");
        String choice = scanner.nextLine();
        while (!choice.equalsIgnoreCase("a") && !choice.equalsIgnoreCase("b")) {
            System.out.print("  Respectively, type 'a' or 'b' to create a Personal account or TFSA: ");
            choice = scanner.nextLine();
        }
        return choice;
    }
    
    
    public static String buyOrSell() {
        System.out.print("\n\n  Would you like to 'buy' or 'sell': ");
        String choice = scanner.nextLine();
        while (!choice.equals("buy") && !choice.equals("sell")) {
            System.out.print("  Would you like to 'buy' or 'sell': ");
            choice = scanner.nextLine();
        }
        return choice;
    }

    public static StockType chooseStock() {
        System.out.print("  Choose a stock: ");
        String stockString = scanner.nextLine(); 
        while (!stockString.equalsIgnoreCase("AAPL") && !stockString.equalsIgnoreCase("FB") && !stockString.equalsIgnoreCase("GOOG") && !stockString.equalsIgnoreCase("TSLA") ) {
            System.out.print("  Choose a stock: ");
            stockString = scanner.nextLine();
        }
        
        return StockType.valueOf(stockString.toUpperCase());
    }

    public static int numShares(String choice) {
        System.out.print("  Enter the number of shares you'd like to " + choice + ": ");
        int shares = scanner.nextInt(); 
        scanner.nextLine(); //throwaway nextLine
        while (shares <= 0) {
            System.out.print("  Enter the number of shares you'd like to " + choice + ": ");
            shares = scanner.nextInt();
            scanner.nextLine(); //throwaway nextLine

        }
        return shares;
    }
    
    
    public static void displayPrices(int day) {
        System.out.println("\n\n\t  DAY " + day + " PRICES\n");

        System.out.println("  " + Color.BLUE + StockType.AAPL + "\t\t" + Color.GREEN + getPrice(StockType.AAPL, day));
        System.out.println("  " + Color.BLUE + StockType.FB + "\t\t" + Color.GREEN + getPrice(StockType.FB, day));
        System.out.println("  " + Color.BLUE + StockType.GOOG + "\t\t" + Color.GREEN + getPrice(StockType.GOOG, day));
        System.out.println("  " + Color.BLUE + StockType.TSLA + "\t\t" + Color.GREEN + getPrice(StockType.TSLA, day) + Color.RESET);

    }
    
    public static void tradeStatus(String result) {
        System.out.println("\n  The trade was " + (result.equals("successful") ? Color.GREEN : Color.RED) + result + Color.RESET + ". Here is your portfolio:");
        System.out.println(account);
        System.out.print("\n  Press anything to continue");
        scanner.nextLine();
    }
    
    
    
    public static String getPrice(StockType stock, int day) {
        //TODO ROUND TO 2.d.p
        Path path = getPath(stock.toString());
        try (Stream<String> stream = Files.lines(path)){
            return stream.skip(1).filter(list->Integer.valueOf(list.split(",")[0])==day).map(list->list.split(",")[1]).findFirst().orElse(null);
            
        } catch (IOException e) {
			e.printStackTrace();
            return null;
		}
        
    }


    public static Path getPath(String stock) {
        try {
            return Paths.get(Thread.currentThread().getContextClassLoader().getResource("src/main/data/"+stock+".csv").toURI());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    


}
