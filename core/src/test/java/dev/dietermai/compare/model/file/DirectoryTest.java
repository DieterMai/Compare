package dev.dietermai.compare.model.file;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import dev.dietermai.compare.model.file.Directory;
import dev.dietermai.compare.model.file.FileTreeRoot;

class DirectoryTest {

	@Test
	void test_name() {
		FileTreeRoot parent = FileTreeRoot.of(Path.of("root"));

		assertEquals("foo", Directory.of(parent, "foo").name());
		assertEquals("bar", Directory.of(parent, "bar").name());
	}

	@Test
	void test_parent() {
		FileTreeRoot root = FileTreeRoot.of(Path.of("root"));
		Directory dirA = Directory.of(root, "dirA");
		Directory dirB = Directory.of(dirA, "dirB");
		Directory dirC = Directory.of(dirB, "dirC");

		assertEquals(root, dirA.parent());
		assertEquals(dirA, dirB.parent());
		assertEquals(dirB, dirC.parent());
	}

	@Test
	void test_path() {
		FileTreeRoot root = FileTreeRoot.of(Path.of("root"));
		Directory dirA = Directory.of(root, "dirA");
		Directory dirB = Directory.of(dirA, "dirB");
		Directory dirC = Directory.of(dirB, "dirC");

		assertEquals(Path.of("root", "dirA"), dirA.path());
		assertEquals(Path.of("root", "dirA", "dirB"), dirB.path());
		assertEquals(Path.of("root", "dirA", "dirB", "dirC"), dirC.path());

	}
}
