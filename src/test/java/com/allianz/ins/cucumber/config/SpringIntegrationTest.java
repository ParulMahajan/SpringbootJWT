package com.allianz.ins.cucumber.config;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
      //  classes = SpringCucumberApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract  class SpringIntegrationTest {

}
