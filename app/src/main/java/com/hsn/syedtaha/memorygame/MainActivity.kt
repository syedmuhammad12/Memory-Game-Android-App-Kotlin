package com.hsn.syedtaha.memorygame



import android.animation.ArgbEvaluator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdView
import com.google.android.material.snackbar.Snackbar
import com.hsn.syedtaha.memorygame.models.BoardSize
import com.hsn.syedtaha.memorygame.models.MemoryGame
import com.unity3d.mediation.*
import com.unity3d.mediation.errors.LoadError
import com.unity3d.mediation.errors.SdkInitializationError
import com.unity3d.mediation.errors.ShowError
import com.google.android.gms.ads.interstitial.InterstitialAd


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
        private const val CREATE_REQUEST_CODE = 248

    }

    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView
    private lateinit var tvNumPairs: TextView
    private lateinit var clRoot: ConstraintLayout
    lateinit var mAdView : AdView
//    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "MainActivity"
    private lateinit var memoryGame: MemoryGame
    private lateinit var  adapter: MemoryBoardAdapter
    private var boardSize: BoardSize = BoardSize.EASY




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val configuration = InitializationConfiguration.builder()
            .setGameId("4915581")
            .setInitializationListener(object : IInitializationListener {
                override fun onInitializationComplete() {
                    // Unity Mediation is initialized. Try loading an ad.
                    println("Unity Mediation is successfully initialized.")
                }

                override fun onInitializationFailed(errorCode: SdkInitializationError?, msg: String?) {
                    // Unity Mediation failed to initialize. Printing failure reason...
                }
            }).build()

        UnityMediation.initialize(configuration)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        val interstitialAdUnitId = "Interstitial_Android"


        val showListener: IInterstitialAdShowListener = object : IInterstitialAdShowListener {


            override fun onInterstitialShowed(p0: com.unity3d.mediation.InterstitialAd?) {
                TODO("Not yet implemented")
            }

            override fun onInterstitialClicked(p0: com.unity3d.mediation.InterstitialAd?) {
                TODO("Not yet implemented")
            }

            override fun onInterstitialClosed(p0: com.unity3d.mediation.InterstitialAd?) {
                TODO("Not yet implemented")
            }

            override fun onInterstitialFailedShow(
                p0: com.unity3d.mediation.InterstitialAd?,
                p1: ShowError?,
                p2: String?
            ) {
                TODO("Not yet implemented")
            }
        }

// Instantiate a new interstitial ad object:

// Instantiate a new interstitial ad object:
        val interstitialAd = InterstitialAd(this@MainActivity, interstitialAdUnitId)

// Implement a load listener interface:

// Implement a load listener interface:
        val loadListener: IInterstitialAdLoadListener = object : IInterstitialAdLoadListener {
            override fun onInterstitialLoaded(p0: com.unity3d.mediation.InterstitialAd?) {

                interstitialAd.show(showListener);


            }

            override fun onInterstitialFailedLoad(
                p0: com.unity3d.mediation.InterstitialAd?,
                p1: LoadError?,
                p2: String?
            ) {

            }
        }

// Load an ad:

// Load an ad:
        interstitialAd.load(loadListener)
        interstitialAd.show(showListener);








//        var adRequest = AdRequest.Builder().build()

//        InterstitialAd.load(this,"ca-app-pub-8721713442445301/2514902298", adRequest, object : InterstitialAdLoadCallback() {
////        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
//            override fun onAdFailedToLoad(adError: LoadAdError) {
//
//                mInterstitialAd = null
//            }
//
//            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//
//                mInterstitialAd = interstitialAd
//            }
//        })
//
//
//        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
//            override fun onAdClicked() {
//                // Called when a click is recorded for an ad.
//
//            }
//
//            override fun onAdDismissedFullScreenContent() {
//                // Called when ad is dismissed.
//                mInterstitialAd = null
//            }
//
//            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
//                // Called when ad fails to show.
//                mInterstitialAd = null
//            }
//
//            override fun onAdImpression() {
//                // Called when an impression is recorded for an ad.
//            }
//
//            override fun onAdShowedFullScreenContent() {
//                // Called when ad is shown.
//            }
//        }






