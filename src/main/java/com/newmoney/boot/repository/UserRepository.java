package com.newmoney.boot.repository;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import com.newmoney.boot.model.User;

@Configuration
public interface UserRepository extends CrudRepository<User, Long> {
	public List<User> findByFirstName(String firstName);
}
