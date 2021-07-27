package com.example.springbootcrudapplication;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:local-test.properties")
public class KarateTests {
    @Karate.Test
    @Sql(scripts = {"classpath:/user-data.sql"})
    public Karate user() {
        return Karate.run("user").relativeTo(getClass());
    }
}
