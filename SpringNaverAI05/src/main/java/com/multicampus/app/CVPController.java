package com.multicampus.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class CVPController {
	
	String clientId="0xok1o8ptm";
	String clientSecret="AbECzqJO1yw9jhtOPJ3Bzu48kIB0oEMUdv2RuPmV";
	
	@GetMapping("/voiceform")
	public ModelAndView csrform() {
		return new ModelAndView("clova_voice");
	}//---------------------------------
	
	@PostMapping(value="/voiceEnd",produces="text/plain;charset=UTF-8;" )
	public String voiceTransform(HttpSession ses, @RequestParam("text") String text) {
		log.info("text: "+text);
		ServletContext app=ses.getServletContext();
		String path=app.getRealPath("/file");
		
		String fileName="mp3νμΌλͺ";
		try {
            text = URLEncoder.encode(text, "UTF-8"); // 13μ
            String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "speaker=nbora&volume=1&speed=0&pitch=0&format=mp3&text=" + text;
            con.setDoOutput(true);
            con.setDoInput(true);
            
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // μ μ νΈμΆ
                InputStream is = con.getInputStream();
                //λ€μ΄λ²μμ λ°μ΄λλ¦¬ λ°μ΄ν°λ₯Ό λ°μμ =>webμλ²μ fileλλ ν λ¦¬μ νμΌννλ‘ μ μ₯νλ€.
                int read = 0;
                byte[] bytes = new byte[1024];
                // λλ€ν μ΄λ¦μΌλ‘ mp3 νμΌ μμ±
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(path,tempname + ".mp3");
                log.info("mp3νμΌ κ²½λ‘==="+f.getAbsolutePath());
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                    outputStream.flush();
                }
                is.close();
                outputStream.close();
                fileName=tempname+".mp3";
            } else {  // μ€λ₯ λ°μ
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                fileName="error.mp3";
            }
        } catch (Exception e) {
            System.out.println(e);
            fileName="error.mp3";
        }
		
		return fileName;
	}
	
}
