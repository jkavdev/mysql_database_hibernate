package br.com.jkavdev.mysql_hibernate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

	public String getLine(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);

		try {
			Stream<String> lines = Files.lines(Paths.get(resource.toURI()), StandardCharsets.UTF_8);
			String line = lines.map(Function.identity()).collect(Collectors.toList()).get(0);
			lines.close();
			return line;
		} catch (IOException | URISyntaxException e) {
			return "";
		}
	}

}
