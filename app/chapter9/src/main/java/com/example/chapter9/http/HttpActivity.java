package com.example.chapter9.http;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter9.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HttpActivity";
    private Button send;
    private TextView textView;
    private Button send1;
    private Button send2;
    private Button send3;
    private Button send4;
    private TextView response;
    private Button gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initView();
    }

    private void initView() {
        send = (Button) findViewById(R.id.send);
        textView = (TextView) findViewById(R.id.response);

        send.setOnClickListener(this);
        send1 = (Button) findViewById(R.id.send1);
        send1.setOnClickListener(this);
        send2 = (Button) findViewById(R.id.send2);
        send2.setOnClickListener(this);
        send3 = (Button) findViewById(R.id.send3);
        send3.setOnClickListener(this);
        send4 = (Button) findViewById(R.id.send4);
        send4.setOnClickListener(this);
        response = (TextView) findViewById(R.id.response);
        response.setOnClickListener(this);
        gson = (Button) findViewById(R.id.gson);
        gson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send) {
            HttpUtil.sendHttpRequest("https://www.baidu.com", new HttpUtil.HttpCallbackListener() {
                @Override
                public void onFinish(final String response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(response);
                        }
                    });
                }

                @Override
                public void onError(Exception e) {

                }
            });
        } else if (v.getId() == R.id.send1) {
            HttpUtil.sendOkHttpRequest("https://www.baidu.com", new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(responseData);
                        }
                    });
                }
            });
        } else if (v.getId() == R.id.send2) {
            methodChoose("http://192.168.137.1/get_data.xml", "SAX");
        } else if (v.getId() == R.id.send3) {
            methodChoose("http://192.168.137.1/get_data.xml", "pull");
        } else if (v.getId() == R.id.send4) {
            methodChoose("http://192.168.137.1/get_data.json", "json");
        }else if (v.getId()==R.id.gson){
            methodChoose("http://192.168.137.1/get_data.json", "gson");
        }
    }

    private void methodChoose(String address, final String name) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                if (name == "pull") {
                    parseXMLWithPull(responseData);
                } else if (name == "SAX") {
                    parseXMLWithSAX(responseData);
                } else if (name == "json") {
                    parseJSONWithJSONObject(responseData);
                }else  if (name=="gson"){
                    parseJSONWithGson(responseData);
                }

            }
        });

    }

    private void parseJSONWithGson(String responseData) {
        Gson gson=new Gson();
        List<App> appList=gson.fromJson(responseData,new TypeToken<List<App>>(){}.getType());
        for (App app:appList){
             Log.d(TAG, "id:" + app.getId());
            Log.d(TAG, "version:" + app.getVersion());
            Log.d(TAG, "name:" + app.getName());
        }
    }

    private void parseJSONWithJSONObject(String responseData) {
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                int version = jsonObject.getInt("version");
                String name = jsonObject.getString("name");
                Log.d(TAG, "id:" + id);
                Log.d(TAG, "version:" + version);
                Log.d(TAG, "name:" + name);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithSAX(String responseData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(responseData)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void parseXMLWithPull(String responseData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(responseData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            Log.d(TAG, "id is" + id);
                            Log.d(TAG, "name is" + name);
                            Log.d(TAG, "version is" + version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


