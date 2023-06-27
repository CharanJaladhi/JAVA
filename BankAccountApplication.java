/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
//EXCEPTION CLASS FOR ACCOUNT ALREADY EXISTS 
class AlreadyAccountExistsException extends Exception{
    AlreadyAccountExistsException(String msg){
        super(msg);
    }
}

//EXCEPTION CLASS FOR ACCOUND NOT FOUND
class AccountNotFoundException extends Exception{
    AccountNotFoundException (String msg){
        super(msg);
    }
}

//EXCEPTION CLASS TO CHECK WHETHER THE ENTERED CHOICE IS VALID OR NOT 
class ChoiceNotCorrectException extends Exception{
    ChoiceNotCorrectException(String msg){
        super(msg);
    }
}

//PASSWORD NOT MATCH EXCEPTION
class PasswordNotMatchException extends Exception{
    PasswordNotMatchException(String msg){
        super(msg);
    }
}


//THIS CLASS IS USED TO CHECK PASSWORDS
class CheckDetails{
    ArrayList<String> WrgAccount;
    public CheckDetails(){
        this.WrgAccount=new ArrayList<String>();
    }
} 

class Manager{
    private String ManagerUsername;
    private String ManagerPassword;
    public Manager(){
        this.ManagerUsername="Charan";
        this.ManagerPassword="C462";
    }
    public boolean checkIfManagerDetailsMatches(String username,String password){
        if (!this.ManagerUsername.equals(username)) {
            System.out.println("Username did not match!");
            return false;
        }
        else if (!this.ManagerPassword.equals(password)) {
            System.out.println("Passwords did not match!");
            return false;
        }
        System.out.println("Details matched");
        return true;
    }
}


public class BankAccountApplication
{
    static Hashtable<String,BankAccountApplication> AccountDetails=new Hashtable<>();
    private String AccHolderName;
    private double balance;
    private String password;
    static Scanner scanner;
    BankAccountApplication object;
    

     //CONSTRUCTOR USED TO INITIALIZE CLASS VARIABLES
    public BankAccountApplication( String Name,double bal,String pass){
        this.AccHolderName=Name;
        this.balance=bal;
        this.password=pass;
        scanner=new Scanner(System.in);
    }
    
