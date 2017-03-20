package services;

import entities.BotEntity;

import java.util.*;

/**
 * Created by rd019985 on 01/03/2017.
 */
public class BreedingService {

    static Comparator<BotEntity> fitnessComparator;
    static int mutationFreq = 9, memberGeneration = 0;
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
            survivors[i].setFitness(0.0);
            newGeneration[i] = survivors[i];
        }

        // create and add children
        newGeneration = addChildren(survivors, newGeneration);

        return newGeneration;
    }

    static BotEntity[] addChildren(BotEntity[] parents, BotEntity[] newGeneration) {
        Random randy = new Random();
        int parentSelector1 = 0;
        int parentSelector2 = 0;

        double[] fitnessBenchmark = defineBenchmarkArray(parents);

        for (int i = 0; i < newGeneration.length - parents.length; i++) {
            while(parentSelector1 == parentSelector2) {
                parentSelector1 = fitnessProportionateSelection(fitnessBenchmark, randy.nextInt(100));
                parentSelector2 = fitnessProportionateSelection(fitnessBenchmark, randy.nextInt(100));
            }

            BotEntity offspring = new BotEntity(memberGeneration,parents.length+i);
            offspring.setGenome(doMeosis(parents[parentSelector1], parents[parentSelector2]));
            offspring.setCode();

            newGeneration[parents.length+i] = offspring;

            parentSelector1 = 0;
            parentSelector2 = 0;
        }


        return newGeneration;
    }

    public static int fitnessProportionateSelection(double[] benchmark, int randy) {
        int selector = benchmark.length - 1;

        for (int i = 0; i < benchmark.length - 1; i++) {
            if(i == 0){
                if(randy < benchmark[i + 1] && randy > 0){
                    selector = i;
                    break;
                }
            } else {
                if (randy < benchmark[i + 1] && randy > benchmark[i]) {
                    selector = i;
                    break;
                }
            }
        }

        return selector;
    }

    public static double[] defineBenchmarkArray(BotEntity[] parents) {
        double totalFitness = 0.0;
        double[] benchmarkArray = new double[parents.length];

        for (BotEntity parent : parents) {
            totalFitness += parent.getFitness();
        }

        for (int i = 0; i < parents.length; i++) {
            parents[i].setPercentageFitness((parents[i].getFitness() * 100) / totalFitness);
        }

        benchmarkArray[0] = 0.0;

        for (int j = 1; j < parents.length ; j++) {
            benchmarkArray[j] = benchmarkArray[j - 1] + parents[j-1].getPercentageFitness();
        }

        return benchmarkArray;
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

        newGene = meosisService.singlePointCrossOver(b1,b2, randy.nextInt(4) + 1);

        if(randy.nextInt(mutationFreq) == 0 ) {
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
        BreedingService.mutationFreq = crossoverFreq;
    }

    void setMeosisService(MeosisService meosisService) {
        this.meosisService = meosisService;
    }


}
