/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc.kraken.isisoap;

import inc.kraken.wsdl.AddPersonRequest;
import inc.kraken.wsdl.AddPersonResponse;
import inc.kraken.wsdl.DeletePersonRequest;
import inc.kraken.wsdl.DeletePersonResponse;
import inc.kraken.wsdl.GetAllPersonsRequest;
import inc.kraken.wsdl.GetAllPersonsResponse;
import inc.kraken.wsdl.GetPersonByIdRequest;
import inc.kraken.wsdl.GetPersonByIdResponse;
import inc.kraken.wsdl.PersonInfo;
import inc.kraken.wsdl.UpdatePersonRequest;
import inc.kraken.wsdl.UpdatePersonResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 *
 * @author carlosndiaye
 */
public class PersonClient extends WebServiceGatewaySupport {
    
    public GetPersonByIdResponse getPersonById(long personId) {
		GetPersonByIdRequest request = new GetPersonByIdRequest();
		request.setPersonId(personId);
		GetPersonByIdResponse response = (GetPersonByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/isisoap/api/getPersonByIdRequest"));
		return response;
	}
	public GetAllPersonsResponse getAllPersons() {
		GetAllPersonsRequest request = new GetAllPersonsRequest();
		GetAllPersonsResponse response = (GetAllPersonsResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/isisoap/api/getAllPersonsRequest"));
     	        return response;
	}	
	public AddPersonResponse addPerson(String firstname, String lastname) {
		AddPersonRequest request = new AddPersonRequest();
		request.setFirstname(firstname);
		request.setLastname(lastname);
		AddPersonResponse response = (AddPersonResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/isisoap/api/addPersonRequest"));
     	        return response;
	}	
	public UpdatePersonResponse updatePerson(PersonInfo personInfo) {
		UpdatePersonRequest request = new UpdatePersonRequest();
		request.setPersonInfo(personInfo);
		UpdatePersonResponse response = (UpdatePersonResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/isisoap/api/updatePersonRequest"));
     	        return response;
	}	
	public DeletePersonResponse deletePerson(long personId) {
		DeletePersonRequest request = new DeletePersonRequest();
		request.setPersonId(personId);
		DeletePersonResponse response = (DeletePersonResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/isisoap/api/deletePersonRequest"));
     	        return response;
	}
}
