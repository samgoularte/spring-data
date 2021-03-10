package br.com.localhost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.localhost.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
