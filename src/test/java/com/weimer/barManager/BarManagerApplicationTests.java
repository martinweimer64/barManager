package com.weimer.barManager;

import com.weimer.barManager.jpa.BarEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.DELETE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application.properties")
class BarManagerApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    final ParameterizedTypeReference<BarEntity> BAR_ENTITY_TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };
    final ParameterizedTypeReference<Boolean> BOOLEAN_TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };
    final ParameterizedTypeReference<List<BarEntity>> BAR_LIST_TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };
    ResponseEntity<BarEntity> barResponse, barResponse2;
    ResponseEntity<List<BarEntity>> barListResponse, barListResponse2;


    void addBar() {
        final BarEntity entity = new BarEntity();
        entity.setCuit(11111);
        entity.setAddress("address");

        final HttpEntity<BarEntity> request = new HttpEntity<>(entity);
        barResponse = testRestTemplate
                .exchange("/v1/bars", POST, request, BAR_ENTITY_TYPE_REFERENCE);
        barResponse2 = testRestTemplate
                .exchange("/v1/bars/" + entity.getCuit(), GET, null, BAR_ENTITY_TYPE_REFERENCE);

		Assert.assertEquals(HttpStatus.OK.value(), barResponse.getStatusCode().value());
        Assert.assertEquals(HttpStatus.OK.value(), barResponse2.getStatusCode().value());
        Assert.assertEquals(entity, barResponse2.getBody());

    }

    @Test
    void AddBarResourceTest()
    {
        addBar();
        testRestTemplate.exchange("/v1/bars/11111", DELETE, null, BOOLEAN_TYPE_REFERENCE);
    }

    @Test
    void DeleteBarResourceTest() {
        addBar();
        barListResponse = testRestTemplate
                .exchange("/v1/bars", POST, null, BAR_LIST_TYPE_REFERENCE);
        testRestTemplate.exchange("/v1/bars/11111" , DELETE, null, BOOLEAN_TYPE_REFERENCE);
        barListResponse2 = testRestTemplate
                .exchange("/v1/bars", POST, null, BAR_LIST_TYPE_REFERENCE);
        Assert.assertEquals(HttpStatus.OK.value(), barResponse2.getStatusCode().value());
        Assert.assertEquals(null, barListResponse2.getBody());
    }


}
