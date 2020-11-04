package org.example.multimodule.utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean isEmpty(String value) {
        return Objects.isNull(value) || value.isEmpty();
    }

    public static boolean isIp(String host) {

        Pattern pattern =
                Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$");
        Matcher matcher = pattern.matcher(host);
        return matcher.matches();

    }
}
