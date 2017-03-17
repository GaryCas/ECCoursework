package services;

import entities.BotEntity;
import runners.ECRunner;

import java.util.*;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BreedingService {

    static Comparator<BotEntity> fitnessComparator;
    static int crossoverFreq = 9, memberGeneration = 0;
    static MeosisService meosisService;

    public static BotEntity[] createNewGeneration(BotEntity[] oldGeneration){
        // add each of the survivors of the old generation

        BotEntity[] newGeneration = new BotEntity[oldGeneration.length];

        for (BotEntity botEntity : oldGeneration) {
            botEntity.setSurvivor(false);
        }

        memberGeneration = oldGeneration[0].getMemberGen() + 1;

        BotEntity[] survivors = returnParents(oldGeneration);

        // add all of the survivors to the new generation
        for (int i = 0; i < survivors.length; i++) {
            survivors[i].setMemberGen(memberGeneration);
            survivors[i].setMemberID(i);
            survivors[i].setSurvivor(true);
            survivors[i].setBotName();
            survivors[i].setCode();
            newGeneration[i] = survivors[i];
        }

        // create and add children
        newGeneration = addChildren(survivors, newGeneration);

        return newGeneration;
    }

    static BotEntity[] addChildren(BotEntity[] survivors, BotEntity[] newGeneration) {
        Random randy = new Random();
        int parentSelector1 = 0;
        int parentSelector2 = 0;


        for (int i = 0; i < newGeneration.length - survivors.length; i++) {
            while(parentSelector1 == parentSelector2) {
                parentSelector1 = randy.nextInt(survivors.length);
                parentSelector2 = randy.nextInt(survivors.length);
            }

            BotEntity offspring = new BotEntity(memberGeneration,survivors.length+i);
            offspring.setGenome(doMeosis(survivors[parentSelector1], survivors[parentSelector2]));
            offspring.setCode();

            newGeneration[survivors.length+i] = offspring;


            parentSelector1 = 0;
            parentSelector2 = 0;
        }


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

        if(meosisService == null){
            meosisService = new MeosisService();
        }

        newGene = meosisService.positionBasedCrossover(b1,b2, randy.nextInt(4) + 1);

        if(randy.nextInt(crossoverFreq) == 0 ) {
            newGene = meosisService.mutate(newGene, randy.nextInt(5), randy.nextInt(9999));
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

    public static MeosisService getMeosisService() {
        return meosisService;
    }

    // setters for unit tests
    void setCrossoverFreq(int crossoverFreq) {
        BreedingService.crossoverFreq = crossoverFreq;
    }

    void setMeosisService(MeosisService meosisService) {
        this.meosisService = meosisService;
    }


}
