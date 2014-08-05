package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDAO;

import entities.Employee;

public class EmployeeTest {

	public static void main(String[] args) {
		try {

			System.out
					.println("enter your choice\n 1.store_single\t 2.store_multiple\t 3.update_single\n4.update_multiple\t5.delete_single\t6.delete_all\n7.display_one\t8.display_all\t 0.Exit\n");
			// EmployeeDAO.get_all();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			int choice = 1;
			int pkey, age;
			String name, dept, address;

			while (choice != 0) {
				try {
					choice = Integer.parseInt(br.readLine());

					switch (choice) {
					case 1:
						System.out
								.println("enter pkey,name, dept,age, address ");// int
																				// pkey,
																				// String
																				// name,
																				// String
																				// dept,
																				// int
																				// age,
																				// String
																				// address
						pkey = Integer.parseInt(br.readLine());
						name = br.readLine();
						dept = br.readLine();
						age = Integer.parseInt(br.readLine());
						address = br.readLine();
						boolean x = EmployeeDAO.store_single(new Employee(name, dept, age, address));
						if (x)
							System.out.println("Entity stored");
						else
							System.out.println("Error try again!!");
						break;
					case 2:
						List<Employee> elist = new ArrayList<Employee>();
						System.out
								.println("enter pkey,name, dept,age, address\t press 0 to exit");
						int i = 1;
						while (i != 0) {
							pkey = Integer.parseInt(br.readLine());
							name = br.readLine();
							dept = br.readLine();
							age = Integer.parseInt(br.readLine());
							address = br.readLine();
							Employee e = new Employee(name, dept, age,
									address);
							elist.add(e);
							System.out.println("continue ? 1. yes 0. no");
							i = Integer.parseInt(br.readLine());

						}
						if (EmployeeDAO.store_multiple(elist))

							System.out.println("Entities stored");
						else
							System.out.println("Error try again!!");
						break;
					case 3:
						System.out
								.println("enter the employee id and new dept value");
						pkey = Integer.parseInt(br.readLine());
						dept = br.readLine();
						if (EmployeeDAO.update_single(pkey, dept))
							System.out.println("Entity updated");
						else
							System.out.println("Error try again!!");
						break;

					case 4:
						System.out
								.println("enter the list of employee id`s and new dept value\t press0 to stop ");
						i = 1;
						List<Integer> id = new ArrayList<Integer>();
						List<String> values = new ArrayList<String>();
						while (i != 0) {
							pkey = Integer.parseInt(br.readLine());
							dept = br.readLine();
							id.add(pkey);
							values.add(dept);
							System.out.println("continue 1.yes 0. no");
							i = Integer.parseInt(br.readLine());
						}
						if (EmployeeDAO.update_multiple(id, values))
							System.out.println("Entities updated");
						else
							System.out.println("Error try again!!");
						break;

					case 5:
						System.out
								.println("enter the id of the employee to be deleted");
						pkey = Integer.parseInt(br.readLine());
						if (EmployeeDAO.delete_single(pkey))
							System.out.println("Entity deleted");
						else
							System.out.println("Error try again!!");
						break;

					case 6:
						System.out.println("deleting all records");
						if (EmployeeDAO.delete_all())
							System.out.println("Entity stored");
						else
							System.out.println("Error try again!!");
						break;

					case 7:
						System.out
								.println("enter the id of employee to display record");
						// br.mark(0);
						// br.reset();
						// pkey=br.read();
						pkey = Integer.parseInt(br.readLine());
						if (!EmployeeDAO.display(pkey))
							System.out.println("Error try again");
						break;

					case 8:
						System.out.println("Displaying all records");
						List<Employee> result = EmployeeDAO.get_all();
						System.out.println(result);
						break;

					case 0:
						return;

					default:
						System.out.print("enter correct choice");
					}

				} catch (Exception e2) {
					System.out.print(e2.getMessage());
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				System.out.print("continue? 1.yes 0.no");
				choice = Integer.parseInt(br.readLine());
			}

		} catch (Exception e) {
			System.out.print("here" + e.getLocalizedMessage());
		}
	}

}
