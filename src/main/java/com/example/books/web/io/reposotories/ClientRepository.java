package com.example.books.web.io.reposotories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.books.web.oi.entities.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
	
	ClientEntity findByClientId(String clientId);
	

	@Query(value ="FROM clients c" )
	List<ClientEntity> findAllClients(Pageable pageable);
	

	@Query(value ="FROM clients c WHERE c.firstName =:filter OR c.lastName =:filter OR  email =:filter" )
	List<ClientEntity> findAllClients(@Param("filter")String filter, Pageable pageable);

	@Query(value="FROM clients c WHERE c.firstName =:firstName AND c.lastName =:lastName AND  email =:email")
	ClientEntity findByKeys(@Param("firstName")String firstName, @Param("lastName")String lastName, @Param("email")String email);
}
