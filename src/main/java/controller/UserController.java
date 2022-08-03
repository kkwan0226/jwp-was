package controller;

import db.DataBase;
import model.User;
import webserver.request.HttpRequest;
import webserver.request.Path;

public class UserController {

    private static final Path USER_CREATE_PATH = Path.from("/user/create");

    public void route(HttpRequest httpRequest) {
        if (USER_CREATE_PATH.equals(httpRequest.getStartLine().getPath())) {
            createUser(httpRequest);
        }
    }

    private void createUser(HttpRequest httpRequest) {
        User user = new User(
                extractBodyValue(httpRequest, "userId"),
                extractBodyValue(httpRequest, "password"),
                extractBodyValue(httpRequest, "name"),
                extractBodyValue(httpRequest, "email")
        );
        DataBase.addUser(user);
    }

    private String extractBodyValue(HttpRequest httpRequest, String key) {
        return httpRequest.getBody().get(key);
    }
}
