package co.aladinjunior.inadipay.register.view

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.AppDatabase
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.main.view.MainActivity
import co.aladinjunior.inadipay.main.view.OperationsFragment
import co.aladinjunior.inadipay.util.App
import co.aladinjunior.inadipay.util.CPFUtil
import co.aladinjunior.inadipay.util.DateUtil
import co.aladinjunior.inadipay.util.Mask
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class RegisterActivity : AppCompatActivity() {

    private lateinit var name: TextInputEditText
    private lateinit var surname: TextInputEditText
    private lateinit var cpf: TextInputEditText
    private lateinit var phone: TextInputEditText

    private lateinit var button: LoadingButton

    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var surnameInputLayout: TextInputLayout
    private lateinit var cpfInputLayout: TextInputLayout
    private lateinit var phoneInputLayout: TextInputLayout





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        name = findViewById(R.id.register_edit_name)
        surname = findViewById(R.id.register_edit_surname)
        cpf = findViewById(R.id.register_edit_cpf)
        phone = findViewById(R.id.register_edit_phone)

        button = findViewById(R.id.register_button)

        nameInputLayout = findViewById(R.id.register_input_name)
        surnameInputLayout = findViewById(R.id.register_input_surname)
        cpfInputLayout = findViewById(R.id.register_input_cpf)
        phoneInputLayout = findViewById(R.id.register_input_phone)

        validateTextFields()
        cpf.addTextChangedListener(Mask.mask("###.###.###-##", cpf))
        phone.addTextChangedListener(Mask.mask("(##)#####-####", phone))








        button = findViewById(R.id.register_button)
        button.setOnClickListener {
            button.showProgress(true)

            if (formIsNotEmpty()){
                if (!CPFUtil.validateCPF(cpf.text.toString())){
                    Handler(Looper.getMainLooper())
                        .postDelayed({
                            cpfInputLayout.error = getString(R.string.invalid_cpf)
                            button.showProgress(false)
                        }, 1000)

                }  else {

                    val i = Intent(this, RegisterNextActivity::class.java)
                        .putExtra("name", name.text.toString())
                        .putExtra("surname", surname.text.toString())
                        .putExtra("cpf", cpf.text.toString())
                        .putExtra("phone", phone.text.toString())
                    startActivity(i)

                }

            } else {
                Handler(Looper.getMainLooper())
                    .postDelayed({
                        Toast.makeText(this, getString(R.string.any_field_can_be_null), Toast.LENGTH_SHORT).show()
                        button.showProgress(false)
                    }, 1000)


            }




        }


    }




    private fun formIsNotEmpty(): Boolean {
        return (name.text.toString().isNotEmpty()
                && surname.text.toString().isNotEmpty()
                && cpf.text.toString().isNotEmpty()
                && phone.text.toString().isNotEmpty())
    }


    private fun validateTextFields() {
        name.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = name.text.toString().trim()
                if (text.isEmpty()) nameInputLayout.error =
                    getString(R.string.this_field_cant_be_null)
                else nameInputLayout.error = null

            }
        }

        surname.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = surname.text.toString().trim()
                if (text.isEmpty()) surnameInputLayout.error =
                    getString(R.string.this_field_cant_be_null)
                else surnameInputLayout.error = null

            }
        }
        cpf.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = cpf.text.toString().trim()
                if (text.isEmpty()) cpfInputLayout.error =
                    getString(R.string.this_field_cant_be_null)
                if (text.length < 14) cpfInputLayout.error = getString(R.string.invalid_cpf)
                else cpfInputLayout.error = null
            }
        }

        phone.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = phone.text.toString().trim()
                if (text.isEmpty()) phoneInputLayout.error =
                    getString(R.string.this_field_cant_be_null)
                if (text.length < 11) phoneInputLayout.error = getString(R.string.invalid_phone)
                else phoneInputLayout.error = null

            }
        }



    }


    }






