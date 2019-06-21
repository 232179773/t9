package com.t9.system.trans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronization;
public class HibernateTransactionManager extends AbstractPlatformTransactionManager implements PlatformTransactionManager, BeanFactoryAware, InitializingBean {


	// 本地事务
	private TransactionCallBack localTransactionManager;
	
	public TransactionCallBack getLocalTransactionManager() {
		return localTransactionManager;
	}


	public void setLocalTransactionManager(
			TransactionCallBack localTransactionManager) {
		this.localTransactionManager = localTransactionManager;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected Object doGetTransaction() throws TransactionException {
		return localTransactionManager.exeGetTransaction();
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {

		 log(localTransactionManager, "doBegin:");
		 
		 localTransactionManager.exeBegin(transaction, definition);
	}                                                 

	@Override
	protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
		DefaultTransactionStatus defaultStatus  =  (DefaultTransactionStatus) status;
		
		Object transaction = defaultStatus.getTransaction();
		
			
		 log(localTransactionManager, "doCommit:");
		 
		 localTransactionManager.exeCommit(status);
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
		DefaultTransactionStatus defaultStatus  =  (DefaultTransactionStatus) status;
		
		Object transaction = defaultStatus.getTransaction();
		
		
		 log(localTransactionManager, "doRollback:");
		 
		 localTransactionManager.exeRollback(status);
	}

	
	// 日志
	private Logger log = LoggerFactory.getLogger(HibernateTransactionManager.class);
	
	private void log(Object manager, String message) {
		
			if (log.isTraceEnabled()) {
				log.trace("ARPTransactionManager local " + "operate message:" + message);
			}
	}
	
	public TransactionCallBack getTransactionManager(Object transaction) {
		 return localTransactionManager;
	}

	public TransactionCallBack getTransactionManagerByContext() {
			return localTransactionManager;
	}
//////-------------------

	@Override
	public DefaultTransactionStatus newTransactionStatus(
			TransactionDefinition definition, Object transaction,
			boolean newTransaction, boolean newSynchronization, boolean debug,
			Object suspendedResources) {
		
		
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		log(transactionManager, "newTransactionStatus:");

		return transactionManager.newTransactionStatus(definition, transaction, newTransaction,
				newSynchronization, debug, suspendedResources);
	}

	@Override
	public void prepareSynchronization(DefaultTransactionStatus status,
			TransactionDefinition definition) {
      
		DefaultTransactionStatus defaultStatus  =  (DefaultTransactionStatus) status;
		
		Object transaction = defaultStatus.getTransaction();
		
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		log(transactionManager, "prepareSynchronization:");
		
		transactionManager.prepareSynchronization(status, definition);
	}

	@Override
	public int determineTimeout(TransactionDefinition definition) {
		
		TransactionCallBack transactionManager = getTransactionManagerByContext();
		
		log(transactionManager, "determineTimeout:");
		
		return transactionManager.determineTimeout(definition);
	}

	@Override
	public boolean isExistingTransaction(Object transaction)
			throws TransactionException {
		
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		log(transactionManager, "isExistingTransaction:");
		
		return transactionManager.isExistingTransaction(transaction);
	}

	@Override
	public boolean useSavepointForNestedTransaction() {
		
		TransactionCallBack transactionManager = getTransactionManagerByContext();
		
		log(transactionManager, "useSavepointForNestedTransaction:");
		
		return transactionManager.useSavepointForNestedTransaction();
	}

	@Override
	public Object doSuspend(Object transaction) throws TransactionException {
		
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		log(transactionManager, "doSuspend:");
		
		
		return transactionManager.doSuspend(transaction);
	}

	@Override
	public void doResume(Object transaction, Object suspendedResources)
			throws TransactionException {
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		log(transactionManager, "doResume:");
		
		transactionManager.doResume(transaction, suspendedResources);
	}

	@Override
	public boolean shouldCommitOnGlobalRollbackOnly() {
		TransactionCallBack transactionManager = getTransactionManagerByContext();
		
		log(transactionManager, "shouldCommitOnGlobalRollbackOnly:");
		
		return transactionManager.shouldCommitOnGlobalRollbackOnly();
	}

	@Override
	public void prepareForCommit(DefaultTransactionStatus status) {
		DefaultTransactionStatus defaultStatus  =  (DefaultTransactionStatus) status;
		
		Object transaction = defaultStatus.getTransaction();
		
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		
		log(transactionManager, "prepareForCommit:");
		
		transactionManager.prepareForCommit(status);
	}

	@Override
	public void doSetRollbackOnly(DefaultTransactionStatus status)
			throws TransactionException {
		DefaultTransactionStatus defaultStatus  =  (DefaultTransactionStatus) status;
		
		Object transaction = defaultStatus.getTransaction();
		
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		log(transactionManager, "doSetRollbackOnly:");
		
		
		transactionManager.doSetRollbackOnly(status);
	}

	@Override
	public void registerAfterCompletionWithExistingTransaction(
			Object transaction,
			List<TransactionSynchronization> synchronizations)
			throws TransactionException {
		
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		log(transactionManager, "registerAfterCompletionWithExistingTransaction:");
		
		
		transactionManager.registerAfterCompletionWithExistingTransaction(transaction,
				synchronizations);
	}

	@Override
	public void doCleanupAfterCompletion(Object transaction) {
		TransactionCallBack transactionManager = getTransactionManager(transaction);
		
		
		log(transactionManager, "doCleanupAfterCompletion:");
		
		
		transactionManager.doCleanupAfterCompletion(transaction);
		
	}
	
	
	//////-------------------
}
