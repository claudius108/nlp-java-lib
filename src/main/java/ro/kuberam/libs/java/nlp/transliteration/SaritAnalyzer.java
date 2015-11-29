package ro.kuberam.libs.java.nlp.transliteration;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class SaritAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		StandardTokenizer tokenizer = new StandardTokenizer();

		TokenStream tok = new StandardFilter(tokenizer);
		tok = new TranscodingFilter(tok);

		return new TokenStreamComponents(tokenizer, tok);
	}
}
