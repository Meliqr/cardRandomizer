package cardRandom;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cardRandom.User;


@RestController
public class GetUsersController {
	
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value="/getUser",method=RequestMethod.POST)
    public User getUser(@RequestParam(value="username", defaultValue="") String username, @RequestParam(value="pass", defaultValue="") String pass) {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName("org.postgresql.Driver");
    	dataSource.setUrl("jdbc:postgresql://localhost:5432");
    	dataSource.setUsername("***");
    	dataSource.setPassword("***");
    	
    	String sha256hexPass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass);  
    	
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    	
    	User retUser = this.jdbcTemplate.queryForObject(
                "SELECT id,username,firstname,lastname,emailaddress FROM Users WHERE username=? AND password=?", 
                new Object[] { username, sha256hexPass },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("firstname"),rs.getString("lastname"),rs.getString("emailaddress"))
        );
    	
    	return retUser;

    }
	
    @RequestMapping("/getAllUsers")
    public List<User> getAllUsers(@RequestParam(value="firstname", defaultValue="") String firstname,@RequestParam(value="lastname", defaultValue="") String lastname){
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName("org.postgresql.Driver");
    	dataSource.setUrl("jdbc:postgresql://localhost:5432");
    	dataSource.setUsername("***");
    	dataSource.setPassword("***");
    	
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    	
    	List<User> retUsers = this.jdbcTemplate.query(
                "SELECT id,username,firstname,lastname,emailaddress FROM Users",
                new UserMapper()
        );
    	return retUsers;
    	
    }
    
    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("firstname"),rs.getString("lastname"),rs.getString("emailaddress"));
            return user;
        }
    }
   
	
}
