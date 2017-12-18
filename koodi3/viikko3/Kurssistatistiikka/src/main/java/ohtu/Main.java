package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );

        String url2 = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();
        
        Gson mapper2 = new Gson();
        Course course = mapper2.fromJson(bodyText2, Course.class);
        
        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm());
        System.out.println("");

        System.out.print("Opiskelijanumero: ");
        System.out.println(studentNr);
        System.out.println("");

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
//        System.out.println("Oliot:");
        int tunnit = 0;
        int tehtavat = 0;
        for (Submission submission : subs) {
            tunnit += submission.getHours();
            tehtavat += submission.getExercises().length;
            System.out.print("Viikko " + submission.getWeek() + ": tehtyjä tehtäviä yhteensä: " + submission.getExercises().length
             + " (maksimi " + course.getExercises()[submission.getWeek()] +  "), aikaa kului " + submission.getHours() + " tuntia, tehdyt tehtävät: ");
            for (int i : submission.getExercises()) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Yhteensä: " + tehtavat + " tehtävää " + tunnit + " tuntia");
        System.out.println("");
        
        String url3 = "https://studies.cs.helsinki.fi/ohtustats/stats";
        
        String bodyText3 = Request.Get(url3).execute().returnContent().asString();
        
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(bodyText3).getAsJsonObject();
        
        JsonObject eka = parsittuData.getAsJsonObject("1");
        JsonObject toka = parsittuData.getAsJsonObject("2");
        JsonObject kolmas = parsittuData.getAsJsonObject("3");
        
        JsonElement ekat = eka.get("exercise_total");
        int ekoja = ekat.getAsInt();
        JsonElement tokat = toka.get("exercise_total");
        int tokia = tokat.getAsInt();
        JsonElement kolmannet = kolmas.get("exercise_total");
        int kolmansia = kolmannet.getAsInt();
        int tehtavia = ekoja + tokia + kolmansia;
        
        JsonElement ekat2 = eka.get("students");
        int ekoja2 = ekat2.getAsInt();
        JsonElement tokat2 = toka.get("students");
        int tokia2 = tokat2.getAsInt();
        JsonElement kolmannet2 = kolmas.get("students");
        int kolmansia2 = kolmannet2.getAsInt();
        int palautuksia = ekoja2 + tokia2 + kolmansia2;
        System.out.println("Kurssilla yhteensä " + palautuksia + " palautusta, palautettuja tehtäviä " + tehtavia + " kpl");
    }
}
