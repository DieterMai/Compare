package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class RootDirectoryTest {

	@Test
	void test_of() {
		assertEquals("foo", RootDirectory.of("foo").path());
		assertEquals("bar", RootDirectory.of("bar").path());
	}

}
