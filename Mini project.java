import java.util.*;
class User{
    String username;
    String password;
    double balance;
    List<String>transactions;
    User(String username,String pasword){
        this.username=username;
        this.password=password;
        this.balance=0.0;
        this.transactions=new ArrayList<>();
        transactions.add("account created with balance ₹0.0");
    }
    void deposit(double amount){
        balance+=amount;
        transactions.add("Deposited ₹"+amount+"current Balance:/₹"+balance);
    }
    boolean withdraw(double amount){
        if(amount<=balance){
            balance-=amount;
            transactions.add("withdraw ₹"+amount+",current Balance:₹"+balance);
            return true;
        }
        else{
            transactions.add("Failed withdrawl attempt of ₹"+amount+"(Insuffiecient funds)");
            return false;
        }
    }
    void receiveTransfer(String fromUser,double amount){
        balance+=amount;
        transactions.add("Received ₹"+amount+"from"+fromUser+",current Balance:₹"+balance);
    }
    boolean sendTransfer(User toUser,double amount){
        if(amount<=balance){
            balance-=amount;
          transactions.add("sent ₹"+amount+"to"+toUser.username+",current Balance:₹"+balance);
            toUser.receiveTransfer(username,amount);
            return true;
        }
        else{
            transactions.add("Failed transfer of ₹"+amount+"to"+toUser.username+"(Insuffiecient funds)");
            return false;
        }
    }
    void printTransactions(){
        System.out.println("Transaction History for"+username+":");
        for(String t:transactions){
            System.out.println("-"+t);
        }
    }
}
  public class Main{
    static Scanner sc=new Scanner(System.in);
    static HashMap<String,User>users=new HashMap<>();
    public static void main(String[] args){
        System.out.println("===Welcome to CLI Bank System===");
        while(true){
            System.out.println("\n1. Login\n2.Sign up\n3. Exit");
            System.out.print("Enter choice:");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1->Login();
                case 2->Signup();
                case 3->{
                    System.out.println("Thank you for using CLI Bank!");
                    return;
                }
                default->System.out.println("Invalid choice!");
            }
        }
    }
    static void Signup(){
        System.out.print("choose a username:");
        String username=sc.nextLine();
        if(!users.containsKey(username)){
            System.out.println("username already exists Try login.");
            return;
        }
        System.out.print("choose a password:");
        String password=sc.nextLine();
        users.put(username,new User(username,password));
        System.out.println("Signup successful ! you can login.");
    }
    static void Login(){
        System.out.print("Enter username:");
        String username=sc.nextLine();
         if (!users.containsKey(username)) {
            System.out.println("User doesn't exist. Please sign up first.");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        User user = users.get(username);

        if (!user.password.equals(password)) {
      
            System.out.println("Incorrect password!");
            return;
        }
        System.out.println("Login successful ! Welcome"+username);
        userDashboard(user);
    }
    static void userDashboard(User currentuser){
        while(true){
            System.out.println("\n--Menu--");
            System.out.println("1.Check Balance");
            System.out.println("2.Deposit");
            System.out.println("3.withdraw");
            System.out.println("4.View Transactions");
            System.out.println("5.Transfer Money");
            System.out.println("6.Logout");
            System.out.print("Choose an option");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1->System.out.println("current Balance:₹"+currentuser.balance);
                case 2->{
                    System.out.print("Enter deposit amount:₹");
                    double amount=sc.nextDouble();
                    currentuser.deposit(amount);
                }
                case 3->{
                    System.out.print("enter withdraw amount:₹");
                    double amount=sc.nextDouble();
                    if(!currentuser.withdraw(amount)){
                        System.out.println("Insufficient balance!");
                    }
                }
                case 4->currentuser.printTransactions();
                case 5->{
                    System.out.print("Enter recipient username:");
                    String toUser=sc.nextLine();
                    if(!users.containsKey(toUser)){
                        System.out.println("Recipient not found!");
                        break;
                    }
                    if(toUser.equals(currentuser.username)){
                        System.out.println("you can't send money to yourself.");
                        break;
                    }
                    System.out.print("Enter amount to transfer:₹");
                    double amount=sc.nextDouble();
                    if(currentuser.sendTransfer(users.get(toUser),amount))
                    {
                        System.out.println("Transfer successful.");
                    }
                    else{
                        System.out.println("Transfer failed due to insufficient funds");
                        
                    }
                }case 6->{
                    System.out.println("Logged out.");
                    return;
                }
                default ->System.out.println("Invalid option!");
            }
        }
    }
}
