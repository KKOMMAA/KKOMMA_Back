package sopt.org.FourthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.web.multipart.MultipartFile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
@RequiredArgsConstructor
public class NaverService {

    @Value("${clover.stt.client-id}")
    private String CLIENT_ID;

    @Value("${clover.stt.client-secret}")
    private String CLIENT_SECRET;



    public static File multipartToFile(MultipartFile multipart, String fileName)
            throws IllegalStateException, IOException
    {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    @Transactional
    public String useCloverSTT(MultipartFile voiceMultipartFile) {
        try {
            File voiceFile = multipartToFile(voiceMultipartFile, "voicevoice");


            String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", CLIENT_SECRET);


            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if (br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                //response가 진짜임


                //json string parser
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response.toString());
                JSONObject jsonObj = (JSONObject) obj;
                System.out.println(jsonObj.get("text"));


                return jsonObj.get("text").toString();
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return "실패했습니다. ㅈㅅㅈㅅㅈㅅ";
    }



    // Naver CLOVA Sentiment
    public String useCloverSentiment(String diaryContent) {
        StringBuffer response = new StringBuffer();

        try {
            JSONObject object = new JSONObject();
            object.put("content", diaryContent);

            String apiURL = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", CLIENT_SECRET);
            con.setRequestProperty("Content-Type", "application/json");


            // post request
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            try (OutputStream os = con.getOutputStream()){
                byte request_data[] = object.toString().getBytes("utf-8");
                os.write(request_data);
            }

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            System.out.println(response);

            //json string parser
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response.toString());
            JSONObject jsonObj = (JSONObject) obj;
            JSONObject Obj = (JSONObject) jsonObj.get("document");


            System.out.println(Obj.get("sentiment"));

            return Obj.get("sentiment").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "실패했습니다. ㅈㅅㅈㅅㅈㅅ";


    }



}

