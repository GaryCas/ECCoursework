package services;

import entities.BotEntity;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BreedingService {

    static Comparator<BotEntity> fitnessComparator;

    /**
     * Can replace with various methods
     *
     * @param botEntities
     * @return an array of BotEntities that are to be breeded
     */
    public static BotEntity[] returnStrongest(BotEntity[] botEntities){
        return Arrays.copyOfRange(sortByFitness(botEntities) ,0, botEntities.length/2);
    }

    static BotEntity[] sortByFitness(BotEntity[] botEntities){
        Arrays.sort(botEntities, fitnessComparator);
        return botEntities;
    }

    static {
        fitnessComparator = new Comparator<BotEntity>() {
            @Override
            public int compare(BotEntity b1, BotEntity b2) {
                return Double.compare(b2.getFitness(), b1.getFitness());
            }
        };
    }


    // put these into a static service for speed
    public static BotEntity crossover(BotEntity b1, BotEntity b2, int gen, int botID) {
        return null;
    }

    // put these into a static service for speed
    public static BotEntity mutate(BotEntity b1) {
        return null;
    }

    // put these into a static service for speed
    public static BotEntity replicate(BotEntity b1){
        return null;
    }
}
