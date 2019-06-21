package com.t9.system.trans;

import javax.transaction.UserTransaction;

import org.hibernate.transaction.JTATransactionFactory;

/**
 * 
 * JTA事务的本地本地封装
 * A Hibernate {@link JTATransactionFactory} implementation that avoids using
 * JNDI for lookups. You must set the
 * 
 * {@link UserTransaction} on this bean with
 * #setUserTransaction(UserTransaction)} before using it.
 */
public class LocalJtaTransactionFactory extends JTATransactionFactory {

	/**
	 * 用户事务接口
	 */
	private static volatile UserTransaction userTransaction;
	

	/**
	 * 设置用户事务
	 * @param userTransaction 设置用户事务
	 */
	public synchronized void setUserTransaction(UserTransaction userTransaction) {
		LocalJtaTransactionFactory.userTransaction = userTransaction;
	}

	
	/**
	 * 获取用户事务
	 */
	protected UserTransaction getUserTransaction() {
		return userTransaction;
	}
	

}
