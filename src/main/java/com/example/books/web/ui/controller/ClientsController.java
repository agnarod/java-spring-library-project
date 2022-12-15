package com.example.books.web.ui.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.books.web.services.ClientService;
import com.example.books.web.shared.dtos.ClientDto;
import com.example.books.web.ui.model.requests.ClientRequestModel;
import com.example.books.web.ui.model.responses.ClientResponseModel;

@RestController
@RequestMapping("/clients")
public class ClientsController {
	
	@Autowired
	ClientService clientService;

	@GetMapping
	public List<ClientResponseModel> getClients(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="10") int limit){
		
		
		List<ClientDto> clients= clientService.getClients(null, page, limit);
		
		//BeanUtils.copyProperties(books, returnValue);
		

		Type listType = new TypeToken<List<ClientResponseModel> >() {
		}.getType();
		List<ClientResponseModel> returnValue = new ModelMapper().map(clients, listType);
		
		
		return returnValue;

	}
	
	@GetMapping(path="/{clientId}")
	public ClientResponseModel getClient(@PathVariable String clientId) {
		
		if(clientId.isEmpty()) throw new RuntimeException("there is no client id provided");
		
		ClientDto clientDto = clientService.getClientByClientId(clientId);
		ClientResponseModel returnValue = new ModelMapper().map(clientDto, ClientResponseModel.class);
		
		
		return returnValue;
	}
	
	@PostMapping
	public ClientResponseModel createClient(@RequestBody ClientRequestModel client) {
		//verifying required elements are not empty
		if(client.getFirstName().isEmpty() || client.getLastName().isEmpty())
			throw new RuntimeException("Some fields ar empty");
		
		//creating return object
		ClientResponseModel returnValue = new ClientResponseModel();
		//move data to DTO object
		ClientDto clientDto = new ClientDto();
		BeanUtils.copyProperties(client, clientDto);
		
		//sending information to create functions
		ClientDto storedClient = clientService.createClient(clientDto);
		
		//moving data to return object
		BeanUtils.copyProperties(storedClient, returnValue);
		
		
		return returnValue;
	}
	
	
	@PutMapping(path="/{clientId}")
	public ClientResponseModel updateClient(@PathVariable String clientId, @RequestBody ClientRequestModel client) {
		ClientResponseModel returnValue = new ClientResponseModel();
		
		
		return returnValue;
	}
	
	
	@DeleteMapping(path="/{clientId}")
	public String deleteClient(@PathVariable String clientId) {
		
		
		return "Client deleted successfully";
	}
	
}
