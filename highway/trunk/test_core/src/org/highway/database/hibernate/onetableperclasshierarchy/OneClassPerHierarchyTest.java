package org.highway.database.hibernate.onetableperclasshierarchy;

import junit.framework.TestCase;

import org.hibernate.cfg.Configuration;
import org.highway.database.Database;
import org.highway.database.DatabaseSession;
import org.highway.database.hibernate.HibernateDatabase;
import org.highway.debug.DebugHome;
import org.highway.debug.DebugLog;
import org.highway.debug.Log4jDebugLog;

public class OneClassPerHierarchyTest extends TestCase
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
		

		
		Payment1 cash = newCashPayment();
		Payment1 cheque = newChequePayment();
		Payment1 card = newCreditCardPayment();
		
		session.delete(new Object[] {cash, cheque, card});
		
		assertTrue(session.select(Payment1.class, 100)==null);
		assertTrue(session.select(Payment1.class, 101)==null);
		assertTrue(session.select(Payment1.class, 102)==null);
		
		// insertion unitaire
		int nbCashPayement = session.select("from Payment1").size();
		session.insert(cash);
		assertEquals(session.select("from Payment1").size(), nbCashPayement+1);
		session.delete(newCashPayment());
		assertEquals(session.select("from Payment1").size(), nbCashPayement);
		
		// insertion multiple
		int nbPayement = session.select("from Payment1").size();
		session.insert(new Object[] {cash, cheque, card});
		assertEquals(session.select("from Payment1").size(), nbPayement+3);

		//recherche
		Object obj = session.select(CashPayment1.class, 100);
		assertTrue(obj instanceof CashPayment1);
		assertTrue(obj.equals(cash));
		Object obj2 = session.select(ChequePayment1.class, 101);
		assertTrue(obj2 instanceof ChequePayment1);
		assertTrue(obj2.equals(cheque));
		Object obj3 = session.select(Payment1.class, 102);
		assertTrue(obj3 instanceof CreditCardPayment1);
		assertTrue(obj3.equals(card));
		
		Object obj4 = session.select(Payment1.class, 100);
		assertFalse(obj4 instanceof CreditCardPayment1);
		
		session.getConnection().commit();
	}

	private Payment1 newCashPayment()
	{
		CashPayment1 paiement = new CashPayment1();
		paiement.setPaiementId(100);
		paiement.setAmount(new Integer(1500));
		paiement.setCurrency("US DOLLARS");
		return paiement;
	}

	private Payment1 newChequePayment()
	{
		ChequePayment1 paiement = new ChequePayment1();
		paiement.setPaiementId(101);
		paiement.setOrder("VALTECH");
		paiement.setAmount(new Integer(1501));
		return paiement;
	}

	private Payment1 newCreditCardPayment()
	{
		CreditCardPayment1 paiement = new CreditCardPayment1();
		paiement.setPaiementId(102);
		paiement.setAmount(new Integer(1502));
		paiement.setCreditCardType(("MASTERCARD"));
		return paiement;
	}
}
