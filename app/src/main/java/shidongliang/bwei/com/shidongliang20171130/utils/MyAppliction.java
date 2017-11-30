package shidongliang.bwei.com.shidongliang20171130.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/11/30 8:37
 */
public class MyAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
