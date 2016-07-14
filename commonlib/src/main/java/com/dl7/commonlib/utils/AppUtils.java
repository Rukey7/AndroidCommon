package com.dl7.commonlib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * AppUtils
 * <ul>
 * <li>{@link AppUtils#isNamedProcess(Context, String)}</li>
 * </ul>
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-5-07
 */
public class AppUtils {

    private AppUtils() {
        throw new AssertionError();
    }

    /**
     * whether this process is named with processName
     * 
     * @param context
     * @param processName
     * @return <ul>
     *         return whether this process is named with processName
     *         <li>if context is null, return false</li>
     *         <li>if {@link ActivityManager#getRunningAppProcesses()} is null, return false</li>
     *         <li>if one process of {@link ActivityManager#getRunningAppProcesses()} is equal to processName, return
     *         true, otherwise return false</li>
     *         </ul>
     */
    public static boolean isNamedProcess(Context context, String processName) {
        if (context == null) {
            return false;
        }

        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();
        if (ListUtils.isEmpty(processInfoList)) {
            return false;
        }

        for (RunningAppProcessInfo processInfo : processInfoList) {
            if (processInfo != null && processInfo.pid == pid
                    && ObjectUtils.isEquals(processName, processInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * whether application is in background
     * <ul>
     * <li>need use permission android.permission.GET_TASKS in Manifest.xml</li>
     * </ul>
     * 
     * @param context
     * @return if application is in background return true, otherwise return false
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断应用是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        boolean hasInstalled = false;
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> list = pm
                .getInstalledPackages(PackageManager.PERMISSION_GRANTED);
        for (PackageInfo p : list) {
            if (packageName != null && packageName.equals(p.packageName)) {
                hasInstalled = true;
                break;
            }
        }
        return hasInstalled;
    }

    /**
     * 获取APP版本
     * @param context
     * @param packageName
     * @return
     */
    public static String getAppVersion(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(packageName,0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0";
    }

    /**
     * 根据包名启动另外一个app
     * @param context
     * @param packageString
     */
    public static void openApp(Context context, String packageString) {
        if (!TextUtils.isEmpty(packageString)) {
            Intent intent = context.getPackageManager()
                    .getLaunchIntentForPackage(packageString);
            if (intent != null) {
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "无法直接打开该应用,可能原因:该应用为桌面插件!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "无法直接打开该应用,可能原因:该应用为桌面插件!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭键盘事件.
     *
     * @param context the context
     */
    public static void closeSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null
                && ((Activity) context).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context)
                            .getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 弹出输入法
     * @param context context
     * @param view  编辑控件
     */
    public static void setEditFocusable(final Context context, final View view) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) context
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(view, 0);
            }
        }, 300);
    }
}