//        Appodeal.setInterstitialCallbacks(object : InterstitialCallbacks {
//            override fun onInterstitialLoaded(isPrecache: Boolean) {
//                // Called when interstitial is loaded
//            }
//
//            override fun onInterstitialFailedToLoad() {
//                // Called when interstitial failed to load
//                Appodeal.show(this@MainActivity, Appodeal.INTERSTITIAL)
//            }
//
//            override fun onInterstitialShown() {
//                // Called when interstitial is shown
//            }
//
//            override fun onInterstitialShowFailed() {
//                // Called when interstitial show failed
//            }
//
//            override fun onInterstitialClicked() {
//                // Called when interstitial is clicked
//            }
//
//            override fun onInterstitialClosed() {
//                // Called when interstitial is closed
//            }
//
//            override fun onInterstitialExpired() {
//                // Called when interstitial is expired
//            }
//        })







        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {

                interstitialAd.load(loadListener)
                interstitialAd.show(showListener);

//                if (mInterstitialAd != null) {
//                    mInterstitialAd?.show(this@MainActivity)
//                } else {
//                    InterstitialAd.load(this@MainActivity,"ca-app-pub-8721713442445301/2514902298", adRequest, object : InterstitialAdLoadCallback() {
//                        override fun onAdFailedToLoad(adError: LoadAdError) {
//                            Log.d(TAG, adError?.toString())
//                            mInterstitialAd = null
//                        }
//
//                        override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                            mInterstitialAd = interstitialAd
//                        }
//                    })
//                }



//        Appodeal.show(this@MainActivity, Appodeal.INTERSTITIAL)
//                if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
//
//                    Appodeal.show(this@MainActivity, Appodeal.INTERSTITIAL)
//
//
//                }
//                else{
//                    Appodeal.initialize(this@MainActivity, "d8028333e80df6c006edf5d4e19327e89cdb0ca2be17cf12",
//                        Appodeal.INTERSTITIAL or Appodeal.BANNER)
//                    if(Appodeal.isInitialized(Appodeal.INTERSTITIAL)){
//                        if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
//
//                            Appodeal.show(this@MainActivity, Appodeal.INTERSTITIAL)
//
//
//                        }
//
//                    }
//
//
//                }
//
//                if(Appodeal.isLoaded(Appodeal.BANNER)){
//                    Appodeal.show(this@MainActivity, Appodeal.BANNER_VIEW)
//                }
//                else{
//                    Appodeal.initialize(this@MainActivity, "d8028333e80df6c006edf5d4e19327e89cdb0ca2be17cf12",
//                        Appodeal.BANNER)
//                }





            }
        },1000)





