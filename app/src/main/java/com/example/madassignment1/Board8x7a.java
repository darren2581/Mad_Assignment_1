package com.example.madassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Board8x7a extends AppCompatActivity {

    private Button[][] boardButtons;
    private int[][] board8x7;
    private int rows = 8;
    private int cols = 7;
    private boolean p1Move = true;
    private String player1Name, player2Name;
    private int chosenAvatar1, chosenAvatar2;
    private int chosenColour1, chosenColour2;
    private int played;
    private int player1Win, player2Win;
    private int player1Lose, player2Lose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board8x7);

        Intent intent = getIntent();
        player1Name = intent.getStringExtra("PLAYER_NAME1");
        chosenAvatar1 = intent.getIntExtra("PLAYER_AVATAR1", R.drawable.a1);
        chosenColour1 = intent.getIntExtra("PLAYER_COLOUR1", R.drawable.red);

        player2Name = intent.getStringExtra("PLAYER_NAME2");
        chosenAvatar2 = intent.getIntExtra("PLAYER_AVATAR2", R.drawable.a2);
        chosenColour2 = intent.getIntExtra("PLAYER_COLOUR2", R.drawable.blue);
        if ("NAME".equals(player2Name)) {
            player2Name = "AI";
            chosenColour2 = R.drawable.green;
        }

        // Initialize statistics to default values
        played = 0;
        player1Win = 0;
        player2Win = 0;
        player1Lose = 0;
        player2Lose = 0;

        board8x7 = new int[rows][cols];
        initializeBoardButtons();

        showStatistics();
    }

    private void initializeBoardButtons() {
        boardButtons = new Button[rows][cols];

        // Initialize all buttons for an 8x7 board
        boardButtons[0][0] = findViewById(R.id.r1c1);
        boardButtons[0][1] = findViewById(R.id.r1c2);
        boardButtons[0][2] = findViewById(R.id.r1c3);
        boardButtons[0][3] = findViewById(R.id.r1c4);
        boardButtons[0][4] = findViewById(R.id.r1c5);
        boardButtons[0][5] = findViewById(R.id.r1c6);
        boardButtons[0][6] = findViewById(R.id.r1c7);

        boardButtons[1][0] = findViewById(R.id.r2c1);
        boardButtons[1][1] = findViewById(R.id.r2c2);
        boardButtons[1][2] = findViewById(R.id.r2c3);
        boardButtons[1][3] = findViewById(R.id.r2c4);
        boardButtons[1][4] = findViewById(R.id.r2c5);
        boardButtons[1][5] = findViewById(R.id.r2c6);
        boardButtons[1][6] = findViewById(R.id.r2c7);

        boardButtons[2][0] = findViewById(R.id.r3c1);
        boardButtons[2][1] = findViewById(R.id.r3c2);
        boardButtons[2][2] = findViewById(R.id.r3c3);
        boardButtons[2][3] = findViewById(R.id.r3c4);
        boardButtons[2][4] = findViewById(R.id.r3c5);
        boardButtons[2][5] = findViewById(R.id.r3c6);
        boardButtons[2][6] = findViewById(R.id.r3c7);

        boardButtons[3][0] = findViewById(R.id.r4c1);
        boardButtons[3][1] = findViewById(R.id.r4c2);
        boardButtons[3][2] = findViewById(R.id.r4c3);
        boardButtons[3][3] = findViewById(R.id.r4c4);
        boardButtons[3][4] = findViewById(R.id.r4c5);
        boardButtons[3][5] = findViewById(R.id.r4c6);
        boardButtons[3][6] = findViewById(R.id.r4c7);

        boardButtons[4][0] = findViewById(R.id.r5c1);
        boardButtons[4][1] = findViewById(R.id.r5c2);
        boardButtons[4][2] = findViewById(R.id.r5c3);
        boardButtons[4][3] = findViewById(R.id.r5c4);
        boardButtons[4][4] = findViewById(R.id.r5c5);
        boardButtons[4][5] = findViewById(R.id.r5c6);
        boardButtons[4][6] = findViewById(R.id.r5c7);

        boardButtons[5][0] = findViewById(R.id.r6c1);
        boardButtons[5][1] = findViewById(R.id.r6c2);
        boardButtons[5][2] = findViewById(R.id.r6c3);
        boardButtons[5][3] = findViewById(R.id.r6c4);
        boardButtons[5][4] = findViewById(R.id.r6c5);
        boardButtons[5][5] = findViewById(R.id.r6c6);
        boardButtons[5][6] = findViewById(R.id.r6c7);

        boardButtons[6][0] = findViewById(R.id.r7c1);
        boardButtons[6][1] = findViewById(R.id.r7c2);
        boardButtons[6][2] = findViewById(R.id.r7c3);
        boardButtons[6][3] = findViewById(R.id.r7c4);
        boardButtons[6][4] = findViewById(R.id.r7c5);
        boardButtons[6][5] = findViewById(R.id.r7c6);
        boardButtons[6][6] = findViewById(R.id.r7c7);

        boardButtons[7][0] = findViewById(R.id.r8c1);
        boardButtons[7][1] = findViewById(R.id.r8c2);
        boardButtons[7][2] = findViewById(R.id.r8c3);
        boardButtons[7][3] = findViewById(R.id.r8c4);
        boardButtons[7][4] = findViewById(R.id.r8c5);
        boardButtons[7][5] = findViewById(R.id.r8c6);
        boardButtons[7][6] = findViewById(R.id.r8c7);

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
            if (board8x7[row][col] == 0) {
                if (p1Move) {
                    board8x7[row][col] = 1; // Player 1's move
                } else {
                    board8x7[row][col] = 2; // Player 2's move
                }

                updateBoardButtons(row, col);

                if (checkWin(row, col)) {
                    stopGame();
                    updatePlayedCount(); // Increment played count when game ends
                    updateWinLossCounts(p1Move); // Update win/loss counts
                    return;
                }

                if (checkDraw()) {
                    stopGame();
                    updatePlayedCount(); // Increment played count when game ends

                    //Open Draws activity
                    Intent intent = new Intent(this, Draws.class);
                    startActivity(intent);
                    return;
                }

                p1Move = !p1Move;
                break;
            }
        }
    }

    private void updateBoardButtons(int row, int col) {
        Button btn = findButtonByRowCol(row, col);

        if (p1Move) {
            btn.setBackgroundResource(chosenColour1);
        } else {
            btn.setBackgroundResource(chosenColour2);
        }
    }

    private Button findButtonByRowCol(int row, int col) {
        return boardButtons[row][col];
    }

    public boolean checkDirec(int row, int col, int rowDirec, int colDirec, int player) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int r = row + i * rowDirec;
            int c = col + i * colDirec;
            if (r >= 0 && r < rows && c >= 0 && c < cols && board8x7[r][c] == player) {
                count++;
            } else {
                break;
            }
        }
        return count >= 4;
    }

    public boolean checkWin(int row, int col) {
        int player = board8x7[row][col];
        return checkDirec(row, col, 0, 1, player) || // Horizontal
                checkDirec(row, col, 1, 0, player) || // Vertical
                checkDirec(row, col, 1, 1, player) || // Diagonal /
                checkDirec(row, col, 1, -1, player);  // Diagonal \
    }

    public boolean checkDraw() {
        for (int row = 0; row < board8x7.length; row++) {
            for (int col = 0; col < board8x7[row].length; col++) {
                if (board8x7[row][col] == 0) {
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

    private void updatePlayedCount() {
        played++; // Increment played count
    }

    private void updateWinLossCounts(boolean p1Move) {
        if (p1Move) {
            // Player 1 won
            player1Win++;
            player2Lose++;
        } else {
            // Player 2 won
            player2Win++;
            player1Lose++;
        }

        // Start the Wins activity and pass data
        Intent intent = new Intent(this, Wins.class);
        intent.putExtra("WINNER_NAME", p1Move ? player1Name : player2Name);
        intent.putExtra("WINNER_AVATAR", p1Move ? chosenAvatar1 : chosenAvatar2);
        startActivity(intent);
    }
}
