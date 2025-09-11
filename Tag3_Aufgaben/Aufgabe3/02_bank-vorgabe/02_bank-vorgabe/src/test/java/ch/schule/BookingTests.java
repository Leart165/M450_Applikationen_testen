package ch.schule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingTests {

	@Test
	void testBookingStoresAmountAndDate() {
		Booking booking = new Booking(100, 20230905);
		assertEquals(100, booking.getAmount());
		assertEquals(20230905, booking.getDate());
	}
}
