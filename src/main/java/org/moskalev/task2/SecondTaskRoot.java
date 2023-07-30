package org.moskalev.task2;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SecondTaskRoot {
    public static void main(String[] args) throws IOException, ParseException, RuntimeException {
        Object jsonParser = new JSONParser().parse(new FileReader("users.json"));
        JSONObject parserToObject = (JSONObject) jsonParser;
        JSONArray usersJsonArray = (JSONArray) parserToObject.get("users");
        List<User> users = usersParserJsonSimpleImpl(usersJsonArray);
        usersAgeLessThan20Counter(users);
        usersWithTwiceWinnersDogs(users);
     }

     public static void usersWithTwiceWinnersDogs (List<User> users){
         users.stream()
                 .filter(user -> user.getDogs().stream()
                         .filter(dog -> dog.getRanks().stream()
                                 .filter(rank -> rank ==1)
                                 .count() >= 2)
                         .count() > 0)
                 .forEach(user -> {
                     try (FileWriter fw = new FileWriter("result2.txt", true);){
                         fw.write(userToJsonObjectGsonImpl(user) + ",");
                         fw.flush();
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }
                 });
     }

     public static void usersAgeLessThan20Counter(List<User> users){
         users.stream().filter(x-> x.getAge() < 20).forEach(x-> {
             try (FileWriter fw = new FileWriter("result1.txt", true);){
                 fw.write(userToJsonObjectGsonImpl(x) + ",");
                 fw.flush();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
         });
     }

    public static String userToJsonObjectGsonImpl(User user){
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public static List<User> usersParserJsonSimpleImpl(JSONArray jsonArray){
        List<User>  users = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject userJsonObject = (JSONObject) jsonArray.get(i);
            JSONArray SingleUserDogsJsonArray = (JSONArray) userJsonObject.get("dogs");
            List<Dog> dogsList = new ArrayList<>();
            for(int j = 0; j < SingleUserDogsJsonArray.size(); j++) {
                JSONObject dogTemp = (JSONObject) SingleUserDogsJsonArray.get(j);
                JSONArray dogJsonRanks = (JSONArray) dogTemp.get("ranks");
                List<Long> dogTempRankList = new ArrayList<>();
                dogTempRankList.addAll(dogJsonRanks);
                dogsList.add(Dog.builder()
                        .age((Long) dogTemp.get("age"))
                        .name((String) dogTemp.get("name"))
                        .ranks(dogTempRankList)
                        .build());
            }
            users.add(User.builder()
                    .name((String) userJsonObject.get("name"))
                    .age((Long) userJsonObject.get("age"))
                    .dogs(dogsList)
                    .build());
        }
        return users;
    }
}
