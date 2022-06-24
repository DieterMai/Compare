package dev.dietermai.compare.model.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import dev.dietermai.compare.model.file.Directory;

class ComparedDirectoryTest {
	@Test
	void test_name() {
		assertEquals("foo", new ComparedDirectory("foo", null).name());
		assertEquals("bar", new ComparedDirectory("bar", null).name());
	}

	@Test
	void test_files() {
		List<Directory> list1 = List.of();
		assertEquals(list1, new ComparedDirectory(null, list1).files());

		List<Directory> list2 = List.of(Directory.of(null, "aaa"), Directory.of(null, "bar"));
		assertEquals(list2, new ComparedDirectory(null, list2).files());
	}

	@Test
	void test_of() {
		List<Directory> list = List.of(Directory.of(null, "aaa"), Directory.of(null, "bbb"));
		var sut = ComparedDirectory.of("foo", List.of(Directory.of(null, "aaa"), Directory.of(null, "bbb")));

		assertEquals("foo", sut.name());
		assertEquals(list, sut.files());
	}

	@Test
	void test_existense() {
		assertFalse(ComparedDirectory.of("foo", List.of(Directory.NULL, Directory.of(null, "bbb"))).existense());
		assertFalse(ComparedDirectory.of("foo", List.of(Directory.of(null, "aaa"), Directory.NULL)).existense());
		assertTrue(ComparedDirectory.of("foo", List.of(Directory.of(null, "aaa"), Directory.of(null, "bbb"))).existense());
	}

	@Test
	void test_perfect() {
		assertFalse(ComparedDirectory.of("foo", List.of(Directory.NULL, Directory.of(null, "bbb"))).perfect());
		assertFalse(ComparedDirectory.of("foo", List.of(Directory.of(null, "aaa"), Directory.NULL)).perfect());
		assertTrue(ComparedDirectory.of("foo", List.of(Directory.of(null, "aaa"), Directory.of(null, "bbb"))).perfect());
	}
}
