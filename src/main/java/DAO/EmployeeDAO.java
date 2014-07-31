package DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import entities.Employee;

public class EmployeeDAO {

	private static PersistenceManagerFactory pmf = JDOHelper
			.getPersistenceManagerFactory("datanucleus.properties");

	@SuppressWarnings("finally")
	public static boolean store_single(Employee e) {
		boolean flag = false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			pm.makePersistent(e);
			tx.commit();
			flag = true;
		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
			flag = false;
		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
			return flag;
		}

	}

	@SuppressWarnings("finally")
	public static boolean store_multiple(List<Employee> emp) {
		boolean flag = false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Iterator<Employee> it = emp.iterator();
			while (it.hasNext()) {
				re_store_multiple(it.next(), pm);
			}
			// pm.makePersistentAll((List<Employee>) E);
			tx.commit();
			flag = true;

		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
			return flag;
		}
	}

	private static void re_store_multiple(Employee employee,
			PersistenceManager pm) {
		pm.makePersistent(employee);

	}

	@SuppressWarnings("finally")
	public static boolean display(int id) {
		boolean flag = false;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();
			Employee e = pm.getObjectById(Employee.class, id);
			System.out.print(e.getPkey() + " " + e.getName() + " " + e.getAge()
					+ " " + e.getDept() + " " + e.getAddress());

			tx.commit();
			flag = true;
		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
		}

		finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
			return flag;

		}
	}

	@SuppressWarnings("finally")
	public static boolean update_single(int key, String value) {
		boolean flag = false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Employee a = pm.detachCopy(pm.getObjectById(Employee.class, key));
			a.setDept(value);

			// e.setDept(value);
			pm.makePersistent(a);
			tx.commit();
			flag = true;
		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
		}

		finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
			return flag;
		}

	}

	public static boolean update_multiple(List<Integer> id, List<String> value) {
		boolean flag = false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Iterator<Integer> it = id.iterator();
			Iterator<String> s = value.iterator();
			while (it.hasNext()) {
				re_update_multiple(pm.detachCopy(pm.getObjectById(
						Employee.class, it.next().intValue())), pm, s.next());
			}

			tx.commit();
			flag = true;
		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
		}

		finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
			return flag;
		}

	}

	private static void re_update_multiple(Employee employee,
			PersistenceManager pm, String value) {
		employee.setDept(value);
		pm.makePersistent(employee);

		// TODO Auto-generated method stub

	}

	@SuppressWarnings("finally")
	public static boolean delete_single(int id) {
		boolean flag = false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {

			tx.begin();
			/*
			 * Query q= pm.newQuery(Employee.class);
			 * 
			 * q.setFilter("pkey == id"); q.declareParameters("Integer id");
			 * Employee x =(Employee) (q.execute(id));
			 */
			Employee e = pm.getObjectById(Employee.class, id);
			pm.deletePersistent(e);
			// pm.deletePersistent(pm.detachCopy(pm.getO);
			tx.commit();
			flag = true;
			// q.close(null);
		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
		} finally {

			if (tx.isActive())

				tx.rollback();
			pm.close();
			return flag;
		}

	}

	
	public static List<Employee> get_all() {
		boolean flag = false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Employee> result = new ArrayList<Employee>();
		int i = 0;
		try {

			tx.begin();

			Query q = pm.newQuery(Employee.class);

			result = (List<Employee>) q.execute();
			pm.makeTransientAll(result);
			System.out.println(result.size());
			int x = result.size();
			while (i < x) {
				/*
				 * System.out.print(result.get(i).getPkey() + " " +
				 * result.get(i).getName() + " " + result.get(i).getAge() + " "
				 * + result.get(i).getDept() + " " +
				 * result.get(i).getAddress());
				 */
				// result.toString();
				System.out.println(result.get(i).toString());
				i++;
			}
			tx.commit();
			flag = true;
		}

		catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());

		}

		finally {
			i = 0;
			if (tx.isActive())

				tx.rollback();
			pm.close();
		}
		return result;
	}
	@SuppressWarnings("finally")
	public static List<Employee> display_all(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<Employee> eList = new ArrayList<Employee>();

		try {

			tx.begin();

			Query qr = pm.newQuery(Employee.class);
			@SuppressWarnings("unchecked")
			List<Employee> eeList = (List<Employee>) qr.execute();
			
			pm.makeTransientAll(eeList);
			eList=eeList;
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());

		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
			return eList;
			

		}
		
		
		
	}

	@SuppressWarnings("finally")
	public static boolean delete_all() {
		boolean flag = false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {

			tx.begin();
			Query q = pm.newQuery(Employee.class);
			List<Employee> eL = (List<Employee>) q.execute();
			pm.deletePersistentAll(eL);
			// pm.deletePersistentAll(result);
			tx.commit();
			flag = true;
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.println("Exception " + ex.getMessage());
		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();

			return flag;
		}
	}

}
