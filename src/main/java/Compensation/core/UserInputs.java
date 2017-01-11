package Compensation.core;

import java.util.HashMap;
import java.util.Map;

public class UserInputs {
    public Map<String, Map> userMap = new HashMap<>();
    public Map<String, String> userInfo;

    public String getValue(String key1, String key2) {
        return (String) userMap.get(key1).get(key2);
    }

    public void user() {
        User user = new User();
        User user0 = new User();
        User user1 = new User();
        User user99 = new User();

        //user
        //admin
        userInfo = new HashMap<>();
        user.company = "";
        user.username = "stdevqa";
        user.password = "Password";
        user.name = "";
        user.lastName = "";
        user.email = user.username + "@stdevmail.com";
        userInfo.put("company", user.company);
        userInfo.put("username", user.username);
        userInfo.put("password", user.password);
        userInfo.put("name", user.name);
        userInfo.put("lastName", user.lastName);
        userInfo.put("email", user.email);
        userMap.put("user", userInfo);

        //user1
        //forgotPassword
        userInfo = new HashMap<>();
        user1.company = "admin";
        user1.username = "stdevqa5";
        user1.password = "Password";
        user1.name = "STDevQA";
        user1.lastName = "Five";
        user1.email = user1.username + "@stdevmail.com";
        userInfo.put("company", user1.company);
        userInfo.put("username", user1.username);
        userInfo.put("password", user1.password);
        userInfo.put("name", user1.name);
        userInfo.put("lastName", user1.lastName);
        userInfo.put("email", user1.email);
        userMap.put("user1", userInfo);

        //user99
        //contactUs
        userInfo = new HashMap<>();
        user99.company = "admin";
        user99.username = "stdevqa";
        user99.password = "STDev123";
        user99.name = "STDevQA";
        user99.lastName = "Five";
        user99.email = user99.username + "@stdevmail.com";
        userInfo.put("company", user99.company);
        userInfo.put("username", user99.username);
        userInfo.put("password", user99.password);
        userInfo.put("name", user99.name);
        userInfo.put("lastName", user99.lastName);
        userInfo.put("email", user99.email);
        userMap.put("user99", userInfo);
    }

    public static class User {
        public String company;
        public String username;
        public String password;
        public String name;
        public String lastName;
        public String email;
    }
}
