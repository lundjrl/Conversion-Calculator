package com.example.calculatorconversion;

import java.util.HashMap;

public class Conversion {

  private HashMap<String, Double> lengthMap;
  private HashMap<String, Double> volumeMap;
  private HashMap<String, HashMap<String, Double>> conversionMap;

  Conversion() {
    lengthMap = new HashMap<>();
    lengthMap.put("MetersMeters", 1.0);
    lengthMap.put("MetersYards", 0.9144);
    lengthMap.put("MetersMiles", 1609.34);
    lengthMap.put("YardsMeters", 1.09361);
    lengthMap.put("YardsYards", 1.0);
    lengthMap.put("YardsMiles", 1760.0);
    lengthMap.put("MilesMeters", 0.000621371);
    lengthMap.put("MilesYards", 0.000568182);
    lengthMap.put("MilesMiles", 1.0);

    volumeMap = new HashMap<>();
    volumeMap.put("LitersLiters", 1.0);
    volumeMap.put("LitersGallons", 3.78541);
    volumeMap.put("LitersQuarts", 0.946353);
    volumeMap.put("GallonsLiters", 0.264172);
    volumeMap.put("GallonsGallons", 1.0);
    volumeMap.put("GallonsQuarts", 0.25);
    volumeMap.put("QuartsLiters", 1.05669);
    volumeMap.put("QuartsGallons", 4.0);
    volumeMap.put("QuartsQuarts", 1.0);

    conversionMap = new HashMap<>();
    conversionMap.put("LENGTH", lengthMap);
    conversionMap.put("VOLUME", volumeMap);
  }

  public HashMap<String, HashMap<String, Double>> getConversionMap() {
    return conversionMap;
  }

}
