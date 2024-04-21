package Gisfy.LinkedIn;



import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Gisfy.LinkedIn.data;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

import java.util.ArrayList;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class main {
	public static void main(String args[]) 
    { 
	    ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/Ram/AppData/Local/Google/Chrome/User Data/");
        ChromeDriver driver = new ChromeDriver(options=options); 
      
       List <String> linkedinconts = new ArrayList<String>();
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
//       List <String> companyIds = Arrays.asList(
//       "13981",
//       "165121",
//       "10564178",
//       "31375091",
//       "410918",
//       "56243",
//       "278203",
//       "115492",
//       "273368",
//       "104914",
//       "34414",
//       "1790817",
//       "51077",
//       "36455",
//       "3037980",
//       "54513",
//       "905282",
//       "52030",
//       "41576",
//       "401992",
//       "11101395",
//       "18836166",
//       "64774",
//       "39999",
//       "530591");
       for(String com : companyIds) {
       	
       	String peopleurl = "https://www.linkedin.com/sales/search/people?_ntb=J2iv7gqUTGeYhpJRknhGHg%3D%3D&query=(filters%3AList((type%3ACURRENT_COMPANY%2Cvalues%3AList((id%3A"+com+"%2CselectionType%3AINCLUDED)))%2C(type%3APERSONA%2Cvalues%3AList((id%3A1745584333%2CselectionType%3AINCLUDED)))))&sessionId=3iY0CdawTLWsgOJCG1HxYw%3D%3D";
           driver.get(peopleurl); 
      	 	try {
        	TimeUnit.SECONDS.sleep(5);
        	} catch (InterruptedException e) {
        	  Thread.currentThread().interrupt();
        	}
      	 WebElement peopleclick = driver.findElement(By.xpath("//*[@id=\"search-results-container\"]"));
        new Actions(driver)
                .moveToElement(peopleclick)
        
                .perform();
        Dimension scrollsizefeed = driver.findElement(By.xpath("//*[@id=\"search-results-container\"]/div[2]")).getSize();
    	
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(peopleclick);
        
        int count = scrollsizefeed.height/500;
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
     
      for(WebElement p : list) {
   	   if(p.getAttribute("href").contains("NAME_SEARCH")) {
   		   if(!linkedinconts.contains(p.getAttribute("href"))) {
   			   linkedinconts.add(p.getAttribute("href"));
   		   }
   	   }
   	   
      }
      
    System.out.println(linkedinconts.size());
	     System.out.println("Okay page "+com+"\n\n\n");
       } 
        OkHttpClient client = new OkHttpClient();
//        List <String> linkedinconts = Arrays.asList(
//        		"https://www.linkedin.com/sales/lead/ACwAAAsNIGcB97d69McK9MRALt6MBICKzePTFLM,NAME_SEARCH,DjEv?_ntb=QGywjv4%2BT1%2BrnWFzc%2BnWTw%3D%3D",
//        		"https://www.linkedin.com/in/mdfaiz027/"
//        		
//        		);
        MediaType mediaType = MediaType.parse("application/json");
        String payload = "";
        for (String c : linkedinconts) {
           payload = payload +"{\"linkedin_url\": \" "+c+" \"},";
        }
        
        RequestBody body = RequestBody.create(mediaType, "{\n    \"api_key\": \"2zyGsvmf1dnnMHOwTcbm9g\",\n    \"reveal_personal_emails\": true,\n    \"details\": [\n  "+payload.substring(0, payload.length() - 1)+"    ]\n}");

        Request request = new Request.Builder()
          .url("https://api.apollo.io/api/v1/people/bulk_match")
          .method("POST", body)
          .addHeader("Content-Type", "application/json")
          .addHeader("Cache-Control", "no-cache")
          .addHeader("Cookie", "GCLB=CNqjvvXtzZfvtgEQAw; X-CSRF-TOKEN=0UmLsvpoVwbO28GuKGjVRSuP8yRvqk4vR7hNyg-82nSzrq3ao4l8JtIKjO83baDjSC--Qw_daUJ4udJxokteCg; _leadgenie_session=jBkU9Qy4DaGexYDef7RZGUS2zNJpbWVEjfIL%2Bp768tmlS2LEdOmwqB0cx5ChDD12VZs3DX3rbgyvGd6BkNs1uagQKM2inlc32rYXgg%2BkflI6FpthQ1yeICFij9cLpxmbbd8Z2uMj%2F6LVxcHhQ7TqQZ8W7FobMDctM%2FQ8oaaqDbAFaq9uEyTkI9RACoByui69PgSHFs28EaCb0v5kT2tAnUvSEVcdHOSYb13qRaxcHr8jdPRt14xt0WzvjFCIFug5gNQZdRsE8pwlg%2F%2FPk%2BybsFL7vRq4hfl3tv0%3D--IywmTXdCzuioPayH--ikFetCJCEKfwssnUZF4p4g%3D%3D")
          .build();

        try {
            Response response = client.newCall(request).execute();
            
            String jsonResponse = response.body().string();
             System.out.println(jsonResponse);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                data person = objectMapper.readValue(jsonResponse, data.class);
                String[] columns = {"Name", "Comapany"};
                
                Workbook workbook = new XSSFWorkbook();    
//                CreationHelper createHelper = workbook.getCreationHelper();
                Sheet sheet = workbook.createSheet("Contacts");
                Row headerRow = sheet.createRow(0);

                for(int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                }
                int rowNum = 1;
                for( match e : person.matches) {
                	Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(e.firstName);

                    row.createCell(1).setCellValue(e.organization.name);
                   
                	System.out.println("Name: " + e.firstName);
                	System.out.println("Name: " + e.organization.name);
                }
                
                for(int i = 0; i < columns.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
                workbook.write(fileOut);
                fileOut.close();

                workbook.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    } 
}














