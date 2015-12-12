package ro.kuberam.libs.java.nlp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class UnicodeBlockDetectingTest {

	@Test
	public void testDetectDevanagari() throws IOException {
		String input = "ऽगन्तवो․";

		assertTrue(input.matches("[\\p{InDevanagari}\\p{InGeneral_Punctuation}]*+"));
	}

	@Test
	public void testDetectDevanagariAndLatin() throws IOException {
		String input = "ऽगन्तवोa․";

		assertFalse(input.matches("[\\p{InDevanagari}\\p{InGeneral_Punctuation}]*+"));
	}

	@Test
	public void testDetectRomanized() throws IOException {
		String input = " na cāsaṃyukte dravye sayogajanyasya guṇasyotpattir iti jñānotpattidarśanād ātmamanaḥsannikarṣaḥ kāraṇam/";

		assertTrue(input
				.matches("[\\p{InBasic_Latin}\\p{InLatin_Extended_A}\\p{InLatin_Extended_Additional}\\p{InGeneral_Punctuation}]*+"));
	}

	@Test
	public void testDetectRomanizedAndDevanagari() throws IOException {
		String input = "rephavarṇotpādakamoṣṭhapadmaṃ ṣoḍaśasirāvṛtamन्․";

		assertFalse(input
				.matches("[\\p{InBasic_Latin}\\p{InLatin_Extended_A}\\p{InLatin_Extended_Additional}\\p{InGeneral_Punctuation}]*+"));
	}

}
