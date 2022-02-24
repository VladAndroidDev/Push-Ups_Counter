package com.v.nevi.p.sv.android.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.v.nevi.p.sv.android.myapplication.databinding.ActivityCounterBinding
import com.v.nevi.p.sv.android.myapplication.mode.Mode
import com.v.nevi.p.sv.android.myapplication.mode.ModeInit

private const val TAG = "CounterActivityClass"

class CounterActivity : AppCompatActivity(), LifecycleOwner,ExitDialogFragment.ExitCallback {

    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var startActivityForResult: ActivityResultLauncher<Intent>
    private val mLinearLayoutManager =
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    private val attemptsAdapter = SetAdapter(mLinearLayoutManager)
    private lateinit var binding: ActivityCounterBinding
    private lateinit var mode: Mode


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerShowAttempts.layoutManager = mLinearLayoutManager
        binding.recyclerShowAttempts.adapter = attemptsAdapter
        mode = ModeInit.execute(this,binding)

        val adRequestAdView = AdRequest.Builder().build()
        binding.adView.loadAd(adRequestAdView)

        val adRequestInterstitialAd = AdRequest.Builder().build()
        InterstitialAd.load(this, "ca-app-pub-2946397644393077/3532602663", adRequestInterstitialAd,
            object: InterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Is loaded")
                    mInterstitialAd = interstitialAd
                }
            })

        if (CounterPreferences.getNowIsFirstStartCounter(this)||CounterPreferences.isChangedModeCounter(this)) {
            showMessage()
        }
        startActivityForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
        binding.endButton.setOnClickListener {
            showAdd()
        }
    }

    private fun startMenuHistory() {
        mode.saveTrainingResult(this)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivityForResult.launch(intent)
    }

    private fun showAdd() {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Is norm")
                startMenuHistory()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Log.d(TAG, "FAIL")
                startMenuHistory()
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "Fullscreen showed")
            }
        }
        if (mInterstitialAd != null) {
            mInterstitialAd!!.show(this)
        } else {
            startMenuHistory()
        }
    }

    private fun showMessage() {
        val counterDialog = CounterDialogFragment()
        counterDialog.show(supportFragmentManager, null)
    }


    override fun onBackPressed() {
        val exitDialogFragment = ExitDialogFragment()
        exitDialogFragment.show(supportFragmentManager, null)
    }



    inner class SetAdapter(private val layoutManager: LinearLayoutManager) :
        RecyclerView.Adapter<SetAdapter.SetViewHolder>() {


        private val TYPE_FOOTER = 0
        private val TYPE_ITEM = 1

        private val listAttempts: MutableList<Int> = mutableListOf()

        init {
            listAttempts.add(0)
        }

        private var isWasFirst: Boolean = true
        fun add(count: Int) {
            if (isWasFirst) {
                isWasFirst = !isWasFirst
                listAttempts[0] = count
                notifyItemChanged(0)
            } else {
                listAttempts.add(count)
                notifyItemInserted(listAttempts.size + 1)
                layoutManager.scrollToPosition(listAttempts.size - 1)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
            val inflater=LayoutInflater.from(baseContext)
                val view =
                    inflater.inflate(R.layout.item_recyclerview_sets, parent, false)
                return SetViewHolder(view)
            }


        override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
            holder.bind(listAttempts[position])
        }

        override fun getItemCount(): Int = listAttempts.size

        inner class SetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private var attemptTextView: TextView = itemView.findViewById(R.id.attempt_textview)
            fun bind(count: Int) {
                attemptTextView.text = count.toString()
            }
        }
    }

    override fun exit() {
        finish()
    }
}
