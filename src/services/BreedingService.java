package services;

import entities.BotEntity;
import runners.ECRunner;

import java.util.*;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BreedingService {

    static Comparator<BotEntity> fitnessComparator;
    static int crossoverFreq = 9;
    static MeosisService meosisService;


    public BreedingService() {
        meosisService = new MeosisService();
    }

    public static ArrayList<BotEntity> createNewGeneration(BotEntity[] oldGeneration){
        // add each of the survivors of the old generation

        ArrayList<BotEntity> newGeneration = new ArrayList<>();

        ArrayList<BotEntity> survivors = (ArrayList<BotEntity>) Arrays.asList(returnParents(oldGeneration));

        // add all of the survivors to the new generation
        for (BotEntity botEntity : survivors) {
            newGeneration.add(botEntity);
        }

        // create and add children
        newGeneration = addChildren(survivors, newGeneration);

        return newGeneration;
    }

    static ArrayList<BotEntity> addChildren(ArrayList<BotEntity> survivors, ArrayList<BotEntity> newGeneration) {
        Random randy = new Random();
        int parentSelector1 = 0;
        int parentSelector2 = 0;
        do {
            while(parentSelector1 == parentSelector2) {
                parentSelector1 = randy.nextInt(survivors.size());
                parentSelector2 = randy.nextInt(survivors.size());
            }

            BotEntity offspring = new BotEntity(0,0);
            offspring.setGenome(doMeosis(survivors.get(parentSelector1), survivors.get(parentSelector2)));

            newGeneration.add(offspring);
        } while(newGeneration.size() < ECRunner.POP_SIZE);

        return newGeneration;
    }

    /**
     * Can replace with various methods
     *
     * @param oldGeneration
     * @return an array of BotEntities that are to be breeded
     */
    static BotEntity[] returnParents(BotEntity[] oldGeneration){
        return Arrays.copyOfRange(sortByFitness(oldGeneration) ,0, oldGeneration.length/2);
    }

    static BotEntity[] sortByFitness(BotEntity[] botEntities){
        Arrays.sort(botEntities, fitnessComparator);
        return botEntities;
    }


    /**
     *
     * put template pattern here
     *
     * @param b1
     * @param b2
     * @return
     */
    static int[] doMeosis(BotEntity b1, BotEntity b2){
        int[] newGene ;
        Random randy = new Random();
        newGene = meosisService.positionBasedCrossover(b1,b2, randy.nextInt(5));

        if(randy.nextInt(crossoverFreq) == 0 ) {
            newGene = meosisService.mutate(newGene, randy.nextInt(5), randy.nextInt(9));
        }

        return newGene;
    }

    static {
        fitnessComparator = new Comparator<BotEntity>() {
            @Override
            public int compare(BotEntity b1, BotEntity b2) {
                return Double.compare(b2.getFitness(), b1.getFitness());
            }
        };
    }

    // setters for unit tests
    void setCrossoverFreq(int crossoverFreq) {
        BreedingService.crossoverFreq = crossoverFreq;
    }

    void setMeosisService(MeosisService meosisService) {
        this.meosisService = meosisService;
    }


}
