package rokon;

import java.util.HashSet;
import java.util.Iterator;

import javax.microedition.khronos.opengles.GL11;

/**
 * @author Richard Taylor
 * A Layer contains your visible objects, Text, Sprites and Emitters.
 * They are useful to layer certain objects above others.
 */
public class Layer {
	public static final int MAX_SPRITES = 128;
	public static final int MAX_TEXTS = 128;
	public static final int MAX_EMITTERS = 100;
	
	private Sprite[] spriteArr = new Sprite[MAX_SPRITES];
	private Emitter[] emitterArr = new Emitter[MAX_EMITTERS];
	private Text[] textArr = new Text[MAX_TEXTS];
	private int i, j, k, l, m, n, o, p, q, r, s, t;
	
	public Layer() {

	}
	
	/**
	 * Adds Text to the layer
	 * @param text
	 */
	public void addText(Text text) {
		j = -1;
		for(i = 0; i < MAX_TEXTS; i++)
			if(textArr[i] == null)
				j = i;
		if(j == -1) {
			Debug.print("TOO MANY EMITTERS");
			System.exit(0);
		}
		textArr[j] = text;
	}
	
	/**
	 * @param text removes a Text object from the layer
	 */
	public void removeText(Text text) {
		text.markForRemoval();
	}
	
	/**
	 * @param sprite removes a Sprite object from the layer
	 */
	public void removeSprite(Sprite sprite) {
		sprite.markForRemoval();
	}

	/**
	 * @param sprite adds a Sprite object to the layer
	 */
	public void addSprite(Sprite sprite) {
		k = -1;
		for(l = 0; l < MAX_SPRITES; l++)
			if(spriteArr[l] == null)
				k = l;
		if(k == -1) {
			Debug.print("TOO MANY SPRITES");
			System.exit(0);
		}
		spriteArr[k] = sprite;
	}
	
	/**
	 * This is currently out of use.
	 * @param emitter
	 * @return
	 */
	public void addEmitter(Emitter emitter) {
		m = -1;
		for(n = 0; n < MAX_EMITTERS; n++)
			if(emitterArr[n] == null)
				m = n;
		if(m == -1) {
			Debug.print("TOO MANY EMITTERS");
			System.exit(0);
		}
		emitterArr[m] = emitter;
	}
	
	/**
	 * All layers must update their objects positions before they can be drawn, or tested for collisions
	 */
	public void updateMovement() {
		for(o = 0; o < MAX_SPRITES; o++)
			if(spriteArr[o] != null)
				spriteArr[o].updateMovement();
	}
	
	/**
	 * Updates all layers, and draws to the surface. There is no need to call this yourself.
	 * @param gl
	 */
	public void drawFrame(GL11 gl) {

		for(p = 0; p < MAX_SPRITES; p++) {
			if(spriteArr[p] != null) {
				spriteArr[p].drawFrame(gl);
				if(spriteArr[p].isDead())
					spriteArr[p] = null;
			}
		}
		
		for(p = 0; p < MAX_EMITTERS; p++) {
			if(emitterArr[p] != null) {
				emitterArr[p].drawFrame(gl);
				if(emitterArr[p].isDead())
					emitterArr[p] = null;
			}
		}
		
		for(p = 0; p < MAX_TEXTS; p++) {
			if(textArr[p] != null) {
				textArr[p].drawFrame(gl);
				if(textArr[p].isDead())
					textArr[p] = null;
			}
		}
	}
}
