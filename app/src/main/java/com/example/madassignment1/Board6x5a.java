package com.example.madassignment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

public class Board6x5a extends AppCompatActivity {

    private Button[][] boardButtons;
    private int[][] board6x5;
    private int rows = 6;
    private int cols = 5;
    private boolean p1Move = true;
    private String player1Name, player2Name;
    private int chosenAvatar1, chosenAvatar2;
    private int chosenColour1, chosenColour2;
    private int played;
    private int player1Win, player2Win;
    private int player1Lose, player2Lose;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board6x5);

        Intent intent = getIntent();
        player1Name = intent.getStringExtra("PLAYER_NAME1");
        chosenAvatar1 = intent.getIntExtra("PLAYER_AVATAR1", R.drawable.a1);
        chosenColour1 = intent.getIntExtra("PLAYER_COLOUR1", R.drawable.red);

        player2Name = intent.getStringExtra("PLAYER_NAME2");
        chosenAvatar2 = intent.getIntExtra("PLAYER_AVATAR2", R.drawable.a2);
        chosenColour2 = intent.getIntExtra("PLAYER_COLOUR2", R.drawable.blue);

        // Load statistics from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("GameStats", MODE_PRIVATE);
        played = prefs.getInt("played", 0);
        player1Win = prefs.getInt("player1Win", 0);
        player2Win = prefs.getInt("player2Win", 0);
        player1Lose = prefs.getInt("player1Lose", 0);
        player2Lose = prefs.getInt("player2Lose", 0);

        SharedPreferences Prefs = getSharedPreferences("game_mode", MODE_PRIVATE);
        gameMode = Prefs.getInt("GAME_MODE", 0);

        if(gameMode == 1){
            board6x5 = new int[rows][cols];
            player2Name = "AI";
            chosenColour2 = R.drawable.green;
            initializeBoardButtons();

            showStatistics();
            showMenu();
        }
        if(gameMode == 2){
            board6x5 = new int[rows][cols];
            initializeBoardButtons();

            showStatistics();
            showMenu();
        }
        updateTurnFragment();
    }

    private void initializeBoardButtons() {
        boardButtons = new Button[rows][cols];
        boardButtons[0][0] = findViewById(R.id.r1c1);
        boardButtons[0][1] = findViewById(R.id.r1c2);
        boardButtons[0][2] = findViewById(R.id.r1c3);
        boardButtons[0][3] = findViewById(R.id.r1c4);
        boardButtons[0][4] = findViewById(R.id.r1c5);

        boardButtons[1][0] = findViewById(R.id.r2c1);
        boardButtons[1][1] = findViewById(R.id.r2c2);
        boardButtons[1][2] = findViewById(R.id.r2c3);
        boardButtons[1][3] = findViewById(R.id.r2c4);
        boardButtons[1][4] = findViewById(R.id.r2c5);

        boardButtons[2][0] = findViewById(R.id.r3c1);
        boardButtons[2][1] = findViewById(R.id.r3c2);
        boardButtons[2][2] = findViewById(R.id.r3c3);
        boardButtons[2][3] = findViewById(R.id.r3c4);
        boardButtons[2][4] = findViewById(R.id.r3c5);

        boardButtons[3][0] = findViewById(R.id.r4c1);
        boardButtons[3][1] = findViewById(R.id.r4c2);
        boardButtons[3][2] = findViewById(R.id.r4c3);
        boardButtons[3][3] = findViewById(R.id.r4c4);
        boardButtons[3][4] = findViewById(R.id.r4c5);

        boardButtons[4][0] = findViewById(R.id.r5c1);
        boardButtons[4][1] = findViewById(R.id.r5c2);
        boardButtons[4][2] = findViewById(R.id.r5c3);
        boardButtons[4][3] = findViewById(R.id.r5c4);
        boardButtons[4][4] = findViewById(R.id.r5c5);

        boardButtons[5][0] = findViewById(R.id.r6c1);
        boardButtons[5][1] = findViewById(R.id.r6c2);
        boardButtons[5][2] = findViewById(R.id.r6c3);
        boardButtons[5][3] = findViewById(R.id.r6c4);
        boardButtons[5][4] = findViewById(R.id.r6c5);

        // Set up onClick listeners
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final int finalCol = j;
                boardButtons[i][j].setOnClickListener(view -> handleColumnClick(finalCol));
            }
        }
    }

    private void handleColumnClick(int col) {
        for (int row = rows - 1; row >= 0; row--) {
            if (board6x5[row][col] == 0) {
                if (p1Move) {
                    board6x5[row][col] = 1; // Player 1's move
                } else {
                    board6x5[row][col] = 2; // Player 2's move
                }

                updateBoardButtons(row, col);

                if (checkWin(row, col)) {
                    stopGame();
                    resetBoard();
                    updatePlayedCount(); // Increment played count when game ends
                    updateWinLossCounts(p1Move); // Update win/loss counts
                    return;
                }

                if (checkDraw()) {
                    stopGame();
                    updatePlayedCount(); // Increment played count when game ends

                    resetBoard();

                    // Open Draws activity
                    Intent intent = new Intent(this, Draws.class);
                    startActivity(intent);
                    return;
                }

                p1Move = !p1Move;
                updateTurnFragment(); // Update the fragment to show the next player's turn
                break;
            }
        }

        // AI's move
        if (gameMode == 1 && !p1Move) {
            int aiCol = generateAiMove();
            for (int row = rows - 1; row >= 0; row--) {
                if (board6x5[row][aiCol] == 0) {
                    board6x5[row][aiCol] = 2; // AI's move
                    updateBoardButtons(row, aiCol);

                    if (checkWin(row, aiCol)) {
                        stopGame();
                        resetBoard();
                        updatePlayedCount(); // Increment played count when game ends
                        updateWinLossCounts(false); // AI wins
                        return;
                    }

                    if (checkDraw()) {
                        stopGame();
                        updatePlayedCount(); // Increment played count when game ends

                        resetBoard();

                        // Open Draws activity
                        Intent intent = new Intent(this, Draws.class);
                        startActivity(intent);
                        return;
                    }

                    p1Move = !p1Move; // Switch back to Player 1 after AI's move
                    updateTurnFragment(); // Update the fragment to show Player 1's turn
                    break;
                }
            }
        }
    }

