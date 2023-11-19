package com.ja.optimgui.pso;

import com.ja.optimgui.math.MVector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Solver {

    private List<Particle> particles = new ArrayList<>();

    private MVector globalBestPosition;
    private double bestValue;
    private Particle bestParticle;
    private Method objectiveFunction;

    public Particle getBestParticle() {
        return bestParticle;
    }

    public double getBestValue() {
        return bestValue;
    }

    public Solver(int swarmSize, Method objectiveFunction, MVector lowerBoundary, MVector upperBoundary) throws InvocationTargetException, IllegalAccessException {

        bestValue = Double.POSITIVE_INFINITY;
        globalBestPosition = new MVector(lowerBoundary.dimension(), Double.NaN);
        this.objectiveFunction = objectiveFunction;

        for (int i = 0; i < swarmSize; i++) {

            Particle particle = new Particle(lowerBoundary, upperBoundary);
            this.checkForBetterGlobalValue(particle);
            particles.add(particle);
        }
    }

    public void checkForBetterGlobalValue(Particle particle) throws InvocationTargetException, IllegalAccessException {
        MVector particlePosition = particle.getPosition();
        double particleValue = (double) objectiveFunction.invoke(null, particlePosition);

        if(particleValue < particle.getBestValue()){
            particle.setBestValue(particleValue);
            particle.setBestPosition(particlePosition);
        }

        if(particleValue < bestValue){
            globalBestPosition.set(particlePosition);
            bestValue = particleValue;
            bestParticle = particle;
        }
    }

    public void update() throws InvocationTargetException, IllegalAccessException {
        //inertia weight constant
        double w = 0.8;
        //cognitive coefficient
        double c1 = 0.1;
        //social coefficient
        double c2 = 0.1;

        double r1 = ThreadLocalRandom.current().nextDouble(0, 1+1);
        double r2 = ThreadLocalRandom.current().nextDouble(0, 1+1);

        for (Particle particle: particles) {
            particle.update(globalBestPosition, w, c1, r1, c2, r2);
            this.checkForBetterGlobalValue(particle);
        }
    }

    public List<MVector> getParticlePositions(){
        List<MVector> particlePositions = new ArrayList<>();

        for (Particle particle: particles) {
            particlePositions.add(particle.getPosition());
        }

        return particlePositions;
    }


}
