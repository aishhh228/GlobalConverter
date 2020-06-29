package com.example.globalconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.globalconverter.R.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_money2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL


class money : Fragment() {

    var baseCurrency="INR"
    var convertedToCurrency="USD"
    var conversionRate=0f



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        spinnerSetup()
        textChanged()
        return inflater.inflate(layout.fragment_money2, container, false)
    }


    private fun textChanged(){
        editTextNumber13.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                try{
                    getApiResults()
                }catch(e: Exception){
                    Log.e("Main","$e")
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("Main","Before text changed")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("Main","On text changed")
            }

        })
    }

    private fun getApiResults(){
        if (editTextNumber13!=null && editTextNumber13.text.isNotEmpty() && editTextNumber13.text.isNotEmpty()){
//            val API="https://api.ratesapi.io/api/2010-01-12?base=$baseCurrency&symbols=$convertedToCurrency"
            val API="https://api.exchangeratesapi.io/latest?symbols=$baseCurrency,$convertedToCurrency"

            if (baseCurrency==convertedToCurrency){
                Toast.makeText(context,"You have selected same currency", Toast.LENGTH_SHORT).show()
            }else{
                GlobalScope.launch(Dispatchers.IO) {
                    try{
                        val apiResult= URL(API).readText()
                        val jsonObject= JSONObject(apiResult)

                        conversionRate=jsonObject.getJSONObject("rates").getString(convertedToCurrency).toFloat()

                        Log.d("Main", "$conversionRate")
                        Log.d("Main",apiResult)

                        withContext(Dispatchers.Main){
                            val text=((editTextNumber13.text.toString().toFloat())*conversionRate).toString()
                            amount?.setText(text)
                        }

                    }catch (e:Exception){
                        Log.e("Main","$e")
                    }


                }
            }
        }
    }


    private fun spinnerSetup(){
        val spinner: Spinner = requireView().findViewById(R.id.spmoney)
        val spinner2: Spinner = requireView().findViewById(R.id.spmoney1)

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                array.currencies,
                android.R.layout.simple_spinner_item
            ).also{ adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter=adapter
            }
        }

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                array.currencies2,
                android.R.layout.simple_spinner_item
            ).also{ adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner2.adapter=adapter
            }
        }

        spinner.onItemSelectedListener= (object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                baseCurrency=p0?.getItemAtPosition(p2).toString()
                getApiResults()
            }


        })

        spinner2.onItemSelectedListener= (object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                convertedToCurrency=p0?.getItemAtPosition(p2).toString()
                getApiResults()
            }


        })


    }





}







