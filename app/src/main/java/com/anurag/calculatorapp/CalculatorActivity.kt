package com.anurag.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.math.exp

class CalculatorActivity : AppCompatActivity() {
     lateinit var zero:TextView
     lateinit var one: TextView
     lateinit var two : TextView
     lateinit var three: TextView
     lateinit var four: TextView
     lateinit var five: TextView
     lateinit var six: TextView
     lateinit var seven: TextView
     lateinit var eight: TextView
     lateinit var nine: TextView

     lateinit var plus: TextView
     lateinit var minus: TextView
     lateinit var multiply: TextView
     lateinit var divide: TextView
    lateinit var modulo: TextView


     lateinit var changeSign: TextView
     lateinit var decimal: TextView
     lateinit var equals: TextView
     lateinit var ac: TextView
     lateinit var back: ImageView


     lateinit var expression : TextView
     lateinit var result : TextView

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_calculator)

         zero = findViewById(R.id.zero)
         one = findViewById(R.id.one)
         two = findViewById(R.id.two)
         three = findViewById(R.id.three)
         four = findViewById(R.id.four)
         five = findViewById(R.id.five)
         six = findViewById(R.id.six)
         seven = findViewById(R.id.seven)
         eight = findViewById(R.id.eight)
         nine = findViewById(R.id.nine)

         plus = findViewById(R.id.addition)
         minus = findViewById(R.id.subtract)
         multiply = findViewById(R.id.multiply)
         divide = findViewById(R.id.divide)
         modulo = findViewById(R.id.modulo)

         changeSign = findViewById(R.id.change_sign)
         decimal = findViewById(R.id.decimal)
         modulo = findViewById(R.id.modulo)
         equals = findViewById(R.id.equals)
         ac = findViewById(R.id.ac)
         back = findViewById(R.id.back)

         expression = findViewById(R.id.expression)
         result = findViewById(R.id.result)

         appendHelper(zero, "0", true)
         appendHelper(one, "1", true)
         appendHelper(two, "2", true)
         appendHelper(three, "3", true)
         appendHelper(four, "4", true)
         appendHelper(five, "5", true)
         appendHelper(six, "6", true)
         appendHelper(seven, "7", true)
         appendHelper(eight, "8", true)
         appendHelper(nine, "9", true)

         appendHelper(plus, "+", false)
         appendHelper(minus, "-", false)
         appendHelper(multiply, "*", false)
         appendHelper(divide, "/", false)
         appendHelper(modulo, "%", false)

         appendHelper(decimal, ".", true)


         equals.setOnClickListener{
             try {
                 val expr = ExpressionBuilder(expression.text.toString()).build()
                 val answer = expr.evaluate()
                 result.text = answer.toString()
             }catch (exception:Exception){
                 result.text = exception.message
             }
         }

         back.setOnClickListener {
             result.text= ""
             result.hint= ""

             // 1234*24 ->
             if(expression.text.isNotEmpty()){
                 expression.text = expression.text.substring(0,expression.length()-1)
             }
         }

         ac.setOnClickListener {
             expression.text = ""
             result.text = ""
             result.hint = ""
         }

         changeSign.setOnClickListener {
             result.text = ""
             result.hint = ""

             // -24 -> 24
             // 24 -> -24

             /*
             val text = "Test"
             println(text.substring(1))
             */

             if (expression.text.isNotEmpty() && expression.text[0] == '-') {
                 expression.text = expression.text.substring(1) // -2345 -> substring(1) => 2345
             } else {
                 expression.text = "-(${expression.text})"
             }
         }





     }

    private fun appendHelper(view: TextView, value: String, toBeCleared: Boolean){
              view.setOnClickListener{
                  appendText(value, toBeCleared)
              }
          }


     private fun appendText(value :String , toBeCleared: Boolean ){
        if(result.text != ""){
            expression.text = ""

            }

        if (toBeCleared){
            result.text= ""
            expression.append(value)
        }else {
            //
            expression.append(result.text)
            expression.append(value)
            result.text = ""
        }

         result.hint = expression.text


     }
}
