package com.tedi.park.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiFunction;
/**
 * 
 * @author KevinBlandy
 *
 */
public class FileUtils {
	
	/**
	 * 重命名指定文件夹下的所有文件,或者文件夹
	 * @param source
	 * @param func
	 * @throws IOException
	 */
	public static void renameDir(String sourceDir,BiFunction<String,Boolean,String> func) throws IOException {
		File file = new File(sourceDir);
		File[] subFiles = file.listFiles();
		if(subFiles != null && subFiles.length > 0) {
			for(File subFile : subFiles) {
				boolean isDirectory = subFile.isDirectory();
				if(isDirectory) {
					renameDir(subFile.getAbsolutePath(),func);
				}
				subFile.renameTo(new File(subFile.getParentFile(), func.apply(subFile.getName(), isDirectory)));
			}
		}
	}

	/**
	 * copy整个文件夹
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copyDir(String sourceDir, String targetDir) throws IOException {
		Path sourcePath = Paths.get(sourceDir);
		Path targetPath = Paths.get(targetDir);
		Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				Files.copy(dir, targetPath.resolve(sourcePath.relativize(dir)));
				return FileVisitResult.CONTINUE;
			}
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.copy(file, targetPath.resolve(sourcePath.relativize(file)));
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
