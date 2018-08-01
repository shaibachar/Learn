package com.example.lucene.luceneDemo.utils;

import java.io.File;
import java.util.Set;

public class FolderUtils {

	public void listFilesForFolder(Set<File> files, final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(files, fileEntry);
			} else {
				files.add(fileEntry);
			}
		}
	}
}
