package pokerBase;

import java.io.*;
import java.util.ArrayList;

public class exHand extends Exception {

	private ArrayList<Hand> Hands; 
	
	public exHand(ArrayList<Hand> Hands){
		this.Hands = Hands;
	}
	
	public  ArrayList<Hand> getHands(){
		return Hands;
	}
}