//    private int generateAiMove() {
//        Random rand = new Random();
//        int aiCol = rand.nextInt(cols);
//        return aiCol;
//    }

    private void updateBoardButtons(int row, int col) {
        Button btn = findButtonByRowCol(row, col);

        if (p1Move) {
            btn.setBackgroundResource(chosenColour1);
        }
        else {
            btn.setBackgroundResource(chosenColour2);
        }
    }

    private Button findButtonByRowCol(int row, int col) {
        return boardButtons[row][col];
    }

    public boolean checkDirec(int row, int col, int rowDirec, int colDirec, int player) {
        int count = 1;

        for (int i = 1; i < 4; i++) {
            int newRow = row + i * rowDirec;
            int newCol = col + i * colDirec;

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && board6x5[newRow][newCol] == player) {
                count++;
            }
            else {
                break;
            }
        }

        for (int i = 1; i < 4; i++) {
            int newRow = row - i * rowDirec;
            int newCol = col - i * colDirec;

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && board6x5[newRow][newCol] == player) {
                count++;
            }
            else {
                break;
            }
        }

        return count >= 4;
    }

    public boolean checkWin(int row, int col) {
        int player = board6x5[row][col];

        return checkDirec(row, col, 0, 1, player) || checkDirec(row, col, 0, -1, player) ||
                checkDirec(row, col, 1, 0, player) || checkDirec(row, col, -1, 0, player) ||
                checkDirec(row, col, 1, 1, player) || checkDirec(row, col, -1, -1, player) ||
                checkDirec(row, col, 1, -1, player) || checkDirec(row, col, -1, 1, player);
    }

    public boolean checkDraw() {
        for (int row = 0; row < board6x5.length; row++) {
            for (int col = 0; col < board6x5[row].length; col++) {
                if (board6x5[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void stopGame() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button btn = findButtonByRowCol(row, col);
                btn.setEnabled(false);
            }
        }
    }

    private void showStatistics() {
        Statistics statisticsFragment = Statistics.newInstance(
                played,
                player1Name,
                player2Name,
                player1Win,
                player2Win,
                player1Lose,
                player2Lose,
                getAvatarString(chosenAvatar1),
                getAvatarString(chosenAvatar2)
        );

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.statistics, statisticsFragment);
        transaction.commit();
    }

    private String getAvatarString(int avatarResId) {
        return getResources().getResourceEntryName(avatarResId);
    }

    private void saveStatistics() {
        SharedPreferences prefs = getSharedPreferences("GameStats", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("played", played);
        editor.putInt("player1Win", player1Win);
        editor.putInt("player2Win", player2Win);
        editor.putInt("player1Lose", player1Lose);
        editor.putInt("player2Lose", player2Lose);
        editor.apply(); // Commit changes
    }

    private void updatePlayedCount() {
        played++; // Increment played count
        saveStatistics(); // Save statistics
    }

    private void updateWinLossCounts(boolean p1Move) {
        if (p1Move) {
            // Player 1 won
            player1Win++;
            player2Lose++;
        }
        else {
            // Player 2 won
            player2Win++;
            player1Lose++;
        }

        saveStatistics(); // Save statistics

        // Start the Wins activity and pass data
        Intent intent = new Intent(this, Wins.class);
        intent.putExtra("WINNER_NAME", p1Move ? player1Name : player2Name);
        intent.putExtra("WINNER_AVATAR", p1Move ? chosenAvatar1 : chosenAvatar2);
        startActivity(intent);
    }

    private void clearSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences("GameStats", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // Clear all preferences
        editor.apply(); // Commit changes
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearSharedPreferences();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        clearSharedPreferences();
    }

    private void showMenu() {
        MenuFragment menuFragment = MenuFragment.newInstance("param1", "param2");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.menu, menuFragment);
        transaction.commit();
    }

    private void updateTurnFragment() {
        String currentPlayer = p1Move ? player1Name : player2Name;
        TurnFragment turnFragment = TurnFragment.newInstance(currentPlayer);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.turn_fragment_container, turnFragment);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the board state
        outState.putSerializable("board6x5", board6x5);
        outState.putBoolean("p1Move", p1Move);

        // Save player and game data
        outState.putString("player1Name", player1Name);
        outState.putString("player2Name", player2Name);
        outState.putInt("chosenAvatar1", chosenAvatar1);
        outState.putInt("chosenAvatar2", chosenAvatar2);
        outState.putInt("chosenColour1", chosenColour1);
        outState.putInt("chosenColour2", chosenColour2);
        outState.putInt("played", played);
        outState.putInt("player1Win", player1Win);
        outState.putInt("player2Win", player2Win);
        outState.putInt("player1Lose", player1Lose);
        outState.putInt("player2Lose", player2Lose);
        outState.putInt("gameMode", gameMode);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore the board state
        board6x5 = (int[][]) savedInstanceState.getSerializable("board6x5");
        p1Move = savedInstanceState.getBoolean("p1Move");

        // Restore player and game data
        player1Name = savedInstanceState.getString("player1Name");
        player2Name = savedInstanceState.getString("player2Name");
        chosenAvatar1 = savedInstanceState.getInt("chosenAvatar1");
        chosenAvatar2 = savedInstanceState.getInt("chosenAvatar2");
        chosenColour1 = savedInstanceState.getInt("chosenColour1");
        chosenColour2 = savedInstanceState.getInt("chosenColour2");
        played = savedInstanceState.getInt("played");
        player1Win = savedInstanceState.getInt("player1Win");
        player2Win = savedInstanceState.getInt("player2Win");
        player1Lose = savedInstanceState.getInt("player1Lose");
        player2Lose = savedInstanceState.getInt("player2Lose");
        gameMode = savedInstanceState.getInt("gameMode");

        // Restore the UI elements (buttons, turn, etc.)
        initializeBoardButtons();
        restoreBoardState();
        updateTurnFragment();
    }

    private void restoreBoardState() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board6x5[row][col] == 1) {
                    boardButtons[row][col].setBackgroundResource(chosenColour1); // Player 1's move
                }
                else if (board6x5[row][col] == 2) {
                    boardButtons[row][col].setBackgroundResource(chosenColour2); // Player 2's move
                }
            }
        }
    }

    private void resetBoard() {
        // Reset the board array
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board6x5[row][col] = 0;
            }
        }

        // Clear the buttons
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button btn = findButtonByRowCol(row, col);
                btn.setBackgroundResource(R.drawable.white_circle); // Assuming default background
                btn.setEnabled(true); // Re-enable buttons for new game
            }
        }

        // Reset the turn
        p1Move = true;
    }

    private int generateAiMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        // Iterate over all possible columns
        for (int col = 0; col < cols; col++) {
            for (int row = rows - 1; row >= 0; row--) {
                // Check if the column has an empty slot
                if (board6x5[row][col] == 0) {
                    // Temporarily make the move (AI = 2)
                    board6x5[row][col] = 2;
                    // Evaluate the board using Alpha-Beta Pruning
                    int score = alphaBeta(board6x5, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    // Undo the move
                    board6x5[row][col] = 0;

                    // Find the best score and move
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = col;
                    }
                    break;  // Move to next column after finding the top-most row in the column
                }
            }
        }
        return bestMove;
    }

    private int alphaBeta(int[][] board, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        // Base case: check for win, draw, or maximum depth (AI or player wins)
        if (checkWinForPlayer(2)) return 10; // AI wins
        if (checkWinForPlayer(1)) return -10; // Player wins
        if (checkDraw()) return 0;  // Draw
        if (depth >= 5) return 0;   // Limit the depth for performance

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int col = 0; col < cols; col++) {
                for (int row = rows - 1; row >= 0; row--) {
                    if (board[row][col] == 0) {
                        // Simulate AI move
                        board[row][col] = 2;
                        int eval = alphaBeta(board, depth + 1, alpha, beta, false);
                        board[row][col] = 0; // Undo the move
                        maxEval = Math.max(maxEval, eval);
                        alpha = Math.max(alpha, eval);
                        if (beta <= alpha) break;  // Prune the search
                    }
                }
            }
            return maxEval;
        }
        else {
            int minEval = Integer.MAX_VALUE;
            for (int col = 0; col < cols; col++) {
                for (int row = rows - 1; row >= 0; row--) {
                    if (board[row][col] == 0) {
                        // Simulate Player move
                        board[row][col] = 1;
                        int eval = alphaBeta(board, depth + 1, alpha, beta, true);
                        board[row][col] = 0; // Undo the move
                        minEval = Math.min(minEval, eval);
                        beta = Math.min(beta, eval);
                        if (beta <= alpha) break;  // Prune the search
                    }
                }
            }
            return minEval;
        }
    }

    // Helper function to check if a player has won
    private boolean checkWinForPlayer(int player) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board6x5[row][col] == player) {
                    if (checkWin(row, col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
