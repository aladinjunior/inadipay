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
    private lateinit var date: TextInputEditText
    private lateinit var amount: TextInputEditText
    private lateinit var button: LoadingButton

    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var surnameInputLayout: TextInputLayout
    private lateinit var cpfInputLayout: TextInputLayout
    private lateinit var dateInputLayout: TextInputLayout
    private lateinit var amountInputLayout: TextInputLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        name = findViewById(R.id.register_edit_name)
        surname = findViewById(R.id.register_edit_surname)
        cpf = findViewById(R.id.register_edit_cpf)
        date = findViewById(R.id.register_edit_date)
        amount = findViewById(R.id.register_edit_amount_released)
        button = findViewById(R.id.register_button)

        nameInputLayout = findViewById(R.id.register_input_name)
        surnameInputLayout = findViewById(R.id.register_input_surname)
        cpfInputLayout = findViewById(R.id.register_input_cpf)
        dateInputLayout = findViewById(R.id.register_input_date)
        amountInputLayout = findViewById(R.id.register_input_amount_released)
        validateTextFields()
        cpf.addTextChangedListener(Mask.mask("###.###.###-##", cpf))
        date.addTextChangedListener(Mask.mask("##/##/####", date))







        button = findViewById(R.id.register_button)
        button.setOnClickListener {

            validate()

            val costumer = Costumer(firstName = name.text.toString(),
                secondName = surname.text.toString(),
                cpf = cpf.text.toString(),
                paymentDay = date.text.toString(),
                amountReleased = amount.text.toString())

            Thread{
                val app = application as App
                val dao = app.db.costumerDao()
                dao.insert(costumer)
                runOnUiThread {
                    Toast.makeText(this, "salvo com sucesso", Toast.LENGTH_SHORT).show()
                }
            }.start()




        }


    }


    private fun validate() {
        button.showProgress(true)
        if (formIsNotEmpty()) {
            if (!CPFUtil.validateCPF(cpf.text.toString())) {
                Handler(Looper.getMainLooper())
                    .postDelayed({
                        button.showProgress(false)
                        cpfInputLayout.error = getString(R.string.invalid_cpf)

                    }, 1000)
            }



            val i = Intent(this, MainActivity::class.java)
            startActivity(i)





            }

        }

    private fun formIsNotEmpty(): Boolean {
        return (name.text.toString().isNotEmpty()
                && surname.text.toString().isNotEmpty()
                && cpf.text.toString().isNotEmpty()
                && date.text.toString().isNotEmpty()
                && amount.text.toString().isNotEmpty())
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
                else cpfInputLayout.error = null
            }
        }

        date.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = date.text.toString().trim()
                if (text.isEmpty()) dateInputLayout.error =
                    getString(R.string.this_field_cant_be_null)
                if (text.length < 10) dateInputLayout.error = getString(R.string.invalid_date)
                else dateInputLayout.error = null

            }
        }

        amount.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = amount.text.toString().trim()
                if (text.isEmpty()) amountInputLayout.error =
                    getString(R.string.this_field_cant_be_null)
                amountInputLayout.error = null

            }
        }

    }


    }






