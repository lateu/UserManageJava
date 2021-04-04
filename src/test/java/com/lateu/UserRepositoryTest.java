package com.lateu;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.lateu.entity.User;
import com.lateu.repository.UserRepository;

@Rollback(false)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Transactional
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	

	public UserRepositoryTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void createUserTest() {
		/*BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		User u=new User();
		u.setUsername("rlateu");
		u.setEmail("rlateu@gmail.com");
		u.setFirstName("firName120");
		u.setLastName("lasame120");
		u.setPassword(encoder.encode("123"));
		userRepository.save(u);
		User inserted=entityManager.find(User.class, u.getId());
		assertThat(inserted.getUsername()).isEqualTo("rlateu");*/
	}
	
	@Test
	public void findUserByEmailTest() {
		String email="rlateu@dgdb.com";
		
	User u=	userRepository.findUserByEmail(email);
	
	assertThat(u).isNotNull();
		
		
	}
	

}
