package ro.kuberam.libs.java.nlp.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.sanskritlibrary.webservice.WebServices;

public class TransliterationFilter extends TokenFilter {

	private final Version matchVersion;
	protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);

	// this is for Lucene 5.3.1.
	// protected TransliterationFilter(TokenStream input) {
	// super(input);
	// }

	// this is for Lucene 4.4.0
	public TransliterationFilter(Version matchVersion, TokenStream in) {
		super(in);
		this.matchVersion = matchVersion;
	}

	@Override
	final public boolean incrementToken() throws IOException {
		if (input.incrementToken()) {
			String currentToken = this.input.getAttribute(CharTermAttribute.class).toString().trim();

			String transcodeCurrentToken = currentToken;
			String sourceEncoding = "";

			if (currentToken.matches("[\\p{InDevanagari}\\p{InGeneral_Punctuation}]*+")) {
				sourceEncoding = "deva";
			}

			if (currentToken
					.matches("[\\p{InBasic_Latin}\\p{InLatin_Extended_A}\\p{InLatin_Extended_Additional}\\p{InGeneral_Punctuation}]*+")) {
				sourceEncoding = "roman";
			}

			if (!sourceEncoding.equals("")) {
				try {
					transcodeCurrentToken = WebServices.transformString(currentToken, sourceEncoding,
							"slp1", "/home/claudius/TranscodeFile/transcoders");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			this.charTermAttribute.setEmpty().append(transcodeCurrentToken);

			return true;
		} else
			return false;
	}
}
