package com.smallworld.test;

import com.smallworld.TransactionDataFetcher;

public class TestTransaction {
	
	public static void main(String[] args) {
			
		TransactionDataFetcher tdf = new TransactionDataFetcher();
			
			System.out.println("    TOTAL TRANSACTION AMOUNT: "+ tdf.getTotalTransactionAmount());
			System.out.println("      MAX TRANSACTION AMOUNT: "+tdf.getMaxTransactionAmount());
			System.out.println("                     CLIENTS: "+tdf.countUniqueClients());
			System.out.println("      GET UNSOLVED ISSUE IDs: "+tdf.getUnsolvedIssueIds());
			System.out.println("GET TRANSACTION BY BENE NAME: "+tdf.getTransactionsByBeneficiaryName());
			System.out.println("  HAS OPEN COMPLIANCE ISSUES: "+tdf.hasOpenComplianceIssues("Grace Burgess"));
			System.out.println("	GET TOTAL AMOUNT SENT BY: "+tdf.getTotalTransactionAmountSentBy("Grace Burgess"));
			System.out.println("                  TOP SENDER: "+tdf.getTopSender());
			System.out.println("          TOP 3 TRANSACTIONS: "+tdf.getTop3TransactionsByAmount());
			System.out.println("   GET SOLVED ISSUE MESSAGES: "+tdf.getAllSolvedIssueMessages());
			
	}

}
