package calculator.practicing_exercise.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class LoveResult {
    
    private String fname;
    private String sname;
    private Integer percentage;
    private String result;

    public Integer getPercentage() {
        return percentage;
    }
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public static LoveResult createJson(JsonObject object){

        LoveResult names = new LoveResult();

        // Step 1: get the first and second names and set them
        names.fname =  object.getString("fname");
        names.sname = object.getString("sname");	

        return names;
    }

    public static LoveResult createResult(String json) throws IOException{
        LoveResult result = new LoveResult();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){

            JsonReader reader = Json.createReader(is);
            JsonObject o = reader.readObject();

            String person1name = URLDecoder.decode(o.getString("fname"), "UTF-8");
            String person2name = URLDecoder.decode(o.getString("sname"), "UTF-8");
            
            result.setFname(person1name);
            result.setSname(person2name);
            result.setPercentage(Integer.parseInt(o.getString("percentage")));
            result.setResult(o.getString("result"));
        }
        return result;
    }
    
}
