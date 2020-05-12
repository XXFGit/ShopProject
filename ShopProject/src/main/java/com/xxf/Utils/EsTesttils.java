package com.xxf.Utils;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EsTesttils {

    private static String host="193.112.27.96"; // 服务器地址
    private static int port=9300; // 端口
    private TransportClient client=null;

    /**
     * 获取连接
     * @return
     */
    @Before
    public void getCient()throws Exception{
        TransportAddress node = new TransportAddress(InetAddress.getByName(EsTesttils.host), EsTesttils.port);
        //定义setting
        Settings settings = Settings.builder().put("cluster.name","my-application").build();
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        System.out.println(client);
    }

    /**
     * 关闭连接
     * @param
     */
    @After
    public void close(){
        if(client!=null){
            client.close();
        }
    }


    /**
     * 添加索引
     */
    @Test
    public void testAdd()throws Exception{
        IndexResponse response =client.prepareIndex("hello", "es", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "zhangsan")
                        .field("birthday", new Date())
                        .field("age", 20)
                        .endObject()
                )
                .get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId()); // 第一次使用是1
        System.out.println("当前实例状态："+response.status());
    }

    /**
     * 功能描述: 根据ID查询
     * @Param: [hello]:索引名称（数据库）
     *         [es] : type(表名，相当于数据库的table)
     *         [id] ： 唯一标识（根据id=1到hello的es表中查询数据）
     * @Return: void
     * @Author: Administrator
     * @Date: 2020/5/7 下午 05:13
     */
    @Test
    public void testGet(){
        GetResponse getResponse=client.prepareGet("hello", "es", "1").get();
        System.out.println(getResponse.getSourceAsString());
    }


    /**
     * 功能描述: 根据ID修改
     * @Param: [hello]:索引名称（数据库）
     *         [es] : type(表名，相当于数据库的table)
     *         [id] ： 唯一标识（根据id=1到hello的es表中查询数据）
     *         setDoc(map)
     * @Return: void
     * @Author: Administrator
     * @Date: 2020/5/7 下午 05:13
     */
    @Test
    public void testUpdate() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "lisi");
        map.put("birthday", new Date());
        map.put("age", "25");
        UpdateResponse response = client.prepareUpdate("hello", "es", "1").setDoc(map).get();
        System.out.println(response.toString());
    }

    @Test
    public void testDelete(){
        DeleteResponse response=client.prepareDelete("hello", "es", "1").get();
        System.out.println(response.toString());
    }

}
