package cardRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cardRandom.User;

@RestController
public class RegisterUsersController {
	
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value="/registerUser", method=RequestMethod.POST)
    public String registerUser(@RequestParam(value="username") String username, @RequestParam(value="pass") String pass,@RequestParam(value="firstname") String firstname,@RequestParam(value="lastname") String lastname,@RequestParam(value="emailaddress") String emailaddress) {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName("org.postgresql.Driver");
    	dataSource.setUrl("jdbc:postgresql://localhost:5432");
    	dataSource.setUsername("***");
    	dataSource.setPassword("***");
    	
    	String sha256hexPass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass);  
    	
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    	
    	
    	this.jdbcTemplate.update(
    	        "INSERT INTO Users (username, password, firstname, lastname, emailaddress) values (?, ?, ?, ?, ?)",
    	        username, sha256hexPass, firstname, lastname, emailaddress);
    	return "User added";
    }

}
