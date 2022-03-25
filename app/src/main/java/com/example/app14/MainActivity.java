package com.example.app14;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.*;

import java.util.Random;



/// Global Varaibles ////
class Global{
    public static float val;
    public static int x_pos;
    public static int y_pos;
    public static int counter;

}
/// Getting XML to connect with Java ////
public class MainActivity extends AppCompatActivity {
    //It executes when the program starts
        ObjectAnimator objectAnimator;
        ImageView imageCard, red_car;
        TextView txt, txt2, TextViewRoll;
        Button btn_display, btn_hide, btn_about, btn_play, btn_pause, btn_roll;
        MediaPlayer mp;
        EditText edtTxtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Global.val = 0;
        Global.x_pos = 0;
        Global.y_pos = 9;
        Global.counter = 0;

        /// Defining visual elements ///
        txt = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textview3);
        imageCard = (ImageView) findViewById(R.id.ImageCard);
        TextViewRoll = findViewById(R.id.textView2);
        btn_display = findViewById(R.id.button2);
        btn_about = findViewById(R.id.btn_about);
        btn_hide = findViewById(R.id.button3);
        btn_play = (Button)findViewById(R.id.button_play);
        btn_pause = (Button)findViewById(R.id.button_pause);
        btn_roll = findViewById(R.id.rand_int_btn);
        edtTxtName = findViewById(R.id.edtTxtName);
        red_car =  findViewById(R.id.red_car);

        // Creating audio player   ////
        mp = new MediaPlayer();
        MainActivity.this.TextViewRoll.setText("Welcome to Rockets and Dragons, click on "+'\u0022'+"About App"+'\u0022'+ " to find out more info");


        imageCard.setVisibility(View.GONE);
        btn_play.setVisibility(View.GONE);
        btn_pause.setVisibility(View.GONE);
        btn_display.setVisibility(INVISIBLE);
        btn_hide.setVisibility(INVISIBLE);
        txt2.setVisibility(INVISIBLE);
        btn_pause.setVisibility(INVISIBLE);



        //// About Me Button /////
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCard.setImageResource(R.drawable.about_me);
                imageCard.setVisibility(View.VISIBLE);
                btn_about.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageCard.setVisibility(INVISIBLE);
                    }
                });
            }
        });

        //// Roll Dice Button ////
        btn_roll.setOnClickListener(new View.OnClickListener() {
            /// Car Animation ////
            public void move_car(int x, int y, int cnt) {
                AnimatorSet animator = new AnimatorSet();
                animator.setStartDelay(cnt* 1200);
                animator.playSequentially(
                        ObjectAnimator.ofFloat(red_car, "x", (x * 155))
                                .setDuration(1000),
                        ObjectAnimator.ofFloat(red_car, "y", (y * 150))
                                .setDuration(1000)
                );
                animator.start();
            }

            @SuppressLint("SetTextI18n")
            @Override


            //// Random Number Generator  (Dice)////
            public void onClick(View v) {
                Random random = new Random();
                int roll = random.nextInt(2);
                while (roll == 0)
                    roll = random.nextInt(2);
                if (Global.y_pos % 2 == 1)
                    Global.x_pos += roll;
                else
                    Global.x_pos -= roll;

                if (Global.x_pos > 9) {
                    Global.y_pos--;
                    Global.x_pos = (9 - (Global.x_pos - 9 - 1));
                }

                if (Global.x_pos < 0) {
                    Global.y_pos--;
                    Global.x_pos = Math.abs(Global.x_pos) - 1;

                }
//                if(Global.y_pos % 2 == 1)
//                    Global.val =  (Global.x_pos*155);
//                else
//                    Global.val = (Global.x_pos*155);




                MainActivity.this.TextViewRoll.setText("Hi "+ edtTxtName.getText()+", you rolled: " +Integer.toString(roll));
                edtTxtName.setVisibility(INVISIBLE);
                move_car(Global.x_pos, Global.y_pos,Global.counter);
                Global.counter++;


                /// BE KIND ONLINE  ///
                if (Global.x_pos == 2 && Global.y_pos == 9) {
                    Global.x_pos = 3;
                    Global.y_pos = 7;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);


                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);

                    //// Setting Image Resource ////
                    imageCard.setImageResource(R.drawable.card_be_kind_online);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                            btn_display.setVisibility(View.INVISIBLE);
                            btn_roll.setVisibility(View.INVISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                            btn_roll.setVisibility(View.VISIBLE);
                            btn_hide.setVisibility(INVISIBLE);
                        }
                    });
                }


                ///BALANCE//
                if (Global.x_pos == 1 && Global.y_pos == 8) {
                    Global.x_pos = 0;
                    Global.y_pos = 9;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);


                    //// Setting Image Resource ////
                    imageCard.setImageResource(R.drawable.card_balance);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);

                        }
                    });


                }

                ///// FOREVER   ////
                if (Global.x_pos == 4 && Global.y_pos == 7) {
                    Global.x_pos = 3;
                    Global.y_pos = 5;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);



                    //// Setting Image Resource ////
                    imageCard.setImageResource(R.drawable.card_forever);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }


                ///// FUTURE  //////
                if (Global.x_pos == 5 && Global.y_pos == 7) {
                    Global.x_pos = 7;
                    Global.y_pos = 9;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);

                    //// Setting Image Resource ////
                    imageCard.setImageResource(R.drawable.card_future);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }
                //// ONLINE CHAT  /////
                if (Global.x_pos == 7 && Global.y_pos == 7) {
                    Global.x_pos = 6;
                    Global.y_pos = 5;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_online_chat);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }

                ///// TROLLING  /////
                if (Global.x_pos == 9 && Global.y_pos == 7) {
                    Global.x_pos = 8;
                    Global.y_pos = 8;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_trolling);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }
                ///// BE KIND ONLINE  ////
                if (Global.x_pos == 2 && Global.y_pos == 9) {
                    Global.x_pos = 3;
                    Global.y_pos = 7;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_be_kind_online);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                        }
                    });

                }

                ///// COOKIES  /////
                if (Global.x_pos == 0 && Global.y_pos == 5) {
                    Global.x_pos = 1;
                    Global.y_pos = 3;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_cookies);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }
                ///// REVIEW SOCIAL MEDIA   /////
                if (Global.x_pos == 8 && Global.y_pos == 4) {
                    Global.x_pos = 9;
                    Global.y_pos = 2;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_review_social_media);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }

                ///// PERSONAL INFORMATION  /////
                if (Global.x_pos == 0 && Global.y_pos == 3) {
                    Global.x_pos = 0;
                    Global.y_pos = 4;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_personal_information);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }

                ///// SLEEP   /////
                if (Global.x_pos == 3 && Global.y_pos == 3) {
                    Global.x_pos = 1;
                    Global.y_pos = 7;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_sleep);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }

                ///// Spam Check   /////
                if (Global.x_pos == 5 && Global.y_pos == 3) {
                    Global.x_pos = 6;
                    Global.y_pos = 4;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_spam_check);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }

                ///// CONNECTED   /////
                if (Global.x_pos == 2 && Global.y_pos == 2) {
                    Global.x_pos = 1;
                    Global.y_pos = 0;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);



                    imageCard.setImageResource(R.drawable.card_connected);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }


                ///// €€€€€€€€€   /////
                if (Global.x_pos == 1 && Global.y_pos == 1) {
                    Global.x_pos = 0;
                    Global.y_pos = 2;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);



