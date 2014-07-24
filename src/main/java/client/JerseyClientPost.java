package client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import DAO.EmployeeDAO;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import entities.Employee;

public class JerseyClientPost {

	public static void main(String[] args) {

		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("http://localhost:8080/e_proj/rest/json/post");
			List<Employee> elist=new ArrayList<Employee>();
			Employee e1 = new Employee(61,"kapil","post",32,"mumbai");
			Employee e2 = new Employee(62,"kiran","post",32,"mumbai");
			elist.add(e1);
			elist.add(e2);
			

			//String input = "{\"pkey\":\"45\",\"name\":\"jackson\",\"dept\":\"research\",\"age\":\"55\",\"address\":\"pune_\"}";

//			String input_arr = "{{\"pkey\":\"10\",\"name\":\"jhon\",\"dept\":\"d_one\",\"age\":\"59\",\"address\":\"pune_\"},"
	//				+ "{\"pkey\":\"20\",\"name\":\"Aron\",\"dept\":\"d_two\",\"age\":\"35\",\"address\":\"pune\"},"
		//			+ "{\"pkey\":\"30\",\"name\":\"Iyan\",\"dept\":\"d_three\",\"age\":\"43\",\"address\":\"pune\"}}";

			// String input
			// ="{\"pkey:77\",\"name:jackson\",\"dept:res\",\"age:89\",\"address:hdh\"}";
			//ObjectMapper mapper = new ObjectMapper();
			// Employee user = mapper.readValue(input, Employee.class);
			/*mapper =mapper.readValue(
					input_arr,TypeFactory.collectionType(List.class, Employee.class));*/
			
			//List<String> list = new ArrayList<String>();
			
			// Employee emp=new Employee(46,"new_employee","rest",55,"pune");

			/*ClientResponse response = webResource
					.accept("application/json")
					.type("application/json")
					.post(ClientResponse.class,
							mapper.readValue(
									input_arr,TypeFactory.collectionType(List.class, Employee.class)));
									TypeFactory.defaultInstance().constructCollectionType(List.class, Employee.class)));*/
			
			//EmployeeDAO.store_multiple(elist);
			GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(elist) {};
			//System.out.println(entity);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class,entity);
 
			
			// ClientResponse response=
			// webResource.accept("application/json").post(ClientResponse.class,
			// input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server \n");

			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}