package Gisfy.LinkedIn;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class match {
    @JsonProperty("id")
    public String id;
    
    @JsonProperty("first_name")
    public String firstName;
    
    @JsonProperty("last_name")
    public String lastName;
    
    @JsonProperty("name")
    public String name;
    
    @JsonProperty("linkedin_url")
    public String linkedinUrl;
    
    @JsonProperty("title")
    public String title;
    
    @JsonProperty("headline")
    public String headline;
    
    @JsonProperty("email")
    public String email;
    
    @JsonProperty("state")
    public String state;
    
    @JsonProperty("city")
    public String city;
    
    @JsonProperty("country")
    public String country;
    
    @JsonProperty("organization")
    public organization organization;
  
    
    @JsonProperty("personal_emails")
    public List<String> personalEmails;
   
}