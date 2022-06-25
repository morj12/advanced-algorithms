package Utils;

public class Constants {

    public static final int MAP_WIDTH = 1600;
    public static final int MAP_HEIGHT = 900;
    public static final int MARKER_SIZE = 25;

    public static final String[] cityNames = new String[]{
            "Genève", "Lausanne", "Montreux", "Martigny", "Brig",
            "Spiez", "Interlaken", "Locarno", "Lugano", "Biasca",

            "Airolo", "Erstfeld", "Neuchatel", "Biel", "Bern",
            "Luzern", "Olten", "Basel", "Schaffhausen", "Konstanz",

            "Rorschach", "Sargans", "Tirano", "Pontresina", "Klosters",
            "Scuol-Tarasp", "Landeck", "Winterhur", "Zürich", "Arth-Goldau"
    };

    public static final int[][] roads = new int[][]{
            // these distances are not straight lines
            {0, 1, 63}, {1, 2, 29}, {1, 12, 73}, {1, 14, 106}, {12, 14, 52},
            {12, 13, 32}, {13, 14, 37}, {13, 17, 90}, {13, 16, 64}, {17, 18, 170},

            {17, 16, 45}, {16, 28, 80}, {16, 15, 54}, {14, 5, 39}, {15, 14, 110},
            {5, 2, 131}, {3, 2, 41}, {4, 3, 88}, {7, 4, 89}, {6, 5, 18},

            {15, 6, 70}, {4, 5, 41}, {18, 28, 51}, {15, 28, 52}, {29, 28, 50},
            {27, 28, 29}, {21, 28, 92}, {19, 27, 45}, {19, 18, 55}, {19, 20, 41},

            {20, 27, 72}, {26, 20, 121}, {20, 21, 70}, {25, 26, 123}, {24, 21, 45},
            {25, 24, 56}, {25, 23, 75}, {23, 21, 120}, {4, 21, 212}, {4, 24, 256},

            {11, 29, 37}, {10, 11, 72}, {9, 10, 35}, {7, 9, 48}, {8, 9, 53},
            {7, 8, 47}, {22, 8, 129}, {23, 22, 57}, {15, 29, 33}, {26, 21, 119},

            {4, 23, 148}, {21, 29, 91}
    };

    public static int[][] cityCoordinates = new int[][]{
            {58, 634}, {231, 519}, {324, 564}, {362, 683}, {668, 608},
            {575, 465}, {636, 462}, {940, 674}, {982, 741}, {989, 599},

            {879, 534}, {895, 423}, {336, 336}, {434, 284}, {499, 354},
            {782, 335}, {658, 201}, {564, 126}, {895, 68}, {1067, 83},

            {1169, 160}, {1151, 331}, {1386, 660}, {1295, 550}, {1291, 400},
            {1425, 432}, {1510, 297}, {924, 147}, {864, 197}, {864, 328}
    };


}
