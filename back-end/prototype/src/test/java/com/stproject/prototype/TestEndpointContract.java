package com.stproject.prototype;

import com.stproject.prototype.entity.Contract;
import com.stproject.prototype.entity.User;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestEndpointContract {

    private RestTemplate restTemplate = new RestTemplate();
    private int idtmp;


    @Test
    void assertGetOKStatus() throws Exception {
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/contracts/1");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(
                httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    void assertGetType() throws Exception {
        // Given
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/contracts/" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertThat(mimeType).isEqualTo(jsonMimeType);
    }

    @Test
    void assertGetResponse () throws Exception {

        Contract cpntract = restTemplate.getForObject("http://localhost:8080/api/contracts/1",Contract.class);
        Contract resultExpected = new Contract(1,120,Date.valueOf("2020-12-24"),125);

        assertThat(cpntract).isEqualTo(resultExpected);
    }

    @Test
    void assertGetError() throws Exception {
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/contracts/-1");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(
                httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void assertPost() throws Exception {
        User newUser = new User();
        newUser.setIdUser(1);
        Contract contract = new Contract(10, Date.valueOf("1998-08-12"), 25,newUser);
        Contract ResultContract = restTemplate.postForObject("http://localhost:8080/api/contracts/",contract,Contract.class);
        this.idtmp = ResultContract.getIdContract();
        contract.setIdContract(ResultContract.getIdContract());

        assertThat(ResultContract).isEqualTo(contract);
    }

    @Test
    void assertPut() throws  Exception {
        User newUser = new User();
        newUser.setIdUser(1);

        Contract contract = new Contract(10, Date.valueOf("1998-08-12"), 30,newUser);

        org.springframework.http.HttpEntity<Contract> contractHttpEntity = new HttpEntity<>(contract);
        ResponseEntity<Contract> result = restTemplate.exchange("http://localhost:8080/api/contracts/1", HttpMethod.PUT, contractHttpEntity, Contract.class);

        contract.setIdContract(result.getBody().getIdContract());
        assertThat(result.getBody()).isEqualTo(contract);
    }


    @Test
    void assertDelete() {
        Boolean result = true;
        try{
            HttpEntity<Boolean> newResult= restTemplate.exchange("http://localhost:8080/api/contracts/5", HttpMethod.DELETE, null,Boolean.class);
            result = newResult.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            assertThat(result).isTrue();
        }
    }

    @Test
    void assertDeleteFalse() {
        Boolean result = true;
        try{
            HttpEntity<Boolean> newResult= restTemplate.exchange("http://localhost:8080/api/contracts/-1", HttpMethod.DELETE, null,Boolean.class);
            result = newResult.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            assertThat(result).isFalse();
        }
    }


}
