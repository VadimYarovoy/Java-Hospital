import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import utils.Utils;

import java.util.*;

public class CourseworkClient {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders header = new HttpHeaders();
    private static final Scanner in = new Scanner(System.in);

    // get by id
    private static void getById(String url) {
        String id = Utils.readId(in);
        ResponseEntity<Object> response = restTemplate.getForEntity(url + "/" + id, Object.class);
        System.out.println(response.getBody());
    }
    private static void getPeopleById() {
        try {
            getById("http://localhost:8080/people/find");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Person not found");
            } else {
                throw e;
            }
        }
    }
    private static void getWardById() {
        try {
            getById("http://localhost:8080/wards/find");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Ward not found");
            } else {
                throw e;
            }
        }
    }
    private static void getDiagnosById() {
        try {
            getById("http://localhost:8080/diagnosis/find");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Diagnos not found");
            } else {
                throw e;
            }
        }
    }

    // delete
    private static void deleteById(String url) {
        String id = Utils.readId(in);

        HttpEntity<String> request = new HttpEntity<>(null, header);
        restTemplate.exchange(
                url + "/" + id, HttpMethod.DELETE, request, Object.class
        );
        System.out.println("Successful deletion");
    }
    private static void deletePeopleById() {
        try {
            deleteById("http://localhost:8080/people/delete");
        } catch (Exception e) {
            System.out.println("Access denied or people not found");
        }
    }
    private static void deleteWardById() {
        try {
            deleteById("http://localhost:8080/wards/delete");
        } catch (Exception e) {
            System.out.println("Access denied or ward not found");
        }
    }
    private static void deleteDiagnosById() {
        try {
            deleteById("http://localhost:8080/diagnosis/delete");
        } catch (Exception e) {
            System.out.println("Access denied or diagnos not found");
        }
    }

    // add
    private static void add(String url, JSONObject json) {
        HttpEntity<String> request = new HttpEntity<>(json.toString(), header);
        ResponseEntity<Object> response = restTemplate.exchange(
                url, HttpMethod.POST, request, Object.class
        );
        //System.out.println(response.getBody());
    }
    private static void addPeople() throws JSONException {
        JSONObject json = Utils.readPeople(in);
        try {
            add("http://localhost:8080/people/add", json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("ward or diagnos not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else if (status.equals(HttpStatus.CONFLICT)) {
                System.out.println("ward is full");
            } else {
                throw e;
            }
        }
    }
    private static void addWard() throws JSONException {
        JSONObject json = Utils.readWard(in);
        try {
            add("http://localhost:8080/wards/add", json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }

    }
    private static void addDiagnos() throws JSONException {
        JSONObject json = Utils.readDiagnos(in);
        try {
            add("http://localhost:8080/diagnosis/add", json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }

    // update
    private static void updateById(String url, String id, JSONObject json) {
        HttpEntity<String> request = new HttpEntity<>(json.toString(), header);
        ResponseEntity<Object> response = restTemplate.exchange(
                url + "/" + id, HttpMethod.PUT, request, Object.class
        );
        System.out.println(response.getBody());
    }
    private static void updatePeopleById() throws JSONException {
        String id = Utils.readId(in);
        JSONObject json = Utils.readPeople(in);
        try {
            updateById("http://localhost:8080/people/update", id, json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("People not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void updateWardById() throws JSONException {
        String id = Utils.readId(in);
        JSONObject json = Utils.readWard(in);
        try {
            updateById("http://localhost:8080/wards/update", id, json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Ward not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void updateDiagnosById() throws JSONException {
        String id = Utils.readId(in);
        JSONObject json = Utils.readDiagnos(in);
        try {
            updateById("http://localhost:8080/diagnosis/update", id, json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Diagnos not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }

    // complicated
    private static void getAllBySubjectId(String url) {
        String id = Utils.readId(in);
        ResponseEntity<List<Object>> response = restTemplate.exchange(
                url + "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){}
        );
        List<Object> list = Objects.requireNonNull(response.getBody());
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    private static void getPeopleByDiagnosId() {
        try {
            getAllBySubjectId("http://localhost:8080/people/find-by-diagnosis-id");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("People not found");
            } else {
                throw e;
            }
        }
    }
    private static void getPeopleByWardId() {
        try {
            getAllBySubjectId("http://localhost:8080/people/find-by-ward-id");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("People not found");
            } else {
                throw e;
            }
        }
    }
    private static void getDiagnosByPeopleId() {
        try {
            getAllBySubjectId("http://localhost:8080/diagnosis/find-by-person-id");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Diagnos not found");
            } else {
                throw e;
            }
        }
    }
    private static void getWardByPeopleId() {
        try {
            getAllBySubjectId("http://localhost:8080/wards/find-by-person-id");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Ward not found");
            } else {
                throw e;
            }
        }
    }

    private static void getPeopleByDiagnosName() {
        System.out.println("Name:");
        String name = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        String url = "http://localhost:8080/people/find-by-diagnosis-name/" + name;
        List list = restTemplate.getForObject(url, List.class);
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void getPeopleByWardName() {
        System.out.println("Name:");
        String name = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        String url = "http://localhost:8080/people/find-by-ward-name/" + name;
        List list = restTemplate.getForObject(url, List.class);
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void getDiagnosByPeopleName() {
        System.out.println("Name:");
        String name = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        String url = "http://localhost:8080/diagnosis/find-by-person-name/" + name;
        List list = restTemplate.getForObject(url, List.class);
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void getWardByPeopleName() {
        System.out.println("Name:");
        String name = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        String url = "http://localhost:8080/wards/find-by-person-name/" + name;
        List list = restTemplate.getForObject(url, List.class);
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }

    // authorization
    private static void signIn() throws JSONException {
        System.out.println("Username: ");
        String username = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Password: ");
        String password = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z0-9]+");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), header);
        String url = "http://localhost:8080/auth/signin";
        try {
            String token = restTemplate.postForObject(url, request, String.class);
            if (token != null) {
                header.set("Authorization", "Bearer " + token);
                System.out.println("Authorization succeeded");
            }
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Bad credentials");
            } else {
                throw e;
            }
        }
    }
    private static void addUser() throws JSONException {
        System.out.println("Username: ");
        String username = Utils.readWithRegex(in, "[а-яА-Яa-zA-Z]+");
        System.out.println("Password: ");
        String password = Utils.readWithRegex(in, "[a-zA-Z]+");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), header);
        String url = "http://localhost:8080/auth/add-user";
        try {
            restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
            System.out.println("User added with privilege USER");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("User not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void deleteUser() throws JSONException {
        System.out.println("Username: ");
        String username = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), header);
        String url = "http://localhost:8080/auth/delete-user";
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, request, Object.class);
            System.out.println("Successful deletion");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("User not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void getAllUsers() throws JSONException {
        String url = "http://localhost:8080/auth/list-users";
        List list = restTemplate.getForObject(url, List.class);

        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    // help
    private static void getCommands() {
        String[] commands;
        commands = new String[]{
            "get people by id",
            "get auto by id",
            "get personnel by id",
            "get route by id",
            "list journals",
            "list autos",
            "list personnels",
            "list routes",
            "add journal [USER, ADMIN]",
            "add auto [USER, ADMIN]",
            "add personnel [USER, ADMIN]",
            "add route [USER, ADMIN]",
            "update journal by id [USER, ADMIN]",
            "update auto by id [USER, ADMIN]",
            "update personnel by id [USER, ADMIN]",
            "update route by id [USER, ADMIN]",
            "delete journal by id [ADMIN]",
            "delete auto by id [ADMIN]",
            "delete personnel by id [ADMIN]",
            "delete route by id [ADMIN]",
            "get journals by auto id",
            "get journals by route id",
            "get autos by personnel id",
            "get journals by auto num",
            "get autos by personnel last name",
            "get auto by num",
            "get autos by color",
            "get autos by mark",
            "sign in [ADMIN]",
            "add user [ADMIN]",
            "delete user [ADMIN]",
        };
        Arrays.stream(commands).forEach(System.out::println);
    }

    public static void main(String[] args) {
        header.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("Hello! In order to see all available commands, enter 'help'");
        String command = in.nextLine();
        while (!(command.equals("exit"))) {
            try {
                switch (command) {
                    case "get people by id" -> getPeopleById(); //
                    case "get diagnos by id" -> getDiagnosById(); //
                    case "get ward by id" -> getWardById(); //

                    case "add people" -> addPeople(); //
                    case "add diagnos" -> addDiagnos(); //
                    case "add ward" -> addWard(); //

                    case "update people by id" -> updatePeopleById(); //
                    case "update diagnos by id" -> updateDiagnosById(); //
                    case "update ward by id" -> updateWardById(); //

                    case "delete people by id" -> deletePeopleById();
                    case "delete diagnos by id" -> deleteDiagnosById();
                    case "delete ward by id" -> deleteWardById();

                    case "get diagnos by people id" -> getDiagnosByPeopleId(); //
                    case "get diagnos by people name" -> getDiagnosByPeopleName(); //
                    case "get ward by people id" -> getWardByPeopleId(); //
                    case "get ward by people name" -> getWardByPeopleName(); //

                    case "get people by diagnos id" -> getPeopleByDiagnosId(); //
                    case "get people by diagnos name" -> getPeopleByDiagnosName(); //
                    case "get people by ward id" -> getPeopleByWardId(); //
                    case "get people by ward name" -> getPeopleByWardName(); //

                    case "sign in" -> signIn();
                    case "add user" -> addUser();
                    case "delete user" -> deleteUser();
                    case "list users" -> getAllUsers();

                    case "help" -> getCommands();
                    case "" -> System.out.print("");

                    default -> System.out.println("Wrong command entered");
                }
            } catch (JSONException | IllegalArgumentException e) {
                System.out.println("Invalid arguments entered");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong, please try again");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            command = in.nextLine();
        }
    }
}
