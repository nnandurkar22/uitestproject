package resources;

import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataUtils extends BaseTest {
	WebDriver driver;
	
	public DataUtils(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	/**
	 * Method for getting data from a csv file in the form of a map<colName,colVal>
	 * @param rowNum - Zero based index for fetching the row from the csv file. For 1st line, supply 0.
	 * @param fileName - Test data fileName present in testdata package
	 * @return Map<String,String>
	 */
	public Map<String,String> getDataFromCSV(int rowNum, String fileName)
	{
		Map<String,String> dataMap=new HashMap<String,String>();
		try
		{
		String localDir = System.getProperty("user.dir");
		File file = new File(localDir+"\\src\\main\\java\\testdata\\"+fileName);
		Reader reader = new FileReader(file);
		dataMap=csvReader(reader).get(rowNum);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dataMap;
	}
	
	/**
	 * Method to read csv file with the help of Jackson API
	 * @param reader - file reader
	 * @return
	 */
	private Map<Integer,Map<String,String>> csvReader(Reader reader)
	{
		Map<Integer,Map<String,String>> outMap=new LinkedHashMap<Integer,Map<String,String>>();
		try {
			Iterator<Map<String, String>> iterator = new CsvMapper().readerFor(Map.class)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues(reader);
			int i=0;
        while (iterator.hasNext()) {
            Map<String, String> keyVals = iterator.next();
            outMap.put(i, keyVals);
            i++;
        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return outMap;
    }

}
