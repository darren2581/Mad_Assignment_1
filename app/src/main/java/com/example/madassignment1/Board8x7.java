package com.example.madassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Board8x7 extends AppCompatActivity {

    private int[][] board8x7; //2D array for the game board
    private int rows = 8; // num of rows
    private int cols = 7; // num of cols
    private boolean p1Move = true; // Player 1 = true; Player 2 = false
    private String player1Name, player2Name;
    private int chosenAvatar1, chosenAvatar2;
    private int chosenColour1, chosenColour2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board8x7);

        Intent intent = getIntent();
        player1Name = intent.getStringExtra("PLAYER_NAME1");
        chosenAvatar1 = intent.getIntExtra("PLAYER_AVATAR1", R.drawable.a1); // Setting the avatar to default if none is selected
        chosenColour1 = intent.getIntExtra("PLAYER_COLOUR1", R.drawable.red);   // Setting the colour to default if none is selected

        player2Name = intent.getStringExtra("PLAYER_NAME2");
        chosenAvatar2 = intent.getIntExtra("PLAYER_AVATAR2", R.drawable.a2); // Setting the avatar to default if none is selected
        chosenColour2 = intent.getIntExtra("PLAYER_COLOUR2", R.drawable.blue);   // Setting the colour to default if none is selected

        board8x7 = new int[rows][cols]; // initialise empty board
        initialiseBoardButtons();
    }

    private void initialiseBoardButtons() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final int finalCol = j;
                Button button = findButtonByRowCol(i, j);
                button.setOnClickListener(view -> handleColumnClick(finalCol));

            }
        }
    }

    private void handleColumnClick(int col) {
        // loop from the last row, move upwards
        for (int row = rows - 1; row >= 0; row--) {
            if (board8x7[row][col] == 0) {
                if (p1Move) {
                    board8x7[row][col] = 1;
                } else {
                    board8x7[row][col] = 2;
                }

                // update button with right colour
                updateBoardButtons(row, col);

                // if win, game stop
                if (checkWin(row, col)) {
                    stopGame();
                    return;
                }

                // if draw, game stop
                if (checkDraw()) {
                    stopGame();
                    return;
                }

                // switch player
                p1Move = !p1Move;
                break;
            }
        }
    }

    private void updateBoardButtons(int row, int col) {
        Button btn = findButtonByRowCol(row, col); // find button for row and col

        if (p1Move) { // p1Move == true
            btn.setBackgroundResource(chosenColour1); // Use P1's chosen colour
        } else {
            btn.setBackgroundResource(chosenColour2);
        }
    }

    // Find the button for a row and col
    private Button findButtonByRowCol(int row, int col) {
        // Button ID based on rows and cols ( r1c1 )
        String buttonID = "r" + (row + 1) + "c" + (col + 1);
        int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
        return findViewById(resourceID);
    }

    public boolean checkDirec(int row, int col, int rowDirec, int colDirec, int player) {
        int count = 1;

        // Check for moving to the right & down
        for (int i = 1; i < 4; i++) {
            int newRow = row + i * rowDirec;
            int newCol = col + i * colDirec;

            // if new position is in bound and is the same player
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && board8x7[newRow][newCol] == player) {
                count++;
            } else {
                break;
            }
        }

        // Check for moving to the left & up
        for (int i = 1; i < 4; i++) {
            int newRow = row - i * rowDirec;
            int newCol = col - i * colDirec;

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && board8x7[newRow][newCol] == player) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }

    public boolean checkWin(int row, int col) {
        int player = board8x7[row][col];

        // horizontal ( left -> right )
        if (checkDirec(row, col, 0, 1, player) || checkDirec(row, col, 0, -1, player)) {
            return true;
        }

        // vertical ( up -> down )
        if (checkDirec(row, col, 1, 0, player) || checkDirec(row, col, -1, 0, player)) {
            return true;
        }

        // diagonal ( top left -> bottom right )
        if (checkDirec(row, col, 1, 1, player) || checkDirec(row, col, -1, -1, player)) {
            return true;
        }

        // diagonal ( top right -> bottom left )
        if (checkDirec(row, col, 1, -1, player) || checkDirec(row, col, -1, 1, player)) {
            return true;
        }

        return false;
    }

    public boolean checkDraw() {
        for (int row = 0; row < board8x7.length; row++) {
            for (int col = 0; col < board8x7[row].length; col++) {
                if (board8x7[row][col] == 0) { // if board is not full
                    return false;
                }
            }
        }
        return true; // draw
    }

    private void stopGame() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button btn = findButtonByRowCol(row, col);
                btn.setEnabled(false); //disable button
            }
        }
    }
}




