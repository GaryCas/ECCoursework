package utils;

import entities.BotEntity;

/**
 * Created by rd019985 on 15/03/2017.
 */
public class BotProvider {

    BotEntity[] botEntities = new BotEntity[10];
    BotEntity b0;
    BotEntity b1;
    BotEntity b2;
    BotEntity b3;
    BotEntity b4;
    BotEntity b5;
    BotEntity b6;
    BotEntity b7;
    BotEntity b8;
    BotEntity b9;

    public BotEntity[] setUpBots() {
        b0 = new BotEntity(0,0);
        int[] g0 = {0,0,0,0,0,0};
        b0.setFitness(0.0);
        b0.setGenome(g0);
        b0.setCode();

        b1 = new BotEntity(0,1);
        int[] g1 = {1,1,1,1,1,1};
        b1.setFitness(1.0);
        b1.setGenome(g1);
        b1.setCode();

        b2 = new BotEntity(0,2);
        int[] g2 = {2,2,2,2,2,2};
        b2.setFitness(0.9);
        b2.setGenome(g2);
        b2.setCode();

        b3 = new BotEntity(0,3);
        int[] g3 = {3,3,3,3,3,3};
        b3.setFitness(2.0);
        b3.setGenome(g3);
        b3.setCode();

        b4 = new BotEntity(0,4);
        int[] g4 = {4,4,4,4,4,4};
        b4.setFitness(0.8);
        b4.setGenome(g4);
        b4.setCode();

        b5 = new BotEntity(0,5);
        int[] g5 = {5,5,5,5,5,5};
        b5.setFitness(3.0);
        b5.setGenome(g5);
        b5.setCode();

        b6 = new BotEntity(0,6);
        int[] g6 = {6,6,6,6,6,6};
        b6.setFitness(0.7);
        b6.setGenome(g6);
        b6.setCode();

        b7 = new BotEntity(0,7);
        int[] g7 = {7,7,7,7,7,7};
        b7.setFitness(4.0);
        b7.setGenome(g7);
        b7.setCode();

        b8 = new BotEntity(0,8);
        int[] g8 = {8,8,8,8,8,8};
        b8.setFitness(0.6);
        b8.setGenome(g8);
        b8.setCode();

        b9 = new BotEntity(0,9);
        int[] g9 = {9,9,9,9,9,9};
        b9.setFitness(5.0);
        b9.setGenome(g9);
        b9.setCode();

        botEntities[0] = b0;
        botEntities[1] = b1;
        botEntities[2] = b2;
        botEntities[3] = b3;
        botEntities[4] = b4;
        botEntities[5] = b5;
        botEntities[6] = b6;
        botEntities[7] = b7;
        botEntities[8] = b8;
        botEntities[9] = b9;

        return botEntities;
    }
}
