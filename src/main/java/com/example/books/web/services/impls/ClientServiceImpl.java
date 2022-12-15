package com.example.books.web.services.impls;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.books.web.io.reposotories.ClientRepository;
import com.example.books.web.oi.entities.ClientEntity;
import com.example.books.web.services.ClientService;
import com.example.books.web.shared.Utils;
import com.example.books.web.shared.dtos.ClientDto;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public ClientDto getClientByClientId(String clientId) {
		ClientEntity clientEntity = clientRepository.findByClientId(clientId);
		if(clientEntity == null) throw new RuntimeException("Client Id invalid");

		
		return new ModelMapper().map(clientEntity, ClientDto.class);
	}

	@Override
	public List<ClientDto> getClients(String filter, int page, int limit) {
		if(page > 0) page--;
		if(page < 0 ) page = 0;
		
		Pageable pageRequest = (Pageable) PageRequest.of(page, limit);
		List<ClientEntity> clients = clientRepository.findAllClients(pageRequest);
		

		Type listType = new TypeToken<List<ClientDto> >() {
		}.getType();
		
		
		// TODO Auto-generated method stub
		return new ModelMapper().map(clients, listType);
	}

	@Override
	public ClientDto createClient(ClientDto clientDto) {
		
		if(clientRepository.findByKeys(clientDto.getFirstName(), clientDto.getLastName(), clientDto.getEmail()) != null)
			throw new RuntimeException("Client already exist");
		ClientDto returnValue = new ClientDto();

		ClientEntity clientEntity = new ClientEntity();
		BeanUtils.copyProperties(clientDto, clientEntity);
		clientEntity.setClientId(utils.generateId(30));
		
		ClientEntity storedClient = clientRepository.save(clientEntity);
		BeanUtils.copyProperties(storedClient, returnValue);
		
		// TODO Auto-generated method stub
		return returnValue;
	}

	@Override
	public ClientDto updateClient(String clientId, ClientDto clientDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClient(String clientId) {
		// TODO Auto-generated method stub

	}

}
