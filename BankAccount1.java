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
    Manager manager=new Manager();

     //CONSTRUCTOR USED TO INITIALIZE CLASS VARIABLES
    public BankAccountApplication( String Name,double bal,String pass){
        this.AccHolderName=Name;
        this.balance=bal;
        this.password=pass;
        scanner=new Scanner(System.in);
    }
    
    
   //METHOD TO ADD DETAILS INTO HASHTABLE
    public  void addDetails(){
        System.out.print("Enter Manager Name:");
        String MName=scanner.next();
        System.out.print("Enter Password:");
        String MPassword=scanner.next();
        if(manager.checkIfManagerDetailsMatches(MName,MPassword)){
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
            System.out.print("Enter Amount being deposited at first :");
            double bal=scanner.nextFloat();
            object=new BankAccountApplication(UName,bal,pass);
            AccountDetails.put(ANumber,object);
            System.out.println("Account Details Added Successfully");
        }
    }

    //TO DISPLAY ALL THE ACCOUNT DETAILS
    public void getAccountDetails(){
        System.out.print("Enter Manager Name:");
        String MName=scanner.next();
        System.out.print("Enter Password:");
        String MPassword=scanner.next();
        if(manager.checkIfManagerDetailsMatches(MName,MPassword)){
            for(String key:AccountDetails.keySet()){
                System.out.println("\nAccount Number:"+key+"\nUser Name:"+AccountDetails.get(key).AccHolderName+"\nBalance:"+AccountDetails.get(key).balance);
            }
        }
    }

    //TO DISPLAY SPECIFIC USER DETAILS
    public void displayDetails(CheckDetails CDetails){
        int k,flag=0,check=0;
        System.out.print("Enter the Account Number:");
        String CheckAccNumber=scanner.next();
        if(AccountDetails.containsKey(CheckAccNumber) && !CDetails.WrgAccount.contains(CheckAccNumber)){
            for(k=0;k<3;k++){
                System.out.print("Enter password to Access Account Number "+CheckAccNumber+" :");
                String CheckPassword=scanner.next();
                    if(CheckPassword.equals(AccountDetails.get(CheckAccNumber).password)){
                        for(String key:AccountDetails.keySet()){
                            if(key.equals(CheckAccNumber)){
                                System.out.println("\nAccount Number:"+key+"\nUser Name:"+AccountDetails.get(key).AccHolderName+
                                "\nPassword:"+AccountDetails.get(key).password+"\nBalance:"+AccountDetails.get(key).balance);
                                check=1;
                            }
                        }
                    }
                    else if(!(CheckPassword.equals(AccountDetails.get(CheckAccNumber).password))&&k!=2){  
                        //THROWS WHEN PASSWORD DOESN'T MATCHES
                        try{ 
                            throw new PasswordNotMatchException("WRONG PASSWORD\nYOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                        }catch(PasswordNotMatchException d){
                            System.out.println(d.getMessage());
                        }
                    }
                    if(check==1){ break;}
            }
            if(k>2){flag=1;}
        }
        else if(CDetails.WrgAccount.contains(CheckAccNumber)){
            System.out.println("You Can't Access your account as you entered wrong password.");
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
    }

    //METHOD FOR DEPOSITING AMOUNT
    public void deposit(CheckDetails CDetails){
        int check=0,flag=0;
        System.out.print("Enter the Account Number:");
        String CheckAccNumber=scanner.next();
        if(!CDetails.WrgAccount.contains(CheckAccNumber) && AccountDetails.containsKey(CheckAccNumber)){
            int k;
            for(k=0;k<3;k++){
                System.out.print("Enter password to access the "+CheckAccNumber+" :");
                String CheckPassword=scanner.next();
                if(CheckPassword.equals(AccountDetails.get(CheckAccNumber).password)){
                    System.out.print("Enter the amount to be deposited:");
                    double NewDepositAmount=scanner.nextFloat();
                    if(NewDepositAmount<0)
                        System.out.println("Enter the positive number");
                    else{
                        AccountDetails.get(CheckAccNumber).balance+=NewDepositAmount;
                        System.out.println("Amount successfully deposited:)");
                    }
                    check=1;
                }
                else if(!CheckPassword.equals(AccountDetails.get(CheckAccNumber).password) && k!=2){   
                    //THROWS WHEN PASSWORD DOESN'T MATCHES
                    try{ 
                        throw  new PasswordNotMatchException("WRONG PASSWORD\nYOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                    }catch(PasswordNotMatchException K){
                        System.out.println(K.getMessage());
                    }
                }
                if(check==1){ break;}
            }
            if(k>2){flag=1;}
        }  
        else if(CDetails.WrgAccount.contains(CheckAccNumber)){
            System.out.println("You Can't Access your account as you entered wrong password.");
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
    }

    //USED TO WITHDRAW THE AMOUNT
    public void withdraw(CheckDetails CDetails){
        int check=0,flag=0;
        System.out.print("Enter the Account Number:");
        String CheckAccNumber=scanner.next();
        if(!CDetails.WrgAccount.contains(CheckAccNumber) && AccountDetails.containsKey(CheckAccNumber)){
        int k;
        for(k=0;k<3;k++){
            System.out.print("Enter password to access the "+CheckAccNumber+":");
            String CheckPassword=scanner.next();
            if(CheckPassword.equals(AccountDetails.get(CheckAccNumber).password)){
                System.out.print("Enter the amount to be withdrawn:");
                double NewWithdrawAmount=scanner.nextFloat();
                if(NewWithdrawAmount<0)
                    System.out.println("Enter the positive number");
                else if(NewWithdrawAmount>AccountDetails.get(CheckAccNumber).balance)
                    System.out.println("The amount you entered to withdraw is more than available balance");
                else{
                    AccountDetails.get(CheckAccNumber).balance-=NewWithdrawAmount;
                System.out.println("Amount successfully withdrawn");
                }
                check=1;
            }
            else if(!CheckPassword.equals(AccountDetails.get(CheckAccNumber).password) && k!=2){  
                //THROWS WHEN PASSWORD DOESN'T MATCHES
                try{ 
                throw new PasswordNotMatchException("WRONG PASSWORD\nYOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                
                }catch(PasswordNotMatchException d){
                    System.out.println(d.getMessage());
                }
            }
            if(check==1){ break;}
        }
        if(k>2){flag=1;}
    }
        else if(CDetails.WrgAccount.contains(CheckAccNumber)){
            System.out.println("You Can't Access your account as you entered wrong password.");
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
}


    //USED TO DISPLAY BALANCE FOR THE ACCOUNT
    public void getBalance(CheckDetails CDetails){
        int check=0,flag=0;
        System.out.print("Enter the Account Number:");
        String CheckAccNumber=scanner.next();
        if(!CDetails.WrgAccount.contains(CheckAccNumber) && AccountDetails.containsKey(CheckAccNumber)){
            int k;
            for(k=0;k<3;k++){
                System.out.print("Enter password to access the "+CheckAccNumber+":");
                String CheckPassword=scanner.next();
                if(CheckPassword.equals(AccountDetails.get(CheckAccNumber).password)){
                    System.out.println("The Amount present in Account number:"+CheckAccNumber+" is "+AccountDetails.get(CheckAccNumber).balance);
                    check=1;
                }
                else if(!CheckPassword.equals(AccountDetails.get(CheckAccNumber).password) && k!=2){   
                    //THROWS WHEN PASSWORD DOESN'T MATCHES
                    try{ 
                        throw  new PasswordNotMatchException("WRONG PASSWORD\nYOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                    }catch(PasswordNotMatchException S){
                        System.out.println(S.getMessage());
                    }
                }
                if(check==1){ break;}
            }
            if(k>2){flag=1;}
        }
        else if(CDetails.WrgAccount.contains(CheckAccNumber)){
            System.out.println("You Can't Access your account as you entered wrong password.");
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
    }
    

    //METHOD TO MODIFY USERNAME AND PASSWORD
    public void changeDetails(CheckDetails CDetails){
        boolean check=true;
        int flag=0;
        System.out.print("Enter the Account Number for which you want to change details:");
        String CheckAccNumber=scanner.next();
        if(!CDetails.WrgAccount.contains(CheckAccNumber) && AccountDetails.containsKey(CheckAccNumber)){
                System.out.print("Enter password to access the "+CheckAccNumber+":");
                String CheckPassword=scanner.next();
                if(CheckPassword.equals(AccountDetails.get(CheckAccNumber).password)){
                    System.out.println("Enter 9 to Change User Name\nEnter 10 to Change Password");
                    int choice=scanner.nextInt();
                    if(choice == 9){
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
                    else if(choice == 10){
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
                                        break;
                                    }
                                    else{
                                        System.out.println("Old Password and New Password are equal.");
                                    }
                                }
                                break;
                            }
                            else{
                                System.out.println("WRONG PASSWORD");
                                flag++;
                            }
                            if(flag>2){
                                System.out.println("TOO MANY ATTEMPTS\nYOU CAN'T CHANGE PASSWORD");
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("ERROR:INCORRECT CHOICE");
                    } 
                }
                else if(!CheckPassword.equals(AccountDetails.get(CheckAccNumber).password)){   
                    //THROWS WHEN PASSWORD DOESN'T MATCHES
                    try{ 
                        throw  new PasswordNotMatchException("WRONG PASSWORD\nYOU CAN'T MODIFY YOUR ACCOUNT");
                    }catch(PasswordNotMatchException S){
                        System.out.println(S.getMessage());
                    }
                }
                
            }
        else if(CDetails.WrgAccount.contains(CheckAccNumber)){
            System.out.println("You Can't Access your account as you entered wrong password.");
        }
        else if(!AccountDetails.containsKey(CheckAccNumber)){
            try{
                    throw new AccountNotFoundException("THE ACCOUNT NUMBER WHICH YOU HAVE ENTERED TO SEARCH IS NOT FOUND");
                }catch(AccountNotFoundException e){
                    System.out.println("Error:"+e.getMessage());
                }
        }
    }




	public static void main(String[] args) throws AlreadyAccountExistsException,AccountNotFoundException,ChoiceNotCorrectException {
        CheckDetails CDetails=new CheckDetails();
        BankAccountApplication mainobj=new BankAccountApplication(null, 0, null);
        while(true){
        System.out.println("\nEnter 1 to Add Account Details");
        System.out.println("Enter 2 to Get Account Details");
	System.out.println("Enter 3 to Display Account Details");
        System.out.println("Enter 4 to Deposit");
        System.out.println("Enter 5 to Withdraw");
        System.out.println("Enter 6 for Balance Enquiry");
        System.out.println("Enter 7 to Change Password or Account Holder Name");
        System.out.println("Enter 8 to Exit\n");
        int choice=scanner.nextInt();
            if(choice==1){
                mainobj.addDetails();
            }
            else if(choice==2){
                mainobj.getAccountDetails();
            }
            else if(choice==3){
                mainobj.displayDetails(CDetails);
            }
            else if(choice==4){
                mainobj.deposit(CDetails);
            }
            else if(choice==5){
                mainobj.withdraw(CDetails);
            }
            else if(choice==6){
                mainobj.getBalance(CDetails);
            }
            else if(choice==7){
                mainobj.changeDetails(CDetails);
            }
            else if (choice==8){
                break;
            }
            else{
                try{
                    throw new ChoiceNotCorrectException("ENTER THE CORRECT NUMBER");
            	}catch(ChoiceNotCorrectException s){
                    System.out.println("Error:"+s.getMessage());
                }
            }
        }
     }
 }
