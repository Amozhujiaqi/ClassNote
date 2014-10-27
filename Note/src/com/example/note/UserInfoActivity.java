package com.example.note;

import com.note.domain.User;
import com.note.service.TopicDatabaseHelper;
import com.note.service.UserService;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ListView;

public class UserInfoActivity extends ListActivity{
	 User user;
     public void onCreate(Bundle savedInstanceState){
    	 super.onCreate(savedInstanceState);
    	 Intent intent = this.getIntent();
    	 Bundle bundle = intent.getExtras();
    	 String id = bundle.getString("id");
    	 if (id==null){
    		 Log.i("id is null!","_");  
    	 }
    	 else Log.i("TAG",id);  
    	 showlist(id);
    	 //UserService userservice=new UserService(UserInfoActivity.this);
    	// setContentView(R.layout.userinfo);
    	 //findviews();
     }
     private void findviews(){
    	 Intent intent = this.getIntent();
    	 Bundle bundle = intent.getExtras();
    	 String id = bundle.getString("id");
    	 UserService userservice=new UserService(UserInfoActivity.this);
    	 
     }
     private void showlist(String id){//显示笔记列表
   	  final UserService userservice=new UserService(UserInfoActivity.this);
   	  Cursor c = userservice.query1(id);
   	  String[] from = {"id","name",};
   	  int[] to = {R.id.id,R.id.name};
   	  SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
   			  R.layout.note,c,from,to);
   	  ListView listview = getListView();//列表视图
   	  listview.setAdapter(adapter);//添加适配器
     }
}
