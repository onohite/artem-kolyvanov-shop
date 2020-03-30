package com.example.artem_kolyvanov_shop

class MathTest {
    fun incrementTest(digit: Int):Any {
        var num = digit
        return ++num
    }


    fun divideTest(denominator: Int): Boolean = denominator != 0

    fun multiplicationTest (a:Int,b:Int): Any = a*b
}