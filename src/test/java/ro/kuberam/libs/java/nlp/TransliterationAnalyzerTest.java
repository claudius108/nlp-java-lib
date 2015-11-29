package ro.kuberam.libs.java.nlp;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sanskritlibrary.TranscodingRule;
import org.sanskritlibrary.webservice.WebServices;

import ro.kuberam.libs.java.nlp.lucene.TransliterationAnalyzer;

@RunWith(com.carrotsearch.randomizedtesting.RandomizedRunner.class)
public class TransliterationAnalyzerTest {

	@Test
	public void test() throws IOException {
		Analyzer analyzer = new TransliterationAnalyzer();

		TokenStream tokenStream = analyzer.tokenStream("contents", "कृष्ण उवाच");

		analyzer.close();

		// OffsetAttribute offsetAttribute =
		// tokenStream.addAttribute(OffsetAttribute.class);
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			// int startOffset = offsetAttribute.startOffset();
			// int endOffset = offsetAttribute.endOffset();
			String term = charTermAttribute.toString();
			System.out.print("[" + term + "] ");

			String processedTerm = term;
			Hashtable<String, Object> h = new Hashtable<String, Object>();
			TranscodingRule dtr = (TranscodingRule) h.get("defaultTranscodingRule");
			try {
				processedTerm = WebServices.transformString(processedTerm, "deva", "slp1", "/home/claudius/TranscodeFile/transcoders");
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.print(" = " + processedTerm + " ");
		}
		tokenStream.end();
		tokenStream.close();
	}
}
