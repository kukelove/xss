package com.xp.brushms.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzm on 2015/7/29.
 */
public class Constants {
    public static final String RoleAdmin = "Admin";

    public static final  Map<Integer, String> typeMap = new HashMap<Integer, String>() {{
        put( 1 , "PC" );
        put( 2 , "Phone" );
    }};
}
