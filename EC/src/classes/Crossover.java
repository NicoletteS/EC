package classes;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Arrays;

import classes.Individual;

public class Crossover  {

	private static final double MUTATION_RATE = 0.5;
	private static final double UNIFORM_RATE = 0.5;
	private static final int LIFETIME = 5;
	private static final boolean elitism = true;
	private static int EVAL_DONE = 0;

	//Omdat ik gebruik maak van twee Population methodes, gaat het waarschijnlijk mis met het verwijzen naar de juiste constructor. Oplossing nodig
	//om dit tot 1 constructor te maken?
	
	
	// Evolve population
	public static Population evolve(Population pop) {
		Population newPop = new Population(pop);
		
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
			Individual child = crossover(parent1, parent2);
			newPop.addIndividual(child, i);
		}
		
		//Mutate population
		for (int i = elitismOffset; i < newPop.size(); i++) {
			mutate(newPop.getIndividual(i));
		}
		
		return newPop;
	}
	
	//crossover parents
	private static Individual crossover(Individual parent1, Individual parent2) {
		Individual child = new Individual();
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
		Population pool = new Population(LIFETIME);
		//Get random parent
		// TODO LIFETIME forloop?
		for(int i = 0; i < LIFETIME; i++) {
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
