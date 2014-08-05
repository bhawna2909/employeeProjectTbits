package client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import entities.Employee;



public class JerseyClient {
	
	public static boolean store_single(String name, String dept, int age,
			String address) {
		boolean flag = false;
		Employee e1 = new Employee(name, dept, age, address);
		GenericEntity<Employee> entity = new GenericEntity<Employee>(e1) {};

		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/e_proj/rest/json/store_single");

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, entity);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Error: " + response.getStatus());
			}
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	
	public static boolean delete_single(int id) {

		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/e_proj/rest/json/delete_single");

			ClientResponse response = webResource.type("application/json").get(
					ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Error: " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean delete_all(){
		boolean flag=false;
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/e_proj/rest/json/delete_all");

			ClientResponse response = webResource.type("application/json").get(
					ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Error: " + response.getStatus());
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static boolean update_single(int id,String dept){
		HashMap<String ,Object> entity = new HashMap<String ,Object>();
		entity.put("id",(Integer)id);
		entity.put("dept", dept);
		
		
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/e_proj/rest/json/update_single");

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, entity);
			
			

			if (response.getStatus() != 201) {
				throw new RuntimeException("Error: " + response.getStatus());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	
	public static List<Employee> getAll() {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/e_proj/rest/json/getAll");

			ClientResponse response = webResource.type("application/json").get(
					ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Error: " + response.getStatus());
			}

			System.out.println("output: ");
			empList = (List<Employee>) response
					.getEntity(new GenericType<List<Employee>>() {
					});
			System.out.println(empList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}

	
	
	
	
	

}
