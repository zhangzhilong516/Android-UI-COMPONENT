package com.hengchang.ui_component.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author zhangzhilong
 * @date 2019/4/4.
 * description：
 */
public class UIIOHelper {
    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
