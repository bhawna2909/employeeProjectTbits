package DAO;

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

	public static void store_single(Employee e) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			pm.makePersistent(e);
			tx.commit();
		} catch (Exception ex) {
		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
		}

	}

	public static void store_multiple(List<Employee> emp) {
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
		} catch (Exception ex) {
		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
		}
	}

	private static void re_store_multiple(Employee employee,
			PersistenceManager pm) {
		pm.makePersistent(employee);

	}

	public static void display(int id) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();
			Employee e = pm.getObjectById(Employee.class, id);
			System.out.print(e.getPkey() + " " + e.getName() + " " + e.getAge()
					+ " " + e.getDept() + " " + e.getAddress());

			tx.commit();
		} catch (Exception ex) {
		}

		finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
		}
	}

	public static void update_single(int key, String value) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {

			Employee a=pm.detachCopy(pm.getObjectById(Employee.class, key));
			a.setDept(value);


			//e.setDept(value);
			pm.makePersistent(a);
			tx.commit();
		} catch (Exception ex) {
		}

		finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
		}

	}

	public static void update_multiple(List<Integer> id, List<String> value) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Iterator<Integer> it = id.iterator();
			Iterator<String > s= value.iterator();
			while (it.hasNext()) {
				re_update_multiple(pm.detachCopy(pm.getObjectById(Employee.class, it.next().intValue())), pm,s.next() );
			}

			tx.commit();
		} catch (Exception ex) {
		}

		finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
		}

	}

	private static void re_update_multiple(Employee employee,
			PersistenceManager pm, String value) {
		employee.setDept(value);
		pm.makePersistent(employee);

		// TODO Auto-generated method stub

	}

	public static void delete_single(int id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {

			tx.begin();
			/*Query q= pm.newQuery(Employee.class);

			q.setFilter("pkey == id");
			q.declareParameters("Integer id");
			Employee x =(Employee) (q.execute(id));*/
			Employee e = pm.getObjectById(Employee.class, id);


			pm.deletePersistent(e);	
			//pm.deletePersistent(pm.detachCopy(pm.getO);
			tx.commit();
			//q.close(null);
		} catch (Exception ex) {
		} finally {

			if (tx.isActive())

				tx.rollback();
			pm.close();
		}

	}

	@SuppressWarnings("unchecked")
	public static void get_all() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		int i = 0;
		try {

			tx.begin();

			Query q = pm.newQuery(Employee.class);


			List<Employee> result = (List<Employee>) q.execute();


			System.out.println(result.size());
			int x=result.size();
			while (i<x) {
				/*System.out.print(result.get(i).getPkey() + " "
						+ result.get(i).getName() + " "
						+ result.get(i).getAge() + " "
						+ result.get(i).getDept() + " "
						+ result.get(i).getAddress());*/
				//result.toString();
				System.out.println(result.get(i).toString());


				i++;

			}
			tx.commit();
		}

		catch (Exception ex) {
			System.out.print(ex.toString());

		}

		finally {
			i=0;
			if (tx.isActive())

				tx.rollback();
			pm.close();
		}
	}

	public static void delete_all() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {

			tx.begin();
			Query q = pm.newQuery(Employee.class);


			List<Employee> eL = (List<Employee>)q.execute();
			pm.deletePersistentAll(eL);
			//pm.deletePersistentAll(result);
			tx.commit();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.print(ex.getLocalizedMessage());
			((Throwable) ex).printStackTrace();
		} finally {
			if (tx.isActive())
				tx.rollback();
			pm.close();
		}
	}

}
