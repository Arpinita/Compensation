package Compensation.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by idska on 05-Dec-16.
 */
public class UserInputs {
    public Map<String, Map> userMap = new HashMap<>();
    public Map<String, String> userInfo;

    public String getValue(String key1, String key2) {
        return (String) userMap.get(key1).get(key2);
    }

    public void user() {
        User user = new User();
        User user0 = new User();


        //user
        userInfo = new HashMap<>();
        user.type = "";
        user.username = "stdevqa";
        user.password = "Password";
        user.firstName = "STDevQA";
        user.lastName = "";
        user.email = user.username + "@stdevmail.com";
        userInfo.put("type", user.type);
        userInfo.put("username", user.username);
        userInfo.put("password", user.password);
        userInfo.put("firstName", user.firstName);
        userInfo.put("lastName", user.lastName);
        userInfo.put("email", user.email);
        userMap.put("user", userInfo);

        //user0
        userInfo = new HashMap<>();
        user0.type = "admin";
        user0.username = "admin";
        user0.password = "pass1234";
        user0.firstName = "STDevQA";
        user0.lastName = "Nine";
        user0.email = user0.username + "@stdevmail.com";
        userInfo.put("type", user0.type);
        userInfo.put("username", user0.username);
        userInfo.put("password", user0.password);
        userInfo.put("firstName", user0.firstName);
        userInfo.put("lastName", user0.lastName);
        userInfo.put("email", user0.email);
        userMap.put("user0", userInfo);


    }

    public static class User {
        public String type;
        public String username;
        public String password;
        public String firstName;
        public String lastName;
        public String email;
        public String id;
    }
}
