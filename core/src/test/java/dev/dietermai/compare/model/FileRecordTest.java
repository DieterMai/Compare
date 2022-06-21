package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class FileRecordTest {

	@Test
	void test_of() {
		RootRecord parent = RootRecord.of(Path.of("root"));

		assertEquals("foo", FileRecord.of(parent, "foo").name());
		assertEquals("bar", FileRecord.of(parent, "bar").name());
	}

	@Test
	void test_parent() {
		RootRecord parent = RootRecord.of(Path.of("root"));
		FileRecord file = FileRecord.of(parent, "file");

		assertEquals(parent, file.parent());
	}

	@Test
	void test_name() {
		final String NAME = "foo";
		RootRecord parent = RootRecord.of(Path.of("root"));

		assertEquals(NAME, FileRecord.of(parent, NAME).name());
	}

	@Test
	void test_path() {
		RootRecord parent = RootRecord.of(Path.of("root"));
		FileRecord file = FileRecord.of(parent, "file");

		assertEquals(Path.of("root", "file"), file.path());
	}
}
