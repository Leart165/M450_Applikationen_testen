package ch.schule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTests {

    @Test
    void testDeposit() {
        Account acc = new Account("A1");
        acc.deposit(200);
        assertEquals(200, acc.getBalance());
    }

    @Test
    void testWithdraw() {
        Account acc = new Account("A2");
        acc.deposit(300);
        acc.withdraw(100);
        assertEquals(200, acc.getBalance());
    }

    @Test
    void testCanTransact() {
        Account acc = new Account("A3");
        acc.deposit(50);
        assertTrue(acc.canTransact(30));
        assertFalse(acc.canTransact(100));
    }
}
