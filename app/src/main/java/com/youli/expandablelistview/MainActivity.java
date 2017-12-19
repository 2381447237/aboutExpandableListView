package com.youli.expandablelistview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private List<PolicyQueryInfo> data=new ArrayList<>();
    private ExpandableListView elv;
    private ElvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elv= (ExpandableListView) findViewById(R.id.elv);

        getLocalData();//读取本地的json数据

    }

    private void getLocalData(){

        try {
            InputStream is=getAssets().open("myjson.txt");

            final String text=readTextFromSDcard(is);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Gson gson=new Gson();
                    data=gson.fromJson(text,new TypeToken<LinkedList<PolicyQueryInfo>>(){}.getType());
                    fretchElv(data);

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readTextFromSDcard(InputStream is) throws IOException {

        InputStreamReader reader=new InputStreamReader(is,"GBK");
        BufferedReader bufferReader=new BufferedReader(reader);
        StringBuffer buffer=new StringBuffer("");
        String str;
        while ((str=bufferReader.readLine())!=null){
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    private void fretchElv(List<PolicyQueryInfo> list){

       List<Map<String,String>> questions=new ArrayList<>();
        List<List<Map<String,String>>> answers=new ArrayList<>();

        for(int i=0;i<list.size();i++){

            String question=list.get(i).getQUESTIONS();
            String answer=list.get(i).getANSWERS();

            Map<String,String> questionMap=new HashMap<>();
            questionMap.put("question", question);
            questions.add(questionMap);

            List<Map<String,String>> answerList=new ArrayList<>();
            Map<String,String> answerMap=new HashMap<>();
            answerMap.put("answer", answer);
            answerList.add(answerMap);
            answers.add(answerList);

        }

        adapter=new ElvAdapter(questions,answers,this);
        elv.setAdapter(adapter);
        //设置全部展开
        int groupCount = elv.getCount();
        for (int i=0; i<groupCount; i++) {
            elv.expandGroup(i);
        }
    }

}
