package com.exam.demo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ZipcodeMerger {
  public List<Zipcode> sortByLowerBounds(List<Zipcode> zipcodeRangeList) {
    Collections.sort(zipcodeRangeList, new ZipcodeComparator());
    return zipcodeRangeList;
  }

  public List<Zipcode> mergeZipcodes(List<Zipcode> sortedZipCodeList) {
    List<Zipcode> mergedZipcodeList = new LinkedList<Zipcode>();
    Zipcode zipcode = null;
    for (Zipcode zipcodeInterval : sortedZipCodeList) {
      if (zipcode == null)
        zipcode = zipcodeInterval;
      else {
        if (zipcode.getUpper_bound() >= zipcodeInterval.getLower_bound()) {
          zipcode.setUpper_bound(Math.max(zipcode.getUpper_bound(), zipcodeInterval.getUpper_bound()));
        } else {
          mergedZipcodeList.add(zipcode);
          zipcode = zipcodeInterval;
        }
      }
    }
    mergedZipcodeList.add(zipcode);
    return mergedZipcodeList;
  }

}
