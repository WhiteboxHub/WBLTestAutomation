import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class Main {

    public static void main(String[] args) {

        try {

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://api.github.com/users/whiteboxhub");
            HttpResponse response = client.execute(request);

            System.out.println("Printing Response Header...\n");

            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println("Key : " + header.getName()
                        + " ,Value : " + header.getValue());

            }

            System.out.println("\nGet Response Header By Key ...\n");
            String server = response.getFirstHeader("Server").getValue();

            if (server == null) {
                System.out.println("Key 'Server' is not found!");
            } else {
                System.out.println("Server - " + server);
            }

            System.out.println("\n Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}