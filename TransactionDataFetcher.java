package com.smallworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.smallworld.dao.TransactionDao;
import com.smallworld.model.Transaction;

public class TransactionDataFetcher {
	
	static List<Transaction> transactions = TransactionDao.loadData();
	
	private Predicate<Transaction> successFullTransactions = t->(t.isIssueSolved() || t.getIssueId() == null);
	
    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
       // throw new UnsupportedOperationException();
       if(transactions == null){
    	   throw new UnsupportedOperationException("System cannot process the request please try later.");
       }
    	return transactions.stream().filter(successFullTransactions).mapToDouble(transaction->transaction.getAmount()).sum();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
    
    	if(transactions == null){
     	   throw new UnsupportedOperationException("System cannot process the request please try later.");
        }
    	
    	return transactions.stream().filter(o-> o.getSenderFullName().equals(senderFullName)).mapToDouble(t->t.getAmount()).sum();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
    	if(transactions == null){
      	   throw new UnsupportedOperationException("System cannot process the request please try later.");
         }
    	List<Transaction> sortedTransactionList = transactions.stream().filter(successFullTransactions).sorted(Comparator.comparingDouble(Transaction::getAmount)).collect(Collectors.toList());
    	return sortedTransactionList.get(sortedTransactionList.size()-1).getAmount();
    	
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
    	if(transactions == null){
       	   throw new UnsupportedOperationException("System cannot process the request please try later.");
          }
    	Set<String> clients = new HashSet<>();
    	transactions.forEach(t-> {
    		
    		clients.add(t.getSenderFullName());
    		clients.add(t.getBeneficiaryFullName());
    		
    	});
    	
    	return clients.size();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
    	if(transactions == null){
        	   throw new UnsupportedOperationException("System cannot process the request please try later.");
           }
    	List<Transaction> transactionsByUser = transactions.stream().
    			filter(o->o.getSenderFullName().equals(clientFullName) || o.getBeneficiaryFullName().equals(clientFullName)).
    			filter(t-> !t.isIssueSolved()).collect(Collectors.toList()); 
    	
     	return (transactionsByUser.size() > 0); 
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Object> getTransactionsByBeneficiaryName() {
    	if(transactions == null){
     	   throw new UnsupportedOperationException("System cannot process the request please try later.");
        }
    	Map<String,Object> transactionsByBeneName = new HashMap<>();
    	transactions.forEach(t->{
    		transactionsByBeneName.put(t.getBeneficiaryFullName(), t);
    	});
    	return transactionsByBeneName;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
    	if(transactions == null){
      	   throw new UnsupportedOperationException("System cannot process the request please try later.");
         }
    	Set<Integer> unsolvedIssueIds =new HashSet<>();
    	transactions.stream().filter(t->!t.isIssueSolved()).forEach(o->{
    		unsolvedIssueIds.add(o.getIssueId());
    	});
    	
     	return unsolvedIssueIds;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
    	if(transactions == null){
       	   throw new UnsupportedOperationException("System cannot process the request please try later.");
          }
     	
        List<String> solvedIssueMessages = new ArrayList<>();
        transactions.stream().filter(t->t.isIssueSolved() && t.getIssueId() != null).forEach(o->{
        	solvedIssueMessages.add(o.getIssueMessage());
    	});

        return solvedIssueMessages;
    }

    /**
     * Returns the 3 transactions with highest amount sorted by amount descending
     */
    public List<Object> getTop3TransactionsByAmount() {
    	if(transactions == null){
        	   throw new UnsupportedOperationException("System cannot process the request please try later.");
           }
    	List<Object> sortedTransactionList = transactions.stream().filter(successFullTransactions).sorted(Comparator.comparingDouble(Transaction::getAmount).reversed()).collect(Collectors.toList());
    	
    	return sortedTransactionList.subList(0, 3);

    }

    /**
     * Returns the sender with the most total sent amount
     */
    public Optional<Object> getTopSender() {
    
      if(transactions == null){
     	   throw new UnsupportedOperationException("System cannot process the request please try later.");
        }
      HashMap<String,List<Double>> map = new HashMap<>();
      
     transactions.forEach(t->{
    	 String sender = t.getSenderFullName();
    	 List<Double> sendersTransactions = null;
    	 
    	 if(map.containsKey(t.getSenderFullName())) {
    		  sendersTransactions = map.get(sender);
    		 	 
    		 if(t.getIssueId() == null || t.isIssueSolved())
    			 sendersTransactions.add(t.getAmount());
    		 
    		 map.put(sender, sendersTransactions);
    	 
    	 }else {
    		
    		  sendersTransactions = new ArrayList<>();
     		 
    		 if(t.getIssueId() == null || t.isIssueSolved())
    			 sendersTransactions.add(t.getAmount());
    		 
    		 map.put(sender, sendersTransactions);
    	    
    	 }
     });
     
    // map.entrySet().stream().forEach(t->{System.out.println(t.toString());});
     Map.Entry<String, List<Double>> max_entry = map.entrySet().iterator().next();

     for(Map.Entry<String, List<Double>> entry:map.entrySet()) {
    	 double sum = entry.getValue().stream().mapToDouble(Double::doubleValue).sum();
    	 double max_sum = max_entry.getValue().stream().mapToDouble(Double::doubleValue).sum();
    	 
    	 if(sum > max_sum) {
    		 max_entry = entry;
    	 }
     }
     
     return Optional.of(max_entry.getKey());
    
    }

}
