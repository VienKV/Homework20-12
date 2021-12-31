package vn.hust.edu.internetexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    // List<ItemModel> items;
    // ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

//        String jsonString = "[{\"name\":\"John\", \"age\":20, \"gender\":\"male\"}, {\"name\":\"Peter\", \"age\":21, \"gender\":\"male\"}, {\"name\":\"July\", \"age\":19, \"gender\":\"female\"}]";
//        try {
//            JSONArray jArr = new JSONArray(jsonString);
//            for (int i = 0; i < jArr.length(); i++) {
//                JSONObject jObj = jArr.getJSONObject(i);
//                String name = jObj.getString("name");
//                int age = jObj.getInt("age");
//                String gender = jObj.getString("gender");
//                Log.v("TAG", name + " - " + age + " - " + gender);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

//        items = new ArrayList<>();
//        adapter = new ItemAdapter(items);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        new DownloadTask(this).execute("https://jsonplaceholder.typicode.com/users");

        try {
            List<ItemModel> items = new ArrayList<>();
            items.add(new ItemModel(12, "peter", "Peter", "peter@gmail.com"));
            items.add(new ItemModel(13, "john", "John", "john@gmail.com"));

            JSONArray jArr = new JSONArray();
            for (int i = 0; i < items.size(); i++) {
                ItemModel item = items.get(i);
                JSONObject jObj = new JSONObject();
                jObj.put("id", item.getId());
                jObj.put("username", item.getUsername());
                jObj.put("name", item.getName());
                jObj.put("email", item.getEmail());
                jArr.put(jObj);
            }

            Log.v("TAG", jArr.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class DownloadTask extends AsyncTask<String, Void, JSONArray> {
        ProgressDialog dialog;
        Context context;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Loading data...");
            dialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null)
                    builder.append(line);
                reader.close();

                String jsonString = builder.toString();
                //Log.v("TAG", jsonString);

                // List<ItemModel> items = new ArrayList<>();
//                JSONArray jArr = new JSONArray(jsonString);
//                for (int i = 0; i < jArr.length(); i++) {
//                    JSONObject jObj = jArr.getJSONObject(i);
//                    int id = jObj.getInt("id");
//                    String username = jObj.getString("username");
//                    String name = jObj.getString("name");
//                    String email = jObj.getString("email");
//
//                    ItemModel item = new ItemModel(id, username, name, email);
//                    items.add(item);
//                }
//                return items;

                return new JSONArray(jsonString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            dialog.dismiss();

//            if (items != null) {
//                Log.v("TAG", "Size:" + items.size());
//                adapter.notifyDataSetChanged();
//            }

            if (jsonArray != null) {
                ItemJSONAdapter adapter = new ItemJSONAdapter(jsonArray);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}