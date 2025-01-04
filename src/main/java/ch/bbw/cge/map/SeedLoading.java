package ch.bbw.cge.map;

import ch.bbw.cge.building.Building;
import ch.bbw.cge.building.HouseBuilding;
import ch.bbw.cge.building.MedicalBuilding;
import ch.bbw.cge.building.ShopBuilding;
import ch.bbw.cge.jokemon.*;
import ch.bbw.cge.map.objects.NPC;
import ch.bbw.cge.map.objects.WildJokemon;
import ch.bbw.cge.map.screens.Screen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SeedLoading {
    private List<NPC> allNPCs = new ArrayList<>();
    private List<WildJokemon> allWildJokemon = new ArrayList<>();
    private List<Building> allBuildings = new ArrayList<>();
    private int[][] bridgePositions;
    private int[][] treePositions;
    private int[][] waterPositions;
    private Screen.Region[][] regionMap = new Screen.Region[50][50];

    private List<int[]> listTreePositions = new ArrayList<>();
    private List<int[]> listWaterPositions = new ArrayList<>();
    private List<int[]> listBridgePositions = new ArrayList<>();
    private List<int[]> listJokemonPositions = new ArrayList<>();
    private List<int[]> listNPCPositions = new ArrayList<>();

    List<Object> seedData = new ArrayList<>();

    private Player player;
    private Map<String, List<String>> movesMap = new HashMap<>();
    public void loadSeed(int seed, Player player, Map<String, List<String>> movesMap) {
        this.player = player;
        this.movesMap = movesMap;

        if (seed == 1) {
            setSeed1();
        } else if (seed == 2) {
            setSeed2();
        } else if (seed == 3) {
            setSeed3();
        } else if (seed == 4) {
            setSeed4();
        } else if (seed == 5) {
            setSeed5();
        } else {
            System.out.println("Invalid seed value: " + seed);
        }
        seedData.add(allNPCs);
        seedData.add(allWildJokemon);
        seedData.add(allBuildings);
        seedData.add(treePositions);
        seedData.add(bridgePositions);
        seedData.add(waterPositions);
        seedData.add(regionMap);
    }

    public List<Object> getSeedData() {
        return seedData;
    }

    private void assignRegion(int startX, int startY, int endX, int endY, Screen.Region region) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (x < 50 && y < 50) {
                    regionMap[x][y] = region;
                }
            }
        }
    }

    private void setSeed1() {

        Trainer trainer1 = new Trainer(Trainer.Status.BOT, "BOT 1", 28,
                List.of(new WaterJokemon("Squirtle", 24, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")),
                        new GrassJokemon("Bulbasaur", 16, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"))));
        NPC npc1 = new NPC(4, 9, trainer1, player.getTrainer());
        allNPCs.add(npc1);

        Jokemon freshBulbasaur = new GrassJokemon("Bulbasaur", 1, 1,45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"));
        Jokemon freshGlomanda = new FireJokemon("Glomanda", 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda"));

        allWildJokemon.add(new WildJokemon(10, 11, freshBulbasaur, false));
        allWildJokemon.add(new WildJokemon(20, 6, freshGlomanda, false));

        allBuildings.add(new MedicalBuilding(8, 4, true));
        allBuildings.add(new ShopBuilding(21, 10 , true));
        allBuildings.add(new HouseBuilding(17, 17, true));

        this.treePositions = new int[][]{
                {0, 5}, {1, 11}, {3, 7}, {6, 22}, {8, 20}, {10, 13},
                {12, 24}, {14, 3}, {15, 8}, {17, 14}, {19, 18}, {21, 2},
                {23, 10}, {24, 12}, {5, 15}, {9, 9}, {18, 1}, {22, 8},
                {4, 21}, {7, 6}, {2, 17}, {16, 19}, {13, 22}, {11, 5},
                {20, 4}, {3, 19}, {25, 1}, {12, 0}, {1, 23}, {9, 12},
                {24, 4}, {0, 2}, {18, 24}, {15, 0}, {10, 5}, {11, 11}};
        this.bridgePositions = new int[][]{
                {14, 12}, {15, 12},
                {19, 4}, {19, 3},
                {3, 18}, {3, 19},
                {11, 18}, {11, 19}};
        this.waterPositions = new int[][]{
                {0, 18}, {0, 19}, {1, 18}, {1, 19}, {2, 18}, {2, 19},
                {3, 18}, {3, 19}, {4, 18}, {4, 19}, {5, 18}, {5, 19},
                {6, 18}, {6, 19}, {7, 18}, {7, 19}, {8, 18}, {8, 19},
                {9, 18}, {9, 19}, {10, 18}, {10, 19}, {11, 18}, {11, 19},
                {12, 18}, {12, 19}, {13, 18}, {13, 19}, {14, 3}, {14, 4},
                {14, 5}, {14, 6}, {14, 7}, {14, 8}, {14, 9}, {14, 10},
                {14, 11}, {14, 12}, {14, 13}, {14, 14}, {14, 15}, {14, 16},
                {14, 17}, {14, 18}, {14, 19}, {15, 3}, {15, 4}, {15, 5},
                {15, 6}, {15, 7}, {15, 8}, {15, 9}, {15, 10}, {15, 11},
                {15, 12}, {15, 13}, {15, 14}, {15, 15}, {15, 16}, {15, 17},
                {15, 18}, {15, 19}, {16, 3}, {16, 4}, {17, 3}, {17, 4},
                {18, 3}, {18, 4}, {19, 3}, {19, 4}, {20, 3}, {20, 4},
                {21, 3}, {21, 4}, {22, 3}, {22, 4}, {23, 3}, {23, 4},
                {24, 3}, {24, 4}
        };

        defineRegionSeed1();
    }
    private void defineRegionSeed1() {
        String[] regionMap = {
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGFFFFFFGGGGGGGGGGGG",
                "GGGGGGGFFFFFFGGGGGGGGGGGG",
                "GGGGGGGFFFFFFGGGGGGGGGGGG",
                "GGGGGGGFFFFFFGGGGGGGGGGGG",
                "GGGGGGGFFFFFFGGGGGGGGGGGG",
                "GGGGGGGFFFFFFGGGGGGGGGGGG",
                "GGGGGGGFFFFFFGGGGGGGGGGGG",
                "GGGGGGGFFFFFFGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "GGGGGGGGGGGGGGGGGGGGGGGGG",
                "MMMMMMMMMMMMMMMMMMMMMMMMM",
                "MMMMMMMMMMMMMMMMMMMMMMMMM",
                "MMMMMMMMMMMMMMMMMMMMMMMMM",
                "MMMMMMMMMMMMMMMMMMMMMMMMM",
                "MMMMMMMMMMMMMMMMMMMMMMMMM"
        };

        defineRegion(regionMap, null);
    }

    private void setSeed2() {
        Trainer trainer1 = new Trainer(Trainer.Status.BOT, "BOT 1", 30,
                List.of(new GrassJokemon("Bulbasaur", 16, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"))
                , new WaterJokemon("Squirtle", 24, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle"))));

        NPC npc1 = new NPC(12, 5, trainer1, player.getTrainer());
        allNPCs.add(npc1);

        Jokemon freshBulbasaur = new GrassJokemon("Bulbasaur", 1, 1,45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"));
        Jokemon freshGlomanda = new FireJokemon("Glomanda", 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda"));

        allWildJokemon.add(new WildJokemon(5, 15, freshBulbasaur, false));
        allWildJokemon.add(new WildJokemon(8, 12, freshGlomanda, false));

        allBuildings.add(new MedicalBuilding(10, 8, true));
        allBuildings.add(new ShopBuilding(19, 12, true));
        allBuildings.add(new HouseBuilding(14, 18, true));

        this.treePositions = new int[][]{
                {0, 24}, {1, 2}, {1, 17}, {2, 4}, {2, 7}, {2, 17},
                {3, 3}, {3, 17}, {3, 22}, {4, 17}, {5, 12}, {5, 17},
                {6, 14}, {6, 18}, {7, 11}, {7, 14}, {8, 14}, {8, 15},
                {8, 20}, {9, 19}, {10, 18}, {11, 16}, {12, 9}, {12, 14},
                {13, 3}, {13, 11}, {14, 10}, {15, 1}, {15, 13}, {16, 20},
                {17, 15}, {18, 10}, {18, 17}, {19, 3}, {19, 14}, {20, 9},
                {20, 15}, {21, 1}, {22, 15}, {22, 22}, {24, 6}, {24, 24}
        };

        this.bridgePositions = new int[][]{
                {4, 6}, {11, 2}, {11, 24}, {12, 24}, {18, 21}, {18, 22}, {22, 5}
        };

        this.waterPositions = new int[][]{
                {0, 6}, {1, 6}, {1, 12}, {1, 13}, {2, 6}, {2, 12}, {2, 13}, {2, 14},
                {3, 6}, {3, 12}, {3, 13}, {3, 14}, {4, 12}, {4, 13}, {5, 5}, {5, 6},
                {5, 7}, {5, 8}, {6, 5}, {6, 6}, {6, 7}, {6, 8}, {6, 9}, {7, 2},
                {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7}, {7, 8}, {8, 2}, {8, 5},
                {8, 6}, {8, 7}, {9, 2}, {9, 6}, {10, 2}, {11, 22}, {11, 23}, {12, 2},
                {12, 21}, {12, 22}, {12, 23}, {14, 2}, {14, 21}, {14, 22}, {15, 2},
                {15, 3}, {15, 4}, {15, 5}, {15, 21}, {15, 22}, {16, 5}, {16, 21},
                {16, 22}, {17, 5}, {17, 21}, {17, 22}, {18, 5}, {19, 5}, {19, 21},
                {19, 22}, {20, 5}, {20, 18}, {20, 19}, {20, 20}, {20, 21}, {20, 22},
                {21, 5}, {21, 18}, {21, 19}, {21, 20}, {21, 21}, {21, 22}, {22, 18},
                {22, 19}, {22, 20}, {22, 21}, {23, 5}, {23, 20}, {24, 5}, {24, 20},
                {13, 2}, {13, 21}, {13, 22}
        };

        defineRegionSeed2();
    }
    private void defineRegionSeed2() {
        String[] regionMap = {
                "GGGGGGGGGGGGGGMMMMMMMMMMG",
                "GGGGGGGGGGGGGGMMMMMMMMMMG",
                "GGGGGGGGGGGGGGMMMMMMMMMMG",
                "GGGGGGGGGGGGGGMMMMMMMMMMG",
                "GGGGGGGGGGGGGGMMMMMMMMMMG",
                "GGGGGFFFFFFFFMMMMMMMMMMGG",
                "GGGGGFFFFFFFFMMMMMMMMMMGG",
                "GGGGGFFFFFFFFMMMMMMMMMMGG",
                "GGGGGFFFFFFFFMMMMMMMMMMGG",
                "GGGGGFFFFFFFFFFFFFFFFFFGG",
                "GGGGFFFFFFFFFFFFFFFFFFFDD",
                "GGGGFFFFFFFFFFFFFFFFFFFDD",
                "DDDDFFFFFFFFFFFFFFFFFFFDD",
                "DDDDFFFFFFFFFFFFFFFFFFFDD",
                "DDDDFFFFFFFFFFFFFFFFFFFDD",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG",
                "DDDDFFFFFFFFFFFFFFFFDDDGG"
        };

        defineRegion(regionMap, null);
    }

    private void setSeed3() {

        Trainer trainer1 = new Trainer(Trainer.Status.BOT, "BOT 1", 22,
                List.of(new GrassJokemon("Bulbasaur", 10, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"))
                        , new WaterJokemon("Squirtle", 8, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle"))));

        allNPCs.add(new NPC(12, 5, trainer1, player.getTrainer()));

        Jokemon freshBulbasaur = new GrassJokemon("Bulbasaur", 1, 1,45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"));
        Jokemon freshGlomanda = new FireJokemon("Glomanda", 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda"));
        allWildJokemon.add(new WildJokemon(1, 16, freshGlomanda, false));
        allWildJokemon.add(new WildJokemon(21, 21, freshBulbasaur, false));

        allBuildings.add(new HouseBuilding(17, 22, false));
        allBuildings.add(new MedicalBuilding(1, 7, true));
        allBuildings.add(new HouseBuilding(1, 10, true));
        allBuildings.add(new ShopBuilding(15, 5, true));

        this.treePositions = new int[][]{
                {0, 1}, {0, 6}, {0, 12}, {0, 19}, {1, 4}, {1, 22},
                {2, 3}, {2, 11}, {2, 18}, {3, 5}, {3, 14}, {3, 20},
                {4, 2}, {4, 9}, {4, 16}, {5, 7}, {5, 15}, {6, 8},
                {6, 21}, {7, 0}, {7, 13}, {8, 4}, {8, 10}, {9, 3},
                {9, 12}, {9, 19}, {10, 1}, {10, 18}, {11, 5}, {11, 23},
                {12, 6}, {12, 14}, {13, 2}, {13, 17}, {14, 8}, {14, 24},
                {15, 9}, {15, 16}, {16, 1}, {16, 20}, {17, 11}, {18, 3},
                {18, 12}, {19, 0}, {20, 7}, {20, 22}, {21, 4}, {22, 15},
                {23, 10}, {24, 19}
        };
        this.bridgePositions = new int[][]{
                {5, 21}, {6, 17}, {8, 7}, {13, 22}, {20, 16}
        };
        this.waterPositions = new int[][]{
                {4, 10}, {4, 11}, {4, 12}, {4, 13}, {4, 14}, {4, 15},
                {4, 22}, {4, 23}, {4, 24}, {5, 7}, {5, 8}, {5, 9},
                {5, 10}, {5, 11}, {5, 12}, {5, 13}, {5, 18}, {5, 19},
                {5, 20}, {5, 22}, {6, 7}, {6, 9}, {6, 10}, {6, 11},
                {6, 12}, {6, 13}, {6, 14}, {6, 15}, {6, 16}, {6, 18},
                {7, 7}, {7, 10}, {7, 11}, {7, 12}, {8, 23}, {8, 24},
                {9, 7}, {9, 23}, {10, 2}, {10, 3}, {10, 4}, {10, 5},
                {10, 6}, {10, 7}, {10, 22}, {10, 23}, {11, 2}, {11, 22},
                {12, 0}, {12, 1}, {12, 2}, {12, 22}, {14, 20}, {14, 21},
                {14, 22}, {15, 17}, {15, 18}, {15, 19}, {15, 20}, {16, 17},
                {17, 16}, {17, 17}, {18, 16}, {19, 16}, {21, 16}, {22, 14},
                {22, 15}, {22, 16}, {22, 17}, {22, 18}, {23, 10}, {23, 11},
                {23, 12}, {23, 13}, {23, 14}, {23, 15}, {23, 16}, {23, 17},
                {23, 18}, {23, 19}, {23, 20}, {24, 7}, {24, 8}, {24, 9},
                {24, 10}, {24, 11}, {24, 12}, {24, 13}, {24, 14}, {24, 15},
                {24, 16}, {24, 17}, {24, 18}, {24, 19}, {24, 20}, {24, 21},
                {5, 14}
        };

        defineRegionSeed3();
    }
    private void defineRegionSeed3() {
        String[] regionMap = {
                "DDDDDDDDDDDDDDDDGGGGGMMMM",
                "DDDDDDDDDDDDDDDGGGGGGMMMM",
                "DDDDDDDDDDDDGGGGGGGGMMMMM",
                "DDDDDDDDDDGGGGGGGGGMMMMMM",
                "DDDDDDDDDDGGGGGGGGGMMMMMM",
                "DDDDDDDDDGGGGGGGGGMMMMMMM",
                "DDDDDDDDGGGGGGGGGMMMMMMMM",
                "DDDDDDDDGGGGGGGGGGMMMMMMM",
                "DDDDDDDDGGGGGGGGGGMMMMMMM",
                "DDDDDDDDGGGFFFFGGMMMMMMMM",
                "DDDDDDDDFFFFFFGGGMMMMMMMM",
                "DDDFFFFFFFFFFFGGGMMMMMMMM",
                "GGGFFFFFFFFFFFFGGGMMMMMMM",
                "GGGGFFFFFFFFFFFGGGGGMMMMM",
                "GGGGFFFFFFFFFFFGGGGMMMMMM",
                "GGGGFFFFFFFFFFFFFGGGGMMMM",
                "GGGGFFFFFFFFFFFFFGGGGGMMM",
                "GGGGFFFFFFFFFFGGGGGGGFFFF",
                "GGGGGFFFFFFFFGGGGGGGGFFFF",
                "GGGGGFFFFFFGGGGGGGGGFFFFF",
                "GGGGGFFFFFGGGGGGGGGGFFFFF",
                "GGGGGFFFFFGGGGGGGGGGGGGFF",
                "GGGGGGFFFGGGGGGGGGGGGGFFF",
                "GGGGGGGGGGGGGGGGGGGFFFFFF",
                "GGGGGGGGGGGGGGGGGGGFFFFFF"
        };


        defineRegion(regionMap, null);
    }

    private void setSeed4() { //50x50 map
        defineRegionSeed4();

        Trainer trainer1 = new Trainer(Trainer.Status.BOT, "BOT 1", 5,
                List.of(new GrassJokemon("Bulbasaur", 3, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"))));
        Trainer trainer2 = new Trainer(Trainer.Status.BOT, "BOT 2", 7,
                List.of(new WaterJokemon("Squirtle", 5, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle"))));
        Trainer trainer3 = new Trainer(Trainer.Status.BOT, "BOT 3", 8,
                List.of(new FireJokemon("Glomanda", 6, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda")),
                        new GrassJokemon("Schaggi", 4, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi"))));
        Trainer trainer4 = new Trainer(Trainer.Status.BOT, "BOT 4", 9,
                List.of(new WaterJokemon("Schaggi", 7, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi"))));
        Trainer trainer5 = new Trainer(Trainer.Status.BOT, "BOT 5", 10,
                List.of(new GrassJokemon("Bulbasaur", 9, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")),
                        new WaterJokemon("Squirtle", 8, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle"))));
        Trainer trainer6 = new Trainer(Trainer.Status.BOT, "BOT 6", 12,
                List.of(new FireJokemon("Glomanda", 10, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda")),
                        new GrassJokemon("Schaggi", 11, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi"))));
        Trainer trainer7 = new Trainer(Trainer.Status.BOT, "BOT 7", 14,
                List.of(new WaterJokemon("Squirtle", 12, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")),
                        new GrassJokemon("Bulbasaur", 13, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"))));
        Trainer trainer8 = new Trainer(Trainer.Status.BOT, "BOT 8", 15,
                List.of(new GrassJokemon("Ivysaur", 13, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new FireJokemon("Glomanda", 12, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda"))));
        Trainer trainer9 = new Trainer(Trainer.Status.BOT, "BOT 9", 16,
                List.of(new WaterJokemon("Squirtle", 14, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")),
                        new GrassJokemon("Schaggi", 15, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi"))));
        Trainer trainer10 = new Trainer(Trainer.Status.BOT, "BOT 10", 18,
                List.of(new FireJokemon("Glomanda", 16, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda")),
                        new GrassJokemon("Bulbasaur", 17, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")),
                        new WaterJokemon("Squirtle", 16, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle"))));
        Trainer trainer11 = new Trainer(Trainer.Status.BOT, "BOT 11", 20,
                List.of(new GrassJokemon("Ivysaur", 18, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new WaterJokemon("Wartortle", 19, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle"))));
        Trainer trainer12 = new Trainer(Trainer.Status.BOT, "BOT 12", 22,
                List.of(new FireJokemon("Charmeleon", 21, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")),
                        new GrassJokemon("Ivysaur", 20, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new WaterJokemon("Wartortle", 19, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle"))));
        Trainer trainer13 = new Trainer(Trainer.Status.BOT, "BOT 13", 21,
                List.of(new GrassJokemon("Schaggi", 19, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi")),
                        new FireJokemon("Glomanda", 20, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda"))));
        Trainer trainer14 = new Trainer(Trainer.Status.BOT, "BOT 14", 19,
                List.of(new WaterJokemon("Squirtle", 17, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")),
                        new GrassJokemon("Bulbasaur", 18, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")),
                        new FireJokemon("Glomanda", 19, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda"))));
        Trainer trainer15 = new Trainer(Trainer.Status.BOT, "BOT 15", 20,
                List.of(new WaterJokemon("Wartortle", 18, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")),
                        new GrassJokemon("Ivysaur", 17, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur"))));
        Trainer trainer16 = new Trainer(Trainer.Status.BOT, "BOT 16", 25,
                List.of(new GrassJokemon("Ivysaur", 23, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new FireJokemon("Charmeleon", 24, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")),
                        new WaterJokemon("Wartortle", 22, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle"))));
        Trainer trainer17 = new Trainer(Trainer.Status.BOT, "BOT 17", 28,
                List.of(new FireJokemon("Charmeleon", 26, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")),
                        new GrassJokemon("Ivysaur", 27, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new WaterJokemon("Wartortle", 25, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")),
                        new GrassJokemon("Schaggi", 24, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi"))));
        Trainer trainer18 = new Trainer(Trainer.Status.BOT, "BOT 18", 29,
                List.of(new WaterJokemon("Wartortle", 27, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")),
                        new GrassJokemon("Ivysaur", 28, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new FireJokemon("Charmeleon", 26, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon"))));
        Trainer trainer19 = new Trainer(Trainer.Status.BOT, "BOT 19", 27,
                List.of(new FireJokemon("Charmeleon", 25, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")),
                        new WaterJokemon("Wartortle", 26, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")),
                        new GrassJokemon("Ivysaur", 23, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new FireJokemon("Glomanda", 24, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda"))));
        Trainer trainer20 = new Trainer(Trainer.Status.BOT, "BOT 20", 35,
                List.of(new FireJokemon("Charmeleon", 32, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")),
                        new WaterJokemon("Wartortle", 34, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")),
                        new GrassJokemon("Ivysaur", 33, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new FireJokemon("Glomanda", 31, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda")),
                        new WaterJokemon("Squirtle", 30, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")),
                        new GrassJokemon("Schaggi", 29, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi"))));
        Trainer midGameBoss = new Trainer(Trainer.Status.BOT, "Mid-Game Boss", 100,
                List.of(new GrassJokemon("Venusaur", 88, 3, 80, 82, 83, 100, 100, 80, movesMap.get("Venusaur")),
                        new WaterJokemon("Blastoise", 95, 3, 79, 83, 100, 85, 105, 78, movesMap.get("Blastoise")),
                        new FireJokemon("Charizard", 110, 3, 78, 84, 78, 109, 85, 100, movesMap.get("Charizard")),
                        new FireJokemon("Charmeleon", 33, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")),
                        new WaterJokemon("Wartortle", 35, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")),
                        new GrassJokemon("Ivysaur", 34, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new FireJokemon("Glomanda", 16, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda")),
                        new WaterJokemon("Squirtle", 16, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")),
                        new GrassJokemon("Schaggi", 15, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi"))));
        Trainer finalBoss = new Trainer(Trainer.Status.BOT, "Final Boss", 745,
                List.of(new GrassJokemon("Venusaur", 482, 3, 80, 82, 83, 100, 100, 80, movesMap.get("Venusaur")),
                        new WaterJokemon("Blastoise", 349, 3, 79, 83, 100, 85, 105, 78, movesMap.get("Blastoise")),
                        new FireJokemon("Charizard", 528, 3, 78, 84, 78, 109, 85, 100, movesMap.get("Charizard")),
                        new GrassJokemon("Venusaur", 178, 3, 80, 82, 83, 100, 100, 80, movesMap.get("Venusaur")),
                        new WaterJokemon("Blastoise", 156, 3, 79, 83, 100, 85, 105, 78, movesMap.get("Blastoise")),
                        new FireJokemon("Charizard", 225, 3, 78, 84, 78, 109, 85, 100, movesMap.get("Charizard")),
                        new FireJokemon("Charmeleon", 36, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")),
                        new WaterJokemon("Wartortle", 36, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")),
                        new GrassJokemon("Ivysaur", 36, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")),
                        new FireJokemon("Glomanda", 16, 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda")),
                        new WaterJokemon("Squirtle", 16, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")),
                        new GrassJokemon("Schaggi", 16, 1, 50, 55, 50, 55, 65, 55, movesMap.get("Schaggi")),
                        new GrassJokemon("Bulbasaur", 18, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"))));

        allNPCs.add(new NPC(30, 14, trainer1, player.getTrainer()));
        allNPCs.add(new NPC(4, 23, trainer2, player.getTrainer()));
        allNPCs.add(new NPC(22, 45, trainer3, player.getTrainer()));
        allNPCs.add(new NPC(41, 7, trainer4, player.getTrainer()));
        allNPCs.add(new NPC(28, 48, trainer5, player.getTrainer()));
        allNPCs.add(new NPC(48, 10, trainer6, player.getTrainer()));
        allNPCs.add(new NPC(33, 12, trainer7, player.getTrainer()));
        allNPCs.add(new NPC(6, 4, trainer8, player.getTrainer()));
        allNPCs.add(new NPC(10, 48, trainer9, player.getTrainer()));
        allNPCs.add(new NPC(7, 41, trainer10, player.getTrainer()));
        allNPCs.add(new NPC(15, 5, trainer11, player.getTrainer()));
        allNPCs.add(new NPC(19, 20, trainer12, player.getTrainer()));
        allNPCs.add(new NPC(12, 9, trainer13, player.getTrainer()));
        allNPCs.add(new NPC(24, 39, trainer14, player.getTrainer()));
        allNPCs.add(new NPC(2, 5, trainer15, player.getTrainer()));
        allNPCs.add(new NPC(35, 21, trainer16, player.getTrainer()));
        allNPCs.add(new NPC(49, 25, trainer17, player.getTrainer()));
        allNPCs.add(new NPC(45, 22, trainer18, player.getTrainer()));
        allNPCs.add(new NPC(9, 33, trainer19, player.getTrainer()));
        allNPCs.add(new NPC(44, 41, trainer20, player.getTrainer()));
        allNPCs.add(new NPC(37, 34, midGameBoss, player.getTrainer()));
        allNPCs.add(new NPC(37, 12, finalBoss, player.getTrainer()));

        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(0)[0], listJokemonPositions.get(0)[1],
                new WaterJokemon("Squirtle", 1, 1, 44, 48, 65, 50, 64, 43, movesMap.get("Squirtle")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(1)[0], listJokemonPositions.get(1)[1],
                new FireJokemon("Glomanda", 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(2)[0], listJokemonPositions.get(2)[1],
                new WaterJokemon("Squirtle", 1, 1, 44, 48, 65, 50, 64, 43, movesMap.get("Squirtle")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(3)[0], listJokemonPositions.get(3)[1],
                new WaterJokemon("Squirtle", 1, 1, 44, 48, 65, 50, 64, 43, movesMap.get("Squirtle")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(4)[0], listJokemonPositions.get(4)[1],
                new GrassJokemon("Bulbasaur", 1, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(5)[0], listJokemonPositions.get(5)[1],
                new FireJokemon("Glomanda", 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(6)[0], listJokemonPositions.get(6)[1],
                new FireJokemon("Glomanda", 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(7)[0], listJokemonPositions.get(7)[1],
                new GrassJokemon("Bulbasaur", 1, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(8)[0], listJokemonPositions.get(8)[1],
                new WaterJokemon("Squirtle", 1, 1, 44, 48, 65, 50, 64, 43, movesMap.get("Squirtle")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(9)[0], listJokemonPositions.get(9)[1],
                new GrassJokemon("Bulbasaur", 1, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(10)[0], listJokemonPositions.get(10)[1],
                new FireJokemon("Glomanda", 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(11)[0], listJokemonPositions.get(11)[1],
                new FireJokemon("Glomanda", 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(12)[0], listJokemonPositions.get(12)[1],
                new GrassJokemon("Bulbasaur", 1, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(13)[0], listJokemonPositions.get(13)[1],
                new WaterJokemon("Squirtle", 1, 1, 44, 48, 65, 50, 64, 43, movesMap.get("Squirtle")), false));
        allWildJokemon.add(new WildJokemon(listJokemonPositions.get(14)[0], listJokemonPositions.get(14)[1],
                new GrassJokemon("Bulbasaur", 1, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")), false));

        allBuildings.add(new HouseBuilding(8, 7, true));
        allBuildings.add(new ShopBuilding(8, 11, true));
        allBuildings.add(new ShopBuilding(1, 36, true));
        allBuildings.add(new MedicalBuilding(1, 39, true));
        allBuildings.add(new MedicalBuilding(17, 32, true));
        allBuildings.add(new HouseBuilding(17, 35, true));
        allBuildings.add(new HouseBuilding(21, 31, true));
        allBuildings.add(new HouseBuilding(21, 34, true));
        allBuildings.add(new ShopBuilding (21, 37, true));
        allBuildings.add(new HouseBuilding(18, 3, true));
        allBuildings.add(new HouseBuilding(18, 6, true));
        allBuildings.add(new HouseBuilding(26, 1, true));
        allBuildings.add(new HouseBuilding(43, 7, false));
        allBuildings.add(new MedicalBuilding(43, 12, true));
        allBuildings.add(new HouseBuilding(43, 29, false));
        allBuildings.add(new HouseBuilding(43, 38, false));
        allBuildings.add(new HouseBuilding(43, 35, false));
        allBuildings.add(new ShopBuilding(43, 32, true));
        allBuildings.add(new HouseBuilding(47, 32, true));
        allBuildings.add(new MedicalBuilding(47, 35, true));
        allBuildings.add(new HouseBuilding(47, 38, false));

        this.waterPositions = convertListTo2DArrayInt(listWaterPositions);
        this.treePositions = convertListTo2DArrayInt(listTreePositions);
        this.bridgePositions = convertListTo2DArrayInt(listBridgePositions);
    }

    private void defineRegionSeed4() {
            String[] regionMap = {
                    "MMMMMMMMMMMMMMMMMMMMMMMMGGGGGFFFFFFFFFFFFFFFFFFFFFF",
                    "MMMMMMMMMMWWWWMMMMTTTTMMMMGGGGGGGGGFFFFFFFFFFFFFFFF",
                    "MMMMMMMMMWWWWWWWMMMMMMMMGGGJGGGGGGGGFFFDDDDFFFTFFFF",
                    "MMMMMMTTWWWWWWWWWMMMMMMGGGGGGGGGGGGDDDDDDDDFFFTFFTF",
                    "MMMMMMMMWWWWWWWWWMMMMMMGGGGGGGGGGGGGDWDJDDDDDDDDFFF",
                    "MMMMMMMMMWWWWWWWMMMMMMMGGGGGGTGGGGGGDWWWDDDDDDDDTDF",
                    "MMMMMMJMMWWWWWWMMMMMMMGGGGGGGGGGGGGGWWWWDDDDTDDDDTF",
                    "MMMMMMMMMMWWMMMMMTMMGGGGGGGGGGGGGGWWWWWWDDDDDDTTDDD",
                    "MMMMMMMMMMMMMMMMMMGGGGGGTGGGGGGGGGGWWWWWWDDDDTTDDDD",
                    "MMMMMMMMMMMMMMMMMMGGGGGGGGGGGGGGGGWWWWWWWDDDDDDDDTD",
                    "MMMMMMMMMMMMMMMMMTGGGGGGGGGJGGGGDDDDWWWWWDDDDDDDDDD",
                    "MMMMMMMMTMMMMMMMMGGGGGGGGGGGGGDDDDDDWWDDTTDDDDDDDDD",
                    "MMMMMMMMMMMMMMMMMTGGGGGGGTGGGGGDDTTDDDDDDDDTTDDDDDD",
                    "MMMMMMMMMMMMMMGGGTTGGGGGGGGGGGDDDJDDDDDDDDTTDDDJDDD",
                    "MMMMMMMMMMMMMMGGGTTGGGGGGGGGGGGDDDDDDDDTTDDDDDDDDDD",
                    "MMMMMMMMMMMMMGGGGGGGGGGGGGGTGGGGGGDDTTTDDDDTTDDDDDD",
                    "MMMTTMMMMMMMGGGGGGGGGWWWWWGGGGGGGGGGDDDDTTDDDDDDDDD",
                    "MMMMMMMMMMGGGGGGGGGGWWWWWWWWGGGGGGFGGGGDDTTTTTTTDDD",
                    "MMMMMMMMMGGGGGGGGGGGGGWWWWWWGGGFFFFFFGGGGDDTTDDDDDD",
                    "MMMMMMMGGGGGGTTGGGGGGWWWWWGGGFFFFFFFFFGGGGDDDDTTDDD",
                    "MMMMMMMGGGGGGGGGGGGGGGGGGGGGGGGFFFFGGGGGGGGDDTTTDDD",
                    "MMMMMMMFFGGGGGGGGGGGGGGJGGGGGGGGGGFFFFFFFFFFDDDFFFF",
                    "MMMMFFFFFFGGGGGGGGGGGGGGGGGGGGGGGGFFFFFFFFFFFFFFFFF",
                    "MMMMFFFFTFTTTGGGGTGGGGGGGGGGGGGGGGGGJFFFFFFFFFFFFFF",
                    "WWWWFFFFFFFFFFFFGGGGGGGGTTGGGGGGGGGGGGFFFFFFFFFJFFF",
                    "FFFWTTFFFFFTFFFFFFGGGDDTTGGGGGGGGGGGGGGGFFFFFFFFFFF",
                    "FFFWFFFFTFFJFFFFFTTTTDDDDDDGGGGGGTGGGGGGFWWWWBBBWWW",
                    "FFFWWFFFFTFFFFFFFFGGGTTDDDDDDGGGGGGGGWWWWWWWWBBBWWW",
                    "FFFFWWFFFFFFFFFFGGGGTTDDDDDDTTWWWWWWWWWWWMMMMMMMMMM",
                    "FFFFFWTTTFTFFFFFFFDDTTTTTTWWWWWWWWGGGMMMMMMMMMMMMMM",
                    "FFTFFBFFFFFFFTDDDDDDDDWWWWWWWDDDMMMMMMMMTJMMMMMMMMM",
                    "FFFFFBFFFFFFFFFFGGDDDBBBBBBBDDDDMMMMTMMMMMMMMMMMMMM",
                    "FFFFFWWTTFFFFFFGGGGGWWWWWWDDDDDDMMMMMMMMMMMMMMMMMMM",
                    "FFFFFFWFTTTFFGGGGWWWWWWWDDDDDDDDDDDDTTMMMMMMMTMMMMM",
                    "FFFFFFWFTTGGGGGGWWWGGGGTTDDDDDDDDDDDDDDDDDDMMMMMMMM",
                    "FFFFFFWWGGGGGGGWWWWWGGDDDDTTTTDDDDDDDJDDDDMMMMMMMMM",
                    "FFFFFFFWWWWWWWWWWWWWGGDDDDDDDDDDDDTTDDDDDDDDMMMMMMM",
                    "FFFTTTTFTTTGGWGGWWGWGGGGGDDDDTTTTDDDDTTDDDDDDDMMMMM",
                    "FFFFFFFFFGGGWWGGGGGWWWDDTTTTDDDDDDDDTTDDDMMMMMMMMMM",
                    "FFFFFFFFGWWWWGGGGGGWWWDTTTTDDDDDDDDTTDDMMMMMMMMMMMM",
                    "FFFFFFWWWWGGTTGGGWWWWWWDDDTTTTTMMMMMMMMMMMMMMMJMMMM",
                    "FFFWBWWGGGGGGGGGWWWWWWWWDDTTDDDDMMMMMMMTMMMMMMMMMMM",
                    "TTTWFGGGGGGGGGGWWWWWWWGGGGGFFFFFFMMMMMMMMMMMMMMMMMM",
                    "WWWWTGGGGGGGGGWWWWWWWGFFFFFFFFMMMMMMMMMMMMMMMMMMMMM",
                    "TTTTGGGGGGGGGGWGGGGGGGFFFFFFFFFMMMMMMMMMMMMTTMMMMMM",
                    "TTTFTGGGGGJGGGWGGGGFFFFFFFFFTFFFMMMMMMMMMMMMMMMMMMM",
                    "FTFTTTGGGGGGGGWWWWWGGGGGFFFFFFFFFFFGGGMMMMMMMMMMMMM",
                    "FFGGGGGGGGGGGGGGGGWWWWWWWWWWWWFFFTGGGGGGGGGMMMMMMMM",
                    "GGGGGGGGGGTTGGGGGGGGGGGGFFFFFBGGGGGGTTGGGGGGGJMMMMM",
                    "GGGGGGGGGGGGGGGGGGGGGGGGGGFFFWGGGGGGGGGGGTTGGGGGMMM"
            };
        String jokemonString = "GDMGDDGFFFMDMGM";
        defineRegion(regionMap, jokemonString);
    }

    private void setSeed5() {
        String path = "seeds/seed5.seed";
        loadSeedFromFile(path);

        this.waterPositions = convertListTo2DArrayInt(listWaterPositions);
        this.treePositions = convertListTo2DArrayInt(listTreePositions);
        this.bridgePositions = convertListTo2DArrayInt(listBridgePositions);
    }

    private void loadSeedFromFile(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            List<String> mapLines = new ArrayList<>();
            boolean map = false;
            boolean jokemonString = false;
            boolean buildings = false;
            boolean wildJokemons = false;
            boolean npcs = false;

            int mapCounter = 0;

            for (String line : lines) {
                if (line.equalsIgnoreCase("MAP")) {
                    map = true;
                    jokemonString = false;
                    buildings = false;
                    wildJokemons = false;
                    npcs = false;
                } else if (line.equalsIgnoreCase("JOKEMONSTRING")) {
                    map = false;
                    jokemonString = true;
                    buildings = false;
                    wildJokemons = false;
                    npcs = false;
                } else if (line.equalsIgnoreCase("BUILDINGS")) {
                    map = false;
                    jokemonString = false;
                    buildings = true;
                    wildJokemons = false;
                    npcs = false;
                } else if (line.equalsIgnoreCase("WILDJOKEMONS")) {
                    map = false;
                    jokemonString = false;
                    buildings = false;
                    wildJokemons = true;
                    npcs = false;
                } else if (line.equalsIgnoreCase("NPCS")) {
                    map = false;
                    jokemonString = false;
                    buildings = false;
                    wildJokemons = false;
                    npcs = true;
                }
                else {
                    if (map && mapCounter < 50) {
                        mapLines.add(line);
                        mapCounter++;
                    } else if (jokemonString) {
                        defineRegion(convertListToArrayString(mapLines), line);
                    } else if (buildings ) {
                        String[] parts = line.split("\\|");
                        if (parts.length >= 3) {
                            allBuildings.add(createBuilding(line));
                        }
                    } else if (wildJokemons) {
                        allWildJokemon.add(createWildJokemon(line));
                    } else if (npcs){
                        allNPCs.add(createNPC(line));
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading seed: " + e.getMessage());
        }
    }

    private NPC createNPC(String line) {
        return new NPC(listNPCPositions.get(allNPCs.size())[0], listJokemonPositions.get(allNPCs.size())[1], createTrainer(line), player.getTrainer());
    }

    private Trainer createTrainer(String line) {
        // BOT1|12|{(Bulbasaur,10)(Squirtle,8)}
        String[] data = line.split("\\|");
        String name = data[0];
        int lvl = Integer.parseInt(data[1]);

        List<Jokemon> jokemons = new ArrayList<>();
        String[] jokemonData = data[2].substring(1, data[2].length() - 1).split("\\)\\(");
        for (int i = 0; i < jokemonData.length; i++) {
            jokemonData[i] = jokemonData[i].replaceAll("\\(", "").replaceAll("\\)", "");
        }
        for (String i : jokemonData) {
            jokemons.add(createJokemon(i));
        }
        return new Trainer(Trainer.Status.BOT, name, lvl, jokemons);
    }

    private Jokemon createJokemon(String fullData) {
        String[] data = fullData.split(",");
        Jokemon jokemon = null;
        switch (data[0]) {
            case "Bulbasaur":
                jokemon = new GrassJokemon(data[0], Integer.parseInt(data[1]), 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"));
                break;
            case "Ivysaur":
                jokemon = new GrassJokemon(data[0], Integer.parseInt(data[1]), 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur"));
                break;
            case "Venusaur":
                jokemon = new GrassJokemon(data[0], Integer.parseInt(data[1]), 3, 80, 82, 83, 100, 100, 80, movesMap.get("Venusaur"));
                break;
            case "Glomanda":
                jokemon = new FireJokemon(data[0], Integer.parseInt(data[1]), 1, 39, 52, 43, 65, 60, 50, movesMap.get("Glomanda"));
                break;
            case "Charmeleon":
                jokemon = new FireJokemon(data[0], Integer.parseInt(data[1]), 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon"));
                break;
            case "Charizard":
                jokemon = new FireJokemon(data[0], Integer.parseInt(data[1]), 3, 78, 84, 78, 109, 85, 100, movesMap.get("Charizard"));
                break;
            case "Squirtle":
                jokemon = new WaterJokemon(data[0], Integer.parseInt(data[1]), 1, 44, 48, 65, 50, 64, 43, movesMap.get("Squirtle"));
                break;
            case "Wartortle":
                jokemon = new WaterJokemon(data[0], Integer.parseInt(data[1]), 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle"));
                break;
            case "Blastoise":
                jokemon = new WaterJokemon(data[0], Integer.parseInt(data[1]), 3, 79, 83, 100, 85, 105, 78, movesMap.get("Blastoise"));
                break;
        }
        return jokemon;
    }

    private WildJokemon createWildJokemon(String line) {
        return new WildJokemon(listJokemonPositions.get(allWildJokemon.size())[0], listJokemonPositions.get(allWildJokemon.size())[1],createJokemonForWildJokemon(line), false);
    }

    private Jokemon createJokemonForWildJokemon(String line) {
        if (line.equalsIgnoreCase("Bulbasaur")) {
            return new GrassJokemon(line, 1, 1, 44, 48, 65, 50, 64, 43, movesMap.get("Bulbasaur"));
        } else if (line.equalsIgnoreCase("Glomanda")) {
            return new FireJokemon(line, 1, 1, 39, 52, 43, 60, 50, 65, movesMap.get("Glomanda"));
        } else {
            return new WaterJokemon(line, 1, 1, 44, 48, 65, 50, 64, 43, movesMap.get("Squirtle"));
        }
    }

    private Building createBuilding(String line) {
        String[] data = line.split("\\|");
        String name = data[0];
        int[] pos = convertStringToArrayInt(data[1]);
        boolean canEnter = Boolean.parseBoolean(data[2]);
        if (name.equalsIgnoreCase("MedicalBuilding")) {
            return new MedicalBuilding(pos[0], pos[1], canEnter);
        } else if (name.equalsIgnoreCase("ShopBuilding")) {
            return new ShopBuilding(pos[0], pos[1], canEnter);
        } else {
            return new HouseBuilding(pos[0], pos[1], canEnter);
        }
    }

    private void defineRegion(String[] map, String jokemonString) {
        for (int x = 0; x < map.length; x++) {
            String row = map[x];
            for (int y = 0; y < row.length(); y++) {
                char regionChar = row.charAt(y);

                switch (regionChar) {
                    case 'D':
                        assignRegion(x, y, x, y, Screen.Region.DESERT);
                        break;
                    case 'G':
                        assignRegion(x, y, x, y, Screen.Region.GRASSLAND);
                        break;
                    case 'F':
                        assignRegion(x, y, x, y, Screen.Region.FOREST);
                        break;
                    case 'M':
                        assignRegion(x, y, x, y, Screen.Region.MOUNTAIN);
                        break;
                    case 'W':
                        listWaterPositions.add(new int[]{x,y});
                        break;
                    case 'T':
                        listTreePositions.add(new int[]{x,y});
                        break;
                    case 'B':
                        listBridgePositions.add(new int[]{x,y});
                        break;
                    case 'N':
                        listNPCPositions.add(new int[]{x,y});
                        break;
                    case 'J':
                        listJokemonPositions.add(new int[]{x,y});
                        char jokemonChar = jokemonString.charAt(listJokemonPositions.size() - 1);

                        switch (jokemonChar) {
                            case 'M':
                                assignRegion(x, y, x, y, Screen.Region.MOUNTAIN);
                                break;
                            case 'F':
                                assignRegion(x, y, x, y, Screen.Region.FOREST);
                                break;
                            case 'G':
                                assignRegion(x, y, x, y, Screen.Region.GRASSLAND);
                                break;
                            case 'D':
                                assignRegion(x, y, x, y, Screen.Region.DESERT);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private int[] convertStringToArrayInt(String string) {
        String[] stringArray = string.replaceAll("[{}]", "").split(",");
        int[] array = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            array[i] = Integer.parseInt(stringArray[i].trim());
        }

        return array;
    }

    private int[][] convertListTo2DArrayInt(List<int[]> list) {
        int[][] grid = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            grid[i] = list.get(i);
        }
        return grid;
    }

    private String[] convertListToArrayString(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
