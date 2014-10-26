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
	EditText id;
	EditText password;
	Button login,register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	//savedInstanceState������pauseʱ���浱ǰactivity��״̬
		setContentView(R.layout.main);  //��仰����˼�����activity��Ӧ�Ĳ�����res/layoutĿ¼�µ�main.xml�ļ�
		findViews();
	}
	private void findViews() {
		id=(EditText) findViewById(R.id.id);  //���������ı���
		password=(EditText) findViewById(R.id.password);
		login=(Button) findViewById(R.id.login);           //����һ����ť���벼�����login��Ӧ
		register=(Button) findViewById(R.id.register);
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {         //ͨ��view���Ի�ȡ�����id������֪��������ĸ���ť
				String idstr=id.getText().toString();
				String passstr=password.getText().toString();
				Log.i("TAG",idstr+"_"+passstr);               //���Ե�ʱ�����name ��password
				UserService uService=new UserService(LoginActivity.this);  //ע�����ﴫ��LoginActivity.this����õ�ǰactivity��������
				int flag=uService.login(idstr, passstr);
				if(flag==1){
					Log.i("TAG","��¼�ɹ�!");               //��ʾ��¼�ɹ�
					Toast.makeText(LoginActivity.this, "��¼�ɹ���", Toast.LENGTH_LONG).show(); //��Ĭ��Ч������Ļ��ʾ��½�ɹ�
					Intent classnote=new Intent(LoginActivity.this,ClassNoteActivity.class);//����register�
					Bundle bundle= new Bundle();
					bundle.putString("id", idstr);
					classnote.putExtra("bundle", bundle);
					startActivity(classnote);
				}else{
					if(flag==0){
						Log.i("TAG","ID�����ڣ�");
						Toast.makeText(LoginActivity.this, "ID�����ڣ�", Toast.LENGTH_LONG).show();
					}else{
					Log.i("TAG","�û������������");
					Toast.makeText(LoginActivity.this, "�û������������", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);//����register�
				startActivity(intent);
			}
		});
	}

}