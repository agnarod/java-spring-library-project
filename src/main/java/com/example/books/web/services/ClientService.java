package com.example.books.web.services;

import java.util.List;

import com.example.books.web.shared.dtos.ClientDto;

public interface ClientService {
	ClientDto getClientByClientId(String clientId);
	List<ClientDto> getClients(String filter, int page, int limit);
	ClientDto createClient(ClientDto clientDto);
	ClientDto updateClient(String clientId, ClientDto clientDto);
	void deleteClient(String clientId);
	
}
