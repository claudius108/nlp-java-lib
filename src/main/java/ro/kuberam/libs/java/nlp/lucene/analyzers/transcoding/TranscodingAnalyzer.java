package ro.kuberam.libs.java.nlp.lucene.analyzers.transcoding;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

public class TranscodingAnalyzer extends Analyzer {

	private Version matchVersion = Version.LUCENE_44;

	public TranscodingAnalyzer(Version matchVersion) {
		super();
	}

	@Override
	protected TokenStreamComponents createComponents(final String fieldName, final Reader reader) {
		StandardTokenizer tokenizer = new StandardTokenizer(matchVersion, reader);

		TokenStream tokenStream = new TranscodingFilter(matchVersion, tokenizer);

		return new TokenStreamComponents(tokenizer, tokenStream);
	}
}
