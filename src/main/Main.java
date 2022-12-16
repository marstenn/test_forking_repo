package src.main;


import java.util.Scanner;

import src.main.model.account.Account;
import src.main.model.account.Personal;
import src.main.model.account.TFSA;
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

            }
            
      
    }
    public static Account createAccount(){
        String choice = accountChoice();
        Double incomingFunds = initialfunds();
        Boolean userconfirmed = accountConfirmation(choice, incomingFunds);
        if(!userconfirmed){
            choice = accountChoice();
            incomingFunds = initialfunds();
            userconfirmed = accountConfirmation(choice, incomingFunds);
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
        return confirmation.equalsIgnoreCase("y")?true:false;

    }
    public static Double initialfunds(){
        Double funds = 0.0;
        while( !(funds > 0 )){
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
        System.out.print(Color.GREEN + "\n               There are two types of accounst to choose from.");
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

    public static String chooseStock() {
        System.out.print("  Choose a stock: ");
        String stock = scanner.nextLine(); 
        while (!stock.equals("AAPL") && !stock.equals("FB") && !stock.equals("GOOG") && !stock.equals("TSLA") ) {
            System.out.print("  Choose a stock: ");
            stock = scanner.nextLine();
        }
        return stock;
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
    
    /* TODO
    public static void displayPrices(int day) {
        System.out.println("\n\n\t  DAY " + day + " PRICES\n");

        System.out.println("  " + Color.BLUE + Stock.AAPL + "\t\t" + Color.GREEN + getPrice(Stock.AAPL, day));
        System.out.println("  " + Color.BLUE + Stock.FB + "\t\t" + Color.GREEN + getPrice(Stock.FB, day));
        System.out.println("  " + Color.BLUE + Stock.GOOG + "\t\t" + Color.GREEN + getPrice(Stock.GOOG, day));
        System.out.println("  " + Color.BLUE + Stock.TSLA + "\t\t" + Color.GREEN + getPrice(Stock.TSLA, day) + Color.RESET);

    }
    */
    public static void tradeStatus(String result) {
        System.out.println("\n  The trade was " + (result.equals("successful") ? Color.GREEN : Color.RED) + result + Color.RESET + ". Here is your portfolio:");
        System.out.println(account);
        System.out.print("\n  Press anything to continue");
        scanner.nextLine();
    }
    
    
    /* TODO
    public static String getPrice(Stock stock, int day) {
        return "15.2343";
    }


    public static Path getPath(String stock) {
        return null;
    }
    */


}
