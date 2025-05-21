package Model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.math3.distribution.TDistribution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class StatisticsModel {
    private double confidenceLevel = 0.95;
    DataHandler dataHandler = new DataHandler();

    public void calculateStatistics() {

        dataHandler.results.put("Sample Size", new ArrayList<>());
        dataHandler.results.put("Arithmetic Mean", new ArrayList<>());
        dataHandler.results.put("Geometric Mean", new ArrayList<>());
        dataHandler.results.put("Standard Deviation", new ArrayList<>());
        dataHandler.results.put("Range", new ArrayList<>());
        dataHandler.results.put("Minimum", new ArrayList<>());
        dataHandler.results.put("Maximum", new ArrayList<>());
        dataHandler.results.put("Coefficient of Variation", new ArrayList<>());
        dataHandler.results.put("Variance", new ArrayList<>());
        dataHandler.results.put("Confidence Interval Lower", new ArrayList<>());
        dataHandler.results.put("Confidence Interval Upper", new ArrayList<>());

        dataHandler.pairwiseResults.put("Covariance", new HashMap<>());

        //средние значения для ковариации
        double[] means = new double[dataHandler.datasets.size()];


        for (int i = 0; i < dataHandler.datasets.size(); i++) {
            List<Double> dataset = dataHandler.datasets.get(i);


            int sampleSize = dataset.size();
            dataHandler.results.get("Sample Size").add((double) sampleSize);


            double min = Collections.min(dataset);
            double max = Collections.max(dataset);
            dataHandler.results.get("Minimum").add(min);
            dataHandler.results.get("Maximum").add(max);


            double range = max - min;
            dataHandler.results.get("Range").add(range);


            double sum = 0;
            for (double value : dataset) {
                sum += value;
            }
            double arithmeticMean = sum / sampleSize;
            means[i] = arithmeticMean;
            dataHandler.results.get("Arithmetic Mean").add(arithmeticMean);


            double product = 1;
            boolean hasNegative = false;
            for (double value : dataset) {
                if (value <= 0) {
                    hasNegative = true;
                    break;
                }
                product *= value;
            }
            double geometricMean = hasNegative ? Double.NaN : Math.pow(product, 1.0 / sampleSize);
            dataHandler.results.get("Geometric Mean").add(geometricMean);


            double sumSquaredDiff = 0;
            for (double value : dataset) {
                sumSquaredDiff += Math.pow(value - arithmeticMean, 2);
            }
            double variance = sumSquaredDiff / (sampleSize - 1);
            dataHandler.results.get("Variance").add(variance);


            double stdDev = Math.sqrt(variance);
            dataHandler.results.get("Standard Deviation").add(stdDev);


            double cv = (stdDev / arithmeticMean) * 100;
            dataHandler.results.get("Coefficient of Variation").add(cv);


            TDistribution tDist = new TDistribution(sampleSize - 1);
            double tValue = tDist.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);
            double marginOfError = tValue * (stdDev / Math.sqrt(sampleSize));

            dataHandler.results.get("Confidence Interval Lower").add(arithmeticMean - marginOfError);
            dataHandler.results.get("Confidence Interval Upper").add(arithmeticMean + marginOfError);


            dataHandler.pairwiseResults.get("Covariance").put(i, new HashMap<>());
        }


        for (int i = 0; i < dataHandler.datasets.size(); i++) {
            List<Double> datasetI = dataHandler.datasets.get(i);
            double meanI = means[i];

            for (int j = 0; j < dataHandler.datasets.size(); j++) {
                if (i == j) {

                    dataHandler.pairwiseResults.get("Covariance").get(i).put(j, dataHandler.results.get("Variance").get(i));
                } else {

                    List<Double> datasetJ = dataHandler.datasets.get(j);
                    double meanJ = means[j];


                    int minSize = Math.min(datasetI.size(), datasetJ.size());


                    double covSum = 0;
                    for (int k = 0; k < minSize; k++) {
                        covSum += (datasetI.get(k) - meanI) * (datasetJ.get(k) - meanJ);
                    }

                    double covariance = covSum / (minSize - 1);
                    dataHandler.pairwiseResults.get("Covariance").get(i).put(j, covariance);
                }
            }
        }
    }



}