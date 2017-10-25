import java.net.HttpURLConnection;
import java.net.URL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

/** 아아아아ㅏ아*/

public class SilentHill3Downloader{
	public static void main(String[] args) throws Exception {
		Document doc = Jsoup.connect("http://hygame.tistory.com/2227").get();
		Elements elements = doc.select("a[href~=attachment]");
		
		int cur = 1, total = elements.size();
		
		System.out.printf("총 %d개\n", total);
		
		
		String zipUrl="";
		for(Element e : elements){
			zipUrl = e.attr("href");
			download(zipUrl, ext(zipUrl));
			System.out.printf("%d / %d ... done!\n", cur++, total);
		}
	}
	private static void download(String zipUrl, String ext) throws Exception {
		File f = new File("c:\\silent hills 3\\");
		f.mkdirs();
		FileOutputStream fos = new FileOutputStream(f+"\\"+"Silent Hill 3"+ext);
		HttpURLConnection conn = (HttpURLConnection)new URL(zipUrl).openConnection();
		InputStream is = conn.getInputStream();
		byte[] buf = new byte[32*1024];
		int len=0;
		
		while((len = is.read(buf))>0){
			fos.write(buf, 0, len);
		}
		fos.close();
	}
	private static String ext(String zipUrl){
		String ext="";
		for(int i=zipUrl.length()-1;i>-1;i--){
			if(zipUrl.charAt(i)=='.') break;
			ext = zipUrl.charAt(i) + ext;
		}
		return "."+ext;
	}
}
