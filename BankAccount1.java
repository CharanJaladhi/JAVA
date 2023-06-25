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

//THIS CLASS IS USED TO CHECK WHETHER THE ACCOUNT NAME TAKEN OR NOT
//AND TO CHECK PASSWORDS
class CheckDetails{
    ArrayList<String> WrgAccount;
    ArrayList<String> ATaken;
    public CheckDetails(){
        this.WrgAccount=new ArrayList<String>();
        this.ATaken=new ArrayList<String>();
    }
} 


public class BankAccount1
{
    private String AccHolderName;
    private String AccNumber;
    private double balance;
    private String password;

    //'checkIf' METHOD USED TO CHECK WHETHER PASSWORD MATCHES OR NOT
    public boolean checkIf(String pass,int index,BankAccount1 []obj){

        if(obj[index].password.equals(pass))
            return true;
        else
            return false;
    }
    
    //CONSTRUCTOR USED TO INITIALIZE CLASS VARIABLES
    public BankAccount1( CheckDetails CDetails,String Name,String ANumber,double bal,String pass){
        this.AccHolderName=Name;
        this.AccNumber=ANumber;
        this.balance=bal;
        this.password=pass;
        CDetails.ATaken.add(ANumber);
        System.out.println("Details saved succesfully");
    }
    
