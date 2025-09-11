package ch.schule;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTests {

	@Test
	void testWithdraw() {
		SavingsAccount sa = new SavingsAccount("S1");
		sa.deposit(200);
		sa.withdraw(50);
		assertEquals(150, sa.getBalance());
	}
}
