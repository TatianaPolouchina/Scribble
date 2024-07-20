package com.example.scribble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//TODO: comment class
public class WorryImageHelper {
    private List<WorryImage> imageList;
    private final WorryImage img1;
    private final WorryImage img2;
    private final WorryImage img3;
    private final WorryImage img4;
    private final WorryImage img5;
    private final WorryImage img6;
    private final WorryImage img7;
    private final WorryImage img8;
    private final WorryImage img9;
    private final WorryImage img10;
    private final WorryImage img11;
    private final WorryImage img12;
    private final WorryImage img13;
    private final WorryImage img14;
    private final WorryImage img15;
    private final WorryImage img16;
    private final WorryImage img17;
    private final WorryImage img18;
    private final WorryImage img19;
    private final WorryImage img20;
    private final WorryImage img21;
    private final WorryImage img22;
    private final WorryImage img23;
    private final WorryImage img24;
    private final WorryImage img25;
    private final WorryImage img26;
    private final WorryImage img27;
    private final WorryImage img28;
    private final WorryImage img29;
    private final WorryImage img30;
    private final WorryImage img31;
    private final WorryImage img32;
    private final WorryImage img33;
    private final WorryImage img34;
    private final WorryImage img35;
    private final WorryImage img36;
    private final WorryImage img37;
    private final WorryImage img38;
    private final WorryImage img39;
    private final WorryImage img40;
    private final WorryImage img41;
    private final WorryImage img42;
    private final WorryImage img43;
    private final WorryImage img44;
    private final WorryImage img45;
    private final WorryImage img46;
    private final WorryImage img47;
    private final WorryImage img48;

    public WorryImageHelper() {
        img1 = new WorryImage(R.drawable.worry_1_red, R.drawable.worry_1_finished_red);
        img2 = new WorryImage(R.drawable.worry_1_org, R.drawable.worry_1_finished_org);
        img3 = new WorryImage(R.drawable.worry_1_yel, R.drawable.worry_1_finished_yel);
        img4 = new WorryImage(R.drawable.worry_1_grn, R.drawable.worry_1_finished_grn);
        img5 = new WorryImage(R.drawable.worry_1_trq, R.drawable.worry_1_finished_trq);
        img6 = new WorryImage(R.drawable.worry_1_blu, R.drawable.worry_1_finished_blu);

        img7 = new WorryImage(R.drawable.worry_2_red, R.drawable.worry_2_finished_red);
        img8 = new WorryImage(R.drawable.worry_2_org, R.drawable.worry_2_finished_org);
        img9 = new WorryImage(R.drawable.worry_2_yel, R.drawable.worry_2_finished_yel);
        img10 = new WorryImage(R.drawable.worry_2_grn, R.drawable.worry_2_finished_grn);
        img11 = new WorryImage(R.drawable.worry_2_trq, R.drawable.worry_2_finished_trq);
        img12 = new WorryImage(R.drawable.worry_2_blu, R.drawable.worry_2_finished_blu);

        img13 = new WorryImage(R.drawable.worry_3_red, R.drawable.worry_3_finished_red);
        img14 = new WorryImage(R.drawable.worry_3_org, R.drawable.worry_3_finished_org);
        img15 = new WorryImage(R.drawable.worry_3_yel, R.drawable.worry_3_finished_yel);
        img16 = new WorryImage(R.drawable.worry_3_grn, R.drawable.worry_3_finished_grn);
        img17 = new WorryImage(R.drawable.worry_3_trq, R.drawable.worry_3_finished_trq);
        img18 = new WorryImage(R.drawable.worry_3_blu, R.drawable.worry_3_finished_blu);

        img19 = new WorryImage(R.drawable.worry_4_red, R.drawable.worry_4_finished_red);
        img20 = new WorryImage(R.drawable.worry_4_org, R.drawable.worry_4_finished_org);
        img21 = new WorryImage(R.drawable.worry_4_yel, R.drawable.worry_4_finished_yel);
        img22 = new WorryImage(R.drawable.worry_4_grn, R.drawable.worry_4_finished_grn);
        img23 = new WorryImage(R.drawable.worry_4_trq, R.drawable.worry_4_finished_trq);
        img24 = new WorryImage(R.drawable.worry_4_blu, R.drawable.worry_4_finished_blu);

        img25 = new WorryImage(R.drawable.worry_5_red, R.drawable.worry_5_finished_red);
        img26 = new WorryImage(R.drawable.worry_5_org, R.drawable.worry_5_finished_org);
        img27 = new WorryImage(R.drawable.worry_5_yel, R.drawable.worry_5_finished_yel);
        img28 = new WorryImage(R.drawable.worry_5_grn, R.drawable.worry_5_finished_grn);
        img29 = new WorryImage(R.drawable.worry_5_trq, R.drawable.worry_5_finished_trq);
        img30 = new WorryImage(R.drawable.worry_5_blu, R.drawable.worry_5_finished_blu);

        img31 = new WorryImage(R.drawable.worry_6_red, R.drawable.worry_6_finished_red);
        img32 = new WorryImage(R.drawable.worry_6_org, R.drawable.worry_6_finished_org);
        img33 = new WorryImage(R.drawable.worry_6_yel, R.drawable.worry_6_finished_yel);
        img34 = new WorryImage(R.drawable.worry_6_grn, R.drawable.worry_6_finished_grn);
        img35 = new WorryImage(R.drawable.worry_6_trq, R.drawable.worry_6_finished_trq);
        img36 = new WorryImage(R.drawable.worry_6_blu, R.drawable.worry_6_finished_blu);

        img37 = new WorryImage(R.drawable.worry_7_red, R.drawable.worry_7_finished_red);
        img38 = new WorryImage(R.drawable.worry_7_org, R.drawable.worry_7_finished_org);
        img39 = new WorryImage(R.drawable.worry_7_yel, R.drawable.worry_7_finished_yel);
        img40 = new WorryImage(R.drawable.worry_7_grn, R.drawable.worry_7_finished_grn);
        img41 = new WorryImage(R.drawable.worry_7_trq, R.drawable.worry_7_finished_trq);
        img42 = new WorryImage(R.drawable.worry_7_blu, R.drawable.worry_7_finished_blu);

        img43 = new WorryImage(R.drawable.worry_8_red, R.drawable.worry_8_finished_red);
        img44 = new WorryImage(R.drawable.worry_8_org, R.drawable.worry_8_finished_org);
        img45 = new WorryImage(R.drawable.worry_8_yel, R.drawable.worry_8_finished_yel);
        img46 = new WorryImage(R.drawable.worry_8_grn, R.drawable.worry_8_finished_grn);
        img47 = new WorryImage(R.drawable.worry_8_trq, R.drawable.worry_8_finished_trq);
        img48 = new WorryImage(R.drawable.worry_8_blu, R.drawable.worry_8_finished_blu);

        imageList = new ArrayList<>(Arrays.asList(img1, img2, img3, img4, img5, img6, img7,
                img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19,
                img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31,
                img32, img33, img34, img35, img36, img37, img38, img39, img40, img41, img42,
                img43, img44, img45, img46, img47, img48));
    }

    /***
     * Returns a random WorryImage pair
     *
     * @return WorryImage pairing
     */
    public WorryImage getRandomImage() {
        Random random = new Random();
        int randomInt = random.nextInt(imageList.size());
        return imageList.get(randomInt);
    }

}
