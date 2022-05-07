package dev.dietermai.compare;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class FileTreeComparatorTest {

	@Test
	void test_emptyTreesOnBothSides() {
		FileTreeComparator fileTreeComparator = new FileTreeComparator();
		FileTreeCompare result = fileTreeComparator.compare(Set.of(), Set.of());
		
		assertTrue(result.getRootFiles().isEmpty());
	}


}
