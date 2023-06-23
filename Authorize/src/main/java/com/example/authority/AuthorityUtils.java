package com.example.authority;

import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AuthorityUtils {

    //key:用户id value:用户拥有的权限
    private static Map<String, Collection<String>> map = new HashMap<>();

    //设置权限
    public static void setAuthority(String uid, Collection<String> authority) {
        map.put(uid, authority);
    }

    //校验权限
    public static boolean verify(String uid, String authority) {
        return map.get(uid).contains(authority);
    }

    //判断是否有权限
    public static boolean isEmpty(String uid) {
        return ObjectUtils.isEmpty(map.get(uid));
    }

}