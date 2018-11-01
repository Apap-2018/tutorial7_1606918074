package com.apap.tutorial7a.cotroller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7a.model.FlightModel;
import com.apap.tutorial7a.model.PilotModel;
import com.apap.tutorial7a.service.FlightService;
import com.apap.tutorial7a.service.PilotService;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private PilotService pilotService;

    @PostMapping(value = "/add")
    public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
    	return flightService.addFlight(flight);
    }

    @RequestMapping(value = "/flight/update", method = RequestMethod.GET)
    private String update(@RequestParam(value = "flightNumber") String flightNumber, Model model) {
        FlightModel archive = flightService.getFlightDetailByFlightNumber(flightNumber).get();
        model.addAttribute("flight", archive);
        return "update-flight";
    }

    @GetMapping(value = "/view/{flightNumber}")
    public FlightModel flightView(@PathVariable("flightNumber") String flightNumber, Model model) {
    	FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber).get();
    	return flight;
    }

    @GetMapping(value = "/all")
    public List<FlightModel> flightAll() {
    	List<FlightModel> allFlight = flightService.getAllFlight();
    	return allFlight;
    }
    
    @RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
    private String delete(@ModelAttribute PilotModel pilot, Model model) {
        for (FlightModel flight : pilot.getListFlight()) {
            flightService.deleteByFlightNumber(flight.getFlightNumber());
        }
        return "delete";
    }
    
    @DeleteMapping(value = "/{flightId}")
    private String deleteFlight(@RequestParam("flightId") long flightId) {
        FlightModel flight = flightService.getFlightDetailByFlightId(flightId).get();
        flightService.deleteFlight(flight);
        return "Flight has been deleted";
    }
    
    @PutMapping(value = "/update/{flightId}")
    public String updateFlightSubmit(@PathVariable("flightId") long flightId,
    		@RequestParam("destination") String destination,
    		@RequestParam("origin") String origin,
    		@RequestParam("time") Date time) {
    	FlightModel flight = flightService.getFlightDetailByFlightId(flightId).get();
    	if (flight.equals(null)) {
    		return "Couldn't find your flight";
    	}
    	
    	flight.setDestination(destination);
    	flight.setOrigin(origin);
    	flight.setTime(time);
    	flightService.updateFlight(flightId, flight);
    	return "Flight update success";
    }
    
//  @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
//  private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
//      PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//      pilot.setListFlight(new ArrayList<FlightModel>(){
//          private ArrayList<FlightModel> init(){
//              this.add(new FlightModel());
//              return this;
//          }
//      }.init());
//
//      model.addAttribute("pilot", pilot);
//      return "add-flight";
//  }
//
//  @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"addRow"})
//  private String addRow(@ModelAttribute PilotModel pilot, Model model) {
//      pilot.getListFlight().add(new FlightModel());
//      model.addAttribute("pilot", pilot);
//      return "add-flight";
//  }
//
//  @RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"removeRow"})
//  public String removeRow(@ModelAttribute PilotModel pilot, Model model, HttpServletRequest req) {
//      Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
//      pilot.getListFlight().remove(rowId.intValue());
//      
//      model.addAttribute("pilot", pilot);
//      return "add-flight";
//  }
    
//  @RequestMapping(value = "/flight/update", method = RequestMethod.POST)
//  private @ResponseBody FlightModel updateFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
//      flightService.addFlight(flight);
//      return flight;
//  }
}