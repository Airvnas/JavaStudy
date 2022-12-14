package com.mongo.ex02;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
public class CrawlingTest2 {
	MongoClient mclient;
	MongoDatabase mdb;
	MongoCollection<MelonVO> mcol;
	
	
	public static void main(String[] args) throws IOException{
		String url="https://www.melon.com/chart/index.htm";
		Document doc=Jsoup.connect(url).get();
		Elements divEle=doc.select("div.service_list_song");
		Elements imgEle=doc.select(".wrap img");
		Elements songEle=divEle.select("tr>td:nth-child(6) div.wrap_song_info");
		List<MelonVO> melonArr=new ArrayList<>();
		for(int i=0;i<songEle.size();i++) {
			Element songInfo=songEle.get(i);
			Element songImg=imgEle.get(i);
			//�뷡��������
			String songTitle=songInfo.select("div.ellipsis.rank01 a").text();
			//�����̸� �����ϱ�
			String songSinger=songInfo.select("div.ellipsis.rank02>a").text();
			//�ٹ� �̹��� url ����
			String songUrl=songImg.attr("src");
			//�ٹ� �̹��� �ٿ�ε�
			saveAlbumImg((i+1),songUrl);
			
			
			MelonVO vo=new MelonVO((i+1),songTitle,songSinger,songUrl);
			melonArr.add(vo);
		}//for-------------------------
		//������� �÷����̸� melonChart_�����
		new CrawlingTest2().saveMongodb(melonArr);
	}//-----------------------------------
	public static synchronized void saveAlbumImg(int num,String imgUrl) {
		try {
			URL url=new URL(imgUrl);
			InputStream is=url.openStream();
			BufferedInputStream bis=new BufferedInputStream(is);
			
			File dir=new File("C:/myjava/crawling/melon_20221214");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			File target=new File(dir,num+"album.jpg");
			BufferedOutputStream bos= new BufferedOutputStream(new FileOutputStream(target));
			
			byte[] data=new byte[1024];
			int n=0;
			while((n=bis.read(data))!=-1) {
				bos.write(data,0,n);
			}
			bos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//-----------------------------------
	
	
	
	
	private void mappingPojo(String db) {
		ConnectionString conStr=new ConnectionString("mongodb://localhost:27017");
		CodecRegistry pojoCodec=fromProviders(PojoCodecProvider.builder().automatic(true).build());
		// �⺻ �ڵ� ������Ʈ�� PojoCodecProvider�� �ڵ����� �����ϵ��� ����
		CodecRegistry codecRegistry=fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojoCodec);
		// �̸� ������� ������ ���� ���� �⺻ �ڵ�������Ʈ���� ������ �Ѵ�
				// ������񿡼� ������ BSon�����͸� �ڹ� POJO�� ���ڵ�,���ڵ��ϵ��� �����ϴ� �۾�
				// Bson������ Java Pojo��ü�� �����ϴ� �޼���==> �ڵ� ������Ʈ���� �ʿ���

		MongoClientSettings clientSettings=MongoClientSettings.builder()
											.applyConnectionString(conStr)
											.codecRegistry(codecRegistry)
											.build();
		//������� ���� ���ڿ��� �����ϰ�, �ڵ�������Ʈ���� �����ѵ� �����Ͽ� ����Ŭ���̾�Ʈ ���� ��ü�� ��´�.
		mclient=MongoClients.create(clientSettings);
		//����Ŭ���̾�Ʈ ��ü�� �����ѵ� �̸� ���� ���������ͺ��̽��� ���´�.
		mdb=mclient.getDatabase(db);
	}//--------------------------------------
	
	//������� 100������ ��Ʈ���� �����ϴ� �޼���
	public void saveMongodb(List<MelonVO> arr) {
		this.mappingPojo("mydb");
		String collectionName="melonChart_20221214";
		mcol=mdb.getCollection(collectionName,MelonVO.class);
		InsertManyResult res=mcol.insertMany(arr);
		int n=res.getInsertedIds().size();
		System.out.println(n+"���� ��ť��Ʈ ���� �Ϸ�");
		
	}//----------------------------------------
	

}