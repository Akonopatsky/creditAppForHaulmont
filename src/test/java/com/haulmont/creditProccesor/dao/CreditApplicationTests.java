package com.haulmont.creditProccesor.dao;

import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.storage.repositities.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CreditApplicationTests {
@Autowired
ClientRepository clientRepository;
	@Test
	void storageSave() {
		clientRepository.save(new Client("dfsd", "fdsfsd","fsddf"));
		clientRepository.save(new Client("dfsddfs", "fdsfsdfd","fsddf"));
		clientRepository.save(new Client("dfdfsd", "fdsfgdfsd","fsgtrhgddf"));
		System.out.println("wwwww");
		System.out.println("all" + clientRepository.findAll());
		clientRepository.findAll().forEach(System.out::println);
	}

}
