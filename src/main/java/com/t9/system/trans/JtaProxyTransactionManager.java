package com.t9.system.trans;

import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronization;

public class JtaProxyTransactionManager extends JtaTransactionManager implements  TransactionCallBack{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4940665884916751068L;

	
	public  Object exeGetTransaction() throws TransactionException {
			return doGetTransaction();
	}

	public  void exeBegin(Object transaction, TransactionDefinition definition)
			throws TransactionException {
			doBegin(transaction, definition);
	}


	public  void exeCommit(DefaultTransactionStatus status)
			throws TransactionException {
			doCommit(status);
	}

	public  void exeRollback(DefaultTransactionStatus status)
			throws TransactionException {
			doRollback(status);
	}

	
	
   //////-------------------

	@Override
	public DefaultTransactionStatus newTransactionStatus(
			TransactionDefinition definition, Object transaction,
			boolean newTransaction, boolean newSynchronization, boolean debug,
			Object suspendedResources) {
		// TODO Auto-generated method stub
		return super.newTransactionStatus(definition, transaction, newTransaction,
				newSynchronization, debug, suspendedResources);
	}

	@Override
	public void prepareSynchronization(DefaultTransactionStatus status,
			TransactionDefinition definition) {
		// TODO Auto-generated method stub
		super.prepareSynchronization(status, definition);
	}

	@Override
	public int determineTimeout(TransactionDefinition definition) {
		// TODO Auto-generated method stub
		return super.determineTimeout(definition);
	}

	@Override
	public boolean isExistingTransaction(Object transaction)
			throws TransactionException {
		// TODO Auto-generated method stub
		return super.isExistingTransaction(transaction);
	}

	@Override
	public boolean useSavepointForNestedTransaction() {
		// TODO Auto-generated method stub
		return super.useSavepointForNestedTransaction();
	}

	@Override
	public Object doSuspend(Object transaction) throws TransactionException {
		// TODO Auto-generated method stub
		return super.doSuspend(transaction);
	}

	@Override
	public void doResume(Object transaction, Object suspendedResources)
			throws TransactionException {
		// TODO Auto-generated method stub
		super.doResume(transaction, suspendedResources);
	}

	@Override
	public boolean shouldCommitOnGlobalRollbackOnly() {
		// TODO Auto-generated method stub
		return super.shouldCommitOnGlobalRollbackOnly();
	}

	@Override
	public void prepareForCommit(DefaultTransactionStatus status) {
		// TODO Auto-generated method stub
		super.prepareForCommit(status);
	}

	@Override
	public void doSetRollbackOnly(DefaultTransactionStatus status)
			throws TransactionException {
		// TODO Auto-generated method stub
		super.doSetRollbackOnly(status);
	}

	@Override
	public void registerAfterCompletionWithExistingTransaction(
			Object transaction,
			List<TransactionSynchronization> synchronizations)
			throws TransactionException {
		// TODO Auto-generated method stub
		super.registerAfterCompletionWithExistingTransaction(transaction,
				synchronizations);
	}

	@Override
	public void doCleanupAfterCompletion(Object transaction) {
		// TODO Auto-generated method stub
		super.doCleanupAfterCompletion(transaction);
	}
	
	
	
	
	//////-------------------
}
