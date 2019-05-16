package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Score implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hashtable<String, ArrayList<Double>> scores;
	
	public Score() {
		scores = new Hashtable<String, ArrayList<Double>>();
	}

	public Hashtable<String, ArrayList<Double>> getScores() {
		return scores;
	}

	public void setScores(Hashtable<String, ArrayList<Double>> scores) {
		this.scores = scores;
	}
	
	public void addScore(String email,double score) {
		scores.get(email).add(score);
	}
	
}
