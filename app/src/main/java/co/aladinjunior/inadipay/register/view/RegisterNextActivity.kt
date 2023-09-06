package co.aladinjunior.inadipay.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.main.view.MainActivity
import co.aladinjunior.inadipay.util.App
import co.aladinjunior.inadipay.util.CPFUtil
import co.aladinjunior.inadipay.util.DateUtil
import co.aladinjunior.inadipay.util.Mask
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterNextActivity : AppCompatActivity() {
    private lateinit var date: TextInputEditText
    private lateinit var installment: TextInputEditText
    private lateinit var amount: TextInputEditText
    private lateinit var button: LoadingButton
    private lateinit var installmentInputLayout: TextInputLayout
    private lateinit var dateInputLayout: TextInputLayout
    private lateinit var amountInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_next)

        val name = intent?.extras?.getString("name")
        val surname = intent?.extras?.getString("surname")
        val cpf = intent?.extras?.getString("cpf")
        val phone = intent?.extras?.getString("phone")


        date = findViewById(R.id.register_edit_date)
        amount = findViewById(R.id.register_edit_amount_released)
        installment = findViewById(R.id.register_edit_installment)
        button = findViewById(R.id.register_button)

        installmentInputLayout = findViewById(R.id.register_input_installment)
        dateInputLayout = findViewById(R.id.register_input_date)
        amountInputLayout = findViewById(R.id.register_input_amount_released)

        date.addTextChangedListener(Mask.mask("##/##/####", date))

        validateTextFields()




        button = findViewById(R.id.register_button)
        button.setOnClickListener {
            button.showProgress(true)

            if (formIsNotEmpty()){
                if(!DateUtil.validateDate(date.text.toString())){
                    dateInputLayout.error = getString(R.string.invalid_date)
                    button.showProgress(false)
                } else {
                    val costumer = Costumer(firstName = name ?: " sem nome",
                        secondName = surname ?: "sem sobrenome",
                        cpf = cpf ?: "sem cpf",
                        paymentDay = date.text.toString(),
                        amountReleased = amount.text.toString(),
                        cellPhone = phone ?: "sem telefone")

                    Thread{
                        val app = application as App
                        val dao = app.db.costumerDao()
                        dao.insert(costumer)
                        runOnUiThread {
                            Toast.makeText(this, "salvo com sucesso", Toast.LENGTH_SHORT).show()
                            button.showProgress(false)
                        }
                    }.start()

                    val i = Intent(this, MainActivity::class.java)
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
        return (installment.text.toString().isNotEmpty()
                && date.text.toString().isNotEmpty()
                && amount.text.toString().isNotEmpty())
    }


    private fun validateTextFields() {

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

        installment.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = installment.text.toString().trim()
                if (text.isEmpty()) installmentInputLayout.error =
                    getString(R.string.this_field_cant_be_null)
                installmentInputLayout.error = null

            }
        }

    }

}