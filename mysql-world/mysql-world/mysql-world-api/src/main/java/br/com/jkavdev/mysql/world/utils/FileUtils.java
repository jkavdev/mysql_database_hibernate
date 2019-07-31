package br.com.jkavdev.mysql.world.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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

	public Stream<String> getInputStreamFromFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);

		try {
			Stream<String> linesStream = Files.lines(Paths.get(resource.toURI()), StandardCharsets.UTF_8);
			return linesStream;
		} catch (IOException | URISyntaxException e) {
			return Stream.empty();
		}
	}

	public URL getURL(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		return resource;
	}

	public FileReader getFileReader(String fileName) {
		URL url = getURL(fileName);

		try {
			Path path = Paths.get(url.toURI());
			return new FileReader(path.toFile());
		} catch (URISyntaxException | FileNotFoundException e) {
			return null;
		}
	}
	
	public Iterable<CSVRecord> getRecordsCsvWithHeaders(String fileName, Class<? extends Enum<?>> enumClass) {
		FileReader fileReader = getFileReader(fileName);
		
		try {
			Iterable<CSVRecord> parser = CSVFormat.DEFAULT
					.withQuote(null)
					.withDelimiter(';')
					.withHeader(enumClass)
					.withFirstRecordAsHeader()
					.parse(fileReader);

			return parser;
		} catch (IOException e) {
			return null;
		}
	}
	
	public Iterable<CSVRecord> getRecordsCsvWithHeadersAndComaAndSingleCote(String fileName, Class<? extends Enum<?>> enumClass) {
		
		CSVFormat csvFormat = CSVFormat.DEFAULT
				.withQuote('\'')
				.withEscape('\\')
				.withHeader(enumClass)
				.withFirstRecordAsHeader();
		
		try {
			Path path = Paths.get(getURL(fileName).toURI());
			
			Iterable<CSVRecord> parser = CSVParser.parse(path.toFile(), Charset.forName("UTF-8"), csvFormat);
			
			return parser;
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

}
