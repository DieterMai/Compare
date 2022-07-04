package dev.dietermai.compare.model.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import dev.dietermai.compare.model.file.Directory;
import dev.dietermai.compare.model.file.FileTreeRoot;

class ComparedDirectoryTest {
	public static final FileTreeRoot rootA = FileTreeRoot.of(Path.of("rootA"));
	public static final FileTreeRoot rootB = FileTreeRoot.of(Path.of("rootB"));
	
	
	@Test
	void test_name() {
		assertEquals("foo", new ComparedDirectory("foo", null).name());
		assertEquals("bar", new ComparedDirectory("bar", null).name());
	}

	@Test
	void test_files() {
		List<Directory> list1 = List.of();
		assertEquals(list1, new ComparedDirectory(null, list1).files());

		List<Directory> list2 = dirList("aaa");
		assertEquals(list2, new ComparedDirectory(null, list2).files());
	}

	@Test
	void test_existense() {
		assertFalse(ComparedDirectory.of("foo", List.of(Directory.NULL, Directory.of(null, "bbb"))).existense());
		assertFalse(ComparedDirectory.of("foo", List.of(Directory.of(null, "aaa"), Directory.NULL)).existense());
		assertTrue(ComparedDirectory.of("foo", dirList("aaa")).existense());
	}
	

	@Test
	void test_perfect() {
		assertFalse(ComparedDirectory.of("foo", List.of(Directory.NULL, Directory.of(null, "bbb"))).perfect());
		assertFalse(ComparedDirectory.of("foo", List.of(Directory.of(null, "aaa"), Directory.NULL)).perfect());
		assertTrue(ComparedDirectory.of("foo", List.of(Directory.of(null, "aaa"), Directory.of(null, "bbb"))).perfect());
	}
	
	private static List<Directory> dirList(String name){
		return List.of(Directory.of(rootA, "aaa"), Directory.of(rootB, "bbb"));
	}
}
