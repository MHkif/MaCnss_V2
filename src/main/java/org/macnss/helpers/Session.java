package org.macnss.helpers;

import java.time.LocalTime;
import java.util.HashMap;

public class Session {
    private static Session instance;
    private HashMap<String, Object> data;

    private Session() {
        data = new HashMap<>();
    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public boolean has(String key){
        return data.containsKey(key);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public void remove(String key) {
        data.remove(key);
    }

    public void unset() {
        data.clear();
    }


}

