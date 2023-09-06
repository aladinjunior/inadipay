package co.aladinjunior.inadipay.util

import co.aladinjunior.inadipay.data.db.entities.Costumer
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        fun validateDate(strDate: String, format: String = "dd/MM/yyyy"): Boolean {
            try {
                val sdf = SimpleDateFormat(format)
                sdf.isLenient = false
                val parsedDate = sdf.parse(strDate)
                val calendar = Calendar.getInstance()
                calendar.time = parsedDate
                val month = calendar.get(Calendar.MONTH) + 1
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                return month in 1..12 && day in 1..31

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

        fun toDate(strDate: String, format: String = "dd/MM/yyyy")  : Date {
            try{
                val sdf = SimpleDateFormat(format)
                val date = sdf.parse(strDate)
                return date
            } catch (e: Exception){
                e.printStackTrace()
                return Date()
            }
        }

        fun fromDate(date: Date) : String {
            try{
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val strDate = sdf.format(date)

                return strDate

            }catch (e: Exception){
                e.printStackTrace()
                return String()
            }
        }

        fun getCurrentDate(format: String ="dd/MM/yyyy") : String{
            val sdf = SimpleDateFormat(format)
            return  sdf.format(Date())
        }

        fun getMonthName(customer: Costumer) : String {
            val splitDate = customer.paymentDay.split("/")
            val month = splitDate[1].toInt()
            val months = listOf(
                "Jan", "Fev", "Mar", "Abr",
                "Mai", "Jun", "Jul", "Ago",
                "Set", "Out", "Nov", "Dez"
            )

            val monthName = months[month - 1]
            return monthName
        }

    }
}