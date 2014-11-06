package com.example.note;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.note.domain.Topic;
import com.note.service.TopicService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class AddTopicActivity extends Activity{
    private static  String processURL="http://172.17.133.231:8080/ServerProject/addTopic.action?";
	private final String processURL_constant  = "http://172.17.133.231:8080/ServerProject/addTopic.action?";
	String result=null;
	Button button;
	EditText title;
	EditText note;
	EditText conclusion;
	EditText site;
    public void onCreate(Bundle savedInstanceState){
  	  super.onCreate(savedInstanceState);
		  setContentView(R.layout.addtopic);
		  findViews();  
    }
    private void findViews(){
   	   Intent intent = this.getIntent();
   	   Bundle bundle = intent.getExtras();
   	   final String idstr = bundle.getString("id");
       button = (Button)findViewById(R.id.publish);
  	   title  = (EditText)findViewById(R.id.title);
  	   note = (EditText)findViewById(R.id.note);
  	   conclusion= (EditText)findViewById(R.id.conclusion);
  	   site = (EditText)findViewById(R.id.site);
  	   button.setOnClickListener(new OnClickListener(){
  		   public void onClick(View v){
  			   String titlestr=title.getText().toString().trim();
  			   String notestr=note.getText().toString();
  			   String  conclusionstr=conclusion.getText().toString();
  			   Log.d("mylog",conclusionstr);
  			   String sitestr=site.getText().toString().trim();
  			 addTopicRemoteService(titlestr,notestr,conclusionstr,idstr,sitestr);
               /*TopicService topicservice = new TopicService(AddTopicActivity.this);
               Topic topic = new Topic(userid,titlestr,notestr,conclusionstr);
               boolean f = topicservice.insert(topic);
               if(f){
            	   Log.i("TAG","�����ɹ�!");  
            	   Toast.makeText(AddTopicActivity.this, "�����ɹ���", Toast.LENGTH_LONG).show();
               }*/
  		   }
  	   });
    }
	public void addTopicRemoteService(String title,String note,
			 String conclusion,String userid,String site){
    	try {
   		    String date=getDate();
   		    date=java.net.URLEncoder.encode(date,"utf-8");
   		    title=java.net.URLEncoder.encode(title,"utf-8");
   		    note=java.net.URLEncoder.encode(note,"utf-8");
   		    conclusion=java.net.URLEncoder.encode(conclusion,"utf-8");
   		    site=java.net.URLEncoder.encode(site,"utf-8");
   		    Log.d("mylog",date);
	    	//����һ��HttpClient����
	    	HttpClient httpclient = new DefaultHttpClient();
	    	//Զ�̵�¼URL
	    	//���������ԭ�е�
	    	//processURL=processURL+"userName="+userName+"&password="+password;
	    	Log.d("mylog",conclusion);
	    	processURL= processURL_constant+"title="+title+"&note="+note+
	    			"&conclusion="+conclusion+"&date="+date+"&userid="+userid
	    			+"&site="+site;
	        //����HttpGet����
	    	HttpPost request=new HttpPost(processURL);
	    	Log.d("mylog","request");
	    	if(request==null) Log.d("mylog","request==null");
	    	//������Ϣ����MIMEÿ����Ӧ���͵��������ͨ�ı���html �� XML��json�����������Ӧ����Ӧ��ƥ����Դ�������ɵ� MIME ����
	    	//��Դ�����ɵ� MIME ����Ӧ��ƥ��һ�ֿɽ��ܵ� MIME ���͡�������ɵ� MIME ���ͺͿɽ��ܵ� MIME ���Ͳ� ƥ�䣬��ô��
	    	//���� com.sun.jersey.api.client.UniformInterfaceException�����磬���ɽ��ܵ� MIME ��������Ϊ text/xml������
	    	//���ɵ� MIME ��������Ϊ application/xml�������� UniformInterfaceException��
	    	request.addHeader("Accept","text/json");
	        //��ȡ��Ӧ�Ľ��
	    	Log.d("mylog","addHeader");
			HttpResponse response =httpclient.execute(request);
			Log.d("mylog","response");
			if(response==null) Log.d("mylog","response==null");
			//��ȡHttpEntity
			HttpEntity entity=response.getEntity();
			//��ȡ��Ӧ�Ľ����Ϣ
			String json =EntityUtils.toString(entity,"UTF-8");
			//JSON�Ľ�������
			if(json!=null){
				Log.d("mylog",json);
				JSONObject jsonObject=new JSONObject(json);
				 Log.d("mylog","new json");
				result=jsonObject.get("message").toString().trim();
				 Log.d("mylog","result="+result);
			}
		   if(result==null){ 
			   Log.d("mylog","result=null");
			   json="ͨ�ų���";
		   }
			//������ʾ�������Ƿ��¼�ɹ�
		   Log.d("mylog","AlertDialog");
			 AlertDialog.Builder builder=new Builder(AddTopicActivity.this);
			 builder.setTitle("��ʾ")
			 .setMessage(result)
			 .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).create().show();
		 
    	 } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getDate(){
		java.util.Date date = new java.util.Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String res = df.format(date);
		return res;
	}
}
