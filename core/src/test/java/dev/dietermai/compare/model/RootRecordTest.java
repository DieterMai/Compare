package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class RootRecordTest {

	@Test
	void test_of() {
		assertEquals(Path.of("foo"), RootRecord.of(Path.of("foo")).path());
		assertEquals(Path.of("bar"), RootRecord.of(Path.of("bar")).path());
	}

	@Test
	void test_parent() {
		assertNull(RootRecord.of(Path.of("foo")).parent());
	}

	@Test
	void test_name() {
		final Path PATH = Path.of("root", "foo", "bar");
		RootRecord parent = RootRecord.of(Path.of("root", "foo", "bar"));

		assertEquals(PATH.toAbsolutePath().toString(), parent.name());
	}
}
