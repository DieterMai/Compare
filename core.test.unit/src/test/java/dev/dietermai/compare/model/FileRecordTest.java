package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileRecordTest {

	@Test
	void test_of() {
		RootRecord parent = RootRecord.of("parent");

		assertEquals("foo", FileRecord.of(parent, "foo").name());
		assertEquals("bar", FileRecord.of(parent, "bar").name());
	}

	@Test
	void test_parent() {
		RootRecord parent = RootRecord.of("parent");
		FileRecord file = FileRecord.of(parent, "file");

		assertEquals(parent, file.parent());
	}
}
