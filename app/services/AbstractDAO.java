package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Abstrakte Basisklasse aller DAO.
 */
public class AbstractDAO extends NamedParameterJdbcDaoSupport {

	@Autowired
	private Initializer initializer;
	
	protected TransactionTemplate txTemplate;

	/**
	 * Per DI injizieren.
	 */
	public void setTransactionTemplate(TransactionTemplate txTemplate) {
		this.txTemplate = txTemplate;
	}
}
