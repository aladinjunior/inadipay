package co.aladinjunior.inadipay.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import co.aladinjunior.inadipay.R
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
            if (!DateUtil.validateDate(date.text.toString())) {
                Handler(Looper.getMainLooper())
                    .postDelayed({
                        button.showProgress(false)
                        dateInputLayout.error = getString(R.string.invalid_date)
                    }, 1000)
            } else Toast.makeText(this, "indo para proxima tela", Toast.LENGTH_SHORT).show()


        } else {
            button.showProgress(false)
            Toast.makeText(this, getString(R.string.any_field_can_be_null), Toast.LENGTH_SHORT)
                .show()

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