    //METHOD TO CHECK WHETHER USER CREDENTIALS MATCHED OR NOT
    public String checkIfUserMatched(CheckDetails CDetails){
        int k,flag=0;
        System.out.print("Enter the Account Number:");
        String CheckAccNumber=scanner.next();
        if(AccountDetails.containsKey(CheckAccNumber) && !CDetails.WrgAccount.contains(CheckAccNumber)){
            for(k=0;k<3;k++){
                System.out.print("Enter password to Access Account Number "+CheckAccNumber+" :");
                String CheckPassword=scanner.next();
                if(CheckPassword.equals(AccountDetails.get(CheckAccNumber).password)){
                    return CheckAccNumber;
                }
                else if(!(CheckPassword.equals(AccountDetails.get(CheckAccNumber).password))&&k!=2){  
                        //THROWS WHEN PASSWORD DOESN'T MATCHES
                        try{ 
                            throw new PasswordNotMatchException("WRONG PASSWORD\nYOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                        }catch(PasswordNotMatchException d){
                            System.out.println(d.getMessage());
                        }
                }
            }
            if(k>2){flag=1;}
        }
        else if(CDetails.WrgAccount.contains(CheckAccNumber)){
            System.out.println("You Can't Access your account as you entered wrong password before, it was blocked :<");
        }
        else if(!AccountDetails.containsKey(CheckAccNumber)){
            try{
                    throw new AccountNotFoundException("THE ACCOUNT NUMBER WHICH YOU HAVE ENTERED TO SEARCH IS NOT FOUND");
                }catch(AccountNotFoundException e){
                    System.out.println("Error:"+e.getMessage());
                }
        }
        if(flag==1){
            CDetails.WrgAccount.add(CheckAccNumber);
            System.out.println("TOO MANY ATTEMPTS\nEXITING FROM ACCOUNT:"+CheckAccNumber);
        }
        return null;
    }
    
    //METHOD TO ADD DETAILS INTO HASHTABLE
    public  void addDetails(){
            System.out.print("Enter User Name:");
            String UName=scanner.next();
            scanner.nextLine();
            System.out.print("Enter Account Number:");
            String ANumber=scanner.nextLine();
            boolean flag=true;
            while(flag){
                //THROWS EXCEPTION IF ALREADY TAKEN
            try{
                if(AccountDetails.containsKey(ANumber)){
                    throw new AlreadyAccountExistsException("THIS ACCOUNT NUMBER IS ALREADY TAKEN\nPLEASE ENTER THE NEW ONE:"); 
                }
                else{
                    flag=false;
                }
            }catch(AlreadyAccountExistsException E){
                System.out.print(E.getMessage()); 
                ANumber=scanner.next();
            }
        }   
        System.out.print("Enter Password:");
        String pass=scanner.next();
        double bal;
        while (true) {
            System.out.print("Enter Amount being deposited at first :");
            try {
                bal = Double.parseDouble(scanner.next());
                break; // will only get to here if input was a double
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input\nONLY NUMERALS ARE ALLOWED");
            }
        }
        object=new BankAccountApplication(UName,bal,pass);
        AccountDetails.put(ANumber,object);
        System.out.println("Account Details Added Successfully");

    }

    //TO DISPLAY ALL THE ACCOUNT DETAILS
    public void getAccountDetails(){
        if(AccountDetails.isEmpty()){
            System.out.println("There are no Account Details present in database.");
        }
        else if(!AccountDetails.isEmpty()){
            for(String key:AccountDetails.keySet()){
                System.out.println("\nAccount Number:"+key+"\nUser Name:"+AccountDetails.get(key).AccHolderName+"\nBalance:"+AccountDetails.get(key).balance);
            }
        }  
    }

    //TO DISPLAY SPECIFIC USER DETAILS
    public void displayDetails(CheckDetails CDetails,String CheckAccNumber){
        for(String key:AccountDetails.keySet()){
            if(key.equals(CheckAccNumber)){
                System.out.println("\nAccount Number:"+key+"\nUser Name:"+AccountDetails.get(key).AccHolderName+
                "\nPassword:"+AccountDetails.get(key).password+"\nBalance:"+AccountDetails.get(key).balance);
            }
        }

    }

    //METHOD FOR DEPOSITING AMOUNT
    public void deposit(CheckDetails CDetails,String CheckAccNumber){
        double NewDepositAmount;
        while (true) {
            System.out.print("Enter the amount to be deposited:");
            try {
                NewDepositAmount = Double.parseDouble(scanner.next());
                break; // will only get to here if input was a double
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input\nONLY NUMERALS ARE ALLOWED");
            }
        }
        if(NewDepositAmount<0)
            System.out.println("Enter the positive number");
        else{
            AccountDetails.get(CheckAccNumber).balance+=NewDepositAmount;
            System.out.println("Amount successfully deposited:)");
        }
    }

    //USED TO WITHDRAW THE AMOUNT
    public void withdraw(CheckDetails CDetails,String CheckAccNumber){
        double NewWithdrawAmount;
        while (true) {
                System.out.print("Enter the amount to be withdrawn:");
                try {
                    NewWithdrawAmount = Double.parseDouble(scanner.next());
                    break; // will only get to here if input was a double
                } catch (NumberFormatException ignore) {
                    System.out.println("Invalid input\nONLY NUMERALS ARE ALLOWED");
                }
            }
        if(NewWithdrawAmount<0)
            System.out.println("Enter the positive number");
        else if(NewWithdrawAmount>AccountDetails.get(CheckAccNumber).balance)
            System.out.println("The amount you entered to withdraw is more than available balance");
        else{
            AccountDetails.get(CheckAccNumber).balance-=NewWithdrawAmount;
        System.out.println("Amount successfully withdrawn");
        }
    }


    //USED TO DISPLAY BALANCE FOR THE ACCOUNT
    public void getBalance(CheckDetails CDetails,String CheckAccNumber){
        System.out.println("The Amount present in Account number:"+CheckAccNumber+" is "+AccountDetails.get(CheckAccNumber).balance);
    }
    

    //METHOD TO MODIFY USERNAME AND PASSWORD
    public void changeDetails(CheckDetails CDetails,String CheckAccNumber){
        boolean check=true;
        System.out.println("Enter 12 to Change User Name\nEnter 13 to Change Password");
        int choice;
        while (true) {
            System.out.print("Enter your choice:");
            try {
                choice= Integer.parseInt(scanner.next());
                break; // will only get to here if input was a double
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input");
            }
        }
        if(choice == 12){
            while(check){
                System.out.print("Enter New User Name:");
                String NewUserName=scanner.next();
                if((AccountDetails.get(CheckAccNumber).AccHolderName.equals(NewUserName))){
                    System.out.println("USERNAME MATCHES\nPlease enter New User Name:");
                }
                else if(!(AccountDetails.get(CheckAccNumber).AccHolderName.equals(NewUserName))){
                    object=new BankAccountApplication(NewUserName,AccountDetails.get(CheckAccNumber).balance,AccountDetails.get(CheckAccNumber).password);
                    AccountDetails.replace(CheckAccNumber,object);
                    System.out.println("USERNAME CHANGED SUCCESFULLY:>");
                    break;
                }
            }
        }
        else if(choice == 13){
            int flag=0,update=0;
            while(check){
                System.out.print("Enter Old Password:");
                String OldPassword=scanner.next();
                if((AccountDetails.get(CheckAccNumber).password.equals(OldPassword))){
                    while(true){
                        System.out.print("Enter New Password:");
                        String NewPassword=scanner.next();
                        if(!(AccountDetails.get(CheckAccNumber).password.equals(NewPassword))){
                            object=new BankAccountApplication(AccountDetails.get(CheckAccNumber).AccHolderName,AccountDetails.get(CheckAccNumber).balance,NewPassword);
                            AccountDetails.replace(CheckAccNumber,object);
                            System.out.println("PASSWORD CHANGED SUCCESFULLY:>");
                            update = 1;
                            break;
                        }
                        else{
                            System.out.println("Old Password and New Password are equal.");
                        }
                    }
                }
                else if(flag<2){
                    System.out.println("PASSWORD MISMATCH\nYou have"+(3-flag-1)+" attempts");
                    flag++;
                }
                else{
                    System.out.println("Too Many Attempts");
                    break;
                }
                if( update == 1 ){ break; }
            }
        }
        else{
            System.out.println("ERROR:INCORRECT CHOICE");
        } 
    }




	public static void main(String[] args) throws AlreadyAccountExistsException,AccountNotFoundException,ChoiceNotCorrectException {
        CheckDetails CDetails=new CheckDetails();
        BankAccountApplication mainobj=new BankAccountApplication(null, 0, null);
        int choice;
        Manager manager=new Manager();
        while(true){
            System.out.println("\nEnter 1 if you are Manager");
            System.out.println("Enter 2 if you are User");
            System.out.println("Enter 3 to Exit\n");
            int selection;
            while (true) {
            System.out.print("Enter your choice:");
            try {
                selection= Integer.parseInt(scanner.next());
                break; // will only get to here if input was a double
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input");
            }
        }
            if(selection==1){
                System.out.print("Enter Manager Name:");
                String MName=scanner.next();
                System.out.print("Enter Password:");
                String MPassword=scanner.next();
                if(manager.checkIfManagerDetailsMatches(MName,MPassword)){
                    while(true){
                        System.out.println("\nEnter 4 to Add Account Details");
                        System.out.println("Enter 5 to Get Account Details");
                        System.out.println("Enter 11 to Exit\n");
                        while (true) {
                            System.out.print("Enter your choice:");
                            try {
                                choice= Integer.parseInt(scanner.next());
                                break; // will only get to here if input was a double
                            } catch (NumberFormatException ignore) {
                                System.out.println("Invalid input");
                            }
                        }
                            if(choice==4){
                            mainobj.addDetails();
                        }
                        else if(choice==5){
                            mainobj.getAccountDetails();
                        }
                        else if (choice==11){
                            break;
                        }
                        else{
                            try{
                                throw new ChoiceNotCorrectException("ENTER THE CORRECT ONE");
                            }catch(ChoiceNotCorrectException s){
                                System.out.println("Error:"+s.getMessage());
                            }
                        }
                    }
                }
                else{
                    System.out.println("Manager Credentials Mismatch");
                }
            }
            if(selection==2){
                String AccNumber=mainobj.checkIfUserMatched(CDetails);
                if( AccNumber != null ){
                    while(true){
                        System.out.println("\nEnter 6 to Display Account Details");
                        System.out.println("Enter 7 to Deposit");
                        System.out.println("Enter 8 to Withdraw");
                        System.out.println("Enter 9 for Balance Enquiry");
                        System.out.println("Enter 10 to Change Password or Account Holder Name");
                        System.out.println("Enter 11 to Exit\n");
                        while (true) {
                            System.out.print("Enter your choice:");
                            try {
                                choice= Integer.parseInt(scanner.next());
                                break; // will only get to here if input was a double
                            } catch (NumberFormatException ignore) {
                                System.out.println("Invalid input");
                            }
                        }
                        if(choice==6){
                            mainobj.displayDetails(CDetails,AccNumber);
                        }
                        else if(choice==7){
                            mainobj.deposit(CDetails,AccNumber);
                        }
                        else if(choice==8){
                            mainobj.withdraw(CDetails,AccNumber);
                        }
                        else if(choice==9){
                            mainobj.getBalance(CDetails,AccNumber);
                        }
                        else if(choice==10){
                            mainobj.changeDetails(CDetails,AccNumber);
                        }
                        else if (choice==11){
                            break;
                        }
                        else{
                        try{
                                throw new ChoiceNotCorrectException("ENTER THE CORRECT ONE");
                            }catch(ChoiceNotCorrectException s){
                                System.out.println("Error:"+s.getMessage());
                            } 
                        }
                    }
                }
                else{
                    System.out.println("User Credentials Mismatch");
                }
            }
            if (selection==3){
                break;
            }
            else if(selection!=1 && selection!=2 && selection!=3){
                try{
                    throw new ChoiceNotCorrectException("ENTER THE CORRECT NUMBER");
                }catch(ChoiceNotCorrectException s){
                    System.out.println("Error:"+s.getMessage());
                }
            }
        }
    }
 }
