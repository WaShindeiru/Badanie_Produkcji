package com.ja.optimgui.math;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class MVector {

    private double[] vecArray;

    public MVector(double[] vecArray){
        this.vecArray = vecArray;
    }

    public MVector(int dimension){
        this.vecArray = new double[dimension];
    }

    public MVector(int dimension, double value){
        double[] tempArray = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            tempArray[i] = value;
        }

        this.vecArray = tempArray;
    }

    public MVector(MVector lowerBoundary, MVector upperBoundary){
        double[] tempArray = new double[lowerBoundary.dimension()];

        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = ThreadLocalRandom.current().nextDouble(lowerBoundary.getElement(i), upperBoundary.getElement(i) + 1);
        }

        this.vecArray = tempArray;
    }

    public MVector(int dimension, double mean, double deviation){
        double[] tempArray = new double[dimension];

        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = ThreadLocalRandom.current().nextGaussian(mean, deviation);
        }

        this.vecArray = tempArray;
    }


    public double getElement(int index){
        return vecArray[index];
    }

    public void setElement(int index, double value){
        if(index < vecArray.length){
            vecArray[index] = value;
        }
    }

    public void set(MVector other){
        for (int i = 0; i < this.dimension(); i++) {
            this.setElement(i, other.getElement(i));
        }
    }

    public MVector scale(double scalar){
        MVector resultMVector = new MVector(this.vecArray.length);
        for (int i = 0; i < this.vecArray.length; i++) {
            resultMVector.setElement(i, this.getElement(i)*scalar);
        }

        return resultMVector;
    }

    public MVector addScalar(double scalar){
        MVector resultMVector = new MVector(this.vecArray.length);
        for (int i = 0; i < this.vecArray.length; i++) {
            resultMVector.setElement(i, this.getElement(i) + scalar);
        }
        return resultMVector;
    }

    public MVector multiplyElWise(MVector other){
        MVector resultMVector = new MVector(this.vecArray.length);
        for (int i = 0; i < this.vecArray.length; i++) {
            resultMVector.setElement(i, this.getElement(i)* other.getElement(i));
        }
        return resultMVector;
    }

    public MVector addElWise(MVector other){
        MVector resultMVector = new MVector(this.vecArray.length);
        for (int i = 0; i < this.vecArray.length; i++) {
            resultMVector.setElement(i, this.getElement(i) + other.getElement(i));
        }
        return resultMVector;
    }

    public MVector subtractElWise(MVector other){
        MVector resultMVector = new MVector(this.vecArray.length);
        resultMVector.set(this.addElWise(other.scale(-1)));
        return resultMVector;
    }

    public int dimension(){
        return this.vecArray.length;
    }

    public double norm(){
        double sum = 0.0;
        for (int i = 0; i < this.dimension(); i++) {
            sum += Math.pow(this.getElement(i), 2);
        }

        return Math.sqrt(sum);
    }

    public double[] toArray(){
        return this.vecArray.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(vecArray);
    }
}
