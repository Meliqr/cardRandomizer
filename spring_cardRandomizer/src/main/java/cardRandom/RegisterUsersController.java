package cardRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestBody;
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
    public User registerUser(@RequestBody User inUser) {
    	String username = inUser.getUserName();
    	String pass = inUser.getPass();
    	String firstname = inUser.getFirstName();
    	String lastname = inUser.getLastName();
    	String emailaddress = inUser.getEmailAddress();
    	
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
    	
    	
    	User retUser = this.jdbcTemplate.queryForObject(
                "SELECT id,username,firstname,lastname,emailaddress FROM Users WHERE username=? AND password=?", 
                new Object[] { username, sha256hexPass },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("firstname"),rs.getString("lastname"),rs.getString("emailaddress"))
        );
    	
    	return retUser;
    }

}
