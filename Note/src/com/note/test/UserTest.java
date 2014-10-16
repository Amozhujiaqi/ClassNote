package com.note.test;

import android.test.AndroidTestCase;
import android.util.Log;
import com.note.domain.User;
import com.note.service.DatabaseHelper;
import com.note.service.UserService;

public class UserTest extends AndroidTestCase {
	public void datatest() throws Throwable{
		DatabaseHelper dbhepler=new DatabaseHelper(this.getContext());
		dbhepler.getReadableDatabase();
	}
	//ע��
	public void registerTest() throws Throwable{	
		UserService uService=new UserService(this.getContext());
		User user=new User();
		user.setName("renhaili");
		user.setPassword("123");
		user.setSex("Ů");
		uService.register(user);
	}
	public void loginTest() throws Throwable{
		UserService uService=new UserService(this.getContext());
		String username="renhaili";
		String password="123";
		boolean flag=uService.login(username, password);
		if(flag){
			Log.i("TAG","��¼�ɹ�");
		}else{
			Log.i("TAG","��¼ʧ��");
		}
	}
	
}
