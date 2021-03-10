package br.com.localhost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.localhost.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query(value = "select funcConcatAddress(?1)", 
		   nativeQuery = true)
	String functionNativeQueryConcatenaEndereco(Long id);
	
	// usando a function com @NamedNativeQuery
	String functionConcatenaEndereco(Long id);
	
	@Query( value = "select * from Addresses where city like ?1 and street like ?2",
			nativeQuery = true
	)
	
	Address buscaPorCidadeRua(String city, String street);
	Address buscaPorEndereco(String city, String street);
	List<Address> buscaPorCidade(String cidade);
	List<Address> findByCityOrderByTypeDesc(String city);
	List<Address> findByCityStartingWithOrStreetEndingWith(String city, String street);
	List<Address> findByStreetContaining(String street);
	List<Address> findByStreetEndingWith(String street);
	List<Address> findByCityStartingWith(String city);
	
}
