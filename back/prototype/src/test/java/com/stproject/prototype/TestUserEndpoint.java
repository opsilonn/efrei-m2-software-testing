package com.stproject.prototype;

import com.stproject.prototype.api.service.UserService;
import com.stproject.prototype.api.service.heartBeat;
import com.stproject.prototype.entity.User;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestUserEndpoint {



    private RestTemplate restTemplate = new RestTemplate();



    @Test
    void assertGetOKStatus() throws Exception {
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/users/1");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(
                httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    void assertGetType() throws Exception {
        // Given
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/users/1" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertThat(mimeType).isEqualTo(jsonMimeType);
    }

    @Test
    void assertGetResponse () throws Exception {

        User user = restTemplate.getForObject("http://localhost:8080/api/users/1",User.class);
        User resultExpected = new User("M","francois","Boni","31 route de la borde", "78110","le vesinet", Date.valueOf("2020-11-24"));
        resultExpected.setIdUser(1);

        assertThat(user).isEqualTo(resultExpected);
    }

    @Test
    void assertGetError() throws Exception {
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/users/7");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(
                httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void assertPost() throws Exception {
        User newUser = new User("Mme","Isabel","Le goff","40 rue de Zurich", "75002","Paris", Date.valueOf("2020-11-28"));
        User ResultUser = restTemplate.postForObject("http://localhost:8080/api/users/",newUser,User.class);
        newUser.setIdUser(ResultUser.getIdUser());

        assertThat(ResultUser).isEqualTo(newUser);
    }

    @Test
    void assertPut() throws  Exception {
        User newUser = new User("Mme","Isabel","Le goff","40 rue jacq ophenbach", "75016","Paris", Date.valueOf("2020-11-28"));

        org.springframework.http.HttpEntity<User> userHttpEntity = new HttpEntity<>(newUser);
        ResponseEntity<User> result = restTemplate.exchange("http://localhost:8080/api/users/8", HttpMethod.PUT, userHttpEntity, User.class);
        newUser.setIdUser(result.getBody().getIdUser());
        assertThat(result.getBody()).isEqualTo(newUser);
    }

    @Test
    void assertPutError() throws  Exception {
        User newUser = new User("Mme","Isabel","Le goff","40 rue jacq ophenbach", "75016","Paris", Date.valueOf("2020-11-28"));

        org.springframework.http.HttpEntity<User> userHttpEntity = new HttpEntity<>(newUser);
        ResponseEntity<User> result = restTemplate.exchange("http://localhost:8080/api/users/7", HttpMethod.PUT, userHttpEntity, User.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void assertDelete() {
        Boolean result = true;
        try{
            HttpEntity<Boolean> newResult= restTemplate.exchange("http://localhost:8080/api/users/8", HttpMethod.DELETE, null,Boolean.class);
            result = newResult.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            assertThat(result).isTrue();
        }
    }

}
