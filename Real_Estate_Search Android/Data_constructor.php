 <?php

/*
 * Get time zone info from PHP config
*/
if (version_compare(PHP_VERSION, 5.3, '>='))
{
  @date_default_timezone_set(date_default_timezone_get());
}

    header('Content-Type: application/json');
    header("Access-Control-Allow-Origin: *");
$data = array('address'=>$_GET["address"],'citystatezip'=>$_GET["mycity"]."+".$_GET["mystate"],'rentzestimate'=>"true");

$gen_query = http_build_query($data);
#echo $gen_query;
$query_str = "http://www.zillow.com/webservice/GetDeepSearchResults.htm?zws-id=<id>".$gen_query;


# HARD_CODING THIS VARIABLE FOR NOW



#Get chart API


$xml=simplexml_load_file($query_str);
$status =intval($xml->message->code);
if($status == 0 ){
    #Success
    generate_json($xml->response->results);
}
else {
    #Failure
    echo "No exact match found -- Verify that the given address is correct";
    return;
}


function generate_json($results){
    #   $json_obj = json_encode($xml,JSON_PRETTY_PRINT);
    
    $homedet = (string)$results->result->links->homedetails;
    
    $array_part1["homedetails"]=$homedet?$homedet:"N/A";
    $array_part1["street"]=$results->result->address->street?(string)$results->result->address->street:"N/A";
    $array_part1["city"]=$results->result->address->city?(string)$results->result->address->city:"N/A";
    $array_part1["state"]=$results->result->address->state?(string)$results->result->address->state:"N/A";
    $array_part1["zipcode"]=$results->result->address->zipcode?(string)$results->result->address->zipcode:"N/A";
    $array_part1["latitude"]=$results->result->address->latitude?(string)$results->result->address->latitude:"N/A";
    $array_part1["longitude"]=$results->result->address->longitude?(string)$results->result->address->longitude:"N/A";
    $array_part1["useCode"]=$results->result->useCode?(string)$results->result->useCode:'N/A';
    $array_part1["lastSoldPrice"]=$results->result->lastSoldPrice?"$".number_format((float)$results->result->lastSoldPrice,2,".",","):"N/A";
    $array_part1["yearBuilt"]=$results->result->yearBuilt?intval($results->result->yearBuilt):"N/A";
    
    #For last sold date
    $string_last_updated = "last-updated";
    $last_update_date = date("d-M-Y", strtotime($results->result->zestimate->$string_last_updated));
    $last_Sold_Date = date("d-M-Y", strtotime($results->result->lastSoldDate));                                                                             
    $array_part1["lastSoldDate"]=$results->result->lastSoldDate?$last_Sold_Date:"N/A";
    # Number format
    $num_val = number_format((float)$results->result->lotSizeSqFt);
    $num_val = $num_val . " sq. ft.";
    $array_part1["lotSizeSqFt"]=$results->result->lotSizeSqFt?$num_val:"N/A";
    $array_part1["estimateLastUpdate"]=$results->result->zestimate->$string_last_updated?$last_update_date:"N/A";
    $est_amt = "$".number_format((float)$results->result->zestimate->amount,2,".",",");
    $array_part1["estimateAmount"]=$results->result->zestimate->amount?$est_amt:"N/A";
    $val = (float)$results->result->finishedSqFt;
    $num_val = number_format($val);
    $num_val = $num_val . " sq. ft.";
    $array_part1["finishedSqFt"]=$results->result->finishedSqFt?$num_val:"N/A";
    $val = (float)$results->result->zestimate->valueChange;
    if($val<0){
    $array_part1["estimateValueChangeSign"]='-';
    }
    else {
        $array_part1["estimateValueChangeSign"]='+';
    }
    $array_part1["imgn"]="http://www-scf.usc.edu/~csci571/2014Spring/hw6/down_r.gif";
    $array_part1["imgp"]="http://www-scf.usc.edu/~csci571/2014Spring/hw6/up_g.gif";
    $array_part1["estimateValueChange"]=$val?"$".number_format(abs($val),2,".",","):"N/A";
    $array_part1["bathrooms"]=($results->result->bathrooms?(string)$results->result->bathrooms:"N/A");
    
    #for value range
    
    $val1=(float)$results->result->zestimate->valuationRange->low;
    $val2=(float)$results->result->zestimate->valuationRange->high;
    $val1=$val1?"$".number_format($val1,2,".",","):"N/A";
    $val2=$val2?"$".number_format($val2,2,".",","):"N/A";
        
    $array_part1["estimateValuationRangeLow"]=$val1;
    $array_part1["estimateValuationRangeHigh"]=$val2;
    $array_part1["bedrooms"]=$results->result->bedrooms?(string)$results->result->bedrooms:"N/A";
    #rent zest update
    $rent_up = $results->result->rentzestimate->$string_last_updated;
    
    $array_part1["restimateLastUpdate"]=$results->result->rentzestimate->$string_last_updated?date("d-M-Y", strtotime($rent_up)):"N/A";
    $array_part1["restimateAmount"]=($results->result->rentzestimate->amount?"$".number_format((float)$results->result->rentzestimate->amount,2,".",","):"N/A");
    $array_part1["taxAssessmentYear"]=($results->result->taxAssessmentYear?intval($results->result->taxAssessmentYear):"N/A");
    $val = (float)$results->result->rentzestimate->valueChange;
    if($val<0){
    $array_part1["restimateValueChangeSign"]='-';
    }
    else{
        $array_part1["restimateValueChangeSign"]='+';
    }
    $array_part1["restimateValueChange"]=$val?"$".number_format(abs($val),2,".",","):"N/A";
    $val = (float)$results->result->taxAssessment;
    $num_val = number_format($val,2,".",",");
    $num_val= "$".$num_val;
    $array_part1["taxAssessment"]=$results->result->taxAssessment?$num_val:"N/A";
    $val1=(float)$results->result->rentzestimate->valuationRange->low;
    $val2=(float)$results->result->rentzestimate->valuationRange->high;
    $array_part1["restimateValuationRangeLow"]=$val1?"$".number_format($val1,2,".",","):"N/A";
    $array_part1["restimateValuationRangeHigh"]=$val2?"$".number_format($val2,2,".",","):"N/A";

    #$array_part1[""]=;    
    
    $query_1year_chart_data = "http://www.zillow.com/webservice/GetChart.htm?zws-id=<id>&unit-type=percent&zpid=".$results->result->zpid."&width=600&height=300&chartDuration=1year";
    
    $query_5year_chart_data = "http://www.zillow.com/webservice/GetChart.htm?zws-id=<id>&unit-type=percent&zpid=".$results->result->zpid."&width=600&height=300&chartDuration=5years";
    
    $query_10year_chart_data = "http://www.zillow.com/webservice/GetChart.htm?zws-id=<id>&unit-type=percent&zpid=".$results->result->zpid."&width=600&height=300&chartDuration=10years";
    
    $xml_1=simplexml_load_file($query_1year_chart_data);
    $xml_5=simplexml_load_file($query_5year_chart_data);
    $xml_10=simplexml_load_file($query_10year_chart_data);
    
    $chart["year_1"] = $xml_1->response->url?(string)$xml_1->response->url:"N/A";
    $chart["year_5"] = $xml_5->response->url?(string)$xml_5->response->url:"N/A";
    $chart["year_10"] = $xml_10->response->url?(string)$xml_10->response->url:"N/A";
    
    $final_result = array("result"=>$array_part1,"chart"=>$chart);
    $json_obj = json_encode($final_result);
   
    $objson = json_decode($json_obj, true);
  
    $return["json"] = json_encode($final_result);
    echo $json_obj;
    
}
?>

