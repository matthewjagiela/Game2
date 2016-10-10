/**********************************************************************
 * @author Travis R. Dewitt
 * @version 1.0
 * Date: June 15, 2015
 * 
 * Title: Data 
 * Description: Hold data from a game in variables for serializing later
 * 
 * 
 * This work is licensed under a Attribution-NonCommercial 4.0 International
 * CC BY-NC-ND license. http://creativecommons.org/licenses/by-nc/4.0/
 *********************************************************************/
//Package
package axohEngine2.data;

//Imports
import java.io.Serializable;

public class Data implements Serializable {	
	//Anything being serialized needs an ID to signify its difference from something else
	private static final long serialVersionUID = -4668422157233446222L;
	
	//Variables to be updated and saved or accessed at some point
	private String _currentMapName;
	private String _currentOverlayName;
	private int _playerX;
	private int _playerY;
	private int _mapX;
	private int _mapY;
	private int _health;
	private int _frisbee;
	private int _gfrisbee;
	private int _files;
	private int _snack;
	private int _keyboard;
	
	//Method used to update the private variables
	public void update(String currentMapName, String currentOverlayName, int playerX, int playerY, int mapX,
			int mapY, int health, int frisbee, int gfrisbee, int files, int snack, int keyboard){
		_currentMapName = currentMapName;
		_currentOverlayName = currentOverlayName;
		_playerX = playerX;
		_playerY = playerY;
		_mapX = mapX;
		_mapY = mapY;
		_health = health;
		_frisbee = frisbee;
		_gfrisbee = gfrisbee;
		_files = files;
		_snack = snack;
		_keyboard = keyboard;
	}
	
	/***************************************************
	 * @return String - Get _currentMapName variable
	 ***************************************************/
	public String getMapName() {
		return _currentMapName;
	}
	
	/***************************************************
	 * @return String - Get _currentOverlayName variable
	 ***************************************************/
	public String getOverlayName() {
		return _currentOverlayName;
	}
	
	/***************************************************
	 * @return Int - Get _playerX variable
	 ***************************************************/
	public int getPlayerX() {
		return _playerX;
	}
	
	/***************************************************
	 * @return Int - Get _playerY variable
	 ***************************************************/
	public int getPlayerY() {
		return _playerY;
	}
	
	public int getMapX()
	{
		return _mapX;
	}
	public int getMapY()
	{
		return _mapY;
	}
	public int getHealth()
	{
		return _health;
	}
	public int getFrisbee()
	{
		return _frisbee;
	}
	public int getGFrisbee()
	{
		return _gfrisbee;
	}
	public int getFiles()
	{
		return _files;
	}
	public int getSnack()
	{
		return _snack;
	}
	public int getKeyboard()
	{
		return _keyboard;
	}
}
