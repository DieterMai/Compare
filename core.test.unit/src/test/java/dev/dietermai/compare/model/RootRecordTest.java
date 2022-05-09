package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class RootRecordTest {

	@Test
	void test_of() {
		assertEquals("foo", RootRecord.of("foo").path());
		assertEquals("bar", RootRecord.of("bar").path());
	}

}
