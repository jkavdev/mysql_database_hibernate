package br.com.jkavdev.mysql_hibernate.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

	public String getLine(String fileName) {
		String line = getStreamLineFromFile(fileName).map(Function.identity()).collect(Collectors.toList()).get(0);
		return line;
	}

	public List<String> getLines(String fileName) {
		List<String> lines = getStreamLineFromFile(fileName).map(Function.identity()).collect(Collectors.toList());
		return lines;
	}

	public Stream<String> getStreamLineFromFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);

		try {
			Stream<String> linesStream = Files.lines(Paths.get(resource.toURI()), StandardCharsets.UTF_8);
			return linesStream;
		} catch (IOException | URISyntaxException e) {
			return Stream.empty();
		}
	}

}
