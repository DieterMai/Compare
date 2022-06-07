package dev.dietermai.compare.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import dev.dietermai.compare.model.IParent;

public class FSService {

	public Set<String> getFiles(IParent parent) {
		HashSet<String> files = new HashSet<>();
		
		try {
			Files.newDirectoryStream(parent.path()).forEach(path -> files.add(path.getFileName().toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return files;
	}
	
	

}
