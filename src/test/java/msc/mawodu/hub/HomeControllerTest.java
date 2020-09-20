package msc.mawodu.hub;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void homeControllerServesContent() {

        ResponseEntity<String> homePageResponse = this.restTemplate.getForEntity("/", String.class);
        assertTrue(homePageResponse.getStatusCode().equals(HttpStatus.OK));
        assertFalse(homePageResponse.getBody().isEmpty());
    }
}
