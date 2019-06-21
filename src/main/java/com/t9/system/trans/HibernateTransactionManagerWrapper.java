package com.t9.system.trans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronization;

/**
 * hibernate的事务管理器
 * 
 * @author sky
 * 
 */
public class HibernateTransactionManagerWrapper extends AbstractPlatformTransactionManager implements TransactionCallBack,
		PlatformTransactionManager, Serializable, InitializingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4750389164182837410L;
	
	
	// 日志
	private Logger log = LoggerFactory.getLogger(HibernateTransactionManagerWrapper.class);
		

	// hibernate模板持有者
	protected HibernateHolder templateHolder;

	public HibernateHolder getTemplateHolder() {
		return templateHolder;
	}

	private Map<HibernateTemplate, TransactionCallBack> managers = new ConcurrentHashMap<HibernateTemplate, TransactionCallBack>();
	
	//表名和实体名的对应关系
	private Map<String, String> tablename2ClassName = new HashMap<String, String>();

	public void setTemplateHolder(HibernateHolder templateHolder) {
		this.templateHolder = templateHolder;
	}

	public TransactionCallBack getCurrentHibernateTransactionManager() {

		HibernateTemplate hibernateTemplate = templateHolder
				.getHibernateTemplate();
		
		if (null == hibernateTemplate) {
			String message = "你在只读事务中执行了数据库的操作,请确认需要开启事务的方法最外层的Service方法必须以save*, update*, insert*, delete*开头";
			log.error(message);
			throw new RuntimeException(message);
		}

		if (managers.containsKey(hibernateTemplate)) {
			return managers.get(hibernateTemplate);
		} else {

			SessionFactory sessionFactory = hibernateTemplate
					.getSessionFactory();
			//HibernateTransactionManager manager = new HibernateTransactionManager(
			//		sessionFactory);
			
			ProxyTransactionManager manager =  new ProxyTransactionManager(sessionFactory);

			 TransactionCallBack callback = manager;
			 
			managers.put(hibernateTemplate, callback);
			
			return callback;
		}
	}



	@Override
	protected Object doGetTransaction() throws TransactionException {
		return getCurrentHibernateTransactionManager().exeGetTransaction();
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition)
			throws TransactionException {
		getCurrentHibernateTransactionManager().exeBegin(transaction, definition);
		
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status)
			throws TransactionException {
		getCurrentHibernateTransactionManager().exeCommit(status);
		
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status)
			throws TransactionException {
		getCurrentHibernateTransactionManager().exeRollback(status);
		
	}
	
//////-------------------

		@Override
		public DefaultTransactionStatus newTransactionStatus(
				TransactionDefinition definition, Object transaction,
				boolean newTransaction, boolean newSynchronization, boolean debug,
				Object suspendedResources) {
			// TODO Auto-generated method stub
			return getCurrentHibernateTransactionManager().newTransactionStatus(definition, transaction, newTransaction,
					newSynchronization, debug, suspendedResources);
		}

		@Override
		public void prepareSynchronization(DefaultTransactionStatus status,
				TransactionDefinition definition) {
			// TODO Auto-generated method stub
			getCurrentHibernateTransactionManager().prepareSynchronization(status, definition);
		}

		@Override
		public int determineTimeout(TransactionDefinition definition) {
			// TODO Auto-generated method stub
			return getCurrentHibernateTransactionManager().determineTimeout(definition);
		}

		@Override
		public boolean isExistingTransaction(Object transaction)
				throws TransactionException {
			// TODO Auto-generated method stub
			
			//return getCurrentHibernateTransactionManager().isExistingTransaction(transaction);
			
			//不知道嵌套事务
			return false;
		}

		@Override
		public boolean useSavepointForNestedTransaction() {
			// TODO Auto-generated method stub
			return getCurrentHibernateTransactionManager().useSavepointForNestedTransaction();
		}

		@Override
		public Object doSuspend(Object transaction) throws TransactionException {
			// TODO Auto-generated method stub
			return getCurrentHibernateTransactionManager().doSuspend(transaction);
		}

		@Override
		public void doResume(Object transaction, Object suspendedResources)
				throws TransactionException {
			// TODO Auto-generated method stub
			getCurrentHibernateTransactionManager().doResume(transaction, suspendedResources);
		}

		@Override
		public boolean shouldCommitOnGlobalRollbackOnly() {
			// TODO Auto-generated method stub
			return getCurrentHibernateTransactionManager().shouldCommitOnGlobalRollbackOnly();
		}

		@Override
		public void prepareForCommit(DefaultTransactionStatus status) {
			// TODO Auto-generated method stub
			getCurrentHibernateTransactionManager().prepareForCommit(status);
		}

		@Override
		public void doSetRollbackOnly(DefaultTransactionStatus status)
				throws TransactionException {
			// TODO Auto-generated method stub
			getCurrentHibernateTransactionManager().doSetRollbackOnly(status);
		}

		@Override
		public void registerAfterCompletionWithExistingTransaction(
				Object transaction,
				List<TransactionSynchronization> synchronizations)
				throws TransactionException {
			// TODO Auto-generated method stub
			getCurrentHibernateTransactionManager().registerAfterCompletionWithExistingTransaction(transaction,
					synchronizations);
		}

		@Override
		public void doCleanupAfterCompletion(Object transaction) {
			// TODO Auto-generated method stub
			getCurrentHibernateTransactionManager().doCleanupAfterCompletion(transaction);
		}
		
		
		//////-------------------

	
	
	
	 class ProxyTransactionManager extends HibernateTransactionManager implements TransactionCallBack {
		
		
		public ProxyTransactionManager() {
			
		}
		
		public ProxyTransactionManager(SessionFactory sessionFactory) {
			super(sessionFactory);
		}


		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = -3619363153640043855L;

		public Object exeGetTransaction() throws TransactionException {
			return doGetTransaction();
		}

		public void exeBegin(Object transaction, TransactionDefinition definition)
				throws TransactionException {
			doBegin(transaction, definition);
		}

		public void exeCommit(DefaultTransactionStatus status)
				throws TransactionException {
			doCommit(status);
		}

		public void exeRollback(DefaultTransactionStatus status)
				throws TransactionException {
			doRollback(status);
		}
		
		public boolean exeIsExistingTransaction(Object transaction) {
			return isExistingTransaction(transaction);
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


	 
	 
	 
	 

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	public Object exeGetTransaction() throws TransactionException {
		return doGetTransaction();
	}

	public void exeBegin(Object transaction, TransactionDefinition definition)
			throws TransactionException {
		doBegin(transaction, definition);
	}

	public void exeCommit(DefaultTransactionStatus status)
			throws TransactionException {
		doCommit(status);
	}

	public void exeRollback(DefaultTransactionStatus status)
			throws TransactionException {
		doRollback(status);
	}


}