    //USED TO WITHDRAW THE AMOUNT
    public int withdraw(CheckDetails CDetails,int index,BankAccount1 obj[]) throws PasswordNotMatchException{
        //THIS if BLOCK CHECKS IF THE PRESENT ENTERED ACCOUNT 
        //IS EQUAL TO WRONG PASSWORD ENETERD ACCOUNT
        if(!CDetails.WrgAccount.contains(AccNumber)){
        int k;
        for(k=0;k<3;k++){
            System.out.print("Enter password to access the "+AccNumber+":");
            Scanner inp1=new Scanner(System.in);
            String pass=inp1.nextLine();
            if(checkIf(pass,index,obj)){
                System.out.print("Enter the amount to be withdrawn:");
                double withdrawa=inp1.nextFloat();
                if(withdrawa<0)
                    System.out.println("Enter the positive number");
                else if(withdrawa>balance)
                    System.out.println("The amount you entered to withdraw is more than available balance");
                else{
                    balance-=withdrawa;
                System.out.println("Amount successfully withdrawn");
                }
                return 1;
            }
            else if(!checkIf(pass,index,obj)&&k!=2){  
                //THROWS WHEN PASSWORD DOESN'T MATCHES
                try{ 
                throw new PasswordNotMatchException("WRONG PASSWORD\nYOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                
            }catch(PasswordNotMatchException d){
                System.out.println(d.getMessage());
            }

        }
        }
    }
        //IF WRONG PASSWORD IS ENETERED MORE THAN 3 TIMES IT ADDS INTO
        //THE 'WrgAccount' ARRAY LIST
        CDetails.WrgAccount.add(AccNumber);
        System.out.println("TOO MANY ATTEMPTS\nEXITING FROM ACCOUNT:"+AccNumber);
        return 0;
}


    public int deposit(CheckDetails CDetails,int index,BankAccount1 obj[]) throws PasswordNotMatchException{
        if(!CDetails.WrgAccount.contains(AccNumber)){
            int k;
            for(k=0;k<3;k++){
                System.out.print("Enter password to access the "+AccNumber+":");
                Scanner inp2=new Scanner(System.in);
                System.out.println(index);
                String pass=inp2.nextLine();
                if(checkIf(pass,index,obj)){
                    System.out.print("Enter the amount to be deposited:");
                    double dep=inp2.nextFloat();
                if(dep<0)
                    System.out.println("Enter the positive number");
                else{
                    balance+=dep;
                    System.out.println("Amount successfully deposited:)");
                }
                return 1;
                }
                else if(!checkIf(pass,index,obj)&&k!=2){   
                        //THROWS WHEN PASSWORD DOESN'T MATCHES
                try{ 
                throw  new PasswordNotMatchException("WRONG PASSWORD\nYOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                
            }catch(PasswordNotMatchException K){
                System.out.println(K.getMessage());
            }
                }
            }
        }
        CDetails.WrgAccount.add(AccNumber);
        System.out.println("TOO MANY ATTEMPTS\nEXITING FROM ACCOUNT:"+AccNumber);
        return 0;
}
    
    
    public int checkbalance(CheckDetails CDetails,int index,BankAccount1 obj[]) throws PasswordNotMatchException{
        if(!CDetails.WrgAccount.contains(AccNumber)){
            int k;
            for(k=0;k<3;k++){
                System.out.print("Enter password to access the "+AccNumber+":");
                Scanner inp3=new Scanner(System.in);
                String pass=inp3.nextLine();
                //inp3.nextLine();
                if(checkIf(pass,index,obj)){
                    System.out.println("The Amount present in Account number:"+AccNumber+" is "+balance);
                    return 1;
                }
                else if(!checkIf(pass,index,obj)&&k!=2){   
                //THROWS WHEN PASSWORD DOESN'T MATCHES
                try{ 
                throw  new PasswordNotMatchException("WRONG PASSWORD\nYOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                
            }catch(PasswordNotMatchException S){
                System.out.println(S.getMessage());
            }
                }
            }
        }
        CDetails.WrgAccount.add(AccNumber);
        System.out.println("TOO MANY ATTEMPTS\nEXITING FROM ACCOUNT:"+AccNumber);
        return 0;
}
    
    
	public static void main(String[] args) throws AlreadyAccountExistsException,AccountNotFoundException,ChoiceNotCorrectException {
       //ArrayList<String> array_lst = new ArrayList<String>();
        CheckDetails CDetails=new CheckDetails();
	    Scanner inp=new Scanner(System.in);
	    System.out.print("Enter how many account details you are going to add:");
	    int size=inp.nextInt();
		BankAccount1 obj[]=new BankAccount1[size];
		for(int i=0;i<size;i++){
            System.out.println("Enter the "+(i+1)+") details:");
            inp.nextLine();//TAKES ENTER AS INPUT
            System.out.print("Name:");
            String Name=inp.nextLine();
            System.out.print("Account Number:");
            String ANumber=inp.nextLine();
            boolean flag=true;
            while(flag){
                //THROWS EXCEPTION IF ALREADY TAKEN
            try{
            if(i !=0  &&  CDetails.ATaken.contains(ANumber)){
                throw new AlreadyAccountExistsException("THIS ACCOUNT NUMBER IS ALREADY TAKEN\nPLEASE ENTER THE NEW ONE:"); 
            }
            else{
                flag=false;
            }
        }catch(AlreadyAccountExistsException E){
            System.out.print(E.getMessage()); 
            ANumber=inp.nextLine();
        }
    }
            System.out.print("Password:");
            String pass=inp.nextLine();
            System.out.print("Amount being deposited at first :");
            double bal=inp.nextFloat();
            obj[i]=new BankAccount1(CDetails,Name,ANumber,bal,pass);
		}
        //BankAccount obj=new BankAccount(Name,ANumber,bal,pass);
        while(true){
        System.out.println("Enter -1 to CONTINUE\nEnter -2 to STOP");
        int chk=inp.nextInt(); //TO CHECK WHETHER USER WANT TO CONTINUE THE PROCESS
        if(chk==-1){
        System.out.print("Enter Account Number for which you want to perform operations:");
        inp.nextLine();//TAKES ENTER AS INPUT
        int flag=0;//VARIABLE USED TO CHECK WHETHER ACCOUNT FOUND OR NOT
        String checkAcc=inp.nextLine();
        for(int i=0;i<size;i++){
            if(obj[i].AccNumber.equals(checkAcc)){
                flag=1;
               // System.out.println(index);
        		while(true){
            		System.out.println("Enter details:\n1-deposit\n2-Withdraw\n3-Check balance\n4-exit");
            		int n=inp.nextInt();
            		if(n==4){
            		    System.out.println("SUCESSFULLY EXITED");
                        break;
                    }
            		if(n==1){
                        try{
            		        int bb=obj[i].deposit(CDetails,i,obj);
                    	    if (bb==0)
                    	        break;
                            }catch(Exception A){
                            System.out.println(A);
                        }
                        }
                    else if(n==2){
                            try{
                    	    int aa=obj[i].withdraw(CDetails,i,obj);
                    	    if (aa==0)
                    	        break;
                        }catch(Exception B){
                            System.out.println(B);
                        }
                    }
                    else if(n==3){
                        try{
                    	    int cc=obj[i].checkbalance(CDetails,i,obj);
                    	    if (cc==0)
                    	        break;
                        }catch(Exception C){
                            System.out.println(C);
                        }
                    }
                    else{
                        try{
                            //THROWS WHEN ENTERED INPUT IS WRONG
                    	    throw new ChoiceNotCorrectException("ENTER THE CORRECT ONE");
            		    }catch(ChoiceNotCorrectException s){
                            System.out.println("Error:"+s.getMessage());
                        }
                    }
                }
            }
        }
                if(flag==0){
                    // THROWS WHEN ACCOUNT NOT THERE
                    try{
                    throw new AccountNotFoundException("THE ACCOUNT NUMBER WHICH YOU HAVE ENTERED TO SEARCH IS NOT FOUND");
                }catch(AccountNotFoundException e){
                    System.out.println("Error:"+e.getMessage());
                }
            }
            } 
            else if(chk==-2){
                break;
            }
            else{
                //THROWS WHEN ENTERED INPUT IS WRONG
                try{
                    	    throw new ChoiceNotCorrectException("ENTER THE CORRECT NUMBER");
            		    }catch(ChoiceNotCorrectException s){
                            System.out.println("Error:"+s.getMessage());
                        }
            }
        }
	}
 }
