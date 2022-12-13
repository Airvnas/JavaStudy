package com.mongo.test;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.*;
import org.bson.codecs.pojo.*;
import org.bson.codecs.configuration.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
public class TestMongoPOJO {
	
	String db="mydb";
	String table="posts";
	MongoClient mclient;
	MongoDatabase mdb;
	MongoCollection<PostVO> mcol;
	
	public TestMongoPOJO() {
		this.mappingPojo();
		
	}
	
	//Bson������ JavaPojo ��ü�� �����ϴ� �޼���==>�ڵ� ������Ʈ���� �ʿ���
	private void mappingPojo() {
		ConnectionString conStr=new ConnectionString("mongodb://localhost:27017");
		CodecRegistry pojoCodec=fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry=fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojoCodec);
		//�����񿡼� ������ Bson�����͸� POJO�� ���ڵ�,���ڵ��ϵ��� �����ϴ� �۾�
		MongoClientSettings clientSettings=MongoClientSettings.builder()
											.applyConnectionString(conStr)
											.codecRegistry(codecRegistry)
											.build();
		mclient=MongoClients.create(clientSettings);
		mdb=mclient.getDatabase(db);
		
		
	}//--------------------------------------
	
	public void insertOne() {
		mcol=mdb.getCollection(table,PostVO.class);
		PostVO vo= new PostVO(null,"Kim","���õ� �����߾��","2022-12-13");
		mcol.insertOne(vo);
		System.out.println(vo.getTitle()+"���� ����߽��ϴ�.");
		
	}//-----------------------------------------
	public static void main(String[] args) {
		TestMongoPOJO app=new TestMongoPOJO();
		app.insertOne();
	}
}////////////////////////////////////////////
