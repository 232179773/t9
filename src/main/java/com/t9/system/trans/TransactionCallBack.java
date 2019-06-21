package com.t9.system.trans;

import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronization;

public interface TransactionCallBack {

	Object exeGetTransaction() throws TransactionException;

	void exeBegin(Object transaction, TransactionDefinition definition)
			throws TransactionException;

	void exeCommit(DefaultTransactionStatus status) throws TransactionException;

	void exeRollback(DefaultTransactionStatus status)
			throws TransactionException;
	
	

	
	 DefaultTransactionStatus newTransactionStatus(
			TransactionDefinition definition, Object transaction,
			boolean newTransaction, boolean newSynchronization, boolean debug,
			Object suspendedResources);

	
	 void prepareSynchronization(DefaultTransactionStatus status,
			TransactionDefinition definition);

	
	 int determineTimeout(TransactionDefinition definition);

	
	 boolean isExistingTransaction(Object transaction)
			throws TransactionException;

	
	 boolean useSavepointForNestedTransaction();

	
	 Object doSuspend(Object transaction) throws TransactionException;

	
	 void doResume(Object transaction, Object suspendedResources)
			throws TransactionException;

	
	 boolean shouldCommitOnGlobalRollbackOnly();

	
	 void prepareForCommit(DefaultTransactionStatus status);

	
	 void doSetRollbackOnly(DefaultTransactionStatus status)
			throws TransactionException;

	
	 void registerAfterCompletionWithExistingTransaction(
			Object transaction,
			List<TransactionSynchronization> synchronizations)
			throws TransactionException;

	
	 void doCleanupAfterCompletion(Object transaction);

}