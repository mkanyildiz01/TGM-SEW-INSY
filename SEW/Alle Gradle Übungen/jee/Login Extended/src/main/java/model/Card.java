/**
 *  Class: Card
 *  Datum: 11.06.2016
 *  Name: Kanyildiz Muhammedhizir
 *  Klasse: 4AHITM
 */

package model;
public class Card {
	private int left;
	private int right;
	private int result;

	public Card() {
		init();
	}

	// Controller

	public String show() {
		result = left * right;
		return "show";
	}

	private void init() {
		left = (int)(Math.random() * 10)+1;
		right = (int)(Math.random() * 10)+1;
		result = 0;
	}

	public String next() {
		init();
		return "next";
	}

	// getter and setter

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}