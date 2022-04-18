package dev.dietermai.compare.fetch;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TreeFetcherTest {

	@Mock File root;
	
	@Test
	void testEmptyDirectory() {
		FileTreeFetcher treeFetcher = new FileTreeFetcher();
		treeFetcher.fetchTreeOf(root);
//		fail("Check if return value is as expected"); // TODO
	}

}
