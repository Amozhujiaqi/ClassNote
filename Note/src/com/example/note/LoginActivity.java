package com.example.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.note.R;
import com.note.service.UserService;


public class LoginActivity extends Activity {
	EditText username;
	EditText password;
	Button login,register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	//savedInstanceState用于在pause时保存当前activity的状态
		setContentView(R.layout.main);  //这句话的意思是这个activity对应的布局是res/layout目录下的main.xml文件
		findViews();
	}
	private void findViews() {
		username=(EditText) findViewById(R.id.username);  //生成输入文本框
		password=(EditText) findViewById(R.id.password);
		login=(Button) findViewById(R.id.login);           //生成一个按钮，与布局里的login对应
		register=(Button) findViewById(R.id.register);
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {         //通过view可以获取点击的id，可以知道点击了哪个按钮
				String name=username.getText().toString();
				String pass=password.getText().toString();
				Log.i("TAG",name+"_"+pass);               //调试的时候输出name 和password
				UserService uService=new UserService(LoginActivity.this);  //注意这里传入LoginActivity.this，获得当前activity的上下文
				boolean flag=uService.login(name, pass);
				if(flag){
					Log.i("TAG","登录成功");               //提示登录成功
					Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show(); //以默认效果在屏幕显示登陆成功
				}else{
					Log.i("TAG","登录失败");
					Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
				}
			}
		});
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);//启动register活动
				startActivity(intent);
			}
		});
	}

}