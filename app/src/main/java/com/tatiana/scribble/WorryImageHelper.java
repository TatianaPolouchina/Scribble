package com.tatiana.scribble;

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
        map.put("1R", new WorryImage(R.drawable.worry_1_red, R.drawable.worry_1_finished_red));
        map.put("1O", new WorryImage(R.drawable.worry_1_org, R.drawable.worry_1_finished_org));
        map.put("1Y", new WorryImage(R.drawable.worry_1_yel, R.drawable.worry_1_finished_yel));
        map.put("1G", new WorryImage(R.drawable.worry_1_grn, R.drawable.worry_1_finished_grn));
        map.put("1B", new WorryImage(R.drawable.worry_1_blu, R.drawable.worry_1_finished_blu));
        map.put("1T", new WorryImage(R.drawable.worry_1_trq, R.drawable.worry_1_finished_trq));
        map.put("2R", new WorryImage(R.drawable.worry_2_red, R.drawable.worry_2_finished_red));
        map.put("2O", new WorryImage(R.drawable.worry_2_org, R.drawable.worry_2_finished_org));
        map.put("2Y", new WorryImage(R.drawable.worry_2_yel, R.drawable.worry_2_finished_yel));
        map.put("2G", new WorryImage(R.drawable.worry_2_grn, R.drawable.worry_2_finished_grn));
        map.put("2B", new WorryImage(R.drawable.worry_2_blu, R.drawable.worry_2_finished_blu));
        map.put("2T", new WorryImage(R.drawable.worry_2_trq, R.drawable.worry_2_finished_trq));
        map.put("3R", new WorryImage(R.drawable.worry_3_red, R.drawable.worry_3_finished_red));
        map.put("3O", new WorryImage(R.drawable.worry_3_org, R.drawable.worry_3_finished_org));
        map.put("3Y", new WorryImage(R.drawable.worry_3_yel, R.drawable.worry_3_finished_yel));
        map.put("3G", new WorryImage(R.drawable.worry_3_grn, R.drawable.worry_3_finished_grn));
        map.put("3B", new WorryImage(R.drawable.worry_3_blu, R.drawable.worry_3_finished_blu));
        map.put("3T", new WorryImage(R.drawable.worry_3_trq, R.drawable.worry_3_finished_trq));
        map.put("4R", new WorryImage(R.drawable.worry_4_red, R.drawable.worry_4_finished_red));
        map.put("4O", new WorryImage(R.drawable.worry_4_org, R.drawable.worry_4_finished_org));
        map.put("4Y", new WorryImage(R.drawable.worry_4_yel, R.drawable.worry_4_finished_yel));
        map.put("4G", new WorryImage(R.drawable.worry_4_grn, R.drawable.worry_4_finished_grn));
        map.put("4B", new WorryImage(R.drawable.worry_4_blu, R.drawable.worry_4_finished_blu));
        map.put("4T", new WorryImage(R.drawable.worry_4_trq, R.drawable.worry_4_finished_trq));
        map.put("5R", new WorryImage(R.drawable.worry_5_red, R.drawable.worry_5_finished_red));
        map.put("5O", new WorryImage(R.drawable.worry_5_org, R.drawable.worry_5_finished_org));
        map.put("5Y", new WorryImage(R.drawable.worry_5_yel, R.drawable.worry_5_finished_yel));
        map.put("5G", new WorryImage(R.drawable.worry_5_grn, R.drawable.worry_5_finished_grn));
        map.put("5B", new WorryImage(R.drawable.worry_5_blu, R.drawable.worry_5_finished_blu));
        map.put("5T", new WorryImage(R.drawable.worry_5_trq, R.drawable.worry_5_finished_trq));
        map.put("6R", new WorryImage(R.drawable.worry_6_red, R.drawable.worry_6_finished_red));
        map.put("6O", new WorryImage(R.drawable.worry_6_org, R.drawable.worry_6_finished_org));
        map.put("6Y", new WorryImage(R.drawable.worry_6_yel, R.drawable.worry_6_finished_yel));
        map.put("6G", new WorryImage(R.drawable.worry_6_grn, R.drawable.worry_6_finished_grn));
        map.put("6B", new WorryImage(R.drawable.worry_6_blu, R.drawable.worry_6_finished_blu));
        map.put("6T", new WorryImage(R.drawable.worry_6_trq, R.drawable.worry_6_finished_trq));
        map.put("7R", new WorryImage(R.drawable.worry_7_red, R.drawable.worry_7_finished_red));
        map.put("7O", new WorryImage(R.drawable.worry_7_org, R.drawable.worry_7_finished_org));
        map.put("7Y", new WorryImage(R.drawable.worry_7_yel, R.drawable.worry_7_finished_yel));
        map.put("7G", new WorryImage(R.drawable.worry_7_grn, R.drawable.worry_7_finished_grn));
        map.put("7B", new WorryImage(R.drawable.worry_7_blu, R.drawable.worry_7_finished_blu));
        map.put("7T", new WorryImage(R.drawable.worry_7_trq, R.drawable.worry_7_finished_trq));
        map.put("8R", new WorryImage(R.drawable.worry_8_red, R.drawable.worry_8_finished_red));
        map.put("8O", new WorryImage(R.drawable.worry_8_org, R.drawable.worry_8_finished_org));
        map.put("8Y", new WorryImage(R.drawable.worry_8_yel, R.drawable.worry_8_finished_yel));
        map.put("8G", new WorryImage(R.drawable.worry_8_grn, R.drawable.worry_8_finished_grn));
        map.put("8B", new WorryImage(R.drawable.worry_8_blu, R.drawable.worry_8_finished_blu));
        map.put("8T", new WorryImage(R.drawable.worry_8_trq, R.drawable.worry_8_finished_trq));
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
