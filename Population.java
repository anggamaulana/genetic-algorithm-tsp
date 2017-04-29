/*
* Population.java
* Manages a population of candidate tours
*/

package tsp;

public class Population {

    // Holds population of tours
    Tour[] tours;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // Saves a tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // Gets a tour from population
    public Tour getTour(int index) {
        return tours[index];
    }

    // Gets the best tour in the population
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            // System.out.print("compare "+fittest.getFitness()+"<="+getTour(i).getFitness());
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                // System.out.print(":yes");
                fittest = getTour(i);
            }else{
                // System.out.print(":no");
            }
            // System.out.print("\n");
        }
        return fittest;
    }

    public void printPopulation(){

         System.out.print("population size: ");
        System.out.print(tours.length);
        System.out.print("\n");
        for(int i=0;i<tours.length;i++){
            System.out.print(tours[i]);
             System.out.print("\n");
              

        }
        
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }
}