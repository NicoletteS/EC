package classes;

import org.vu.contest.ContestEvaluation;

import java.util.Arrays;
import java.util.Random;
import classes.Individual;

public class Population {

	private int populationSize;
	private Individual[] population;
	private ContestEvaluation evaluation_;
	private int evaluations;
	private int evaluations_limit_;
	
	public Population(Population pop){
		this.population = new Individual[pop.size()];
		this.populationSize = pop.size();
		this.evaluation_ = pop.getContestEvaluation();
		this.evaluations_limit_ = pop.evaluations_limit_-pop.getEvaluation();
		
			for(int i=0; i<populationSize;i++) {
			
			Individual ind = new Individual();
//			if (evaluation_ == null){
//				System.out.println("Al niet gevonden");
//			
//			}
			ind.generateIndividual(evaluation_);
			addIndividual(ind, i);
		}
	//	System.out.println("TEST "+ this.evaluations);
	}
	
	
	public ContestEvaluation getContestEvaluation(){
		
		return this.evaluation_;
	}
	public Population(int populationSize) {
		evaluations = 5;
		if (populationSize == 0) {
	//		System.out.println("POPclass pop=0");
			
		} else {
			this.population = new Individual[populationSize];
	//		System.out.println("POPclass1");
		}
		this.populationSize = populationSize;
	} 
	
	public Population(int populationSize, ContestEvaluation eval, int evalLimit) {
	//	System.out.println("POPCLASS2");
		evaluation_ = eval;
		evaluations_limit_ = evalLimit;
		evaluations = 0;
		
		population = new Individual[populationSize];
		Individual first = new Individual();
		addIndividual(first, 0);
		first.evaluation_ = eval;
		for(int i=1; i<populationSize;i++) {
			
			Individual ind = new Individual();
			ind.generateIndividual(evaluation_);
			addIndividual(ind, i);
		}
		
//		Arrays.fill(population, new Individual(evaluation_));
	}
	
	public void addIndividual(Individual ind, int i) {
		//CHECK
		population[i] = ind;
	}
	
	public void evalPopulation() {
		for (int i=0; i<population.length && evaluations < evaluations_limit_; i++) {
			population[i].evaluate();
//			System.out.println("Eval: " + evaluations);
			evaluations++;
		}
		//Kill of lowest individual
	//	evolve();
	}
	/*
	public void selectParents() {
		Crossover CO = new Crossover(population, evaluation_);
		population = CO.makeParents();
	}
	*/
	public int getEvaluation() {
		return evaluations;
	}
	
	public int getEvaluationLimit() {
		return evaluations_limit_;
	}
	
	public Individual getIndividual(int i) {
		return population[i];
	}
	
	public Individual getFittest() {
		//CHECK
		Individual fittest = population[0];
		for (int i=0; i< size(); i++) {
			if (fittest.getFitness() <= getIndividual(i).getFitness()) {
				fittest = getIndividual(i);
			}
	//		fittest = population[population.length-i-1];
		}
		//CHECK
		return fittest;
	}
	
	public int size() {
        return population.length;
    }
	
}