//        loadBannerAd()

        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)
        clRoot = findViewById(R.id.clRoot)

        setupBoard()
    }

    private fun loadBannerAd() {
//        MobileAds.initialize(this) {}
//
//        mAdView = findViewById(R.id.adView)
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)
//
//        mAdView.adListener = object : AdListener() {
//            override fun onAdClicked() {
//                // Code to be executed when the user clicks on an ad.
//            }
//
//            override fun onAdClosed() {
//                // Code to be executed when the user is about to return
//                // to the app after tapping on an ad.
//            }
//
//            override fun onAdFailedToLoad(adError: LoadAdError) {
//                // Code to be executed when an ad request fails.
//            }
//
//            override fun onAdImpression() {
//                // Code to be executed when an impression is recorded
//                // for an ad.
//            }
//
//            override fun onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//            }
//
//            override fun onAdOpened() {
//                // Code to be executed when an ad opens an overlay that
//                // covers the screen.
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_refresh -> {

//                if (mInterstitialAd != null) {
//                    mInterstitialAd?.show(this@MainActivity)
//                }
//                if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
//
//                    Appodeal.show(this@MainActivity, Appodeal.INTERSTITIAL)
//
//
//                }
//

                if (memoryGame.getNumMoves() > 0 && !memoryGame.hasWonGame()) {
                    showAlertDialog("Quit your current game?", null, View.OnClickListener {
                        setupBoard()
                    })
                } else {
                    setupBoard()
                }
            }
            R.id.mi_new_size -> {
                showNewSizeDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showNewSizeDialog() {
        val boardSizeView = LayoutInflater.from(this).inflate(R.layout.dialog_board_size, null)
        val radioGroupSize = boardSizeView.findViewById<RadioGroup>(R.id.radioGroup)
        when (boardSize) {
            BoardSize.EASY -> radioGroupSize.check(R.id.rbEasy)
            BoardSize.MEDIUM -> radioGroupSize.check(R.id.rbMedium)
            BoardSize.HARD -> radioGroupSize.check(R.id.rbHard)
        }
        showAlertDialog("Choose game difficulty", boardSizeView, View.OnClickListener {
            // Set a new value for board size
            boardSize = when (radioGroupSize.checkedRadioButtonId) {
                R.id.rbEasy -> BoardSize.EASY
                R.id.rbMedium -> BoardSize.MEDIUM
                else -> BoardSize.HARD
            }
            setupBoard()
        })
    }

    private fun showAlertDialog(title: String, view: View?, positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("OK") {_, _ ->
                positiveClickListener.onClick(null)
            }.show()

    }

    private fun setupBoard() {
        when (boardSize) {
            BoardSize.EASY -> {
                tvNumMoves.text = "Easy: 4 x 2"
                tvNumPairs.text = "Pairs: 0 / 4"
            }
            BoardSize.MEDIUM -> {
                tvNumMoves.text = "Medium: 6 x 3"
                tvNumPairs.text = "Pairs: 0 / 9"

            }
            BoardSize.HARD -> {
                tvNumMoves.text = "Hard: 6 x 6"
                tvNumPairs.text = "Pairs: 0 / 12"

            }
        }
        tvNumPairs.setTextColor(ContextCompat.getColor(this, R.color.color_progress_none))
        memoryGame = MemoryGame(boardSize)

//        second argument is number of total elements in a recycler grid
        adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards, object: MemoryBoardAdapter.CardClickListener{
            override fun onCardClick(position: Int) {
                updateGameWithFlip(position)
            }

        })
        rvBoard.adapter = adapter
//        for optimization purposes
        rvBoard.setHasFixedSize(true)

//        second argument is number of columns in our recycler view grid
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())

    }

    //toggling the image facing on screen
    private fun updateGameWithFlip(position: Int) {
        // Error Checking
        if (memoryGame.hasWonGame()) {
            // Alert the user of invalid move
            Snackbar.make(clRoot, "You have already won!", Snackbar.LENGTH_LONG).show()
            return
        }
        if (memoryGame.isCardFaceUp(position)) {
            // Alert the user of invalid move
            Snackbar.make(clRoot, "Invalid move!", Snackbar.LENGTH_SHORT).show()
            return
        }
        // Actually flipping the card
        if (memoryGame.flipCard(position)) {
            Log.i(TAG, "Found a match! Num pairs found ${memoryGame.numPairsFound}")
            val color = ArgbEvaluator().evaluate(
                memoryGame.numPairsFound.toFloat()/boardSize.getNumPairs(),
                ContextCompat.getColor(this, R.color.color_progress_none),
                ContextCompat.getColor(this, R.color.color_progress_full),
            ) as Int
            tvNumPairs.setTextColor(color)
            tvNumPairs.text = "Pairs: ${memoryGame.numPairsFound} / ${boardSize.getNumPairs()}"
            if (memoryGame.hasWonGame()) {
                Snackbar.make(clRoot, "You won!! Congratulations.", Snackbar.LENGTH_LONG).show()
            }
        }
        tvNumMoves.text = "Moves: ${memoryGame.getNumMoves()}"
        adapter.notifyDataSetChanged()

    }
}