package Gisfy.LinkedIn;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {
	public static void main(String args[]) 
    { 
	    ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/Ram/AppData/Local/Google/Chrome/User Data/");
        ChromeDriver driver = new ChromeDriver(options=options); 
      
       
        driver.manage().window().maximize(); 
        String url = "https://www.linkedin.com/sales/search/company?query=(filters%3AList((type%3ACOMPANY_HEADCOUNT%2Cvalues%3AList((id%3AF%2Ctext%3A501-1%252C000%2CselectionType%3AINCLUDED)))%2C(type%3AREGION%2Cvalues%3AList((id%3A103644278%2Ctext%3AUnited%2520States%2CselectionType%3AINCLUDED)))%2C(type%3AINDUSTRY%2Cvalues%3AList((id%3A116%2Ctext%3ATransportation%252C%2520Logistics%252C%2520Supply%2520Chain%2520and%2520Storage%2CselectionType%3AINCLUDED)))))&sessionId=J2iv7gqUTGeYhpJRknhGHg%3D%3D&viewAllFilters=true";
        String[] parts = url.split("q");
        int page = 1;
        boolean chromeproceed = true;
        boolean  proceed = true;
        
        List <String> companyIds = new ArrayList<String>();
        
        while(chromeproceed) {
        try {
        	String newurl = parts[0]+"page="+page+"&q"+parts[1];
       	 driver.get(newurl); 
       	 	try {
         	TimeUnit.SECONDS.sleep(5);
         	} catch (InterruptedException e) {
         	  Thread.currentThread().interrupt();
         	}
      	  WebElement next = driver.findElement(By.xpath("//*[@id=\"content-main\"]/div[1]/div[2]/div[2]/h3"));
            System.out.println(next.getText());
            if (next.getText()!=null) {
            	proceed = false;
            	chromeproceed = false;
            }
            else {
            	proceed = true;
            }
            
            
       }
       catch( Exception e) {
      	 proceed = true;
       }
        if(proceed) {
        
        	 
            
             WebElement clickable = driver.findElement(By.xpath("//*[@id=\"search-results-container\"]"));
             new Actions(driver)
                     .moveToElement(clickable)
             
                     .perform();
             WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(clickable);
             int count = 8;
             while(count >0) {
             	 new Actions(driver)
                  .scrollFromOrigin(scrollOrigin, 0, 500)
                  .perform();
                  count = count - 1;
                  try {
                  	TimeUnit.SECONDS.sleep(2);
                  	} catch (InterruptedException e) {
                  	  Thread.currentThread().interrupt();
                  	}
             }
            
             
             List<WebElement> list = driver.findElements(By.cssSelector("li.artdeco-list__item a"));
             int index = 0;
             for(WebElement a : list) {
            	 if(index % 3 == 0) {
            		
            		 companyIds.add(a.getAttribute("href").split("/")[5].split("\\?")[0]);
            		 System.out.println( a.getAttribute("href").split("/")[5].split("\\?")[0]);
            	 }
            	 index++;
             	
             }
             
             System.out.println(list.size());
     	     System.out.println("Okay page "+page+"\n\n\n");
     	     page = page+1;
     		chromeproceed = false;
         }
        }
        System.out.println("Okay Done");
        for(String e : companyIds) {
        	System.out.println(e);
        }
	
    } 
}














