/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
public class BankAccount1
{
    private String AccHolderName;
    private String AccNumber;
    private double balance;
    private String password;
    
    public static boolean checkIf(String pass,int index,BankAccount1 []obj){

        if(obj[index].password.equals(pass))
            return true;
        else
            return false;
    }
    
    
    public BankAccount1(String Name,String ANumber,double bal,String pass){
        this.AccHolderName=Name;
        this.AccNumber=ANumber;
        this.balance=bal;
        this.password=pass;
        System.out.println("Details saved succesfully");
    }
    
    
    public int withdraw(ArrayList<String> array_lst,int index,BankAccount1 obj[]){
        if(!array_lst.contains(AccNumber)){
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
                System.out.println("WRONG PASSWORD");
                System.out.println("YOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
            }
        }
    }
        array_lst.add(AccNumber);
        System.out.println("TOO MANY ATTEMPTS\nEXITING FROM ACCOUNT:"+AccNumber);
        return 0;
}
    public int deposit(ArrayList<String> array_lst,int index,BankAccount1 obj[]){
        if(!array_lst.contains(AccNumber)){
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
                        System.out.println("WRONG PASSWORD");
                        System.out.println("YOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                }
            }
        }
        array_lst.add(AccNumber);
        System.out.println("TOO MANY ATTEMPTS\nEXITING FROM ACCOUNT:"+AccNumber);
        return 0;
}
    
    
    public int checkbalance(ArrayList<String> array_lst,int index,BankAccount1 obj[]){
        if(!array_lst.contains(AccNumber)){
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
                        System.out.println("WRONG PASSWORD");
                        System.out.println("YOU HAVE "+(3-k-1)+" MORE ATTEMPTS");
                }
            }
        }
        array_lst.add(AccNumber);
        System.out.println("TOO MANY ATTEMPTS\nEXITING FROM ACCOUNT:"+AccNumber);
        return 0;
}
    
    
	public static void main(String[] args) {
        ArrayList<String> array_lst = new ArrayList<String>();
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
            System.out.print("Password:");
            String pass=inp.nextLine();
            System.out.print("Amount being deposited at first :");
            double bal=inp.nextFloat();
            obj[i]=new BankAccount1(Name,ANumber,bal,pass);
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
            		        int bb=obj[i].deposit(array_lst,i,obj);
                    	    if (bb==0)
                    	        break;
                            }
                    else if(n==2){
                    	    int aa=obj[i].withdraw(array_lst,i,obj);
                    	    if (aa==0)
                    	        break;
                        }
                    else if(n==3){
                    	    int cc=obj[i].checkbalance(array_lst,i,obj);
                    	    if (cc==0)
                    	        break;
                        }
                    else{
                    	System.out.print("ENTER THE CORRECT ONE:");
            		        }
                        }
                    }
                }
                if(flag==0){
                    System.out.println("THE ACCOUNT NUMBER WHICH YOU HAVE ENTERED TO SEARCH IS NOT FOUND");
                }
            } 
            else if(chk==-2){
                break;
            }
            else{
                System.out.println("ENTER THE CORRECT NUMBER");
            }
        }
	}
 }
