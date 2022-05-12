package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.dietermai.compare.model.FileTreeBuilder;
import dev.dietermai.compare.model.RootRecord;
import dev.dietermai.compare.service.FSService;

@ExtendWith(MockitoExtension.class)
class FileTreeBuilderTest {

	@Mock
	FSService fs;

	FileTreeBuilder builder;

	@BeforeEach
	void beforeEach() {
		builder = new FileTreeBuilder(fs);
	}

	@Test
	void test_getRootDirectory() {
		assertEquals(RootRecord.of("rootPath"), builder.buildFileTreeOf("rootPath").root());
		assertEquals(RootRecord.of("foo"), builder.buildFileTreeOf("foo").root());
	}

}
