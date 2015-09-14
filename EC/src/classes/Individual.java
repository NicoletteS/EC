package classes;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Arrays;

public class Individual {
	
	static int GEN_LENGTH = 10;
	static private double MIN_VALUE = -5;
	static private double MAX_VALUE = 5;
	
	private ContestEvaluation evaluation_;
	private double[] genes;
	private double fitness;
	
	public Individual() {
		genes =  new double[GEN_LENGTH];
	}
	//Generate random individual
	public void generateIndividual(ContestEvaluation eval) {
//		System.out.println("generateInd");
		evaluation_ = eval;
//		genes = new double[GEN_LENGTH];
		Random rand = new Random();
		for (int i=0; i<GEN_LENGTH;i++) {
			double gene = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * rand.nextDouble();
			genes[i] = gene;
		}
//		Arrays.fill(genes, getRandomGen());
	}
	
	//Get genotype
	public void getGenotype(ContestEvaluation evaluation, double[] genotype) {
		System.out.println("getGene class");
		evaluation_ = evaluation;
		this.genes = genotype;
	}
	
	//Get fitness level
	public void evaluate() {
		System.out.println("evaluate class");
		if(evaluation_ == null){
			System.out.println("IS NULL");
			System.exit(1);
		}
		Object test =  evaluation_.evaluate(genes);
		fitness = ((Double)test).doubleValue();
		System.out.println(fitness);
		
	}
	
/*	private double getRandomGen() {
		Random rand = new Random();
		return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * rand.nextDouble();
	} */
	
	public double getGene(int i) {
		//CHECK
		return genes[i];
	}
	

	public void setGene(int i, double d) {
	//CHECK	
		if(d > MAX_VALUE) {
			genes[i] = (d-MIN_VALUE)%(MAX_VALUE-MIN_VALUE)+MIN_VALUE;
		} else if (d < MIN_VALUE) {
			genes[i] = (d-MIN_VALUE)%(MAX_VALUE-MIN_VALUE)+MAX_VALUE;
		} else {
			genes[i] = d;
		}
	} 
	
	public double getFitness() {
		//CHECK
		return fitness;
	}
	
	public int size() {
        return genes.length;
    }

	
}