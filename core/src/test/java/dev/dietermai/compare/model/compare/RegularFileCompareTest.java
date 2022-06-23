package dev.dietermai.compare.model.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import dev.dietermai.compare.model.file.RegularFile;

class RegularFileCompareTest {
	@Test
	void test_name() {
		assertEquals("foo", new RegularFileCompare("foo", null).name());
		assertEquals("bar", new RegularFileCompare("bar", null).name());
	}

	@Test
	void test_files() {
		List<RegularFile> list1 = List.of();
		assertEquals(list1, new RegularFileCompare(null, list1).files());

		List<RegularFile> list2 = List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bar"));
		assertEquals(list2, new RegularFileCompare(null, list2).files());
	}

	@Test
	void test_of() {
		List<RegularFile> list = List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bbb"));
		var sut = RegularFileCompare.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bbb")));

		assertEquals("foo", sut.name());
		assertEquals(list, sut.files());
	}

	@Test
	void test_existense() {
		assertFalse(RegularFileCompare.of("foo", List.of(RegularFile.NULL, RegularFile.of(null, "bbb"))).existense());
		assertFalse(RegularFileCompare.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.NULL)).existense());
		assertTrue(RegularFileCompare.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bbb"))).existense());
	}

	@Test
	void test_perfect() {
		assertFalse(RegularFileCompare.of("foo", List.of(RegularFile.NULL, RegularFile.of(null, "bbb"))).perfect());
		assertFalse(RegularFileCompare.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.NULL)).perfect());
		assertTrue(RegularFileCompare.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bbb"))).perfect());
	}
}
