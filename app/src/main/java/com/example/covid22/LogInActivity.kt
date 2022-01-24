package com.example.covid22

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase


class LogInActivity : AppCompatActivity() {

    /*  private lateinit var  auth: FirebaseAuth
      private val RC_SIGN_IN: Int = 123
      private lateinit var googleSignInClient: GoogleSignInClient
      lateinit var Button : Button


      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_log_in)
          Button = findViewById(R.id.googleSignIn)
          auth = FirebaseAuth.getInstance()

          val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
              .requestIdToken(getString(R.string.web_client_id))
              .requestEmail()
              .build()
          googleSignInClient = GoogleSignIn.getClient(this, gso)

          auth = FirebaseAuth.getInstance()

          Button.setOnClickListener{
              signIn()
          }

      }


      private fun signIn() {
          val signInIntent = googleSignInClient.signInIntent
          startActivityForResult(signInIntent, RC_SIGN_IN)
      }

      override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
          super.onActivityResult(requestCode, resultCode, data)

          // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
          if (requestCode == RC_SIGN_IN) {
              val task = GoogleSignIn.getSignedInAccountFromIntent(data)
              val exception = task.exception
              if (task.isSuccessful){
                  try {
                      // Google Sign In was successful, authenticate with Firebase
                      val account = task.getResult(ApiException::class.java)!!
                      firebaseAuthWithGoogle(account.idToken!!)
                  } catch (e: ApiException) {
                      // Google Sign In failed, update UI appropriately
                      Toast.makeText(this,"SignIn error",Toast.LENGTH_SHORT).show()
                  }

              }else {
                  Log.w("SignInActivity", exception.toString())
              }

          }

      }

      private fun firebaseAuthWithGoogle(idToken: String) {
          val credential = GoogleAuthProvider.getCredential(idToken, null)
          auth.signInWithCredential(credential)
              .addOnCompleteListener(this) { task ->
                  if (task.isSuccessful) {
                      // Sign in success, update UI with the signed-in user's information
                      //val user = auth.currentUser
                      val intent = Intent(this,MainActivity ::class.java)
                      startActivity(intent)

                  } else {
                      // If sign in fails, display a message to the user.
                      Toast.makeText(this,"sorry authentication Failed",Toast.LENGTH_SHORT).show()
                  }
              }
      }

     */

    /*private lateinit var  auth: FirebaseAuth
    private val RC_SIGN_IN: Int = 123
    private var googleSignInClient: GoogleSignInClient? = null
    lateinit var Button : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        Button =findViewById(R.id.googleSignIn)




        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        auth= Firebase.auth

        googleSignInClient = GoogleSignIn.getClient(this,gso)




        Button.setOnClickListener {
            signIn()
        }





    }


    override fun onStart() {
        super.onStart()
        var currentUser=auth.currentUser       //stores currently logged in user
        updateUI(currentUser)
        //update ui if user logged in or ask to login if null
    }

    private fun signIn() {                                //signed in user using google account


        //signInButton.visibility = View.GONE

        val signInIntent = googleSignInClient?.signInIntent              //shows list of users in device
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(ContentValues.TAG, "Google sign in failed", e)
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    //updateUI(null)
                    updateUI(null)
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
    }
     @SuppressLint("RestrictedApi")
     private fun updateUI(firebaseUser: FirebaseUser?) {

         if(firebaseUser != null) {
             val intent=Intent(this, MainActivity::class.java)
             startActivity(intent)
             finish()
         }
         else{
             signIn()
         }
     }

     */
    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN: Int = 986
    private var googleSignInClient: GoogleSignInClient? = null
    lateinit var Button: Button

    override fun onStart() {
        super.onStart()
        val currenUser = auth.currentUser
        if (currenUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        Button = findViewById(R.id.googleSignIn)
        auth = FirebaseAuth.getInstance()

        Button.setOnClickListener {
            signIn()
        }


        createRequest();
    }


    private fun createRequest() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(this, gso)


    }

    private fun signIn() {
        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, "SignIn error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "sorry authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }

    }


    /*lateinit var button: Button

    lateinit var signInButton: com.google.android.gms.common.SignInButton
    lateinit var  googleSignInClient: GoogleSignInClient
    val RC_SIGN_IN=123
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)



        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        auth= Firebase.auth

        googleSignInClient = GoogleSignIn.getClient(this,gso)




        button.setOnClickListener {
            signIn()
        }






    }


    override fun onStart() {
        super.onStart()
        var currentUser=auth.currentUser       //stores currently logged in user
                        //update ui if user logged in or ask to login if null
    }

    private fun signIn() {                                //signed in user using google account


        //signInButton.visibility = View.GONE

        val signInIntent = googleSignInClient.signInIntent              //shows list of users in device
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(ContentValues.TAG, "Google sign in failed", e)
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
    }

     */


}


