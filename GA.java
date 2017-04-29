/*
* GA.java
* Manages algorithms for evolving population
*/

package tsp;

public class GA {

    /* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = false;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop,double mutation_rate) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        // System.out.println("=========================================================");
        //     System.out.print("PEMBENTUKAN POPULASI BARU MULAI");
        // System.out.println("=========================================================");
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            // Tour parent1 = tournamentSelection(pop);
            // Tour parent2 = tournamentSelection(pop);
            Tour parent1 = rouletteWheelSelection(pop);
            Tour parent2 = rouletteWheelSelection(pop);
            
            // System.out.println("=========================================================");
            // System.out.print("Cross Over parent: \nRute "+parent1+" \ndengan\n Rute"+parent2+"\n");
            // System.out.println("=========================================================");
            // Crossover parents
            Tour child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveTour(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i),mutation_rate);
        }

        // System.out.println("=========================================================");
        //     System.out.print("PEMBENTUKAN POPULASI BARU SELESAI");
        // System.out.println("=========================================================");


        // newPopulation.printPopulation();
        System.out.print("\n\n\n");

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Tour crossover(Tour parent1, Tour parent2) {
        // Create new child tour
        Tour child = new Tour();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.tourSize());
        int endPos = (int) (Math.random() * parent1.tourSize());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.tourSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.tourSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Tour tour,double mutation_rate) {
        // Loop through tour cities
        for(int tourPos1=0; tourPos1 < tour.tourSize(); tourPos1++){
            // Apply mutation rate
            if(Math.random() < mutation_rate){
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.tourSize() * Math.random());

                // Get the cities at target position in tour
                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                // Swap them around
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

    // Selects candidate tour for crossover
    private static Tour tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // Get the fittest tour
        Tour fittest = tournament.getFittest();
        return fittest;
    }

    private static Tour rouletteWheelSelection(Population pop) {
        // Create a tournament population
        double random = Math.random();
        Tour selected=pop.getTour(0);
        // System.out.println("random : ");
        // System.out.print(random);
        double[] fkum=new double[pop.populationSize()];
        double kumulatif=0;
        // get sigma fitness
        double sigmaFitness=0;
        for(int j=0;j<pop.populationSize();j++){
            sigmaFitness+=pop.getTour(j).getFitness();
        }
        // System.out.println("sigmaFitness : "+sigmaFitness);
        //get kumulatif
        for(int i=0;i<pop.populationSize();i++){
            kumulatif+=(pop.getTour(i).getFitness()/sigmaFitness);
            fkum[i]=kumulatif;
        }
        // System.out.println("kumulatif : "+fkum.toString());
        // for(int x=0;x<fkum.length;x++){System.out.print(fkum[x]);System.out.println(" ");}
        
        // get minimal kumulatif-random
            int indeksTerpilih=0;
        for(int k=0;k<pop.populationSize();k++){
            
            if(k>0){
                double cek=fkum[k]-random;
                // double cekSebelumnya=fkum[k-1]-random;
                if(cek>0){
                    selected=pop.getTour(k);
                    indeksTerpilih=k;
                    break;
                }
            }

        }
         // System.out.println("indeks terpilih : "+indeksTerpilih);
        
        return selected;
    }

}