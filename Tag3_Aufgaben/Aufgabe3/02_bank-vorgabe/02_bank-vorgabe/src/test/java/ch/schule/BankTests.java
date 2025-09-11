package ch.schule.bank.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTests {

    @Test
    void testCreateAccount() {
        Bank bank = new Bank();
        bank.createAccount("123");
        assertEquals(0, bank.getBalance("123"));
    }

    @Test
    void testDeposit() {
        Bank bank = new Bank();
        bank.createAccount("123");
        bank.deposit("123", 100);
        assertEquals(100, bank.getBalance("123"));
    }

    @Test
    void testWithdraw() {
        Bank bank = new Bank();
        bank.createAccount("123");
        bank.deposit("123", 200);
        bank.withdraw("123", 50);
        assertEquals(150, bank.getBalance("123"));
    }
}
