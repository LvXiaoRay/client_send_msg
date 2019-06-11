package com.client.client_send_msg.utils;

public class Hostlist {
    private String IP;
    private String age;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{remote_host：" + IP + "; date：" + age + "}";
    }
}
