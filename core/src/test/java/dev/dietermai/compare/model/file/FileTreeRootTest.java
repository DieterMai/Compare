package dev.dietermai.compare.model.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class FileTreeRootTest {

	@Test
	void test_of() {
		assertEquals(Path.of("foo"), FileTreeRoot.of(Path.of("foo")).path());
		assertEquals(Path.of("bar"), FileTreeRoot.of(Path.of("bar")).path());
	}

	@Test
	void test_parent() {
		assertNull(FileTreeRoot.of(Path.of("foo")).parent());
	}

	@Test
	void test_name() {
		final Path PATH = Path.of("root", "foo", "bar");
		FileTreeRoot parent = FileTreeRoot.of(Path.of("root", "foo", "bar"));

		assertEquals(PATH.toAbsolutePath().toString(), parent.name());
	}
}
