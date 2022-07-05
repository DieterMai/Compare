package dev.dietermai.compare.model.file;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import dev.dietermai.compare.core.model.file.FileTreeRoot;
import dev.dietermai.compare.core.model.file.RegularFile;

class RegularFileTest {

	@Test
	void test_of() {
		FileTreeRoot parent = FileTreeRoot.of(Path.of("root"));

		assertEquals("foo", RegularFile.of(parent, "foo").name());
		assertEquals("bar", RegularFile.of(parent, "bar").name());
	}

	@Test
	void test_parent() {
		FileTreeRoot parent = FileTreeRoot.of(Path.of("root"));
		RegularFile file = RegularFile.of(parent, "file");

		assertEquals(parent, file.parent());
	}

	@Test
	void test_name() {
		final String NAME = "foo";
		FileTreeRoot parent = FileTreeRoot.of(Path.of("root"));

		assertEquals(NAME, RegularFile.of(parent, NAME).name());
	}

	@Test
	void test_path() {
		FileTreeRoot parent = FileTreeRoot.of(Path.of("root"));
		RegularFile file = RegularFile.of(parent, "file");

		assertEquals(Path.of("root", "file"), file.path());
	}

	@Test
	void test_exists() {
		assertFalse(RegularFile.of(null, "file").exists());
		assertTrue(RegularFile.NULL.exists());
	}
}
