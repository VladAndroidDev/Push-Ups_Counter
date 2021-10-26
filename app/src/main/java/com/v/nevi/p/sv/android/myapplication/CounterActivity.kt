package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.v.nevi.p.sv.android.myapplication.model.History
import java.text.SimpleDateFormat
import java.util.*

class CounterActivity : AppCompatActivity(), SensorEventListener,TextToSpeech.OnInitListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor
    private lateinit var counter: TextView
    private lateinit var counterAll: TextView
    private lateinit var buttonReset: Button
    private lateinit var buttonEnd: Button
    private lateinit var tts:TextToSpeech
    private val repository:HistoryRepository = HistoryRepository.getInstance()
    private var currentHistory:History?=null
    private var ttsEnabled:Boolean=false
    private var count = 0
    private var countAll = 0
    private var isDown=false
    private var countOfApproaches:Int=0
    private lateinit var currentDate:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(CounterPreferences.getNowFirstStart(this)){
            firstStart()
        }
        setContentView(R.layout.activity_counter)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        counter = findViewById(R.id.counter)
        counterAll = findViewById(R.id.counter_all)
        buttonReset=findViewById(R.id.reset_button)
        buttonEnd=findViewById(R.id.end_button)
        buttonReset.setOnClickListener {
            if(count!=0){
                countOfApproaches++
            }
            count=0
            counter.text=count.toString()
        }
        tts = TextToSpeech(this,this)
        initHistory()
        val startActivityForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){}
        buttonEnd.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivityForResult.launch(intent)
        }
    }
    private fun firstStart(){
        CounterPreferences.setNowIsFirstStart(this,false)
        val counterDialog=CounterFirstStartDialogFragment()
        counterDialog.show(supportFragmentManager,null)
    }

    private fun initHistory(){
        val date:Date=Date(System.currentTimeMillis())
        currentDate=SimpleDateFormat("EEEE, d MMMM yyyy", Locale(Locale.getDefault().language)).format(date)
        repository.getHistoryByIdLiveData(currentDate).observe(this){
            currentHistory=it
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] != 0.0f) {
                isDown=false
            }else if(!isDown){
                countPushUps()
            }
        }
    }

    private fun countPushUps(){
        count++
        countAll++
        counter.text=count.toString()
        counterAll.text=countAll.toString()
        speak(count.toString())
        isDown=true
    }
    
    private fun speak(text:String){
        if(!ttsEnabled) return
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            if(tts.isLanguageAvailable(Locale(Locale.getDefault().language))==TextToSpeech.LANG_AVAILABLE){
                tts.language=Locale(Locale.getDefault().language)
            }else{
                tts.language=Locale.US
            }
            tts.setPitch(1.3f)
            tts.setSpeechRate(0.7f)
            ttsEnabled=true
        }else{
            ttsEnabled=false
        }
    }

    override fun onDestroy() {
        tts.shutdown()
        if(countAll!=0){
            if(countOfApproaches==0||count!=0){
                countOfApproaches++
            }
            if(currentHistory!=null){
                currentHistory!!.count+=countAll
                currentHistory!!.countOfApproaches+=countOfApproaches
                repository.updateHistory(currentHistory!!)
            }else{
                currentHistory = History(currentDate,countAll,countOfApproaches)
                repository.insertHistory(currentHistory!!)
            }
        }
        super.onDestroy()
    }
}