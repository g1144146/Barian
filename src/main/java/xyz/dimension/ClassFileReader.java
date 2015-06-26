package xyz.dimension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ClassFileReader {

	public ClassFileReader() {}

	public static byte[] getBytes(String fileName) throws IOException {
		return Files.readAllBytes(Paths.get(fileName));
	}
}