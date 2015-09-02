package ec;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Arrays;

public class Individual {
	
	static int GEN_LENGTH = 10;
	static private double MIN_VALUE = -5;
	static private double MAX_VALUE = 5;
	
	private ContestEvaluation evaluation_;
	private double[] genotype;
	private double fenotype;
	
	public Individual() {
		
	}
	
	public Individual(ContestEvaluation eval) {
		evaluation_ = eval;
		genotype = new double[GEN_LENGTH];
		Arrays.fill(genotype, getRandomGen());
	}
	
	public Individual(ContestEvaluation evaluation, double[] genotype) {
		evaluation_ = evaluation;
		this.genotype = genotype;
	}
	
	public void evaluate() {
		fenotype = ((Double) evaluation_.evaluate(genotype)).doubleValue();
	}
	
	private double getRandomGen() {
		Random rand = new Random();
		return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * rand.nextDouble();
	}
	
	private double getGene(int i) {
		return genotype[i];
	}
	
	public double getFenotype() {
		return fenotype;
	}
}