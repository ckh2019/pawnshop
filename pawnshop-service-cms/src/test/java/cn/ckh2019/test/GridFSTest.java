/*
package cn.ckh2019.test;

import cn.ckh2019.pawnshop.service.cms.PawnshopServiceCmsApplication;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

*/
/**
 * @author Chen Kaihong
 * 2019-10-29 21:48
 *//*

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PawnshopServiceCmsApplication.class)
public class GridFSTest {


    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;
    */
/**
     * 存文件
     *//*

    @Test
    public void test1() throws FileNotFoundException {
        File file = new File("C:\\Users\\陈凯宏\\Desktop/msg.txt");
        FileInputStream fis = new FileInputStream(file);
        ObjectId objectId = gridFsTemplate.store(fis,"msg");
        System.out.println(objectId);

    }

    */
/**
     * 取文件
     *//*

    @Test
    public void test2 () throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5db851ccd2647e29b4872b85")));
        System.out.println("gridFSFile == " + gridFSFile);
        GridFSDownloadStream stream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());

        GridFsResource resource = new GridFsResource(gridFSFile, stream);
        String s = IOUtils.toString(resource.getInputStream(), "gbk");
        System.out.println(s);
    }


}
*/
