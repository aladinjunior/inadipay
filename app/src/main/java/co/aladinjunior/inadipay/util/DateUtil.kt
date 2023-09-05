package co.aladinjunior.inadipay.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        fun validateDate(strDate: String, format: String = "dd/MM/yyyy"): Boolean {
            try {
                val sdf = SimpleDateFormat(format)
                sdf.isLenient = false
                val date = sdf.parse(strDate)
                val today = Date()

                return date.after(today)
            } catch (e: Exception) {
                return false
            }
        }
    }
}