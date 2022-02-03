package edu.usfca.cs272;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Utility class for parsing text in a consistent manner.
 *
 * @author CS 272 Software Development (University of San Francisco)
 * @version Spring 2022
 */
public class TextParser {
	/** Regular expression that matches any whitespace. **/
	public static final Pattern SPLIT_REGEX = Pattern.compile("(?U)\\p{Space}+");

	/** Regular expression that matches non-alphabetic characters. **/
	public static final Pattern CLEAN_REGEX = Pattern.compile("(?U)[^\\p{Alpha}\\p{Space}]+");

	/**
	 * Cleans the text by removing any non-alphabetic characters (e.g. non-letters
	 * like digits, punctuation, symbols, and diacritical marks like the umlaut)
	 * and converting the remaining characters to lowercase.
	 *
	 * @param text the text to clean
	 * @return cleaned text
	 */
	public static String clean(String text) {
		String cleaned = Normalizer.normalize(text, Normalizer.Form.NFD);
		cleaned = CLEAN_REGEX.matcher(cleaned).replaceAll("");
		return cleaned.toLowerCase();
	}

	/**
	 * Splits the supplied text by whitespaces.
	 *
	 * @param text the text to split
	 * @return an array of {@link String} objects
	 */
	public static String[] split(String text) {
		return text.isBlank() ? new String[0] : SPLIT_REGEX.split(text.strip());
	}

	/**
	 * Parses the text into an array of clean words.
	 *
	 * @param text the text to clean and split
	 * @return an array of {@link String} objects
	 *
	 * @see #clean(String)
	 * @see #parse(String)
	 */
	public static String[] parse(String text) {
		return split(clean(text));
	}

	/**
	 * Demonstrates this class.
	 *
	 * @param args unused
	 */
	public static void main(String[] args) {
		String text = """
				Sally Sue...\t sells 76 sea-shells
				at THE sEa_shorE soirée!""";

		System.out.println("Cleaned:");
		System.out.println(clean(text));

		System.out.println();
		System.out.println(" Split: " + Arrays.toString(split(text)));
		System.out.println("Parsed: " + Arrays.toString(parse(text)));
	}
}
