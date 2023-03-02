package view

import model.ConstantStrings
import model.Days
import model.Gender

object GetInput {
    inline fun getStringInput(printMessage:()->Unit): String{
        printMessage()
        return readln()
    }
    inline fun getIntInput(printMessage:()->Unit): Int{
        printMessage()
        return try{ readln().toInt() } catch(e: Exception){ 100 }
    }
    fun getGender(): String{
        println("Enter\n1.For Male\n2.For Female: ")
        return when(try{readln().toInt()}catch (e: Exception){100}){
            1 -> Gender.MALE.name
            2 -> Gender.FEMALE.name
            else -> getGender()
        }
    }
    fun getDay(): Days = when(getIntInput { println("Enter\n1.Monday\n2.Tuesday\n3.Wednesday\n4.Thursday\n5.Friday\n6.Saturday\n7.Sunday") }){
        1 -> Days.MONDAY
        2 -> Days.TUESDAY
        3 -> Days.WEDNESDAY
        4 -> Days.THURSDAY
        5 -> Days. FRIDAY
        6 -> Days.SATURDAY
        7 -> Days.SUNDAY
        else -> {
            println(ConstantStrings.ENTERVALIDINPUT.message)
            getDay()
        }
    }
    /*
    inline fun getDoubleInput(printMessage:()->Unit): Double{
        printMessage()
        return try{ readln().toDouble() } catch(e: Exception){ 100.00 }
    }
     */
}