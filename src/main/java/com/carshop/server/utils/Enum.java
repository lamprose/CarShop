package com.carshop.server.utils;


public class Enum {

    public enum Code{
        COMMON(400),
        ERR_TOKEN(404),
        ERR_ONLINE(403),
        ERR_EMPTY(402),
        ERR_PASSWORD(401);

        private final int value;
        Code(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }
    }
}
