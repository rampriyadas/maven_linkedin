package Gisfy.LinkedIn;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
//import Gisfy.LinkedIn.match;

@JsonIgnoreProperties(ignoreUnknown = true)
public class data {
    @JsonProperty("status")
    public String status;
    
    @JsonProperty("error_code")
    public String errorCode;
    
    @JsonProperty("error_message")
    public String errorMessage;
    
    @JsonProperty("total_requested_enrichments")
    public int totalRequestedEnrichments;
    
    @JsonProperty("unique_enriched_records")
    public int uniqueEnrichedRecords;
    
    @JsonProperty("missing_records")
    public int missingRecords;
    
    @JsonProperty("credits_consumed")
    public int creditsConsumed;
    
    @JsonProperty("matches")
    public List<match> matches;

}