//                    imageCard.setImageResource(R.drawable.card_euro);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }

                ///// DIGITAL AGE   /////
                if (Global.x_pos == 9 && Global.y_pos == 0) {
                    Global.x_pos = 7;
                    Global.y_pos = 2;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);

                    btn_play.setVisibility(View.VISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    btn_display.setVisibility(View.VISIBLE);
                    btn_hide.setVisibility(View.VISIBLE);




                    imageCard.setImageResource(R.drawable.card_digital_age);
                    btn_display.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(View.VISIBLE);
                        }
                    });
                    btn_hide.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCard.setVisibility(INVISIBLE);
                            imageCard.setImageResource(0);
                        }
                    });

                }



                ///// 100 !!!! //////
                if (Global.x_pos == 0 && Global.y_pos == 5) {
                    txt2.setVisibility(View.VISIBLE);
                    txt2.setText("Well done you Have completed the game, Game will restart now");
                    Global.x_pos = 0;
                    Global.y_pos = 0;
                    move_car(Global.x_pos,Global.y_pos, Global.counter);


                }


            }

            ;


        });

        //methods


    }

    private void move_car(int x_pos, int y_pos, int counter) {
        AnimatorSet animator = new AnimatorSet();
        animator.setStartDelay(counter* 150);
        animator.playSequentially(
                ObjectAnimator.ofFloat(red_car, "x", (x_pos * 155))
                        .setDuration(1000),
                ObjectAnimator.ofFloat(red_car, "y", (y_pos * 150))
                        .setDuration(1000)
        );
//                animator.setEndDelay(2000);
        animator.start();
    }


    /// Assigning correct audio track  ////
    public void startMusic(View v){
        if (Global.x_pos == 3 && Global.y_pos == 7) {
//            mp.reset();
            mp = MediaPlayer.create(this, R.raw.be_kind_online);
            mp.start();
//            if (!mp.isPlaying()){
//                mp.reset();
//            }

        }
        else if (Global.x_pos == 3 && Global.y_pos == 5) {
            mp.reset();
            mp = MediaPlayer.create(this, R.raw.forever);
            mp.start();
    }
        else if (Global.x_pos ==0  && Global.y_pos == 9) {
            mp = MediaPlayer.create(this, R.raw.balance);
            mp.start();
        }
        else if (Global.x_pos == 7 && Global.y_pos == 9) {
            mp = MediaPlayer.create(this, R.raw.future);
            mp.start();
        }
        else if (Global.x_pos == 6 && Global.y_pos == 5) {
            mp = MediaPlayer.create(this, R.raw.online_chat);
            mp.start();
        }
        else if (Global.x_pos == 1 && Global.y_pos == 3) {
            mp = MediaPlayer.create(this, R.raw.cookies);
            mp.start();
        }
        else if (Global.x_pos == 0 && Global.y_pos == 4) {
            mp = MediaPlayer.create(this, R.raw.personal_information);
            mp.start();
        }
        else if (Global.x_pos == 1 && Global.y_pos == 7) {
            mp = MediaPlayer.create(this, R.raw.sleep);
            mp.start();
        }
        else if (Global.x_pos == 6 && Global.y_pos == 4) {
            mp = MediaPlayer.create(this, R.raw.spam_check);
            mp.start();
        }
        else if (Global.x_pos == 1 && Global.y_pos == 0) {
            mp = MediaPlayer.create(this, R.raw.connected);
            mp.start();
        }
        else if (Global.x_pos == 4 && Global.y_pos == 0) {
            mp = MediaPlayer.create(this, R.raw.age_classification);
            mp.start();
        }
        else if (Global.x_pos == 7 && Global.y_pos == 2) {
            mp = MediaPlayer.create(this, R.raw.digital_age);
            mp.start();
        }


    }

    public void audioPause(View v){
        mp.pause();

    }
}