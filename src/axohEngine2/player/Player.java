package axohEngine2.player;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JFrame;

import axohEngine2.Judgement;
import axohEngine2.entities.AnimatedSprite;
import axohEngine2.entities.Mob;
import axohEngine2.entities.SpriteSheet;
import axohEngine2.project.TYPE;

import java.util.ListIterator;

public class Player
{
	private final long serialVersionUID = 1L;
	
	//int scale = 4;
	
	
	public Mob getPlayerMobStart(SpriteSheet mainCharacter, Mob playerMob, Graphics2D graphics, JFrame jframe,
			LinkedList<AnimatedSprite> sprites, int playerNumber){
		
		
		mainCharacter = new SpriteSheet("/textures/characters/Pokemon2.png", 8, 8, 32, 4);
		Rectangle leftBounds = new Rectangle(11 * 4 + 1, 22 * 4 + 12, 1, 9 * 4 - 12);
		Rectangle rightBounds = new Rectangle(11 * 4 + 35, 22 * 4 + 12, 1, 9 * 4 - 12);
		Rectangle upBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 8, 9 * 4 - 7, 1);
		Rectangle downBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 40, 9 * 4 - 7, 1);
		
		Rectangle fullLeftBounds = new Rectangle(12 * 4 + 4, 1 * 4, 9 * 4 - 4, 30 * 4);
		Rectangle fullRightBounds = new Rectangle(12 * 4, 1 * 4, 9 * 4 - 4, 30 * 4);
		Rectangle fullUpBounds = new Rectangle(8 * 4 + 2, 1 * 4, 14 * 4, 30 * 4);
		Rectangle fullDownBounds = new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4);
		
		Rectangle hurtBoxLeft = new Rectangle(12 * 4 + 8, 21 * 4 - 20, 9 * 4 - 8, 10 * 4 + 20);
		Rectangle hurtBoxRight = new Rectangle(12 * 4, 21 * 4 - 20, 9 * 4 - 8, 10 * 4 + 20);
		Rectangle hurtBoxUp = new Rectangle(8 * 4 + 4, 21 * 4 - 20, 14 * 4 - 4, 10 * 4 + 20);
		Rectangle hurtBoxDown = new Rectangle(8 * 4 + 4, 21 * 4 - 20, 14 * 4 - 4, 10 * 4 + 20);
		
		playerMob = new Mob(jframe, graphics, mainCharacter, 40, TYPE.PLAYER, "Original", true, 56, 
				leftBounds, rightBounds, upBounds, downBounds, 
				fullLeftBounds, fullRightBounds, fullUpBounds, fullDownBounds,
				hurtBoxLeft, hurtBoxRight, hurtBoxUp, hurtBoxDown);

		playerMob.setMoveAnim(32, 48, 40, 56, 3, 8);
		
		LinkedList<Rectangle> attackLeftBounds = new LinkedList<Rectangle>();
		attackLeftBounds.add(new Rectangle(0,0,0,0));
		attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
		attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
		
		LinkedList<Rectangle> attackRightBounds = new LinkedList<Rectangle>();
		attackRightBounds.add(new Rectangle(0,0,0,0));
		attackRightBounds.add(new Rectangle(90, 46, 38, 68));
		attackRightBounds.add(new Rectangle(90, 46, 38, 68));
		
		LinkedList<Rectangle> attackUpBounds = new LinkedList<Rectangle>();
		attackUpBounds.add(new Rectangle(0,0,0,0));
		attackUpBounds.add(new Rectangle(32, 40, 58, 18));
		attackUpBounds.add(new Rectangle(32, 40, 58, 18));
		
		LinkedList<Rectangle> attackDownBounds = new LinkedList<Rectangle>();
		attackDownBounds.add(new Rectangle(0,0,0,0));
		attackDownBounds.add(new Rectangle(32, 96, 58, 18));
		attackDownBounds.add(new Rectangle(32, 96, 58, 18));
		
		playerMob.addAttack("sword", 1, attackLeftBounds, attackRightBounds, attackUpBounds, attackDownBounds);
		playerMob.getAttack("sword").addMovingAnim(17, 25, 9, 1, 3, 8);
		playerMob.getAttack("sword").addAttackAnim(20, 28, 12, 4, 3, 6);
		playerMob.getAttack("sword").addInOutAnim(16, 24, 8, 0, 1, 10);
		playerMob.setCurrentAttack("sword"); //Starting attack
	//	playerMob.setHealth(35); //If you change the starting max health, dont forget to change it in inGameMenu.java max health also
		playerMob.setMaxHealth(35);
		
		sprites.add(playerMob);
		playerMob.stopAnim(); // stops the moving in place right when player loads
		playerMob.setAnimTo(playerMob.getStartFrame()); // stops the odd animation loading and sets player to start on its wanted start frame
	//	playerUpdate = false; 
		playerMob.setHealthBarLocation(new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4));
		
