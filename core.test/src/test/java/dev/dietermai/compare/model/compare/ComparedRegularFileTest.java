package dev.dietermai.compare.model.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import dev.dietermai.compare.core.model.compare.ComparedRegularFile;
import dev.dietermai.compare.core.model.file.RegularFile;

class ComparedRegularFileTest {
	@Test
	void test_name() {
		assertEquals("foo", new ComparedRegularFile("foo", null).name());
		assertEquals("bar", new ComparedRegularFile("bar", null).name());
	}

	@Test
	void test_files() {
		List<RegularFile> list1 = List.of();
		assertEquals(list1, new ComparedRegularFile(null, list1).files());

		List<RegularFile> list2 = List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bar"));
		assertEquals(list2, new ComparedRegularFile(null, list2).files());
	}

	@Test
	void test_of() {
		List<RegularFile> list = List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bbb"));
		var sut = ComparedRegularFile.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bbb")));

		assertEquals("foo", sut.name());
		assertEquals(list, sut.files());
	}

	@Test
	void test_existense() {
		assertFalse(ComparedRegularFile.of("foo", List.of(RegularFile.NULL, RegularFile.of(null, "bbb"))).existense());
		assertFalse(ComparedRegularFile.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.NULL)).existense());
		assertTrue(ComparedRegularFile.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bbb"))).existense());
	}

	@Test
	void test_perfect() {
		assertFalse(ComparedRegularFile.of("foo", List.of(RegularFile.NULL, RegularFile.of(null, "bbb"))).perfect());
		assertFalse(ComparedRegularFile.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.NULL)).perfect());
		assertTrue(ComparedRegularFile.of("foo", List.of(RegularFile.of(null, "aaa"), RegularFile.of(null, "bbb"))).perfect());
	}
}
