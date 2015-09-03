package ec;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Arrays;

import ec.Individual;

public class ParentsGenerator {

	ContestEvaluation evaluation_;
	Individual[] population;
	Individual[] parents = new Individual[this.population.length];
	int nrParents = 0;
	
	public ParentsGenerator() {
		
	}
	
	void addParent(double[] genotype) {
		if(nrParents < parents.length) {
			parents[nrParents] = new Individual(evaluation_, genotype);
			nrParents++;
		}
	}
	
	double getRandomDouble() {
		Random random = new Random();
		return random.nextDouble();
	}
	
	int getRandomInt(int maxVal) {
		Random random = new Random();
		return random.nextInt(maxVal);
	}
	
	Individual[] getParents() {
		return parents;
	}
	
}
