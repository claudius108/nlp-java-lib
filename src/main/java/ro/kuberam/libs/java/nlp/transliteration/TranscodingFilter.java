package ro.kuberam.libs.java.nlp.transliteration;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.sanskritlibrary.webservice.WebServices;

public class TranscodingFilter extends TokenFilter {

	protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);

	protected TranscodingFilter(TokenStream input) {
		super(input);
	}

	@Override
	public boolean incrementToken() throws IOException {
		if (input.incrementToken()) {
			String currentToken = this.input.getAttribute(CharTermAttribute.class).toString().trim();

			String transcodeCurrentToken = null;
			try {
				transcodeCurrentToken = WebServices.transformString(currentToken, "deva", "slp1",
						"/home/claudius/TranscodeFile/transcoders");
			} catch (Exception e) {
				e.printStackTrace();
			}

			this.charTermAttribute.setEmpty().append(transcodeCurrentToken);

			return true;
		} else
			return false;
	}
}
