package com.example.a0624;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private ImageView questionImage;
    private EditText editText;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    private int quizCountEnd = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"画像名", "正解ID"}
            {"apple", "1"},
            {"banana", "2"},
            {"durian", "3"},
            {"grape", "4"},
            {"ichigo", "5"},
            {"kaki", "6"},
            {"kiwi", "7"},
            {"lemon", "8"},
            {"melon", "9"},
            {"mikan", "10"},
            {"momo", "11"},
            {"nashi", "12"},
            {"pineapple", "13"},
            {"sakuranbo", "14"},
            {"starfruit", "15"},
            {"suika", "16"},
            {"younashi", "17"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        editText = findViewById(R.id.editText);

        // Enterキーを押した時に回答チェック
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        checkAnswer();
                    }
                }
                return false;
            }
        });

        // クイズデータquizDataからクイズ出題用のquizArrayを作成する
        for (int i = 0; i < quizData.length; i++) {
            // 新しいArrayListを準備
            ArrayList<String> tmpArray = new ArrayList<>();

            // クイズデータを追加
            tmpArray.add(quizData[i][0]); // 画像名
            tmpArray.add(quizData[i][1]); // 正解

            // tmpArrayをquizArrayに追加する
            quizArray.add(tmpArray);
        }
        showNextQuiz();
    }

    public void showNextQuiz() {
        // クイズカウントラベルを更新
        countLabel.setText("Q" + quizCount);

        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz;

        // randomNumを使って、quizArrayからクイズを一つ取り出す
        quiz = quizArray.get(randomNum);
        // 画像をセットする
        questionImage.setImageResource(
                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName())
        );

        // 正解をrightAnswerにセット
        rightAnswer = quiz.get(1);

        // このクイズをquizArrayから削除
        quizArray.remove(randomNum);
        quizCountEnd = quizCountEnd + 1;
    }

    public void checkAnswer() {
        // ユーザーが入力したテキストを取得
        String answer = editText.getText().toString();
        String alertTitle;

        // 正解フラグ
        Integer answerflg = 0;

        //　答えのパターンの照合
        switch (rightAnswer){
            case "1":
                if (answer.equals("りんご")||answer.equals("リンゴ")||answer.equals("林檎")||answer.equals("アップル")||answer.equals("apple")) {
                    answerflg = 1;
                }
            case "2":
                if (answer.equals("ばなな")||answer.equals("バナナ")||answer.equals("banana")) {
                    answerflg = 1;
                }
            case "3":
                if (answer.equals("ドリアン")||answer.equals("dorian")) {
                    answerflg = 1;
                }
            case "4":
                if (answer.equals("ぶどう")||answer.equals("ブドウ")||answer.equals("グレープ")||answer.equals("grape")) {
                    answerflg = 1;
                }
            case "5":
                if (answer.equals("いちご")||answer.equals("イチゴ")||answer.equals("苺")||answer.equals("ストロベリー")||answer.equals("strawberry")) {
                    answerflg = 1;
                }
            case "6":
                if (answer.equals("かき")||answer.equals("カキ")||answer.equals("柿")||answer.equals("kaki")) {
                    answerflg = 1;
                }
            case "7":
                if (answer.equals("キウイ")||answer.equals("キウイフルーツ")||answer.equals("kiwi")) {
                    answerflg = 1;
                }
            case "8":
                if (answer.equals("レモン")||answer.equals("檸檬")||answer.equals("lemon")) {
                    answerflg = 1;
                }
            case "9":
                if (answer.equals("メロン")||answer.equals("melon")) {
                    answerflg = 1;
                }
            case "10":
                if (answer.equals("みかん")||answer.equals("ミカン")||answer.equals("蜜柑")||answer.equals("mikan")) {
                    answerflg = 1;
                }
            case "11":
                if (answer.equals("もも")||answer.equals("モモ")||answer.equals("桃")||answer.equals("ピーチ")||answer.equals("peach")) {
                    answerflg = 1;
                }
            case "12":
                if (answer.equals("なし")||answer.equals("ナシ")||answer.equals("梨")||answer.equals("pear")) {
                    answerflg = 1;
                }
            case "13":
                if (answer.equals("さくらんぼ")||answer.equals("サクランボ")||answer.equals("チェリー")||answer.equals("cherry")) {
                    answerflg = 1;
                }
            case "14":
                if (answer.equals("パイナップル")||answer.equals("pineapple")) {
                    answerflg = 1;
                }
            case "15":
                if (answer.equals("スターフルーツ")||answer.equals("starfruit")) {
                    answerflg = 1;
                }
            case "16":
                if (answer.equals("すいか")||answer.equals("スイカ")||answer.equals("西瓜")||answer.equals("watermelon")) {
                    answerflg = 1;
                }
            case "17":
                if (answer.equals("洋梨")||answer.equals("洋ナシ")||answer.equals("洋なし")||answer.equals("pear")) {
                    answerflg = 1;
                }
        }

        // if (answer.equals(rightAnswer)) {
        if (answerflg == 1) {
            // 正解
            alertTitle = "正解！";
            rightAnswerCount++;
        } else {
            // 不正解
            alertTitle = "不正解...";
        }

        // ダイアログ作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        // builder.setMessage("答え：" + answer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editText.setText("");

                //if (quizArray.size() < 1) {
                if (quizCount == 5) {
                    // 5問を出題したら結果を表示
                    showResult();
                } else {
                    // 次の問題を出題
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("結果");
        builder.setMessage("5問中" + rightAnswerCount + "問正解");
        builder.setPositiveButton("もう一度", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("終了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }
}