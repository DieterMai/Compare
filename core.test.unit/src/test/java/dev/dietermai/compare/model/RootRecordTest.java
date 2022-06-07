package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class RootRecordTest {

	@Test
	void test_of() {
		assertEquals(Path.of("foo"), RootRecord.of(Path.of("foo")).path());
		assertEquals(Path.of("bar"), RootRecord.of(Path.of("bar")).path());
	}
}
