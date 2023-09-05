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

        fun calculateDelayedDays(paymentDay: String): Int {
            val sdf = SimpleDateFormat("dd/MM/yyyy")

            try {
                val currentDate = Date()
                val paymentDayDate = sdf.parse(paymentDay)
                val differenceInMilis = currentDate.time - paymentDayDate.time


                val delayedDays = (differenceInMilis / (1000 * 60 * 60 * 24)).toInt()

                return delayedDays
            } catch (e: Exception) {
                e.printStackTrace()
                return 0
            }
        }



    }
}