package com.example.lenovo_g50_70.materialdesign;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/**
 * Created by lenovo-G50-70 on 2017/4/25.
 */

public class MyActionProvider extends android.support.v4.view.ActionProvider {

    public MyActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        super.onPrepareSubMenu(subMenu);
        //每次点击都会新添加子菜单，所以要清除上一次的
        subMenu.clear();
        subMenu.add("sub item 1")
                .setIcon(R.mipmap.ic_launcher)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
        subMenu.add("sub item 2")
                .setIcon(R.mipmap.ic_launcher)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }
}
