package com.touchwise.androidtool.security;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * 权限检查Android 6.0: 动态权限管理的解决方案
 * 权限检查Android 6.0推送的新特性让用户更加容易的控制自己的隐私
 * Created by esirong on 2016/2/20.
 */
public class PermissionsChecker {

    private final Context mContext;

    public PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 判断权限
     * @param permission 权限
     * @return true 未获得授权
     */
    public boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
    }

    /**
     * 判断权限集合
     * @param permissions 权限集合
     * @return true 未获得授权（含部分未获授权）
     */
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }
}
