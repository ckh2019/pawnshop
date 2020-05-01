package test;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Chen Kaihong
 * 2019-08-25 21:25
 */
public class LuceneTest {

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        //创建一个indexwrite对象
        //指定索引存放位置
        //指定一个分词器,对文档内容进行分析
        Directory directory = FSDirectory.open(Paths.get("D:\\Apicture\\index"));
        //Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory,config);
        //创建Field对象,将Field添加到document对象中
        File f = new File("D:\\Apicture\\source");
        File[] listFiles = f.listFiles();
        for(File file : listFiles) {
            Document document = new Document();
            //4个域
            //文件名称
            String fileName = file.getName();
            Field nameField = new TextField("fileName",fileName, Field.Store.YES);
            //文件大小
            long fileSize = FileUtils.sizeOf(file);
            Field sizeField = new LongPoint("sizeField",fileSize);
            //文件路径
            String filePath = file.getPath();
            Field pathField = new StoredField("filePath",filePath);
            //文件内容
            String fileContent = FileUtils.readFileToString(file);
            Field contentField = new TextField("fileContent",fileContent, Field.Store.NO);

            document.add(nameField);
            document.add(sizeField);
            document.add(pathField);
            document.add(contentField);
            //使用indexwriter将document对象写入索引库.此过程创建索引,并将document对象写入索引库
            indexWriter.addDocument(document);
        }
        //关闭IndexWriter对象
        indexWriter.close();
    }

    /**
     * 执行查询
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        //创建一个Directory对象,,也就是索引库存放的位置
        Directory directory = FSDirectory.open(Paths.get("D:\\Apicture\\index"));
        //创建一个IndexReader对象,需要指定Directory
        IndexReader indexReader = DirectoryReader.open(directory);
        //创建一个indexSearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建一个TermQuery对象,指定查询的域和查询的关键词
        Query query = new TermQuery(new Term("fileContent","可以"));
        //执行查询
        TopDocs topDocs = indexSearcher.search(query,2);
        //遍历查询结果
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for(ScoreDoc scoreDoc : scoreDocs) {
            //文件id
            int doc = scoreDoc.doc;
            Document document = indexReader.document(doc);
            //文件名称
            String fileName = document.get("fileName");
            System.out.println(fileName);
            //文件路径
            String filePath = document.get("filePath");
            System.out.println(filePath);
            //文件大小,(没有存储,会打印null)
            String fileSize = document.get("fileSize");
            System.out.println(fileSize);
        }
        //关流
        indexReader.close();
    }
}
