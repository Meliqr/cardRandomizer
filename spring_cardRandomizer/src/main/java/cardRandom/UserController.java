package cardRandom;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.codec.digest.DigestUtils;

import cardRandom.User;


@RestController
public class UserController {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final DriverManagerDataSource DATA_SOURCE = new DriverManagerDataSource("jdbc:postgresql://localhost:5432","xxx","xxx");

    @RequestMapping(value="/getUser",method=RequestMethod.POST)
    public User getUser(@RequestBody User inUser) {
    	String userName = inUser.getUserName();
    	String pass = inUser.getPass();
    	
    	System.out.println(userName + " " + pass + "\n");	
    	String sha256hexPass = DigestUtils.sha256Hex(pass);  
    	
    	this.jdbcTemplate = new JdbcTemplate(DATA_SOURCE);
    	
    	User retUser = this.jdbcTemplate.queryForObject(
                "SELECT id,username,firstname,lastname,emailaddress FROM Users WHERE username=? AND password=?", 
                new Object[] { userName, sha256hexPass },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("firstname"),rs.getString("lastname"),rs.getString("emailaddress"))
        );
    	
    	return retUser;

    }
	
    @RequestMapping("/getAllUsers")
    public List<User> getAllUsers(@RequestParam(value="firstname", defaultValue="") String firstName,@RequestParam(value="lastname", defaultValue="") String lastName){  	
    	this.jdbcTemplate = new JdbcTemplate(DATA_SOURCE);
    	
    	List<User> retUsersList = this.jdbcTemplate.query(
                "SELECT id,username,firstname,lastname,emailaddress FROM Users",
                new UserMapper()
        );
    	return retUsersList;
    	
    }
    
    @RequestMapping(value="/registerUser", method=RequestMethod.POST)
    public User registerUser(@RequestBody User inUser) {
    	String userName = inUser.getUserName();
    	String pass = inUser.getPass();
    	String firstName = inUser.getFirstName();
    	String lastName = inUser.getLastName();
    	String emailAddress = inUser.getEmailAddress();
    	
    	   	
    	String sha256hexPass = DigestUtils.sha256Hex(pass);  
    	
    	this.jdbcTemplate = new JdbcTemplate(DATA_SOURCE);
    	
    	this.jdbcTemplate.update(
    	        "INSERT INTO Users (username, password, firstname, lastname, emailaddress) values (?, ?, ?, ?, ?)",
    	        userName, sha256hexPass, firstName, lastName, emailAddress);
    	
    	
    	User retUser = this.jdbcTemplate.queryForObject(
                "SELECT id,username,firstname,lastname,emailaddress FROM Users WHERE username=? AND password=?", 
                new Object[] { userName, sha256hexPass },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("firstname"),rs.getString("lastname"),rs.getString("emailaddress"))
        );
    	
    	return retUser;
    }
    
    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("firstname"),rs.getString("lastname"),rs.getString("emailaddress"));
            return user;
        }
    }
   
	
}
