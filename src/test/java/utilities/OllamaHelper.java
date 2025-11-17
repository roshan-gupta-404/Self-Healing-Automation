package utilities;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OllamaHelper {
  public static String GetLocatorsForPageAsJson(String pageSource) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    String encodedPageSource = Base64.getEncoder().encodeToString(pageSource.getBytes());

    String prompt = "Your Role: you are experienced selenium QA automation tester. Context: here is the Base64 encoded pagesource '"+encodedPageSource+"' Ask: Return only a JSON array of locators from the page source, provide the locator for the elements described in JSON format like {\"locatorName\":\"name of the locator should match the label name if exist\", \"locatorType\":\"id|name|css|xpath|linkText\", \"locator\":\"value\"}. Do not include any explanations, markdown formatting, or additional text.";

    String escapedPrompt = prompt
        .replace("\\", "\\\\") // escape backslashes first
        .replace("\"", "\\\""); // then escape double quotes

    String json = "{"
        + "\"model\":\"gpt-oss:120b-cloud\","
        + "\"prompt\":\"" + escapedPrompt + "\""
        + "}";

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:11434/api/generate"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();

    HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

    BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()));
    String line;
    StringBuilder finalResponse = new StringBuilder();

    while ((line = reader.readLine()) != null) {
      if (!line.isBlank()) {
        var jsonNode = mapper.readTree(line);
        if (jsonNode.has("response")) {
          finalResponse.append(jsonNode.get("response").asText());
        }
      }
    }


    return finalResponse.toString().trim();
  }
}
