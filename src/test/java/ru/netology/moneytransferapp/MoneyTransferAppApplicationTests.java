package ru.netology.moneytransferapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.moneytransferapp.model.Amount;
import ru.netology.moneytransferapp.model.ConfirmOperation;
import ru.netology.moneytransferapp.model.Transfer;
import ru.netology.moneytransferapp.model.TransferResult;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferAppApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private static final GenericContainer<?> transfer = new GenericContainer<>("transfer").withExposedPorts(5500);

    @BeforeAll
    public static void setUp() {
        transfer.start();
    }

    @Test
    void contextLoadsTransfer() {
        Transfer transferOp = new Transfer("1234123412341234", "12/23", "123", "4321432143214321", new Amount(10, "RUB"));
        ResponseEntity<TransferResult> transferEntity = testRestTemplate.postForEntity("http://localhost:" + transfer.getMappedPort(5500) + "/transfer", transferOp, TransferResult.class);
        Assertions.assertEquals(Objects.requireNonNull(transferEntity.getBody()).getOperationId(), "0");

    }
}
