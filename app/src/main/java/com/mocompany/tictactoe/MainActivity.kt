package com.mocompany.tictactoe

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // empty initialated with 2
    //default game state is empty
    var gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    val winningPositions = arrayOf(
       //winning position horizontal first row
        arrayOf(0, 1, 2),
        //winning position horizontal second row
        arrayOf(3, 4, 5),
        //winning position horizontal third row
        arrayOf(6, 7, 8),
        //winning position vertical first column
        arrayOf(0, 3, 6),
        //winning position vertical second column
        arrayOf(1, 4, 7),
        //winning position vertical third column
        arrayOf(2, 5, 8),
        // winning cross position
        arrayOf(0, 4, 8),
        arrayOf(2, 4, 6)
    )
    // X player is 0 ,  and O Player is zero
    var activePlayer: Int = 0
    // gameActive is the boolean var that tell you whether the game is locked or not
    var gameActive: Boolean = true
    // the default value of the winner is null
    var winner: String = ""

    fun dropIn(view: View) {
        var counter: ImageView = view as ImageView
        var tappedCounter = Integer.parseInt(counter.tag.toString())
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.xicon)
                activePlayer = 1
            } else {
                counter.setImageResource(R.drawable.oicon)
                activePlayer = 0

            }
            for (winningPosition in winningPositions) {
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)) {
                    if (activePlayer == 1) {
                        winner = "X"
                    } else {
                        winner = "O"
                    }

                    gameActive = false
                    playAgainButton.isVisible = true
                    winnerTextView.isVisible = true
                    winnerTextView.setText("$winner Wins!")
                }else if((isPresent(gameState , 2)==false) && (winner =="")){
                    gameActive = false
                    playAgainButton.isVisible=true
                    winnerTextView.isVisible=true
                    winnerTextView.setText("No one wins")

                }

            }
        }
    }

    fun <T> isPresent(arr: Array<T>, target: T): Boolean {
        return arr.contains(target)
    }

    fun playAgain(view: View) {
        playAgainButton.isVisible = false
        winnerTextView.isVisible = false
        winner =""
        val count: Int = gridlayout.getChildCount()
        for (i in 0 until count) {
            val child: ImageView = gridlayout.getChildAt(i) as ImageView
            child.setImageDrawable(null)
            gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
            gameActive = true

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}