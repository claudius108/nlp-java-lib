package ro.kuberam.libs.java.nlp;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;
import org.junit.runner.RunWith;

import ro.kuberam.libs.java.nlp.lucene.TransliterationAnalyzer;

@RunWith(com.carrotsearch.randomizedtesting.RandomizedRunner.class)
public class TransliterationAnalyzerTest {

	@Test
	public void test1() throws IOException {
		Directory index = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(new TransliterationAnalyzer())
				.setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(index, config);
		Document document1 = new Document();
		document1.add(new TextField("title", "कृष्ण उवाच", Store.YES));

		Document document2 = new Document();
		document2.add(new TextField("title", "कृष्ण", Store.YES));

		writer.addDocument(document1);
		writer.addDocument(document2);
		writer.commit();
		writer.close();

		int limit = 10;
		try (IndexReader reader = DirectoryReader.open(index)) {
			Query query = new WildcardQuery(new Term("title", "kfzRa*"));
			printSearchResults(limit, query, reader);
		}

		index.close();
	}

	private void printSearchResults(final int limit, final Query query, final IndexReader reader)
			throws IOException {
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs docs = searcher.search(query, limit);

		System.out.println(docs.totalHits + " found for query: " + query);

		for (final ScoreDoc scoreDoc : docs.scoreDocs) {
			System.out.println(searcher.doc(scoreDoc.doc));
		}
	}
}
