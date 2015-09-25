import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

import classes.Individual;
import classes.Crossover;
import classes.Population;

public class player1 implements ContestSubmission
{
	public static Random rnd_ = new Random();
	private ContestEvaluation evaluation_;
	private int evaluations_limit_;
	int popSize = 100;
	boolean isMultimodal;
	

	public player1()
	{
		rnd_ = new Random();
	}

	public void setSeed(long seed)
	{
		// Set seed of algorithms random process
		rnd_.setSeed(seed);
	}

	public void setEvaluation(ContestEvaluation evaluation)
	{
		// Set evaluation problem used in the run
		evaluation_ = evaluation;

		// Get evaluation properties
		Properties props = evaluation.getProperties();
		evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
		boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Change settings(?)
		if(isMultimodal){
			
		}else{
			// Do sth else
		}
	}

	public void run()
	{
		Population pop = new Population(popSize, evaluation_, evaluations_limit_, isMultimodal);
		pop.evalPopulation();
		// Run your algorithm here

	//	int currentEvalstatus = ;
		while (pop.getEvaluation() < pop.getEvaluationLimit()) {
//			System.out.println("Hierdan " + pop.getEvaluation() + "/" + evaluations_limit_ );
			pop = Crossover.evolve(pop, isMultimodal);
			pop.evalPopulation();
			
//			System.out.println("evaluated: ");
		}
		
/*		
		int evals = 0;
		while(evals<evaluations_limit_){
			// Select parents
			// Apply variation operators and get children
		//	double child[] = ...
	//		Double fitness = evaluation_.evaluate(child);
			evals++;
			// Select survivors
		} */
	}
}