package com.ja.optimgui.pso;

import com.ja.optimgui.math.MVector;

public class Particle {

    private MVector position;
    private MVector velocity;
    private MVector bestPosition;

    private double bestValue;

    public double getBestValue() {
        return bestValue;
    }

    public void setBestValue(double bestValue) {
        this.bestValue = bestValue;
    }

    public void setBestPosition(MVector bestPosition) {
        this.bestPosition = bestPosition;
    }

    public Particle(MVector lowerBoundary, MVector upperBoundary) {
        position = new MVector(lowerBoundary, upperBoundary);
        velocity = new MVector(lowerBoundary.dimension(), 0, 0.1);

        bestPosition = new MVector(lowerBoundary.dimension());
        bestPosition.set(position);

        bestValue = Double.POSITIVE_INFINITY;
    }

    public MVector getPosition() {
        return position;
    }

    public MVector getVelocity() {
        return velocity;
    }

    public MVector getBestPosition() {
        return bestPosition;
    }

    public void update(MVector globalBestPosition, double w, double c1, double r1, double c2, double r2){
        velocity.set(velocity.scale(w).addElWise(bestPosition.subtractElWise(position).scale(c1*r1)).addElWise(globalBestPosition.subtractElWise(position).scale(c2*r2)));
        position.set(position.addElWise(velocity));
    }
}
