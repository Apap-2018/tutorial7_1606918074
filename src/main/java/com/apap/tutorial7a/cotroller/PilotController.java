package com.apap.tutorial7a.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7a.model.PilotModel;
import com.apap.tutorial7a.rest.PilotDetail;
import com.apap.tutorial7a.rest.Setting;
import com.apap.tutorial7a.service.PilotService;

/**
 * PilotController
 */
@RestController
@RequestMapping("/pilot")
public class PilotController {
    @Autowired
    private PilotService pilotService;

//    @RequestMapping("/")
//    private String home() {
//        return "home";
//    }

//    @RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
//    private String add(Model model) {
//        model.addAttribute("pilot", new PilotModel());
//        return "add-pilot";
//    }

    @PostMapping(value = "/add")
    private PilotModel addPilotSubmit(@RequestBody PilotModel pilot) {
        return pilotService.addPilot(pilot);
    }

    @RequestMapping(value = "/view/{licenseNumber}")
    private PilotModel view(@PathVariable(value = "licenseNumber") String licenseNumber) {
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        return pilot;
    }

    @DeleteMapping(value = "/delete")
    private String delete(@RequestParam(value = "licenseNumber") String licenseNumber) {
    	pilotService.deletePilotByLicenseNumber(licenseNumber);
        return "delete";
    }

//    @RequestMapping(value = "/pilot/update", method = RequestMethod.GET)
//    private String update(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
//        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//        model.addAttribute("pilot", archive.get());
//        return "update-pilot";
//    }

    @PutMapping(value = "/update/{licenseNumber}")
    public String updatePilotSubmit(@PathVariable("licenseNumber")String licenseNumber,
    	@RequestParam("name") String name,
    	@RequestParam("flyHour") int flyHour){
    	PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
    	if(pilot.equals(null)) {
    		return "Couldn't find your pilot";
    	}
    	
    	pilot.setName(name);
    	pilot.setFlyHour(flyHour);
    	pilotService.updatePilot(pilot, licenseNumber);
        return "update";
    }
    
    @Autowired
    RestTemplate restTemplate;
    
    @Bean
    public RestTemplate rest() {
    	return new RestTemplate();
    }	
    
    @GetMapping(value="/status/{licenseNumber}")
    public String getStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
    	String path = Setting.pilotUrl + "/pilot?licenseNumber=" + licenseNumber;
    	return restTemplate.getForEntity(path,  String.class).getBody();
    }
    
    @GetMapping(value="/full/{licenseNumber}")
    public PilotDetail postStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
    	String path = Setting.pilotUrl + "/pilot";
    	PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
    	PilotDetail detail = restTemplate.postForObject(path,  pilot,  PilotDetail.class);
    	return detail;
    }
    
    
}