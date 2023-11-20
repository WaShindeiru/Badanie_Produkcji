package com.ja.optimgui.test;

import com.ja.optimgui.math.MVector;
import com.ja.optimgui.pso.Solver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    //funkcja celu, w tym przypadku dwa argumenty
    public static double objFunc(MVector args){
        double x = args.getElement(0);
        double y = args.getElement(1);

        return Math.sqrt(Math.pow(x - 3, 2)+ Math.pow(y - 1, 2));
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method objFuncHandle = Test.class.getMethod("objFunc", MVector.class);

        //Przedziały do rozpatrywania
        MVector lowBound = new MVector(new double[]{-5, -5});
        MVector upBound = new MVector(new double[]{5, 5});

        Solver solver = new Solver(1000, Test::objFunc, lowBound, upBound);


        double previousValue = Double.POSITIVE_INFINITY;
        int counter = 0;

        //na szybko zrobiona pętla do testowania algorytmu
        //counter w ifie mówi ile musi powtórzyć się wynik by uznać że można skończyć
        for (int i = 0; i < 10000; i++) {
            System.out.println(solver.getBestParticle().getPosition());
            System.out.println(solver.getBestValue());
            if(solver.getBestValue() == previousValue){
                counter++;
            }else{
                counter = 0;
            }

            if (counter >= 10){
                System.out.println();
                System.out.println("znaleziono:");
                System.out.println("Iteracje: " + i);
                System.out.println(solver.getBestParticle().getPosition());
                System.out.println(solver.getBestValue());
                break;
            }
            previousValue = solver.getBestValue();
            solver.update();
        }

    }
}
