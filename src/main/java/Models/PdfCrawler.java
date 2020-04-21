package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class PdfCrawler {

    //split using '.'
    public String [] getSentences(String content){
        String [] sentenceArray=content.split("\\.");
        return sentenceArray;
    }
    public ArrayList<String []> filterSentences(int pageNumber ,String[] sentenceArray){
       ArrayList<String[]> filteredArr=new ArrayList<>();
        for(int sentenceNumber=0;sentenceNumber<sentenceArray.length;sentenceNumber++){
            String[] doc=new String[2];
            if(!sentenceArray[sentenceNumber].equals(" ")){
                doc[0]=String.valueOf(pageNumber);
                doc[1]=sentenceArray[sentenceNumber];
                filteredArr.add(doc);
            }

        }

        return filteredArr;
    }

    public ArrayList<String[]> getCrawlData(ArrayList<String> contentArrayList){

         ArrayList<String[]> crawlData=new ArrayList<> ();
        for(int i=0;i<contentArrayList.size();i++){
            int pageNumber=i+1;
            ArrayList<String[]> filteredArr= this.filterSentences(pageNumber ,this.getSentences(contentArrayList.get(i)));
            crawlData.addAll(filteredArr);
        }
        return crawlData;


    }}


















