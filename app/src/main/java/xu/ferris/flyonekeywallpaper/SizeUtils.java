package xu.ferris.flyonekeywallpaper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewConfiguration;

/**
 * 尺寸工具类
 * 
 * @author liwenxue
 * 
 */
public class SizeUtils {
	
	public static float sDensity = 1.0f;
	public static float sScaleDensity = 1.0f;
	public static int sWidthPixels;
	public static int sHeightPixels;
	
	/**
	 * 使用前重置
	 * @param context
	 */
	public static void reset(Context context) {
		if (context != null && null != context.getResources()) {
			DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			sDensity = metrics.density;
			sScaleDensity = metrics.scaledDensity;
			sWidthPixels = metrics.widthPixels;
			sHeightPixels = metrics.heightPixels;
		}
	}
	
	/**
	 * dip to dp转像素
	 * @param dipValue dip或 dp大小
	 * @return 像素值
	 */
	public static int dip2px(float dipVlue) {
		return (int) (dipVlue * sDensity + 0.5f);
	}

	/**
	 * 像素转dip to dp
	 * @param pxValue 像素大小
	 * @return dip值
	 */
	public static int px2dip(float pxValue) {
		final float scale = sDensity;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * sp to px
	 * @param spValue sp大小
	 * @return 像素值
	 */
	public static int sp2px(float spValue) {
		final float scale = sScaleDensity;
		return (int) (scale * spValue);
	}

	/**
	 * px to sp
	 * @param pxValue 像素大小
	 * @return sp值
	 */
	public static int px2sp(float pxValue) {
		final float scale = sScaleDensity;
		return (int) (pxValue / scale);
	}
	
	/**
	 * 灞忓箷楂樺害(px)
	 * 
	 * @return
	 */
	public static int getScreenHeight() {
		
		return sHeightPixels;
	}

	/**
	 * 灞忓箷瀹藉害(px)
	 * 
	 * @return
	 */
	public static int getScreenWidth() {
		
		return sWidthPixels;
	}
	
	/**
	 * 获取状态栏高度
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context){
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight){
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        return statusHeight;
    }
	
	/**
	 * 获取底部栏的高度
	 * @param context
	 * @return
	 */
	public static int getNavigationBarHeight(Context context) {  
        Resources resources = context.getResources(); 
        int resourceId = resources.getIdentifier("navigation_bar_height",  
                "dimen", "android");  
        // 获取NavigationBar的高度 
        int height = resources.getDimensionPixelSize(resourceId);
        
        return height;  
    }  
	
	/**
	 * 判断设备是否有导航栏
	 * @param context
	 * @return
	 */
    public static boolean checkDeviceHasNavigationBar(Context context) {
		// 特殊机型适配
		if ("Halo".equals(Build.MODEL)) {
			return false;
		}

        // 通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(context)
                .hasPermanentMenuKey();
//        Log.i("liwx", "hasMenuKey:" + hasMenuKey);
//        boolean hasBackKey = KeyCharacterMap
//                .deviceHasKey(KeyEvent.KEYCODE_BACK);
//        Log.i("liwx", "hasBackKey:" + hasBackKey);
//        if (!hasMenuKey && !hasBackKey) {  
//            // 做任何你需要做的,这个设备有一个导航栏  
//            return true;  
//        }
        
        return !hasMenuKey;
    }
    
    /**
     * 函数打印堆栈
     * @param TAG
     */
    public static void printStack(String TAG) {
    	Log.i(TAG, "function call stack:" + Log.getStackTraceString(new Throwable()));
    }
}
