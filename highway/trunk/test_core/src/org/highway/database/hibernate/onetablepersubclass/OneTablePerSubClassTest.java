package org.highway.database.hibernate.onetablepersubclass;

import junit.framework.TestCase;

import org.hibernate.cfg.Configuration;
import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.database.hibernate.HibernateDatabase;
import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;

public class OneTablePerSubClassTest extends TestCase
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
		
		Payment2 cash = newCashPayment();
		Payment2 cheque = newChequePayment();
		Payment2 card = newCreditCardPayment();
		if (session.select(Payment2.class, 100)!=null){
			session.delete(cash);
		}
		if (session.select(Payment2.class, 101)!=null){
			session.delete(cheque);
		}
		if (session.select(Payment2.class, 102)!=null){
			session.delete(card);
		}

		assertTrue(session.select(Payment2.class, 100)==null);
		assertTrue(session.select(Payment2.class, 101)==null);
		assertTrue(session.select(Payment2.class, 102)==null);
		
		// insertion unitaire
		int nbCashPayement = session.select("from CashPayment2").size();
		int nbPayement = session.select("from Payment2").size();
		int nbChequePayement = session.select("from ChequePayment2").size();
		int nbCardPayement = session.select("from CreditCardPayment2").size();
		
		session.insert(cash);
		assertEquals(session.select("from CashPayment2").size(), nbCashPayement+1);
		assertEquals(session.select("from Payment2").size(), nbPayement+1);
		session.delete(newCashPayment());
		assertEquals(session.select("from CashPayment2").size(), nbCashPayement);
		assertEquals(session.select("from Payment2").size(), nbPayement);
		
		// insertion multiple
		session.insert(new Object[] {cash, cheque, card});
		assertEquals(session.select("from Payment2").size(), nbPayement+3);
		assertEquals(session.select("from CashPayment2").size(), nbCashPayement+1);
		assertEquals(session.select("from CreditCardPayment2").size(), nbCardPayement+1);
		assertEquals(session.select("from ChequePayment2").size(), nbChequePayement+1);
		
		//recherche
		Object obj = session.select(CashPayment2.class, 100);
		assertTrue(obj instanceof CashPayment2);
		assertTrue(obj.equals(cash));
		Object obj2 = session.select(ChequePayment2.class, 101);
		assertTrue(obj2 instanceof ChequePayment2);
		assertTrue(obj2.equals(cheque));
		Object obj3 = session.select(Payment2.class, 102);
		assertTrue(obj3 instanceof CreditCardPayment2);
		assertTrue(obj3.equals(card));
		
		Object obj4 = session.select(Payment2.class, 100);
		assertFalse(obj4 instanceof CreditCardPayment2);
		
		session.getConnection().commit();
	}

	private Payment2 newCashPayment()
	{
		CashPayment2 paiement = new CashPayment2();
		paiement.setPaymentId(100);
		paiement.setAmount(new Integer(1500));
		paiement.setCurrency("US DOLLARS");
		return paiement;
	}

	private Payment2 newChequePayment()
	{
		ChequePayment2 paiement = new ChequePayment2();
		paiement.setPaymentId(101);
		paiement.setOrder("VALTECH");
		paiement.setAmount(new Integer(1501));
		return paiement;
	}

	private Payment2 newCreditCardPayment()
	{
		CreditCardPayment2 paiement = new CreditCardPayment2();
		paiement.setPaymentId(102);
		paiement.setAmount(new Integer(1502));
		paiement.setCreditCardType(("MASTERCARD"));
		return paiement;
	}
}
