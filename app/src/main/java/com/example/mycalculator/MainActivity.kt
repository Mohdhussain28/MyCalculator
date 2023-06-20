package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView?=null
    var lastNumeric: Boolean=false
    var lastDot:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput=findViewById(R.id.tvNumber)

    }

    private fun removeZeroAfterDot(result: String):String{
        var value=result
        if(result.contains(".")){
            value=result.substring(0,result.length-2)
        }
        return value
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue=tvInput?.text.toString()
            var prefix=""
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    var split=tvValue.split("-")

                    var one=split[0]
                    var two=split[1]

                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    var difference=one.toDouble()-two.toDouble()
                    tvInput?.text=removeZeroAfterDot(difference.toString())

                } else if(tvValue.contains("+")){
                    var split=tvValue.split("+")

                    var one=split[0]
                    var two=split[1]

                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    var addition=one.toDouble()+two.toDouble()
                    tvInput?.text=removeZeroAfterDot(addition.toString())
                } else if(tvValue.contains("*")){
                    var split=tvValue.split("*")

                    var one=split[0]
                    var two=split[1]

                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    var multiplication=one.toDouble()*two.toDouble()
                    if(multiplication ==-0.0){
                        multiplication=0.0
                    }
                    tvInput?.text=removeZeroAfterDot(multiplication.toString())
                } else if(tvValue.contains("/")){
                    var split=tvValue.split("/")

                    var one=split[0]
                    var two=split[1]

                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    var divide=one.toDouble()/two.toDouble()
                    tvInput?.text=removeZeroAfterDot(divide.toString())
                }
            }catch (e:ArithmeticException){
                e.printStackTrace()
            }

        }

    }
    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric=true
        lastDot=false
    }

    fun onClear(view: View){
        tvInput?.text=""
    }
    fun checkDot(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }
    }

    private fun isOperatorAdded(value:String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("+")
                    || value.contains("*")
                    || value.contains("/")
                    || value.contains("-")
        }
    }


}