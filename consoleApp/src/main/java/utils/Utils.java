package utils;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.sql.Timestamp;
import java.util.Scanner;

public class Utils {

    public static String readWithRegex(Scanner in, String regex) {
        String str = in.next();
        if (!str.matches(regex)) {
            throw new IllegalArgumentException("Invalid format");
        }
        return str;
    }
    public static String readId(Scanner in) {
        System.out.println("Id:");
        return readWithRegex(in, "[1-9][0-9]*");
    }
    public static String readTimestamp(Scanner in) {
        System.out.println("Year:");
        String year = Utils.readWithRegex(in, "[1-9][0-9]{3}");
        System.out.println("Month:");
        String month = Utils.readWithRegex(in, "(0?[1-9])|(1[0-2])");
        System.out.println("Day:");
        String day = Utils.readWithRegex(in, "(0?[1-9])|([1-2][0-9])|(3[0-2])");
        System.out.println("Hour:");
        String hour = Utils.readWithRegex(in, "(0?[0-9])|(1[0-9])|2[0-3]");
        System.out.println("Minute:");
        String minute = Utils.readWithRegex(in, "(0?[0-9])|([1-5][0-9])");

        return String.format("%04d-%02d-%02dT%02d:%02d:00",
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day),
                Integer.parseInt(hour),
                Integer.parseInt(minute)
        );
    }

    public static JSONObject readPeople(Scanner in) throws JSONException {
        System.out.println("First name:");
        String firstName = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Last name:");
        String lastName = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Pather name:");
        String patherName = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("ward_id");
        String ward_id = Utils.readWithRegex(in,"[1-9][0-9]*");
        System.out.println("diagnosis_id:");
        String diagnosis_id = Utils.readWithRegex(in,"[1-9][0-9]*");

        JSONObject json = new JSONObject();
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("patherName", patherName);
        json.put("wardId", ward_id);
        json.put("diagnosisId", diagnosis_id);

        return json;
    }
    public static JSONObject readDiagnos(Scanner in) throws JSONException {
        System.out.println("name:");
        String name = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");

        JSONObject json = new JSONObject();
        json.put("name", name);

        return json;
    }

    public static JSONObject readWard(Scanner in) throws JSONException {
        System.out.println("name:");
        String name = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("max_count:");
        String max_count = Utils.readWithRegex(in,"[1-9][0-9]*");

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("maxCount", max_count);


        return json;
    }
}
