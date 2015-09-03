package ec;

import org.vu.contest.ContestEvaluation;

import java.util.Arrays;
import java.util.Random;
import ec.Individual;

public class Population {

	private int populationSize;
	private Individual[] population;
	private ContestEvaluation evaluation_;
	private int evaluations;
	private int evaluations_limit_;
	
	public Population(int populationSize, ContestEvaluation eval, int evalLimit) {
		this.populationSize = populationSize;
		evaluation_ = eval;
		evaluations_limit_ = evalLimit;
		evaluations = 0;
		
		population = new Individual[this.populationSize];
		Arrays.fill(population, new Individual(evaluation_));
	}
	
	public void evalPopulation() {
		for (int i=0; i<population.length && evaluations < evaluations_limit_; i++) {
			population[i].evaluate();
			evaluations++;
		}
	}
	
	public void selectParents() {
		UniformCrossover UC = new Uniform
	}
	
}
