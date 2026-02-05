package org.example.backend.context;

import javax.naming.Context;

public class UserContext{
    private static final ThreadLocal<Long> USER_HOLDER = new ThreadLocal<>();

    public static void set(Long userID) {
        USER_HOLDER.set(userID);
    }

    public static Long get() {
        return USER_HOLDER.get();
    }

    public static void remove() {
        USER_HOLDER.remove();
    }
}
