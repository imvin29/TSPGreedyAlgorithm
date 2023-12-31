import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSPGreedyAlgorithm {
    private int[][] distanceMatrix;
    private List<Integer> visitedCities;
    private int totalDistance;

    public TSPGreedyAlgorithm(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.visitedCities = new ArrayList<>();
        this.totalDistance = 0;
    }

    public List<Integer> solveTSP() {
        int numCities = distanceMatrix.length;
        int startingCity = 0; // Starting from city 0

        visitedCities.add(startingCity);

        int currentCity = startingCity;
        while (visitedCities.size() < numCities) {
            int nextCity = findNearestUnvisitedCity(currentCity);
            visitedCities.add(nextCity);
            totalDistance += distanceMatrix[currentCity][nextCity];
            currentCity = nextCity;
        }

        totalDistance += distanceMatrix[currentCity][startingCity];
        visitedCities.add(startingCity);

        return visitedCities;
    }

    private int findNearestUnvisitedCity(int currentCity) {
        int numCities = distanceMatrix.length;
        int nearestCity = -1;
        int shortestDistance = Integer.MAX_VALUE;

        for (int city = 0; city < numCities; city++) {
            if (!visitedCities.contains(city) && distanceMatrix[currentCity][city] < shortestDistance) {
                nearestCity = city;
                shortestDistance = distanceMatrix[currentCity][city];
            }
        }

        return nearestCity;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public static void main(String[] args) {
        int[][] distanceMatrix = {
                {0, 2, 3, 4, 1},
                {2, 0, 2, 1, 3},
                {3, 2, 0, 5, 2},
                {4, 1, 5, 0, 4},
                {1, 3, 2, 4, 0}
        };

        TSPGreedyAlgorithm tsp = new TSPGreedyAlgorithm(distanceMatrix);
        List<Integer> route = tsp.solveTSP();

        System.out.println("Optimal route: " + route);
        System.out.println("Total distance: " + tsp.getTotalDistance());
    }
}
