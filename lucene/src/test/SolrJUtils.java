package test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Chen Kaihong
 * 2019-08-27 13:05
 */
public class SolrJUtils {

    @Test
    public void testAdd() throws IOException, SolrServerException {
        String baseUrl = "http://localhost:8082/solr/collection1";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(baseUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
        for(int i = 0; i < 5; i++) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",i);
            document.setField("name","陈凯宏"+i);
            solrClient.add(document);
        }
        solrClient.commit();
    }

    @Test
    public void testDelete() throws IOException, SolrServerException {
        String baseUrl = "http://localhost:8082/solr/collection1";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(baseUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
        //solrClient.deleteById("40");
        solrClient.deleteByQuery("name:陈");
        solrClient.commit();
    }
    @Test
    public void testQuery() throws IOException, SolrServerException {
        String baseUrl = "http://localhost:8082/solr/collection1";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(baseUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("凯");
        //id排序
        solrQuery.addSort("id", SolrQuery.ORDER.desc);

        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(3);
        solrQuery.set("df","name");
        solrQuery.set("fl","id,name");
        QueryResponse response = solrClient.query(solrQuery);

        SolrDocumentList docs = response.getResults();

        long num = docs.getNumFound();
        System.out.println(num);
        for (SolrDocument document : docs) {
            System.out.println(document.get("id"));
            System.out.println(document.get("name"));
        }

        solrClient.commit();
    }

    @Test
    public void test3(){
        double[] scores = {1.5,1,2,4,2,1.5,4,4.5,0.5,
                0.5,1,2,3,2,2,1,0.5,2,4,4,2,1,2,
        1,1,2,2,2,2,3,2,3,2,5,};
    }

    public double getJD(int score) {
        if(score >= 90) {
            return 4;
        }else if (score >= 85) {
            return 3.7;
        }else if (score >= 82) {
            return 3.3;
        }else if(score >= 78) {
            return 3;
        }else if(score >=75) {
            return 2.7;
        }else if (score >= 71) {
            return 2.3;
        }else if(score >= 66) {
            return 2;
        }else if(score >= 62){
            return 1.7;
        }else if(score>= 60){
            return 1.3;
        }else {
            return 1;
        }
    }


    @Test
    public void test11() {



    }




}



class MyRunnable implements Runnable {

    @Override
    public void run() {
        Ticket ticket = new Ticket();
        System.out.println(Ticket.sum);
        while (Ticket.sum > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket.sale();
        }
    }
}



class Ticket {

    public static int sum = 100;

    private Object object = new Object();

    public static synchronized void sale2() {
        if (sum > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第" + sum);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum--;

        } else {
            System.out.println(Thread.currentThread().getName() + "无票");
        }
    }

    public void sale() {
        synchronized (this) {
            if (sum > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + sum);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum--;

            } else {
                System.out.println(Thread.currentThread().getName() + "无票");
            }
        }






    }

}
