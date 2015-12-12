package ro.kuberam.libs.java.nlp.lucene;

public class UnicodeBlocksDetection {

	public static Boolean detectDevanagariBlocks(String input) {
		return input.matches("[\\p{InDevanagari}\\p{InGeneral_Punctuation}]*+");
	}

	public static Boolean detectDevanagariTransliterationBlocks(String input) {
		return input
				.matches("[\\p{InBasic_Latin}\\p{InLatin_Extended_A}\\p{InLatin_Extended_Additional}\\p{InGeneral_Punctuation}]*+");
	}

}
