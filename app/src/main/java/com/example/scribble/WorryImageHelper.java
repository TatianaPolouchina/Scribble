package com.example.scribble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/***
 * Class to provide random Worry images
 */
//TODO: comment all classes
//TODO: comment class
public class WorryImageHelper {
    private final List<WorryImage> imageList;

    public WorryImageHelper() {
        WorryImage img1 = new WorryImage(R.drawable.worry_1_red, R.drawable.worry_1_finished_red);
        WorryImage img2 = new WorryImage(R.drawable.worry_1_org, R.drawable.worry_1_finished_org);
        WorryImage img3 = new WorryImage(R.drawable.worry_1_yel, R.drawable.worry_1_finished_yel);
        WorryImage img4 = new WorryImage(R.drawable.worry_1_grn, R.drawable.worry_1_finished_grn);
        WorryImage img5 = new WorryImage(R.drawable.worry_1_trq, R.drawable.worry_1_finished_trq);
        WorryImage img6 = new WorryImage(R.drawable.worry_1_blu, R.drawable.worry_1_finished_blu);

        WorryImage img7 = new WorryImage(R.drawable.worry_2_red, R.drawable.worry_2_finished_red);
        WorryImage img8 = new WorryImage(R.drawable.worry_2_org, R.drawable.worry_2_finished_org);
        WorryImage img9 = new WorryImage(R.drawable.worry_2_yel, R.drawable.worry_2_finished_yel);
        WorryImage img10 = new WorryImage(R.drawable.worry_2_grn, R.drawable.worry_2_finished_grn);
        WorryImage img11 = new WorryImage(R.drawable.worry_2_trq, R.drawable.worry_2_finished_trq);
        WorryImage img12 = new WorryImage(R.drawable.worry_2_blu, R.drawable.worry_2_finished_blu);

        WorryImage img13 = new WorryImage(R.drawable.worry_3_red, R.drawable.worry_3_finished_red);
        WorryImage img14 = new WorryImage(R.drawable.worry_3_org, R.drawable.worry_3_finished_org);
        WorryImage img15 = new WorryImage(R.drawable.worry_3_yel, R.drawable.worry_3_finished_yel);
        WorryImage img16 = new WorryImage(R.drawable.worry_3_grn, R.drawable.worry_3_finished_grn);
        WorryImage img17 = new WorryImage(R.drawable.worry_3_trq, R.drawable.worry_3_finished_trq);
        WorryImage img18 = new WorryImage(R.drawable.worry_3_blu, R.drawable.worry_3_finished_blu);

        WorryImage img19 = new WorryImage(R.drawable.worry_4_red, R.drawable.worry_4_finished_red);
        WorryImage img20 = new WorryImage(R.drawable.worry_4_org, R.drawable.worry_4_finished_org);
        WorryImage img21 = new WorryImage(R.drawable.worry_4_yel, R.drawable.worry_4_finished_yel);
        WorryImage img22 = new WorryImage(R.drawable.worry_4_grn, R.drawable.worry_4_finished_grn);
        WorryImage img23 = new WorryImage(R.drawable.worry_4_trq, R.drawable.worry_4_finished_trq);
        WorryImage img24 = new WorryImage(R.drawable.worry_4_blu, R.drawable.worry_4_finished_blu);

        WorryImage img25 = new WorryImage(R.drawable.worry_5_red, R.drawable.worry_5_finished_red);
        WorryImage img26 = new WorryImage(R.drawable.worry_5_org, R.drawable.worry_5_finished_org);
        WorryImage img27 = new WorryImage(R.drawable.worry_5_yel, R.drawable.worry_5_finished_yel);
        WorryImage img28 = new WorryImage(R.drawable.worry_5_grn, R.drawable.worry_5_finished_grn);
        WorryImage img29 = new WorryImage(R.drawable.worry_5_trq, R.drawable.worry_5_finished_trq);
        WorryImage img30 = new WorryImage(R.drawable.worry_5_blu, R.drawable.worry_5_finished_blu);

        WorryImage img31 = new WorryImage(R.drawable.worry_6_red, R.drawable.worry_6_finished_red);
        WorryImage img32 = new WorryImage(R.drawable.worry_6_org, R.drawable.worry_6_finished_org);
        WorryImage img33 = new WorryImage(R.drawable.worry_6_yel, R.drawable.worry_6_finished_yel);
        WorryImage img34 = new WorryImage(R.drawable.worry_6_grn, R.drawable.worry_6_finished_grn);
        WorryImage img35 = new WorryImage(R.drawable.worry_6_trq, R.drawable.worry_6_finished_trq);
        WorryImage img36 = new WorryImage(R.drawable.worry_6_blu, R.drawable.worry_6_finished_blu);

        WorryImage img37 = new WorryImage(R.drawable.worry_7_red, R.drawable.worry_7_finished_red);
        WorryImage img38 = new WorryImage(R.drawable.worry_7_org, R.drawable.worry_7_finished_org);
        WorryImage img39 = new WorryImage(R.drawable.worry_7_yel, R.drawable.worry_7_finished_yel);
        WorryImage img40 = new WorryImage(R.drawable.worry_7_grn, R.drawable.worry_7_finished_grn);
        WorryImage img41 = new WorryImage(R.drawable.worry_7_trq, R.drawable.worry_7_finished_trq);
        WorryImage img42 = new WorryImage(R.drawable.worry_7_blu, R.drawable.worry_7_finished_blu);

        WorryImage img43 = new WorryImage(R.drawable.worry_8_red, R.drawable.worry_8_finished_red);
        WorryImage img44 = new WorryImage(R.drawable.worry_8_org, R.drawable.worry_8_finished_org);
        WorryImage img45 = new WorryImage(R.drawable.worry_8_yel, R.drawable.worry_8_finished_yel);
        WorryImage img46 = new WorryImage(R.drawable.worry_8_grn, R.drawable.worry_8_finished_grn);
        WorryImage img47 = new WorryImage(R.drawable.worry_8_trq, R.drawable.worry_8_finished_trq);
        WorryImage img48 = new WorryImage(R.drawable.worry_8_blu, R.drawable.worry_8_finished_blu);

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