//		 while (listIterator.hasNext()) 
//		 {
//	            if (listIterator.next()._name == "Original")
//	            {
//	            	listIterator.remove();
//	            	break;
//	            }
//	     }
//		
		sprites.add(playerMob);
		Judgement.playerUpdate = true;
		return playerMob;
		
	}
	
	/** Old method to have the human main character
	 * public Mob getPlayerMobStart(SpriteSheet mainCharacter, Mob playerMob, Graphics2D graphics, JFrame jframe,
			LinkedList<AnimatedSprite> sprites, int playerNumber)
	{
		mainCharacter = new SpriteSheet("/textures/characters/mainCharacter.png", 8, 8, 32, 4);
		
		Rectangle leftBounds = new Rectangle(11 * 4 + 1, 22 * 4 + 12, 1, 9 * 4 - 12);
		Rectangle rightBounds = new Rectangle(11 * 4 + 35, 22 * 4 + 12, 1, 9 * 4 - 12);
		Rectangle upBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 8, 9 * 4 - 7, 1);
		Rectangle downBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 40, 9 * 4 - 7, 1);
		
		Rectangle fullLeftBounds = new Rectangle(12 * 4 + 4, 1 * 4, 9 * 4 - 4, 30 * 4);
		Rectangle fullRightBounds = new Rectangle(12 * 4, 1 * 4, 9 * 4 - 4, 30 * 4);
		Rectangle fullUpBounds = new Rectangle(8 * 4 + 2, 1 * 4, 14 * 4, 30 * 4);
		Rectangle fullDownBounds = new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4);
		
		Rectangle hurtBoxLeft = new Rectangle(12 * 4 + 8, 21 * 4 - 20, 9 * 4 - 8, 10 * 4 + 20);
		Rectangle hurtBoxRight = new Rectangle(12 * 4, 21 * 4 - 20, 9 * 4 - 8, 10 * 4 + 20);
		Rectangle hurtBoxUp = new Rectangle(8 * 4 + 4, 21 * 4 - 20, 14 * 4 - 4, 10 * 4 + 20);
		Rectangle hurtBoxDown = new Rectangle(8 * 4 + 4, 21 * 4 - 20, 14 * 4 - 4, 10 * 4 + 20);
		
		playerMob = new Mob(jframe, graphics, mainCharacter, 40, TYPE.PLAYER, "Original", true, 56, 
				leftBounds, rightBounds, upBounds, downBounds, 
				fullLeftBounds, fullRightBounds, fullUpBounds, fullDownBounds,
				hurtBoxLeft, hurtBoxRight, hurtBoxUp, hurtBoxDown);

		playerMob.setMoveAnim(32, 48, 40, 56, 3, 8);
		
		LinkedList<Rectangle> attackLeftBounds = new LinkedList<Rectangle>();
		attackLeftBounds.add(new Rectangle(0,0,0,0));
		attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
		attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
		
		LinkedList<Rectangle> attackRightBounds = new LinkedList<Rectangle>();
		attackRightBounds.add(new Rectangle(0,0,0,0));
		attackRightBounds.add(new Rectangle(90, 46, 38, 68));
		attackRightBounds.add(new Rectangle(90, 46, 38, 68));
		
		LinkedList<Rectangle> attackUpBounds = new LinkedList<Rectangle>();
		attackUpBounds.add(new Rectangle(0,0,0,0));
		attackUpBounds.add(new Rectangle(32, 40, 58, 18));
		attackUpBounds.add(new Rectangle(32, 40, 58, 18));
		
		LinkedList<Rectangle> attackDownBounds = new LinkedList<Rectangle>();
		attackDownBounds.add(new Rectangle(0,0,0,0));
		attackDownBounds.add(new Rectangle(32, 96, 58, 18));
		attackDownBounds.add(new Rectangle(32, 96, 58, 18));
		
		playerMob.addAttack("sword", 1, attackLeftBounds, attackRightBounds, attackUpBounds, attackDownBounds);
		playerMob.getAttack("sword").addMovingAnim(17, 25, 9, 1, 3, 8);
		playerMob.getAttack("sword").addAttackAnim(20, 28, 12, 4, 3, 6);
		playerMob.getAttack("sword").addInOutAnim(16, 24, 8, 0, 1, 10);
		playerMob.setCurrentAttack("sword"); //Starting attack
	//	playerMob.setHealth(35); //If you change the starting max health, dont forget to change it in inGameMenu.java max health also
		playerMob.setMaxHealth(35);
		
		sprites.add(playerMob);
		playerMob.stopAnim(); // stops the moving in place right when player loads
		playerMob.setAnimTo(playerMob.getStartFrame()); // stops the odd animation loading and sets player to start on its wanted start frame
	//	playerUpdate = false; 
		playerMob.setHealthBarLocation(new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4));
		
		// 12, 12, 48 for 48x48 [sheet = 1024], 8, 8, 32 for 32x32 [sheet = 256]
		Judgement.playerUpdate = true;
		
		return playerMob;	
	}
	**/
	public Mob getNewPlayer(SpriteSheet mainCharacter, Mob playerMob, Graphics2D graphics, JFrame jframe,
			LinkedList<AnimatedSprite> sprites, int playerNumber)
	{
		ListIterator<AnimatedSprite> listIterator = sprites.listIterator();
		System.out.println("Player Number ="+ playerNumber);
		//---------------THIS IS A TEMPORARY FIX! SHOULD EVENTUALLY ALLOW PLAYERS TO LOOP BOTH CHARACTERS WITH Y---------/
		
		//---------------SCRUM 2 NEW PLAYER WILL BE HUMAN -------------------------------------------//
		if (playerNumber == 1)
		{
			mainCharacter = new SpriteSheet("/textures/characters/mainCharacter.png", 8, 8, 32, 4);
			
			Rectangle leftBounds = new Rectangle(11 * 4 + 1, 22 * 4 + 12, 1, 9 * 4 - 12);
			Rectangle rightBounds = new Rectangle(11 * 4 + 35, 22 * 4 + 12, 1, 9 * 4 - 12);
			Rectangle upBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 8, 9 * 4 - 7, 1);
			Rectangle downBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 40, 9 * 4 - 7, 1);
			
			Rectangle fullLeftBounds = new Rectangle(12 * 4 + 4, 1 * 4, 9 * 4 - 4, 30 * 4);
			Rectangle fullRightBounds = new Rectangle(12 * 4, 1 * 4, 9 * 4 - 4, 30 * 4);
			Rectangle fullUpBounds = new Rectangle(8 * 4 + 2, 1 * 4, 14 * 4, 30 * 4);
			Rectangle fullDownBounds = new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4);
			
			Rectangle hurtBoxLeft = new Rectangle(12 * 4 + 8, 21 * 4 - 20, 9 * 4 - 8, 10 * 4 + 20);
			Rectangle hurtBoxRight = new Rectangle(12 * 4, 21 * 4 - 20, 9 * 4 - 8, 10 * 4 + 20);
			Rectangle hurtBoxUp = new Rectangle(8 * 4 + 4, 21 * 4 - 20, 14 * 4 - 4, 10 * 4 + 20);
			Rectangle hurtBoxDown = new Rectangle(8 * 4 + 4, 21 * 4 - 20, 14 * 4 - 4, 10 * 4 + 20);
			
			playerMob = new Mob(jframe, graphics, mainCharacter, 40, TYPE.PLAYER, "Original", true, 56, 
					leftBounds, rightBounds, upBounds, downBounds, 
					fullLeftBounds, fullRightBounds, fullUpBounds, fullDownBounds,
					hurtBoxLeft, hurtBoxRight, hurtBoxUp, hurtBoxDown);

			playerMob.setMoveAnim(32, 48, 40, 56, 3, 8);
			
			LinkedList<Rectangle> attackLeftBounds = new LinkedList<Rectangle>();
			attackLeftBounds.add(new Rectangle(0,0,0,0));
			attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
			attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
			
			LinkedList<Rectangle> attackRightBounds = new LinkedList<Rectangle>();
			attackRightBounds.add(new Rectangle(0,0,0,0));
			attackRightBounds.add(new Rectangle(90, 46, 38, 68));
			attackRightBounds.add(new Rectangle(90, 46, 38, 68));
			
			LinkedList<Rectangle> attackUpBounds = new LinkedList<Rectangle>();
			attackUpBounds.add(new Rectangle(0,0,0,0));
			attackUpBounds.add(new Rectangle(32, 40, 58, 18));
			attackUpBounds.add(new Rectangle(32, 40, 58, 18));
			
			LinkedList<Rectangle> attackDownBounds = new LinkedList<Rectangle>();
			attackDownBounds.add(new Rectangle(0,0,0,0));
			attackDownBounds.add(new Rectangle(32, 96, 58, 18));
			attackDownBounds.add(new Rectangle(32, 96, 58, 18));
			
			playerMob.addAttack("sword", 1, attackLeftBounds, attackRightBounds, attackUpBounds, attackDownBounds);
			playerMob.getAttack("sword").addMovingAnim(17, 25, 9, 1, 3, 8);
			playerMob.getAttack("sword").addAttackAnim(20, 28, 12, 4, 3, 6);
			playerMob.getAttack("sword").addInOutAnim(16, 24, 8, 0, 1, 10);
			playerMob.setCurrentAttack("sword"); //Starting attack
		//	playerMob.setHealth(35); //If you change the starting max health, dont forget to change it in inGameMenu.java max health also
			playerMob.setMaxHealth(35);
			
			sprites.add(playerMob);
			playerMob.stopAnim(); // stops the moving in place right when player loads
			playerMob.setAnimTo(playerMob.getStartFrame()); // stops the odd animation loading and sets player to start on its wanted start frame
		//	playerUpdate = false; 
			playerMob.setHealthBarLocation(new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4));
			
			// 12, 12, 48 for 48x48 [sheet = 1024], 8, 8, 32 for 32x32 [sheet = 256]
			Judgement.playerUpdate = true;
		}
		
		else if (playerNumber == 2) //Condition for the crash
		{
			mainCharacter = new SpriteSheet("/textures/characters/Pokemon2.png", 8, 8, 32, 4);
			Rectangle leftBounds = new Rectangle(11 * 4 + 1, 22 * 4 + 12, 1, 9 * 4 - 12);
			Rectangle rightBounds = new Rectangle(11 * 4 + 35, 22 * 4 + 12, 1, 9 * 4 - 12);
			Rectangle upBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 8, 9 * 4 - 7, 1);
			Rectangle downBounds = new Rectangle(11 * 4 + 3, 22 * 4 + 40, 9 * 4 - 7, 1);
			
			Rectangle fullLeftBounds = new Rectangle(12 * 4 + 4, 1 * 4, 9 * 4 - 4, 30 * 4);
			Rectangle fullRightBounds = new Rectangle(12 * 4, 1 * 4, 9 * 4 - 4, 30 * 4);
			Rectangle fullUpBounds = new Rectangle(8 * 4 + 2, 1 * 4, 14 * 4, 30 * 4);
			Rectangle fullDownBounds = new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4);
			
			Rectangle hurtBoxLeft = new Rectangle(12 * 4 + 8, 21 * 4 - 20, 9 * 4 - 8, 10 * 4 + 20);
			Rectangle hurtBoxRight = new Rectangle(12 * 4, 21 * 4 - 20, 9 * 4 - 8, 10 * 4 + 20);
			Rectangle hurtBoxUp = new Rectangle(8 * 4 + 4, 21 * 4 - 20, 14 * 4 - 4, 10 * 4 + 20);
			Rectangle hurtBoxDown = new Rectangle(8 * 4 + 4, 21 * 4 - 20, 14 * 4 - 4, 10 * 4 + 20);
			
			playerMob = new Mob(jframe, graphics, mainCharacter, 40, TYPE.PLAYER, "Original", true, 56, 
					leftBounds, rightBounds, upBounds, downBounds, 
					fullLeftBounds, fullRightBounds, fullUpBounds, fullDownBounds,
					hurtBoxLeft, hurtBoxRight, hurtBoxUp, hurtBoxDown);

			playerMob.setMoveAnim(32, 48, 40, 56, 3, 8);
			
			LinkedList<Rectangle> attackLeftBounds = new LinkedList<Rectangle>();
			attackLeftBounds.add(new Rectangle(0,0,0,0));
			attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
			attackLeftBounds.add(new Rectangle(8, 46, 38, 68));
			
			LinkedList<Rectangle> attackRightBounds = new LinkedList<Rectangle>();
			attackRightBounds.add(new Rectangle(0,0,0,0));
			attackRightBounds.add(new Rectangle(90, 46, 38, 68));
			attackRightBounds.add(new Rectangle(90, 46, 38, 68));
			
			LinkedList<Rectangle> attackUpBounds = new LinkedList<Rectangle>();
			attackUpBounds.add(new Rectangle(0,0,0,0));
			attackUpBounds.add(new Rectangle(32, 40, 58, 18));
			attackUpBounds.add(new Rectangle(32, 40, 58, 18));
			
			LinkedList<Rectangle> attackDownBounds = new LinkedList<Rectangle>();
			attackDownBounds.add(new Rectangle(0,0,0,0));
			attackDownBounds.add(new Rectangle(32, 96, 58, 18));
			attackDownBounds.add(new Rectangle(32, 96, 58, 18));
			
			playerMob.addAttack("sword", 1, attackLeftBounds, attackRightBounds, attackUpBounds, attackDownBounds);
			playerMob.getAttack("sword").addMovingAnim(17, 25, 9, 1, 3, 8);
			playerMob.getAttack("sword").addAttackAnim(20, 28, 12, 4, 3, 6);
			playerMob.getAttack("sword").addInOutAnim(16, 24, 8, 0, 1, 10);
			playerMob.setCurrentAttack("sword"); //Starting attack
		//	playerMob.setHealth(35); //If you change the starting max health, dont forget to change it in inGameMenu.java max health also
			playerMob.setMaxHealth(35);
			
			sprites.add(playerMob);
			playerMob.stopAnim(); // stops the moving in place right when player loads
			playerMob.setAnimTo(playerMob.getStartFrame()); // stops the odd animation loading and sets player to start on its wanted start frame
		//	playerUpdate = false; 
			playerMob.setHealthBarLocation(new Rectangle(8 * 4, 1 * 4, 14 * 4, 30 * 4));
			
//			 while (listIterator.hasNext()) 
//			 {
//		            if (listIterator.next()._name == "Original")
//		            {
//		            	listIterator.remove();
//		            	break;
//		            }
//		     }
//			
			sprites.add(playerMob);
			Judgement.playerUpdate = true;
		}
		
		return playerMob;	
	}
	
	
}
