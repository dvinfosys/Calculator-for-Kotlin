package net.dvinfosys.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    private var screen: EditText? = null
    private var numberbefor: Float = 0.toFloat()
    private var opration: String? = null
    private var btn: ButtonClickListeaner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screen = findViewById<EditText>(R.id.inputvalue)as EditText
        screen!!.setEnabled(false)
        btn = ButtonClickListeaner()
        val idslist = intArrayOf(R.id.clear, R.id.sub, R.id.add, R.id.div, R.id.mul, R.id.show, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero, R.id.dot)
        for (id in idslist) {
            val v = findViewById<View>(id)
            v.setOnClickListener(btn)
        }


    }

    fun backtext(view: View) {
        var str = screen!!.getText().toString()
        if (str.length >= 1) {
            str = str.substring(0, str.length - 1)
            screen!!.setText(str)
        } else if (str.length <= 1) {
            screen!!.setText("0")
        }
    }

    fun math(str: String) {
        numberbefor = java.lang.Float.parseFloat(screen!!.getText().toString())
        opration = str
        screen!!.setText("0")
    }

    fun keybord(str: String) {
        var current = screen!!.getText().toString()
        if (current == "0")
            current = ""
        current += str
        screen!!.setText(current)

    }

    private fun result() {
        val numberafter = java.lang.Float.parseFloat(screen!!.getText().toString())
        var result = 0f
        if (opration.equals("+")) {
            result = numberbefor + numberafter
        }
        if (opration.equals("-")) {
            result = numberbefor - numberafter
        }
        if (opration.equals("*")) {
            result = numberbefor * numberafter
        }
        if (opration.equals("/")) {
            result = numberbefor / numberafter
        }
        screen!!.setText(result.toString())
    }

    private inner class ButtonClickListeaner : View.OnClickListener {
        override fun onClick(v: View) {
            when (v.id) {
                R.id.clear -> {
                    screen!!.setText("0")
                    opration = ""
                }
                R.id.add -> math("+")
                R.id.sub -> math("-")
                R.id.div -> math("/")
                R.id.mul -> math("*")
                R.id.show -> result()
                else -> {
                    val number = (v as Button).getText().toString()
                    keybord(number)
                }
            }
        }
    }
}
