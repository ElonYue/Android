package com.yue.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.yue.demo.R;
import com.yue.demo.DemoInfo;
import com.yue.demo.DemoListAdapter;
import com.yue.demo.service.AllService;
import com.yue.demo.ui.actionbar.MainActivity_ActionBar;
import com.yue.demo.ui.dialog.MainActivity_Dialog;
import com.yue.demo.ui.layout.MainActivity_Layout;
import com.yue.demo.ui.menu.MenuTest;
import com.yue.demo.ui.view.MainActivity_View;

/**
 * UI界面编程 ，导航到各种三级具体UI的MainActivity
 * 
 * @author chengyue
 */
public class MainActivity_UI extends Activity {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        ListView mListView = (ListView) findViewById(R.id.listView);
        // 添加ListItem，设置事件响应
        mListView.setAdapter(new DemoListAdapter(this, demos));
        mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int index,
                    long arg3) {
                onListItemClick(index);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 10);
            }
        }
        
        startService(new Intent(this, AllService.class));

    }

    @Override
    protected  void  onActivityResult( int  requestCode,  int  resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(this, "not granted", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    protected void onListItemClick(int index) {
        Intent intent = new Intent(this, demos[index].demoClass);
        startActivity(intent);
    }

    private static final DemoInfo[] demos = {
        new DemoInfo(R.string.demo_ui_name_view, R.string.demo_ui_title_view, MainActivity_View.class),
        
        new DemoInfo(R.string.demo_ui_name_layout, R.string.demo_ui_title_layout, MainActivity_Layout.class),
        
        new DemoInfo(R.string.demo_ui_name_dialog, R.string.demo_ui_title_dialog, MainActivity_Dialog.class),
        
        new DemoInfo(R.string.demo_ui_name_menu, R.string.demo_ui_title_menu, MenuTest.class),
        
        new DemoInfo(R.string.demo_ui_name_actionbar, R.string.demo_ui_title_actionbar, MainActivity_ActionBar.class),

    };

}
