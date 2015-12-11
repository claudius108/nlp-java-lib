package ro.kuberam.libs.java.nlp.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

public class TransliterationAnalyzer extends Analyzer {

	private Version matchVersion = Version.LUCENE_44;

	public TransliterationAnalyzer() {
		super();
	}

	@Override
	protected TokenStreamComponents createComponents(final String fieldName, final Reader reader) {
		StandardTokenizer tokenizer = new StandardTokenizer(matchVersion, reader);

		TokenStream tokenStream = new TransliterationFilter(matchVersion, tokenizer);

		return new TokenStreamComponents(tokenizer, tokenStream);
	}
}
