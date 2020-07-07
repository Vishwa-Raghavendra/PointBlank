package com.dscepointblank.pointblank.WebScrap

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dscepointblank.pointblank.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class codeforceActivity : AppCompatActivity() {

    object GlobalVariable {
        var editText:EditText? = null
        var textViewHandle : TextView? = null
        var textViewRank : TextView? = null
        var textViewContribution : TextView? =null
        var textViewRating : TextView? = null
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codeforce)

        GlobalVariable.editText = findViewById(R.id.editTextTextPersonName2)
        GlobalVariable.textViewContribution = findViewById(R.id.textViewContribution)
        GlobalVariable.textViewRating = findViewById(R.id.textViewRating)
        GlobalVariable.textViewRank = findViewById(R.id.textViewRank)
        GlobalVariable.textViewHandle = findViewById(R.id.textViewHandle)
    }

    class DownloadTask :
        AsyncTask<String?, Void?, String?>() {
        protected override fun doInBackground(vararg params: String?): String? {
            var result: String? = ""
            val url: URL
            var urlConnection: HttpURLConnection? = null
            try {
                url = URL(params[0])
                urlConnection = url.openConnection() as HttpURLConnection
                val inputStream = urlConnection.inputStream
                val reader = InputStreamReader(inputStream)
                var data = reader.read()
                while (data != -1) {
                    val current = data.toChar()
                    result += current
                    data = reader.read()
                }
                //                Log.i("Result",result);
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(s: String?) {
            super.onPostExecute(s)
            try {
                val jsonObject = JSONObject(s)
                val result = jsonObject.getString("result")
                val jsonArray = JSONArray(result)
                for (i in 0 until jsonArray.length()) {
                    val part = jsonArray.getJSONObject(i)
                    //                    Log.i("Title",String.valueOf(part.getString("title")));


                        /*Log.i("Handle", part.getString("handle").toString());
                        Log.i("Rating", part.getString("rating").toString());
                        Log.i("Contribution", part.getString("contribution").toString());
                        Log.i("Rank", part.getString("rank").toString());*/

                        if (part.has("contribution")) {
                            GlobalVariable.textViewContribution?.setText(
                                "Contribution: " + part.getString(
                                    "contribution"
                                ).toString()
                            )
                        } else {
                            GlobalVariable.textViewContribution?.setText("Contribution: Data Not Found")
                        }

                        if (part.has("handle")) {
                            GlobalVariable.textViewHandle?.setText(
                                "Handle: " + part.getString("handle").toString()
                            )
                        } else {
                            GlobalVariable.textViewHandle?.setText("Handle: Data Not Found")
                        }

                        if (part.has("rank")) {
                            GlobalVariable.textViewRank?.setText(
                                "Rank: " + part.getString("rank").toString()
                            )
                        } else {
                            GlobalVariable.textViewRank?.setText("Rank: Data Not Found")
                        }

                        if (part.has("rating")) {
                            GlobalVariable.textViewRating?.setText(
                                "Rating: " + part.getString("rating").toString()
                            )
                        } else {
                            GlobalVariable.textViewRating?.setText("Rating: Data Not Found")
                        }


                }

                //                Log.i("Articles ",articles);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun submitClicked(view: View) {

        var handleName:String = GlobalVariable.editText?.text.toString()
        val downloadTask = DownloadTask()
        downloadTask.execute("https://codeforces.com/api/user.info?handles=$handleName") //Put your api key here

    }
}