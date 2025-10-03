package ch.schule;

import ch.schule.Account;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTests {

    @Test
    void testDeposit() {
        Account acc = new SavingsAccount("A1");  // statt Account
        acc.deposit(200, 20230905);              // deposit braucht auch Datum
        assertEquals(200, acc.getBalance());
    }

    @Test
    void testWithdraw() {
        Account acc = new SavingsAccount("A2");
        acc.deposit(300, 20230905);
        acc.withdraw(100, 20230906);             // withdraw braucht auch Datum
        assertEquals(200, acc.getBalance());
    }

    @Test
    void testCanTransact() {
        Account acc = new SavingsAccount("A3");
        acc.deposit(50, 20230905);
        assertTrue(acc.canTransact(30));
        assertFalse(acc.canTransact(100));
    }
}
