package cardRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final DriverManagerDataSource DATA_SOURCE = new DriverManagerDataSource("jdbc:postgresql://localhost:5432","Billiam","train1ng");

    @RequestMapping("/getBooster")
    public BoosterPack getBooster(@RequestBody CardSet inCardSet) {
    	return inCardSet.generateBoosterPack(10,3,1,1);
    }
}
