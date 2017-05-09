package com.simiacryptus.util.text;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.Apint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Scratch {
    public static class Position {
        Apfloat[] space;
        Apfloat time;

        public Position(Apfloat time, Apfloat... space) {
            this.space = space;
            this.time = time;
        }

        public Apfloat distance(Position to) {
            assert to.space.length == space.length;
            Apfloat total = ApfloatMath.pow(to.time.subtract(time), 2).negate();
            for(int i=0;i<space.length;i++) {
                total = total.add(ApfloatMath.pow(to.space[i].subtract(space[i]), 2));
            }
            return total;
        }

        public Position add(Position to) {
            assert to.space.length == space.length;

            Apfloat[] newSpace = new Apfloat[space.length];
            for(int i=0;i<space.length;i++) {
                newSpace[i] = to.space[i].add(space[i]);
            }
            return new Position(to.time.add(time), newSpace);
        }

        public Position multiply(Apfloat factor) {
            Apfloat[] newSpace = new Apfloat[space.length];
            for(int i=0;i<space.length;i++) {
                newSpace[i] = space[i].multiply(factor);
            }
            return new Position(time.multiply(factor), newSpace);
        }
    }

    public static Apfloat solveZero(final Function<Apfloat,Apfloat> fn, Apfloat min, Apfloat max) {
        Apfloat zero = new Apfloat(0);
        Apfloat minVal = fn.apply(min);
        Apfloat maxVal = fn.apply(max);
        Apfloat current = minVal.add(maxVal).divide(new Apint(2));
        Apfloat currentVal = fn.apply(current);
        while(min.compareTo(max) < 0) {
            if(currentVal.compareTo(zero) == 0) break;
            if(minVal.compareTo(zero) == currentVal.compareTo(zero)) {
                min = current;
            } else {
                max = current;
            }
            current = minVal.add(maxVal).divide(new Apint(2));
            currentVal = fn.apply(current);
        }
        return current;
    }

    public static class WorldLine {
        List<Position> positions = new ArrayList<>();

        public Position positionAtIndex(Apfloat index) {
            Apint intPart = index.truncate();
            Apfloat aFrac = index.frac();
            Apfloat bFrac = new Apfloat(1,aFrac.precision()).subtract(aFrac);
            Position a = positions.get(intPart.intValue());
            Position b = positions.get(intPart.intValue()+1);
            return a.multiply(aFrac).add(b.multiply(bFrac));
        }

        public Position connectedPoint(Position to) {
            return positionAtIndex(solveZero(i->{
                return positionAtIndex(i).distance(to);
            },new Apfloat(0),new Apfloat(positions.size())));
        }

    }

}
