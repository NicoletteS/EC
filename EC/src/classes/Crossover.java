package classes;

import org.vu.contest.ContestEvaluation;

import classes.Population;
import java.util.Arrays;
import java.util.Random;

import classes.Individual;

public class Crossover  {

	public static double MUTATION_RATE = 0.03;
	private static final double UNIFORM_RATE = 0.6;
	private static final int POOL = 5;
	private static final boolean elitism = true;
	private static int EVAL_DONE = 0;
	static public Random random = new Random(System.currentTimeMillis());

	//Omdat ik gebruik maak van twee Population methodes, gaat het waarschijnlijk mis met het verwijzen naar de juiste constructor. Oplossing nodig
	//om dit tot 1 constructor te maken?
	
	
	// Evolve population
	public static Population evolve(Population pop, boolean isMultimodal) {
		Population newPop = new Population(pop, isMultimodal);
		
		//In deze methode gaat het mis. Het lijkt erop dat zodra de populatie volzit, er een loop ontstaat waarbij er steeds individuals worden toegevoegd
		//terwijl dit niet meer past. Komt dit door de boolean elitism? Waar laat ik de slechtste fitness afsterven?

		//Keep best individual
		if (elitism) {
			newPop.addIndividual(pop.getFittest(), 0);
	//		LOOP
		}
	//	LOOP
		//crossover population
		int elitismOffset;
		if (elitism) {
			elitismOffset = 1;
		} else {
			elitismOffset = 0;
		}
		
		// Loop population and create new ind with crossover
		for (int i=elitismOffset; i<pop.size(); i++) {
			Individual parent1 = selection(pop);
			Individual parent2 = selection(pop);
			Individual child;
			if(isMultimodal) {
				child = BLXcrossover(parent1, parent2, 0.5, isMultimodal);
			}
			child = uniformCrossover(parent1, parent2);
//			System.out.println(child.evaluation_);
			newPop.addIndividual(child, i);
		}
		
		//Mutate population
		for (int i = elitismOffset; i < newPop.size(); i++) {
			mutate(newPop.getIndividual(i));
		}	
		return newPop;
	}
	
	//crossover parents
	public static Individual BLXcrossover(Individual parent1, Individual parent2, double alpha, boolean isMultimodal) {
		Individual child = new Individual(isMultimodal);
		Individual x1;
		Individual x2;
		
		if(parent1.getFitness() < parent2.getFitness()) {
			x1 = parent1;
			x2 = parent2;
		} else {
			x1 = parent2;
			x2 = parent1;
		}
		
		for(int i = 0; i<Individual.GEN_LENGTH;i++) {
			double gene;
			if(random.nextDouble()<x2.CR) {
				double min = x1.getGene(i) - alpha * (x2.getGene(i)-x1.getGene(i));
				double max = x2.getGene(i) - alpha * (x2.getGene(i)-x1.getGene(i));
				double ran = random.nextDouble();
				
				gene = min + (max- min) * ran;
				child.CR = (x1.CR + x2.CR)/2;
			} else {
				if (random.nextBoolean()) {
				gene = x1.getGene(i);
				child.CR = x1.CR;
				} else {
				gene = x2.getGene(i);
				child.CR = x2.CR;
				}
			}
			child.setGene(i, gene);
		}
		return child;
	}
	
	
	private static Individual uniformCrossover(Individual parent1, Individual parent2) {
		Individual child = new Individual(parent1);
		//CHECK
		//loop genes
		for (int i = 0; i< parent1.size(); i++) {
			if (Math.random() <= UNIFORM_RATE) {
				child.setGene(i, parent1.getGene(i));
			} else {
				child.setGene(i, parent2.getGene(i));
			}
		}
		return child;
	}
	
	//Mutate individual
	private static void mutate(Individual ind) {
		//Loop genes
		for (int i = 0; i<ind.size(); i++) {
			if(Math.random() <=MUTATION_RATE) {
				//Create random gene
				double gene = Math.round(Math.random());
				ind.setGene(i, gene);				
			}
		}
	}
	
	//Select parents for crossover
	private static Individual selection(Population pop) {
		//CHECK
		//Create pool from population
		Population pool = new Population(POOL);
		//Get random parent
		// TODO LIFETIME forloop?
		for(int i = 0; i < POOL; i++) {
			int randomInd = (int) (Math.random() * pop.size());
			pool.addIndividual(pop.getIndividual(randomInd), i);
		}
		//Get fittest
		Individual fittest = pool.getFittest();
		//CHECK
		return fittest;
	}
	
	/*
	private static Individual selection(Population pop) {
		Individual parent1;
		Individual parent2;
		
		for(int i=0; i < )
	}
	public Crossover(Individual[] population, ContestEvaluation evaluation) {
		this.population = population;
		evaluation_ = evaluation;
	}
	public Individual[] makeParents() {
		Individual parent1;
		Individual parent2;
		for (int i = 0; nrParents < this.population.length; i++) {
			parent1 = getFittest();
			parent2 = getFittest();
			combineParents(parent1, parent2);
		}
		return parents;
	}
	private void combineParents(Individual parent1, Individual parent2) {
		double[] genes1 = new double[parent1.GEN_LENGTH];
		double[] genes2 = new double[parent2.GEN_LENGTH];
		
		double[] random = new double
	} */
}