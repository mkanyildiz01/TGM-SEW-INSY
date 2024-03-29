/**
 *  Class: Converter
 *  Datum: 11.06.2016
 *  Name: Kanyildiz Muhammedhizir
 *  Klasse: 4AHITM
 */

package model;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
public class Converter implements Serializable{
	private static final long serialVersionUID = 1L;
	private double convert;
	private double converted;
	private boolean initial;
	private String unit;
	
	public double getConvert() {
		return convert;
	}
	public void setConvert(double convert) {
		this.convert = convert;
	}
	public double getConverted() {
		return converted;
	}
	public String getUnit(){
		return unit;
	}
	public boolean getInitial(){
		return initial;
	}
	public void init(){
		initial = true;
		converted =0;
		convert = 0;
		unit= "";
	}
	public String reset(){
		init();
		return "reset";
	}
	public void fahrenheitToCelsius(){
		this.initial = false;
		this.unit = "Celsius";
		this.converted = (convert - 32) / 1.8;
	}
	public void kelvinToCelsius(){
		this.initial = false;
		this.unit = "Celsius";
		this.converted = convert - 273;
	}
	public void celsiusToFahrenheit(){
		this.initial = false;
		this.unit = "Fahrenheit";
		this.converted= (convert * 1.8) +32;
	}
	public void kelvinToFahrenheit(){
		this.initial = false;
		this.unit = "Fahrenheit";
		this.converted= (convert * 1.8) - 459.67;
	}
	public void fahrenheitToKelvin(){
		this.initial = false;
		this.unit = "Kelvin";
		this.converted= (convert + 459.67) /1.8;
	}
	public void celsiusToKelvin(){
		this.initial = false;
		this.unit = "Kelvin";
		this.converted = convert +273;
	}
	
}
