package me.wuxm.lightingmvp.utils;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import java.io.File;
import java.util.List;

/**
 * Created by gekson on 2015/8/28.
 */
public class ManagerUtil {
    /**
     * 从返回的Body中获取字段
     *
     * @param
     * @return
     */
//    public static String fromBody(retrofit.client.Response response) {
//        TypedInput body = response.getBody();
//        try {
//            if (body != null) {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));
//                StringBuilder out = new StringBuilder();
//                String newLine = System.getProperty("line.separator");
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    out.append(line);
//                    out.append(newLine);
//                }
//                // Prints the correct String representation of body.
//                return out.toString();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /*
     * 检查是否存在sd卡
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    private static boolean deleteDir(File dir) {
        try {
            if (dir != null && dir.isDirectory()) {
                String[] children = dir.list();
                for (String aChildren : children) {
                    boolean success = deleteDir(new File(dir, aChildren));
                    if (!success) {
                        return false;
                    }
                }
            }
            return dir != null && dir.delete();
        } catch (Exception ignored) {
            return true;
        }
    }

    public static void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    public static void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean isEmpty(List list){
        if (list != null && list.size() > 0){
            return false;
        }
        return true;
    }
}
