package com.stproject.prototype;

import com.stproject.prototype.api.service.heartBeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PrototypeApplicationTests {

	@Autowired
	private heartBeat heart;

	@Test
	void contextLoads() throws Exception {
		assertThat(heart).isNotNull();
	}

}
