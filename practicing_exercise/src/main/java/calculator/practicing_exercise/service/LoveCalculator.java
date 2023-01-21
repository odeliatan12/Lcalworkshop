package calculator.practicing_exercise.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service;

import calculator.practicing_exercise.model.LoveResult;

@Service
public class LoveCalculator {
    
    private static final String LOVE_CALCULATOR_URL = "https://love-calculator.p.rapidapi.com/getPercentage";  

    public LoveResult getDescription(String firstName, String secondName) throws IOException, InterruptedException{

        String apikey = System.getenv("LOVE_CALCULATOR_API_KEY");
        HttpRequest requestURL = HttpRequest.newBuilder()
                                        .uri(URI.create(LOVE_CALCULATOR_URL + "?fname=" + firstName + "&sname=" + secondName))
                                        // how to add the headers into the REST template class
                                        .header("X-RapidAPI-Key", apikey)
                                        .header("X-RapidAPI-Host", "love-calculator.p.rapidapi.com")
                                        .method("GET", HttpRequest.BodyPublishers.noBody())
                                        .build();

        System.out.println("error =============" + requestURL);
        HttpResponse<String> response = HttpClient.newHttpClient().send(requestURL, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        LoveResult l = LoveResult.createResult(response.body());

        return l;
    }

    
}
