package com.cg.banking.client;

import java.util.List;
import java.util.Scanner;

import com.cg.banking.beans.Account;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;

public class MainClass {

	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.println("___________Banking System__________");
	boolean flag=true;
	while(flag) {
		System.out.println("Enter your choice:\n1.Open Account\n2.Get Account Details\n3.Get All Account details\n4.  \n5.Exit");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			BankingServices bankingServices=new BankingServicesImpl();
		    System.out.println(bankingServices.openAccount("Savings",100000.0f));
		    break;
	    case 2:
				BankingServices bankingServices1=new BankingServicesImpl();
		    System.out.println("Enter account no to find details");
		         int accountNo=sc.nextInt();
			    System.out.println(bankingServices1.getAccountDetails(accountNo));
			    break;
	    case 3:
					BankingServices bankingServices2=new BankingServicesImpl();
			        List <Account>allAccountDetails=bankingServices2.getAllAccountDetails();
				    System.out.println(allAccountDetails);
				    break;	
	    case 4:
	    	        break;
	    case 5:
	    	      flag=false;
	    	      break;
	    default:
	    	System.out.println("Please enter valid choice");
	    	       break;
		}
	}

	}

}
