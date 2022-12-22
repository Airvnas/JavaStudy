package ex03;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import common.KomoranUtil;
import common.nlp.CloudImageGenerator;
import common.nlp.CloudViewer;
import common.nlp.StringProcessor;
import common.nlp.WordCount;
import lombok.extern.log4j.Log4j;

@Log4j
public class NaverNewsCrawlingKomoran {

	public static void main(String[] args) throws Exception{
		String url="http://www.digitaltoday.co.kr/news/articleView.html?idxno=467093";
		NaverNewsCrawlingKomoran app=new NaverNewsCrawlingKomoran();
		app.getNewsContents(url);
		
	}//---------------------------------------------------------

	public String getNewsContents(String url) throws Exception{
		String str="";
		//http프로토콜만 가능. https프로토콜은 보안상 안된다.
		Document doc=Jsoup.connect(url).get();
		//log.info(doc);
		Elements newsContent= doc.select("#article-view-content-div");
		str=newsContent.text();
		List<String> nounList= KomoranUtil.getWordNouns(str);
		Map<String,Integer> wordCountMap= KomoranUtil.getWordCount(nounList);
		List<WordCount> wordCountList= KomoranUtil.getWordCountSortProc(wordCountMap, 1);
		
		log.info(wordCountList);
		HashSet<String> stopWord=new HashSet<>();//불용어 컬렉션
		stopWord.add("상황");
		stopWord.add("오프라인");
		
		StringProcessor strProc=new StringProcessor(str, stopWord);
		
		ArrayList<WordCount> arrList=strProc.processString2(wordCountMap,0);
		
		makeWordCloudView(strProc);
		return str;
	}//----------------------------------------------------------
	public static final int WIDTH=1200;
	public static final int HEIGHT=600;
	public static final int PADDING=30;
	
	
	public void makeWordCloudView(StringProcessor strProc) {
		JFrame f=new JFrame("WordCloudView");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationByPlatform(true);
		f.pack();
		Insets inset=f.getInsets();
		Dimension dim=calcScreenSize(inset);
		f.setSize(dim);
		
		CloudImageGenerator gen=new CloudImageGenerator(WIDTH, HEIGHT, PADDING);//w 1200, h 800 padding 30
		BufferedImage bufImg=gen.generateImage(strProc, 1);
		CloudViewer panel=new CloudViewer(bufImg);
		f.setContentPane(panel);
		f.setVisible(true);
	}	
	private static Dimension calcScreenSize(Insets insets) {
        int width = insets.left + insets.right + WIDTH + PADDING * 2;
        int height = insets.top + insets.bottom + HEIGHT + PADDING * 2;
        return new Dimension(width, height);
    }

}
