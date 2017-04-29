/*
* TSP_GA.java
* Create a tour and evolve a solution
* http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5
*/



package tsp;


import java.util.Scanner;  

public class TSP_GA {

    public static void main(String[] args) throws InterruptedException {

        // parameter
        double mutationRate;
        double crossOverRate;
        int generationCount;
        int popSize;


        Scanner sc=new Scanner(System.in);  
     
       System.out.println("Nilai Mutation Rate : ");  
       mutationRate=sc.nextDouble();  
       System.out.println("Nilai crossOver Rate : ");  
       crossOverRate=sc.nextDouble(); 
        System.out.println("Jumlah Generasi : ");  
       generationCount=sc.nextInt(); 
       System.out.println("Ukuran Populasi : ");  
       popSize=sc.nextInt();

        // Create and add our cities
        City city = new City(60, 200,"A");
        TourManager.addCity(city);
        City city2 = new City(180, 200,"B");
        TourManager.addCity(city2);
        City city3 = new City(80, 180,"C");
        TourManager.addCity(city3);
        City city4 = new City(140, 180,"D");
        TourManager.addCity(city4);
        City city5 = new City(20, 160,"E");
        TourManager.addCity(city5);
        City city6 = new City(100, 160,"F");
        TourManager.addCity(city6);
        // City city7 = new City(200, 160);
        // TourManager.addCity(city7);
        // City city8 = new City(140, 140);
        // TourManager.addCity(city8);
        // City city9 = new City(40, 120);
        // TourManager.addCity(city9);
        // City city10 = new City(100, 120);
        // TourManager.addCity(city10);
        // City city11 = new City(180, 100);
        // TourManager.addCity(city11);
        // City city12 = new City(60, 80);
        // TourManager.addCity(city12);
        // City city13 = new City(120, 80);
        // TourManager.addCity(city13);
        // City city14 = new City(180, 60);
        // TourManager.addCity(city14);
        // City city15 = new City(20, 40);
        // TourManager.addCity(city15);
        // City city16 = new City(100, 40);
        // TourManager.addCity(city16);
        // City city17 = new City(200, 40);
        // TourManager.addCity(city17);
        // City city18 = new City(20, 20);
        // TourManager.addCity(city18);
        // City city19 = new City(60, 20);
        // TourManager.addCity(city19);
        // City city20 = new City(160, 20);
        // TourManager.addCity(city20);

        // Initialize population
        Population pop = new Population(popSize, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop,0.015);
        for (int i = 0; i < generationCount; i++) {
            System.out.print("Generasi Ke :");
            System.out.print(i);
            System.out.print("\n");
            System.out.println("Jarak Fitness terbaik yang dihasilkan : " + pop.getFittest().getFitness());
            System.out.println("dengan Jarak : " + pop.getFittest().getDistance());
            pop.printPopulation();

            pop = GA.evolvePopulation(pop,0.015);
            Thread.sleep(1000);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}