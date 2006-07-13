package org.highway.database.hibernate.onetableperconcreteclass;

import junit.framework.TestCase;

import org.hibernate.cfg.Configuration;
import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.database.hibernate.HibernateDatabase;
import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;

public class OneTablePerConcreteClassTest extends TestCase
{

	private Database database;

	protected void setUp() throws Exception
	{
		System.setProperty(DebugLog.ENABLE_SYSTEM_PROPERTY, "true");
		DebugHome.setDebugLog(new Log4jDebugLog());

		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.connection.driver_class",
				"com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url",
				"jdbc:mysql://localhost:3306/highway");
		configuration.setProperty("hibernate.connection.username", "anonymous");
		configuration.setProperty("hibernate.connection.isolation", "2");
		configuration.configure("/hibernate.cfg.xml");

		database = new HibernateDatabase(configuration);
	}

	public void testInsertAndDelete() throws Exception
	{
		DatabaseSession session = database.openSession();
		
		CashPayment3 cash = newCashPayment();
		ChequePayment3 cheque = newChequePayment();
		CreditCardPayment3 card = newCreditCardPayment();
		if (session.select(CashPayment3.class, 100)!=null){
			session.delete(cash);
		}
		if (session.select(ChequePayment3.class, 101)!=null){
			session.delete(cheque);
		}
		if (session.select(CreditCardPayment3.class, 102)!=null){
			session.delete(card);
		}

		assertTrue(session.select(CashPayment3.class, 100)==null);
		assertTrue(session.select(ChequePayment3.class, 101)==null);
		assertTrue(session.select(CreditCardPayment3.class, 102)==null);
		
		// insertion unitaire
		int nbCashPayement = session.select("from CashPayment3").size();
		int nbChequePayement = session.select("from ChequePayment3").size();
		int nbCardPayement = session.select("from CreditCardPayment3").size();
		
		session.insert(cash);
		assertEquals(session.select("from CashPayment3").size(), nbCashPayement+1);
		session.delete(newCashPayment());
		assertEquals(session.select("from CashPayment3").size(), nbCashPayement);
		
		// insertion multiple
		session.insert(new Object[] {cash, cheque, card});
		assertEquals(session.select("from CashPayment3").size(), nbCashPayement+1);
		assertEquals(session.select("from CreditCardPayment3").size(), nbCardPayement+1);
		assertEquals(session.select("from ChequePayment3").size(), nbChequePayement+1);
		
		//recherche
		Object obj = session.select(CashPayment3.class, 100);
		assertTrue(obj instanceof CashPayment3);
		assertTrue(obj.equals(cash));
		Object obj2 = session.select(ChequePayment3.class, 101);
		assertTrue(obj2 instanceof ChequePayment3);
		assertTrue(obj2.equals(cheque));
		Object obj3 = session.select(CreditCardPayment3.class, 102);
		assertTrue(obj3 instanceof CreditCardPayment3);
		assertTrue(obj3.equals(card));
		
		Object obj4 = session.select(CashPayment3.class, 100);
		assertFalse(obj4 instanceof CreditCardPayment3);
		
		session.getConnection().commit();
	}

	private CashPayment3 newCashPayment()
	{
		CashPayment3 paiement = new CashPayment3();
		paiement.setPaymentId(100);
		paiement.setAmount(new Integer(1500));
		paiement.setCurrency("US DOLLARS");
		return paiement;
	}

	private ChequePayment3 newChequePayment()
	{
		ChequePayment3 paiement = new ChequePayment3();
		paiement.setPaymentId(101);
		paiement.setOrder("VALTECH");
		paiement.setAmount(new Integer(1501));
		return paiement;
	}

	private CreditCardPayment3 newCreditCardPayment()
	{
		CreditCardPayment3 paiement = new CreditCardPayment3();
		paiement.setPaymentId(102);
		paiement.setAmount(new Integer(1502));
		paiement.setCreditCardType(("MASTERCARD"));
		return paiement;
	}
}
