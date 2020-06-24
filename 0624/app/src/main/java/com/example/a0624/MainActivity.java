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
            // {"画像名", "正解"}
            {"apple", "リンゴ"},
            {"banana", "バナナ"},
            {"durian", "ドリアン"},
            {"grape", "ぶどう"},
            {"ichigo", "いちご"},
            {"kaki", "かき"},
            {"kiwi", "キウイ"},
            {"lemon", "レモン"},
            {"melon", "メロン"},
            {"mikan", "ミカン"},
            {"momo", "もも"},
            {"nashi", "なし"},
            {"pineapple", "パイナップル"},
            {"sakuranbo", "さくらんぼ"},
            {"starfruit", "スターフルーツ"},
            {"suika", "スイカ"},
            {"younashi", "洋なし"}
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
        if (answer.equals(rightAnswer)) {
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
        builder.setMessage("答え：" + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editText.setText("");

                //if (quizArray.size() < 1) {
                if (quizCount == 5) {
                    // 全てのクイズを出題したら結果を表示
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