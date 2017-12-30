package projects.ritu.financeadviser;
//
//import android.app.ActionBar;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//public class Article extends AppCompatActivity {
//
//    Toolbar mToolbar;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_article);
//        Toast.makeText(this.getApplicationContext(),"reached in article",Toast.LENGTH_LONG).show();
//
////        mToolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(mToolbar);
////        getSupportActionBar().setDisplayShowHomeEnabled(true);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Take appropriate action for each action item click
//        switch (item.getItemId()) {
//            case R.id.action_logout:
//                SharedPreferences myPrefs = getSharedPreferences("mySharedPref",
//                        0);
//                SharedPreferences.Editor editor = myPrefs.edit();
//                editor.clear();
//                editor.commit();
//                Intent loginIntent=new Intent(this,LoginActivity.class);
//                startActivity(loginIntent);
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
//}
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import projects.ritu.financeadviser.R;

import static android.widget.Toast.makeText;

public class Article extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView mainArticle;
    static String article;
    JSONParser jsonParser=new JSONParser();
    static int CommentId=1;
    static int ArticleId=1;
    private static final String TAG_SUCCESS = "success";
    JSONObject commentsJSON=null;
    JSONObject repliesJSON=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        mainArticle=(TextView) findViewById(R.id.main_article);
        // preparing list data
        prepareListData();

        expListView.setOnTouchListener(new ListView.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
//                 Toast.makeText(getApplicationContext(),
//                 "Group Clicked " + listDataHeader.get(groupPosition),
//                 Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
        article= getIntent().getStringExtra("Article Id");
        mainArticle.setText(article);

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        String article = "this is not null";
        (new CreateNewProduct()).execute(article);

    }
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            String article = args[0];
            // Building Parameters
            HashMap<String, String> params =new  HashMap<String, String>();
            params.put("article",article);

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest("http://139.59.4.190/MainPhpDir/get_comments.php?articleId="+ArticleId,
                    "GET", params);
            JSONObject repliesjson = jsonParser.makeHttpRequest("http://139.59.4.190/MainPhpDir/get_replies.php?commentId="+CommentId,
                    "GET", params);
            // check log cat fro response
            Log.d("Create Response", json.toString());
            Log.d("Create Response", repliesjson.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully fetched categories
                    commentsJSON=json;
                    repliesJSON=repliesjson;
                } else {
                    // failed to create product
                    Toast toast = makeText(getApplicationContext(), "Failed to fetch articles!", Toast.LENGTH_LONG);
                    toast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            try {
                if(commentsJSON!=null) {
                    JSONArray comntJsonArr = commentsJSON.getJSONArray("comments");
                    int numOfComments = comntJsonArr.length();
                    listDataHeader=new ArrayList<String>();
                    for (int i = 0; i < numOfComments; i++) {
                        JSONObject catJsonObj = comntJsonArr.getJSONObject(i);
                        listDataHeader.add(catJsonObj.getString("comment"));

                        List<String> tempArrList = new ArrayList<String>();
                        int tempNumofReplies=repliesJSON.length();
                        JSONArray replyJsonArr = repliesJSON.getJSONArray("replies");
                        listDataChild = new HashMap<String, List<String>>();
                        for(int j=0;j<tempNumofReplies;j++)
                        {
                            JSONObject replyJsonObj = replyJsonArr.getJSONObject(j);
                            tempArrList.add(replyJsonObj.getString("reply"));
                        }
                        listDataChild.put(listDataHeader.get(i), tempArrList); // Header, Child data
                    }
                }
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(listAdapter);
            }
            catch (JSONException e){}
        }

    }
}