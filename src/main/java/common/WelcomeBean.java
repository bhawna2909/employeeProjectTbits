package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;



import dao.EmployeeDAO;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@ManagedBean
@SessionScoped
public class WelcomeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3656975705043612942L;
	

	public WelcomeBean() {
		super();
		init();
		// TODO Auto-generated constructor stub
	}

	private String choice;
	Client client; 
	@PostConstruct
	public void init() {
		
		ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(
                JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		client = Client.create(clientConfig);

	}

	

	

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public List<String> getChoiceList() {
		List<String> results = new ArrayList<String>();
		results.add("Add");
		results.add("Delete");
		results.add("Update");
		results.add("Display");
		

		return results;
	}
	
	public void resp(){
	
		String re="http://localhost:8080/e_proj/rest/json/"+choice;
		 
		
		WebResource webResource = client.resource(re);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
		String output = response.getEntity(String.class);
		 
		System.out.println("Output from Server .... \n");
		System.out.println(output);
		
	}

}
