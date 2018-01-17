package com.concurrent_core.phaser;

import java.util.concurrent.Phaser;

public class Test7 {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(2){

			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("到达了未通过,phase="+phase+" registerParties="+registeredParties);
				return super.onAdvance(phase, registeredParties);
			}
		};
		
		System.out.println("A1 getPhase ="+phaser.getPhase() +"---getRegisterPaties="+phaser.getRegisteredParties()+ 
				"---getArriverdParties="+phaser.getArrivedParties());
		
		phaser.arrive();
		
		System.out.println("A1 getPhase ="+phaser.getPhase() +"---getRegisterPaties="+phaser.getRegisteredParties()+ 
				"---getArriverdParties="+phaser.getArrivedParties());
		
		System.out.println("A2 getPhase ="+phaser.getPhase() +"---getRegisterPaties="+phaser.getRegisteredParties()+ 
				"---getArriverdParties="+phaser.getArrivedParties());
		
		phaser.arrive();
		
		System.out.println("A2 getPhase ="+phaser.getPhase() +"---getRegisterPaties="+phaser.getRegisteredParties()+ 
				"---getArriverdParties="+phaser.getArrivedParties());
		
		System.out.println("B1 getPhase ="+phaser.getPhase() +"---getRegisterPaties="+phaser.getRegisteredParties()+ 
				"---getArriverdParties="+phaser.getArrivedParties());
		
		phaser.arrive();
		
		System.out.println("B1 getPhase ="+phaser.getPhase() +"---getRegisterPaties="+phaser.getRegisteredParties()+ 
				"---getArriverdParties="+phaser.getArrivedParties());
		
		System.out.println("B2 getPhase ="+phaser.getPhase() +"---getRegisterPaties="+phaser.getRegisteredParties()+ 
				"---getArriverdParties="+phaser.getArrivedParties());
		
		phaser.arrive();
		
		System.out.println("B2 getPhase ="+phaser.getPhase() +"---getRegisterPaties="+phaser.getRegisteredParties()+ 
				"---getArriverdParties="+phaser.getArrivedParties());
	}
}
