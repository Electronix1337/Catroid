/**
 *  Catroid: An on-device graphical programming language for Android devices
 *  Copyright (C) 2010  Catroid development team 
 *  (<http://code.google.com/p/catroid/wiki/Credits>)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package at.tugraz.ist.catroid.test.content.brick;

import android.test.AndroidTestCase;
import at.tugraz.ist.catroid.content.Sprite;
import at.tugraz.ist.catroid.content.bricks.GoNStepsBackBrick;


public class GoNStepsBackBrickTest extends AndroidTestCase {
	
	private int steps = 17;
	private int oldPosition; 
	
	public void testSteps() {
		Sprite sprite = new Sprite("testSprite");
		assertEquals("Unexpected initial sprite Z position", 0, sprite.getZPosition());
		
		oldPosition = sprite.getZPosition();
		
		GoNStepsBackBrick brick = new GoNStepsBackBrick(sprite, steps);
		brick.execute();
		assertEquals("Incorrect sprite Z position after GoNStepsBackBrick executed",
				(oldPosition - steps), sprite.getZPosition());		
	}
	
	public void testNullSprite() {
		GoNStepsBackBrick brick = new GoNStepsBackBrick(null, steps);
		
		try {
			brick.execute();
			fail("Execution of GoNStepsBackBrick with null Sprite did not cause " +
					"a NullPointerException to be thrown");
		} catch (NullPointerException e) {
			// expected behavior
		}
	}
	
	public void testBoundarySteps() {
		Sprite sprite = new Sprite("testSprite");
		
		oldPosition = sprite.getZPosition();
		
		GoNStepsBackBrick brick = new GoNStepsBackBrick(sprite, Integer.MAX_VALUE);
		brick.execute();
		assertEquals("GoNStepsBackBrick execution failed. Wrong Z position.",
				(oldPosition - Integer.MAX_VALUE), sprite.getZPosition());
		
		brick = new GoNStepsBackBrick(sprite, -steps);
		
		try {
			brick.execute();
			fail("Execution of GoNStepsBackBrick with negative steps value did not" +
					" cause a NumberFormatException to be thrown");
		} catch (NumberFormatException e) {
			// expected behavior
		}
		
		brick = new GoNStepsBackBrick(sprite, Integer.MAX_VALUE);
		brick.execute();
		brick.execute();
		assertEquals("An Integer underflow occured during GoNStepsBackBrick execution.", 
				Integer.MIN_VALUE, sprite.getZPosition());
	}

}
