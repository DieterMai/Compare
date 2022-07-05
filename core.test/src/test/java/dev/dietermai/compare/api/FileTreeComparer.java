package dev.dietermai.compare.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import dev.dietermai.compare.core.api.FileTreeComparator;
import dev.dietermai.compare.core.model.compare.ComparedRegularFile;
import dev.dietermai.compare.core.model.compare.ComparedTree;
import dev.dietermai.compare.core.model.file.FileTreeRoot;
import dev.dietermai.compare.core.model.file.RegularFile;

class FileTreeComparer {

	private static final FileTreeRoot rootA = FileTreeRoot.of(Path.of("rootA"));
	private static final FileTreeRoot rootB = FileTreeRoot.of(Path.of("rootB"));
	
	
	@Test
	void test_compare_emptyRoots() {
		ComparedTree comparedTree =  new FileTreeComparator().compare(rootA, rootB);
		
		assertTrue(comparedTree.isEmpty());
		assertEquals(Set.of(), comparedTree.getRootChildren());
	}
	
	@Test
	void test_compare_rootsWithEqualRegularFiles() {
		ComparedTree comparedTree =  new FileTreeComparator().compare(rootA, rootB);
		// TODO
//		assertFalse(comparedTree.isEmpty());
//		assertEquals(equalRegFileCompares("file1", "file2", "file3", "file4"), comparedTree.getRootChildren());
	}
	
	private Set<ComparedRegularFile> equalRegFileCompares(String...names){
		return Stream.of(names).map(this::equalRegFileCompare).collect(Collectors.toSet());
	}
	
	private ComparedRegularFile equalRegFileCompare(String name) {
		return ComparedRegularFile.of(name, List.of(RegularFile.of(rootA, name), RegularFile.of(rootB, name)));
	}

}
