package com.distanceOptimizer.utility;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Efrem  on 4/19/19
 * @project DistanceOptimizer
 */

@Component
public class DataUtil implements IDataUtil {


    public  BufferedReader readFile(String filePath){

        File file = new File(filePath);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return br;

    }


}
