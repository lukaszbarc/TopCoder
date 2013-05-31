package com.topcoder.srm580.div2.second;

import java.util.*;

/**
 * @author lukasz.barc
 */
public class EelAndRabbit {

    /**
     * required by TopCoder *
     */
    public int getmax(final Integer[] lenghts, final Integer[] time) {
        int maxTime = Collections.max(getAsList(time)) + Collections.max(getAsList(lenghts));
        Set<Eel> eelsInRiver = initializeEels(lenghts, time);
        int catchedAtFirstTime = countMaxNumberOfEels(maxTime, eelsInRiver);
        int catchedAtSecondTime = countMaxNumberOfEels(maxTime, eelsInRiver);
        int result = catchedAtFirstTime + catchedAtSecondTime;
        return result;
    }

    /**
     * required by Spock *
     */
    private int _getmax(List<Integer> lengths, List<Integer> time) {
        return getmax(lengths.toArray(new Integer[]{}), time.toArray(new Integer[]{}));
    }

    private List<Integer> getAsList(final Integer[] array) {
        ArrayList<Integer> result = new ArrayList<Integer>(array.length);
        for (int i : array) {
            result.add(i);
        }
        return result;
    }


    private Set<Eel> initializeEels(final Integer[] lenghts, final Integer[] time) {
        Set<Eel> eelsInRiver = new HashSet<Eel>();
        for (int i = 0; i < lenghts.length; i++) {
            Integer currentEelArriveTime = time[i];
            Integer currentEelLength = lenghts[i];
            eelsInRiver.add(new Eel.Builder()
                    .withLength(currentEelLength)
                    .whichArriveAtTime(new Time(currentEelArriveTime)).build()
            );
        }
        return eelsInRiver;
    }

    private int countMaxNumberOfEels(final int maxTime, Set<Eel> eelsInRiver) {
        ArrayList<Eel> catched = new ArrayList<Eel>();
        for (int i = 0; i < maxTime; i++) {
            ArrayList<Eel> currentCatches = new ArrayList<Eel>();
            for (Eel eel : eelsInRiver) {
                if (eel.isCatchableAt(new Time(i))) {
                    currentCatches.add(eel);
                }
            }
            if (currentCatches.size() > catched.size()) {
                catched = currentCatches;
            }
        }
        for (Eel eel : catched) {
            eelsInRiver.remove(eel);
        }
        return catched.size();
    }

    public static class Time {
        private int time;

        public Time(final int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }

        public boolean isBetween(final Time availableFrom, final Time availableTo) {
            return time >= availableFrom.getTime() && time <= availableTo.getTime();
        }
    }

    public static class Eel {
        static int id = 0;

        private int hashCode;
        private Time availableFrom;
        private Time availableTo;

        private Eel(final int lenght, final Time arriveTime) {
            this.hashCode = id++;
            this.availableFrom = arriveTime;
            this.availableTo = new Time(arriveTime.getTime() + lenght);
        }

        public boolean isCatchableAt(final Time time) {
            if (time.isBetween(availableFrom, availableTo)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        static class Builder {
            private int length;
            private Time arriveTime;

            public Builder withLength(final int length) {
                this.length = length;
                return this;
            }

            public Builder whichArriveAtTime(final Time time) {
                this.arriveTime = time;
                return this;
            }

            public Eel build() {
                return new Eel(length, arriveTime);
            }

        }
    }
}
