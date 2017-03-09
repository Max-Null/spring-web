package com.qipachong.maxnull.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

/**
 * @Author ZZC
 * @Class CharsetUtil
 * @Date 2016/12/23 14:23
 * @Desc 重新编码对象中String字段
 */
public class CharsetUtil {
    public enum Charset {
        ISO_8859_1("iso-8859-1"),
        UTF_8("utf-8"),
        GBK("gbk");

        private final String name;

        Charset(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static final <T> T changeCharset(T t, Charset oldCharset, Charset newCharset) {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        int length = declaredFields.length;
        if (length == 0) {
            return t;
        } else {
            try {
                for (int i = 0; i < length; i++) {
                    Field f = declaredFields[i];
                    f.setAccessible(true);
                    if (f.getType().equals(String.class)) {
                        String str = (String) f.get(t);
                        if (str != null && str.length() != 0) {
                            str = changeCharset(oldCharset.getName(), newCharset.getName(), str);
                            f.set(t, str);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return t;
    }

    private static String changeCharset(String oldCharset, String newCharset, String str) {
        try {
            byte[] bytes = str.getBytes(oldCharset);
            str = new String(bytes, newCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        class Inner {
            String str1;
            String str2;
            String str3;

            public Inner(String str1, String str2, String str3) {
                this.str1 = str1;
                this.str2 = str2;
                this.str3 = str3;
            }

            @Override
            public String toString() {
                return "Inner{" +
                        "str1='" + str1 + '\'' +
                        ", str2='" + str2 + '\'' +
                        ", str3='" + str3 + '\'' +
                        '}';
            }
        }
        Inner obj = new Inner("hhhh", "哈哈哈", null);
        changeCharset(obj, Charset.UTF_8, Charset.ISO_8859_1);
        System.out.println(obj);
    }
}
