package com.example.covid22

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.covid22.model.State
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    lateinit var Name: TextView
    lateinit var Email: TextView
    lateinit var LogOut: Button

    lateinit var Recyclerdash: RecyclerView
    lateinit var layoutmanager: RecyclerView.LayoutManager
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    lateinit var googleSignInAccount: GoogleSignInAccount

    val stateList = arrayListOf<State>()

    lateinit var recyclerAdapter: RecyclerAdapter


    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Recyclerdash = findViewById(R.id.RecyclerDash)
        layoutmanager = LinearLayoutManager(this)

        progressBar = findViewById(R.id.progressBar)
        progressLayout = findViewById(R.id.rlProgress)

        progressLayout.visibility = View.VISIBLE

        LogOut = findViewById(R.id.LogOutButton)
        Name = findViewById(R.id.name)
        Email = findViewById(R.id.email)


        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this)!!

        if (googleSignInAccount != null) {
            Name.setText(googleSignInAccount.displayName)
            Email.setText(googleSignInAccount.email)

        }

        LogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }


        val queue = Volley.newRequestQueue(this)
        val url = "https://api.rootnet.in/covid19-in/stats/latest"

        val jsonObjectRequest =
            object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                try {
                    progressLayout.visibility = View.GONE
                    val success = it.getBoolean("success")
                    if (success) {
                        //extract data
                        val json = it.getJSONObject("data")
                        val data = json.getJSONArray("regional")
                        for (i in 0 until data.length()) {
                            val stateJASON = data.getJSONObject(i)
                            val state = State(
                                stateJASON.getString("loc"),
                                ("confirmed Cases Indian: " + stateJASON.getString("confirmedCasesIndian")),
                                ("confirmed Cases Foreign: " + stateJASON.getString("confirmedCasesForeign")),
                                ("discharged: " + stateJASON.getString("discharged")),
                                ("deaths: " + stateJASON.getString("deaths")),
                                ("total Confirmed: " + stateJASON.getString("totalConfirmed"))
                            )
                            stateList.add(state)
                            recyclerAdapter = RecyclerAdapter(this, stateList)
                            Recyclerdash.adapter = recyclerAdapter
                            Recyclerdash.layoutManager = layoutmanager

                            Recyclerdash.addItemDecoration(
                                DividerItemDecoration(
                                    Recyclerdash.context,
                                    (layoutmanager as LinearLayoutManager).orientation
                                )
                            )
                        }
                    } else {
                        //failure in getting data
                        Toast.makeText(
                            this,
                            "Error Occurred!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: JSONException) {
                    Toast.makeText(this, "Some unexpected error occur", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener {
                //handle the error
                Toast.makeText(
                    this,
                    "Volley Error Occurred!",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-type"] = "application/json"
                    return headers
                }

            }
        //add the request to the queue
        queue.add(jsonObjectRequest)

    }
}