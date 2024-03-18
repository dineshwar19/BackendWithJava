package com.dIDemo.dIDemo;

public class Demo {
	private int note;
	private Pen pen;
	
	private Car car;
	
	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public Demo(int note) {
		this.note = note;
	}//for constructor injection
	
	
	public Pen getPen() {
		return pen;
	}

	public void setPen(Pen pen) {
		this.pen = pen;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public void doSomeThing() {
		System.out.println("hello from xml based");
		pen.hasPen();
		car.model();
	}
}
