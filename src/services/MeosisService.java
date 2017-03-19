package services;

import entities.BotEntity;

import java.util.Arrays;

/**
 * Created by rd019985 on 14/03/2017.
 */
public class MeosisService {
    // move this to cell reproduction service

    // put these into a static service for speed
    int[] positionBasedCrossover(BotEntity b1, BotEntity b2, int position) {
        int crossoverPoint = position % b1.getGenome().length;

        int[] motherGenome =  getMotherGene(b1, crossoverPoint);
        int[] fatherGenome = getFatherGene(b2, crossoverPoint);
        int[] offSpringGene = combineGenes(motherGenome, fatherGenome);

        return offSpringGene;
    }

    int[] combineGenes(int[] motherGenome, int[] fatherGenome) {
        int[] offSpringGene = new int[6];

        for (int i = 0; i < motherGenome.length; i++) {
            offSpringGene[i] = motherGenome[i];
        }

        for (int i = 0; i < fatherGenome.length; i++) {
            offSpringGene[i+motherGenome.length] = fatherGenome[i];
        }

        return offSpringGene;
    }

    int[] getFatherGene(BotEntity b2, int crossoverPoint) {
        return Arrays.copyOfRange(b2.getGenome(), crossoverPoint, b2.getGenome().length);
    }

    int[] getMotherGene(BotEntity b1, int crossoverPoint) {
        return Arrays.copyOfRange(b1.getGenome(), 0, crossoverPoint);
    }

    int[] mutate(int[] newGenome, int position, int randomValue) {
        // preserve event data
        int oldGenome = newGenome[position] / 10000;

        newGenome[position] = (oldGenome * 10000) + randomValue;
        return newGenome;
    }
}
