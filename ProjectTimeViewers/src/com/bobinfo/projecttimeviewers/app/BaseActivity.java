package com.bobinfo.projecttimeviewers.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.bobinfo.projecttimeviewers.async.AutoCancelController;
import com.bobinfo.projecttimeviewers.async.Cancelable;

/**
 * activity基础类
 * @author Change
 *
 */
public class BaseActivity extends Activity{
	private AutoCancelController mAutoCancelController = new AutoCancelController();
	private MySharePreference sharePreference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		((MyApplication) this.getApplication()).getActivityManager()
				.pushActivity(this); //将activity推入管理栈
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() { //销毁时activity出栈
		// TODO Auto-generated method stub
		((MyApplication) this.getApplication()).getActivityManager()
				.popActivity(this);
		super.onDestroy();
	}
	
	public void autoCancel(Cancelable task) {
		mAutoCancelController.add(task);
	}

	public void removeAutoCancel(Cancelable task) {
		mAutoCancelController.remove(task);
	}

	public AutoCancelController getAutoCancelController() {
		return mAutoCancelController;
	}

	public MySharePreference getSharePreference() {
		return ((MyApplication)getApplication()).getSharePreference();
	}

	/**
	 * activity跳转
	 * @param ctx
	 * @param clazz
	 * @param data
	 */
	protected void goTo(Context ctx,Class<? extends BaseActivity> clazz,Bundle data){
		Intent intent = new Intent();
		intent.setClass(ctx, clazz);
		intent.putExtras(data);
		ctx.startActivity(intent);
	}
}
