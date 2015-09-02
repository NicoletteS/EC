package ec;

import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

public class SubmissionTemplate
{
	Random rnd_;
	ContestEvaluation evaluation_;
	private int evaluations_limit_;
	
	public SubmissionTemplate()
	{
		rnd_ = new Random();
	}
	
	public void setSeed(long seed)
	{
		// Set seed of algortihms random process
		rnd_.setSeed(seed);
	}

	//TODO Used to pass evaluation object to algorithm
	public void setEvaluation(ContestEvaluation evaluation)
	{
		// Set evaluation problem used in the run
		evaluation_ = evaluation;
		
		// Get evaluation properties
		Properties props = evaluation.getProperties();
		
		evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
		boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Change settings(?)
		if(isMultimodal){
			// Do sth
		}else{
			// Do sth else
		}
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));

		// Do sth with property values, e.g. specify relevant settings of your algorithm
	}
	
	public void run()
	{
		// Run your algorithm here

		int evals = 0;
		while(evals<evaluations_limit_){
			// Select parents
			// Apply variation operators and get children
		//	double child[] = ...
	//		Double fitness = evaluation_.evaluate(child);
			evals++;
			// Select survivors
		}
		
		// Getting data from evaluation problem (depends on the specific evaluation implementation)
		// E.g. getting a vector of numbers
		// Vector<Double> data = (Vector<Doulbe>)evaluation_.getData("trainingset1");

		// Evaluating your results
		// E.g. evaluating a series of true/false predictions
		// boolean pred[] = ...
		// Double score = (Double)evaluation_.evaluate(pred);
	}
}