package com.example.scribble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/***
 * Class to provide random Worry image pairs
 */
public class WorryImageHelper {
    private final HashMap<String, WorryImage> map;
    private List<String> unchosenKeys;

    public WorryImageHelper() {
        map = new HashMap<>();
        populateMap();
        refreshKeySet();
    }

    /***
     * Populates the map with all possible WorryImages
     */
    private void populateMap() {
        map.put("worry1R", new WorryImage(R.drawable.worry_1_red, R.drawable.worry_1_finished_red));
        map.put("worry1O", new WorryImage(R.drawable.worry_1_org, R.drawable.worry_1_finished_org));
        map.put("worry1Y", new WorryImage(R.drawable.worry_1_yel, R.drawable.worry_1_finished_yel));
        map.put("worry1G", new WorryImage(R.drawable.worry_1_grn, R.drawable.worry_1_finished_grn));
        map.put("worry1B", new WorryImage(R.drawable.worry_1_blu, R.drawable.worry_1_finished_blu));
        map.put("worry1T", new WorryImage(R.drawable.worry_1_trq, R.drawable.worry_1_finished_yel));
        map.put("worry2R", new WorryImage(R.drawable.worry_2_red, R.drawable.worry_2_finished_red));
        map.put("worry2O", new WorryImage(R.drawable.worry_2_org, R.drawable.worry_2_finished_org));
        map.put("worry2Y", new WorryImage(R.drawable.worry_2_yel, R.drawable.worry_2_finished_yel));
        map.put("worry2G", new WorryImage(R.drawable.worry_2_grn, R.drawable.worry_2_finished_grn));
        map.put("worry2B", new WorryImage(R.drawable.worry_2_blu, R.drawable.worry_2_finished_blu));
        map.put("worry2T", new WorryImage(R.drawable.worry_2_trq, R.drawable.worry_2_finished_yel));
        map.put("worry3R", new WorryImage(R.drawable.worry_3_red, R.drawable.worry_3_finished_red));
        map.put("worry3O", new WorryImage(R.drawable.worry_3_org, R.drawable.worry_3_finished_org));
        map.put("worry3Y", new WorryImage(R.drawable.worry_3_yel, R.drawable.worry_3_finished_yel));
        map.put("worry3G", new WorryImage(R.drawable.worry_3_grn, R.drawable.worry_3_finished_grn));
        map.put("worry3B", new WorryImage(R.drawable.worry_3_blu, R.drawable.worry_3_finished_blu));
        map.put("worry3T", new WorryImage(R.drawable.worry_3_trq, R.drawable.worry_3_finished_yel));
        map.put("worry4R", new WorryImage(R.drawable.worry_4_red, R.drawable.worry_4_finished_red));
        map.put("worry4O", new WorryImage(R.drawable.worry_4_org, R.drawable.worry_4_finished_org));
        map.put("worry4Y", new WorryImage(R.drawable.worry_4_yel, R.drawable.worry_4_finished_yel));
        map.put("worry4G", new WorryImage(R.drawable.worry_4_grn, R.drawable.worry_4_finished_grn));
        map.put("worry4B", new WorryImage(R.drawable.worry_4_blu, R.drawable.worry_4_finished_blu));
        map.put("worry4T", new WorryImage(R.drawable.worry_4_trq, R.drawable.worry_4_finished_yel));
        map.put("worry5R", new WorryImage(R.drawable.worry_5_red, R.drawable.worry_5_finished_red));
        map.put("worry5O", new WorryImage(R.drawable.worry_5_org, R.drawable.worry_5_finished_org));
        map.put("worry5Y", new WorryImage(R.drawable.worry_5_yel, R.drawable.worry_5_finished_yel));
        map.put("worry5G", new WorryImage(R.drawable.worry_5_grn, R.drawable.worry_5_finished_grn));
        map.put("worry5B", new WorryImage(R.drawable.worry_5_blu, R.drawable.worry_5_finished_blu));
        map.put("worry5T", new WorryImage(R.drawable.worry_5_trq, R.drawable.worry_5_finished_yel));
        map.put("worry6R", new WorryImage(R.drawable.worry_6_red, R.drawable.worry_6_finished_red));
        map.put("worry6O", new WorryImage(R.drawable.worry_6_org, R.drawable.worry_6_finished_org));
        map.put("worry6Y", new WorryImage(R.drawable.worry_6_yel, R.drawable.worry_6_finished_yel));
        map.put("worry6G", new WorryImage(R.drawable.worry_6_grn, R.drawable.worry_6_finished_grn));
        map.put("worry6B", new WorryImage(R.drawable.worry_6_blu, R.drawable.worry_6_finished_blu));
        map.put("worry6T", new WorryImage(R.drawable.worry_6_trq, R.drawable.worry_6_finished_yel));
        map.put("worry7R", new WorryImage(R.drawable.worry_7_red, R.drawable.worry_7_finished_red));
        map.put("worry7O", new WorryImage(R.drawable.worry_7_org, R.drawable.worry_7_finished_org));
        map.put("worry7Y", new WorryImage(R.drawable.worry_7_yel, R.drawable.worry_7_finished_yel));
        map.put("worry7G", new WorryImage(R.drawable.worry_7_grn, R.drawable.worry_7_finished_grn));
        map.put("worry7B", new WorryImage(R.drawable.worry_7_blu, R.drawable.worry_7_finished_blu));
        map.put("worry7T", new WorryImage(R.drawable.worry_7_trq, R.drawable.worry_7_finished_yel));
        map.put("worry8R", new WorryImage(R.drawable.worry_8_red, R.drawable.worry_8_finished_red));
        map.put("worry8O", new WorryImage(R.drawable.worry_8_org, R.drawable.worry_8_finished_org));
        map.put("worry8Y", new WorryImage(R.drawable.worry_8_yel, R.drawable.worry_8_finished_yel));
        map.put("worry8G", new WorryImage(R.drawable.worry_8_grn, R.drawable.worry_8_finished_grn));
        map.put("worry8B", new WorryImage(R.drawable.worry_8_blu, R.drawable.worry_8_finished_blu));
        map.put("worry8T", new WorryImage(R.drawable.worry_8_trq, R.drawable.worry_8_finished_yel));
    }

    /***
     * Populates the unchosenKeys List with all possible keys
     */
    private void refreshKeySet() {
        unchosenKeys = new ArrayList<>(map.keySet());
    }

    /***
     * Returns a random key from the unchosenKeys list and removes it from unchosenKeys. If all
     * keys have been chosen, unchosenKeys is reset.
     *
     * @return random String key from unchosenKeys
     */
    public String getRandomKey() {
        if (unchosenKeys.isEmpty()) {
            refreshKeySet();
        }
        Random random = new Random();
        int randomInt = random.nextInt(unchosenKeys.size());
        String randomKey = unchosenKeys.get(randomInt);
        unchosenKeys.remove(randomInt);
        return randomKey;
    }

    /***
     * Returns the WorryImage associated with the key
     * @param key WorryImage's access key
     * @return WorryImage associated with key
     */
    public WorryImage getWorryImage(String key) {
        return map.get(key);
    }

    public List<String> getUnchosenKeys() {
        return unchosenKeys;
    }

    public void setUnchosenKeys(List<String> unchosenKeys) {
        this.unchosenKeys = unchosenKeys;
    }
}
