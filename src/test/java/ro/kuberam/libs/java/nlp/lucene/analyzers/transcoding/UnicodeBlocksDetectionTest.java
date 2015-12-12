package ro.kuberam.libs.java.nlp.lucene.analyzers.transcoding;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import ro.kuberam.libs.java.nlp.lucene.analyzers.transcoding.UnicodeBlocksDetection;

public class UnicodeBlocksDetectionTest {

	@Test
	public void testDetectDevanagari() throws IOException {
		String input = "ऽगन्तवो․";

		assertTrue(UnicodeBlocksDetection.detectDevanagariBlocks(input));
	}

	@Test
	public void testDetectDevanagariAndLatin() throws IOException {
		String input = "ऽगन्तवोa․";

		assertFalse(UnicodeBlocksDetection.detectDevanagariBlocks(input));
	}

	@Test
	public void testDetectRomanized() throws IOException {
		String input = " na cāsaṃyukte dravye sayogajanyasya guṇasyotpattir iti jnānotpattidarśanād ātmamanaḥsannikarṣaḥ kāraṇam/";

		assertTrue(UnicodeBlocksDetection.detectDevanagariTransliterationBlocks(input));
	}

	@Test
	public void testDetectRomanizedAndDevanagari() throws IOException {
		String input = "rephavarṇotpādakamoṣṭhapadmaṃ ṣoḍaśasirāvṛtamन्․";

		assertFalse(UnicodeBlocksDetection.detectDevanagariTransliterationBlocks(input));
	}
}
