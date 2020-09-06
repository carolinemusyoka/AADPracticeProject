package com.carolmusyoka.aadpracticeproject


import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.carolmusyoka.aadpracticeproject.data.api.form.FormApiInterface
import kotlinx.android.synthetic.main.activity_submit.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class SubmitActivity : AppCompatActivity() {
    private var context: Context? = null
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        submitButton = findViewById(R.id.submitButton)

        val successDialog = Dialog(this)

        successDialog.setContentView(R.layout.submit_successful_dialog)
        successDialog.window?.setLayout(1000,1000)
        successDialog.setTitle("Successful")

        val errorDialog = Dialog(this)
        errorDialog.setContentView(R.layout.submit_error_dialog)
        errorDialog.window?.setLayout(1000,1000)
        errorDialog.setTitle("Error")


        submitButton.setOnClickListener {
            if (!TextUtils.isEmpty(firstName.text?.trim().toString()) &&
                !TextUtils.isEmpty(lastName.text?.trim().toString()) &&
                !TextUtils.isEmpty(email.text?.trim().toString()) &&
                !TextUtils.isEmpty(githubLink.text?.trim().toString())
            ) {

                val _firstname = firstName.text.toString()
                val _lastname = lastName.text.toString()
                val _emailaddress = email.text.toString()
                val _projectlink = githubLink.text.toString()

            val alert: AlertDialog.Builder = AlertDialog.Builder(this,R.style.Alertdialogtheme)
                lateinit var formApiInterface: FormApiInterface
            alert.setTitle("are you sure?")
            alert.setPositiveButton("yes") { p0, p1 ->
                formApiInterface.addData(_firstname, _lastname, _emailaddress, _projectlink)
                    ?.enqueue(object : retrofit2.Callback<Void?> {
                        override fun onFailure(call: Call<Void?>, t: Throwable) {
                            errorDialog.show()
                        }

                        override fun onResponse(
                            call: Call<Void?>,
                            response: Response<Void?>
                        ) {
                            Toast.makeText(
                                this@SubmitActivity,
                                "Submitted successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            successDialog.show();
                        }

                    })
            }.show()

            } else{

                Toast.makeText(this,"Invalid form data", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
