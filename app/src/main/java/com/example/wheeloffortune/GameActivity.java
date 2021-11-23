package com.example.wheeloffortune;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    String[] wheels = {"Синий", "Красный", "Черный"};
    ImageView imageViewWheel;
    ImageView imageViewFlecha;
    TextView textViewQuestion;
    TextView textViewPlayer1;
    TextView textViewPlayer2;
    TextView textViewPlayer3;
    MediaPlayer mp;
    EditText editText;


    Random random;
    int old_degree=0;
    int degree=0;
    int rnd;
    int counter=0;
    int counterPlayers=1;
    int player1Points;
    int player2Points;
    int player3Points;
    int player1Wins;
    int player2Wins;
    int player3Wins;

    Button buttonRoll;
    Button buttonAnswer;
    Button buttonValidySec;
    TextView textViewRoll;


    private  static  final  float FACTOR = 4.0f;

    private String[] numbers = {"600","0","400","700","600","X2","600","500","400","650","450","0","1000",
                                "500","350","550","750","600","500","350","400","X2","600","500","0","600",
                                "350","300","200","500","600","400","0","500","350","600","400","450","500","350",};

    private String[] QuestionsEasy = {"Как у западных и южных славян назывались селение, деревня, курень?",
            "В Швеции существует налог на собак, от которого многие пытаются уклониться. В налоговой инспекции в Стокгольме придумали забавный способ борьбы с неплательщиками: сотрудница службы Эльфрида Карлсон ходит по домам и делает это.",
            "Что использовали в Китае для глажки белья вместо утюга?",
            "По традиции в Китае муж может в одностороннем порядке объявить о расторжении брака по семейным обстоятельствам. Назовите одну из причин для этого.",
            "В Австралии на парковках возле некоторых торговых центров по ночам и вечерам включают классическую музыку, чтобы отпугнуть кого-то. Кого?",
            "Первый подобный музей появился в Париже до 1975 года. Экскурсии по нему проводились на лодке. Сейчас туристы осматривают его экспонаты со специальных решеток и пандусов. О каком музее идет речь?",
            "В 1931 году Аркадий Райкин сам придумал и произнес со сцены некое слово. Оно стремительно вошло в обиход — так стали называть несуразную легкую сумку.",
            "Герцогиня Англии XVIII века Сара Черчилль говорила: «Вы молоды, если вы еще способны…»",
            "Если скорость ветра тропического шторма превышает 60 км/ч, ему присваивают личное имя. Во времена Второй мировой войны американские синоптики начали давать ураганам имена кого?",
            "В словаре Владимира Ивановича Даля встречается старинное название барометра. Какое?",
            "Чтобы отпугнуть врагов, гуси могут грозно шипеть и больно щипаться. В Шотландии решили использовать эту особенность птиц и создали отряд гусей, который стал охранять завод. Какой?",
            "Ювелиры часто говорят, что бриллиантам необходимо это.",
            "Английский писатель Киплинг говорил: «Женская интуиция намного точнее, чем мужская…»\n",
            "Какого слова нет в языке народов Арктики?",
            "Соседи по улице знали Дмитрия Ивановича Менделеева как замечательного мастера по изготовлению чего?",
            "Польская журналистка и писательница Янина Ипохорская говорила: «Взгляд одной женщины на другую напоминает контроль…»",
            "Чтобы сделать комплимент женщине в Индии, нужно сравнить ее с коровой и слоном. Причем с коровой она должна быть схожа глазами, а со слоном — этим.",
            "Во время второй мировой войны этот предмет являлся символом единства у Норвежцев. В честь него даже построили памятник",
             "Так в старину называли сторожа городских ворот"};

    private String[] AnswersEasy = {"Жупа","Гавкает","Сковорода","Болтливость ","Подросток","Канализация","Авоська","Разочаровываться","Теща","Буревестник",
            "Алкогольный","Одиночество","Уверенность","Война", "Чемодан","Багаж","Походка","Скрепка","Вратарь"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Spinner spinner = findViewById(R.id.spinner);
        //mp = MediaPlayer.create(this, R.raw.roulette);

        imageViewWheel= findViewById(R.id.imageViewWheel);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, wheels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String) parent.getItemAtPosition(position);
                if(item=="Синий"){
                    imageViewWheel.setImageResource(R.drawable.wheel1);
                }
                else if(item=="Красный"){
                    imageViewWheel.setImageResource(R.drawable.wheel3);
                }
                else if(item=="Черный"){
                    imageViewWheel.setImageResource(R.drawable.wheel2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);



        random = new Random();

        buttonRoll = findViewById(R.id.buttonRoll);
        buttonValidySec=findViewById(R.id.buttonValidySec);
        textViewRoll = findViewById(R.id.textViewRoll);
        imageViewFlecha = findViewById(R.id.imageViewFlecha);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        editText = findViewById(R.id.editTextTextPersonName);
        buttonAnswer = findViewById(R.id.buttonAnswer);

    }


    public void buttonRoll_onClick(View view) {


            buttonRoll.setVisibility(View.GONE);
            textViewRoll.setVisibility(View.GONE);


            Bundle arguments = getIntent().getExtras();
            String difficulty = arguments.get("Difficulty").toString();

            old_degree = degree % 360;
            degree = random.nextInt(3600) + 720;

            RotateAnimation rotate = new RotateAnimation(old_degree, degree,
                    RotateAnimation.RELATIVE_TO_SELF,
                    0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(3600);
            rotate.setFillAfter(true);
            rotate.setInterpolator(new DecelerateInterpolator());
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Toast.makeText(GameActivity.this, getResult(360 - (degree % 360)), Toast.LENGTH_SHORT).show();

                    switch (counterPlayers){
                        case 1:
                            player1Points+= Integer.parseInt(getResult(360 - (degree % 360)));
                            break;
                        case 2:
                            player2Points+= Integer.parseInt(getResult(360 - (degree % 360)));
                            break;
                        case 3:
                            player3Points+= Integer.parseInt(getResult(360 - (degree % 360)));
                            break;
                    }

                    imageViewFlecha.setVisibility(View.GONE);
                    textViewQuestion.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    buttonAnswer.setVisibility(View.VISIBLE);

                    if (difficulty.equals("1")) {
                        textViewQuestion.setText(QuestionsEasy[counter]);
                    } else if (difficulty.equals("2")) {
                        rnd = random.nextInt(15) + 1;
                        textViewQuestion.setText("Сложность 2");
                    } else if (difficulty.equals("3")) {
                        rnd = random.nextInt(15) + 1;
                        textViewQuestion.setText("Сложность 3");
                    }

                }


                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            imageViewWheel.startAnimation(rotate);

            // mp.start();
    }

    private String getResult(int deegre) {
        String text="";

        int factor_x=1;
        int factor_y=3;
        for(int i=0;i<40    ;i++)
        {
            if(deegre >= (FACTOR * factor_x) && deegre < (FACTOR*factor_y))
            {
                text = numbers[i];
            }
            factor_x+=2;
            factor_y+=2;
        }
        if(deegre>=(FACTOR * 73 ) && deegre<368 || deegre >=0 && deegre< (FACTOR*1)) text= numbers[numbers.length-1];
        return text;
    }

    public void buttonAnswer_onClick(View view) {
        if(editText.getText().toString().equals(AnswersEasy[counter]))
        {
            textViewQuestion.setText("Поздравляем, вы ответили правильно и заработали "+getResult(360 -(degree%360))+" очков");

            buttonValidySec.setVisibility(View.VISIBLE);
            switch (counterPlayers){
                case 1:
                    player1Wins+=1 ;
                    break;
                case 2:
                    player2Wins+=1 ;
                    break;
                case 3:
                    player3Wins+=1 ;
                    break;
            }
            counter++;
        }
        else {
            textViewQuestion.setText("Ответ неправильный, ход переходит к другому игроку!");
            buttonValidySec.setVisibility(View.VISIBLE);
        }

    }

    public void buttonValidySec_onClick(View view) {
        if(counter<=14)
        {
        textViewPlayer1=findViewById(R.id.textViewPlayer1);
        textViewPlayer2=findViewById(R.id.textViewPlayer2);
        textViewPlayer3=findViewById(R.id.textViewPlayer3);
        switch (counterPlayers) {
            case 1:
                textViewPlayer2.setTypeface(null, Typeface.BOLD);
                textViewPlayer1.setTypeface(null, Typeface.NORMAL);
                buttonValidySec.setVisibility(View.GONE);
                counterPlayers++;
                buttonRoll.setVisibility(View.VISIBLE);
                textViewRoll.setVisibility(View.VISIBLE);
                imageViewFlecha.setVisibility(View.VISIBLE);
                textViewQuestion.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                buttonAnswer.setVisibility(View.GONE);
                break;
            case 2:
                textViewPlayer3.setTypeface(null, Typeface.BOLD);
                textViewPlayer2.setTypeface(null, Typeface.NORMAL);
                counterPlayers++;
                buttonValidySec.setVisibility(View.GONE);
                buttonRoll.setVisibility(View.VISIBLE);
                textViewRoll.setVisibility(View.VISIBLE);
                imageViewFlecha.setVisibility(View.VISIBLE);
                textViewQuestion.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                buttonAnswer.setVisibility(View.GONE);
                break;
            case 3:
                textViewPlayer1.setTypeface(null, Typeface.BOLD);
                textViewPlayer3.setTypeface(null, Typeface.NORMAL);
                counterPlayers = 1;
                buttonValidySec.setVisibility(View.GONE);
                buttonRoll.setVisibility(View.VISIBLE);
                textViewRoll.setVisibility(View.VISIBLE);
                imageViewFlecha.setVisibility(View.VISIBLE);
                textViewQuestion.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                buttonAnswer.setVisibility(View.GONE);
                break;
        }
        }
        else if(player1Wins==player2Wins&&player1Wins==player3Wins&&player3Wins==player2Wins)
        {
            textViewQuestion.setText("Увы, все проиграли!");
        }
        else if(player1Wins>player2Wins)
        {
            textViewQuestion.setText("Поздравляю, игрок 1 победил и заработал"+getResult(360 -(degree%360))+" очков");
        }
        else if(player2Wins>player3Wins)
        {
            textViewQuestion.setText("Поздравляю, игрок 2 победил и заработал"+getResult(360 -(degree%360))+" очков");
        }
        else {
            textViewQuestion.setText("Поздравляю, игрок 3 победил и заработал"+getResult(360 -(degree%360))+" очков");
        }

    }
}
