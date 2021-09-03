package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.softraysolutions.basicpassengerservice.models.Company;
import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.models.Route;
import com.softraysolutions.basicpassengerservice.repositories.RouteRepository;
import com.softraysolutions.basicpassengerservice.requests.*;
import com.softraysolutions.basicpassengerservice.responses.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class RouteService {

    private final RouteRepository routeRepository;

    private final CompanyService companyService;

    public Response addRoute(RouteRequest routeRequest) throws ParseException {
        Company company = companyService.getCompany(routeRequest.getCompanyId());

        //date format dd/MM/yyyy
        //time format HH:mm

        if(checkDateFormat(routeRequest.getDepartureDate()).equals("Ok.")
        && checkDateFormat(routeRequest.getArrivalDate()).equals("Ok.")
        && checkTimeFormat(routeRequest.getDepartureTime()).equals("Ok.")
        && checkTimeFormat(routeRequest.getArrivalTime()).equals("Ok.")){

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            String sDepartureDateAndTime = routeRequest.getDepartureDate() + " " + routeRequest.getDepartureTime();
            Date departureDateAndTime = formatter.parse(sDepartureDateAndTime);

            String sArrivalDateAndTime = routeRequest.getArrivalDate() + " " + routeRequest.getArrivalTime();
            Date arrivalDateAndTime = formatter.parse(sArrivalDateAndTime);

            Route route = new Route(routeRequest.getDeparturePoint(), departureDateAndTime,
                    routeRequest.getArrivalPoint(),arrivalDateAndTime, company);

            routeRepository.save(route);

            return new Response("Route is saved.", 200);
        }

       return new Response("Invalid date and time format.", 404);
    }

    public Route getRoute(Long id) {
        return routeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route with Id=" + id + " does not exist."));
    }

    public List<Route> getRoutes() {
        List<Route> routes = routeRepository.findAll();
        if (routes.size() == 0) throw new ResourceNotFoundException("No routes found.");
        return routes;
    }

    public Response putRoute(EditRouteRequest editRouteRequest) throws ParseException {
        Optional<Route> route = routeRepository.findById(editRouteRequest.getId());
        if (!route.isPresent()) return new Response("Route with Id=" + editRouteRequest.getId() + " does not exist.", 400);

        if(checkDateFormat(editRouteRequest.getDepartureDate()).equals("Ok.")
                && checkDateFormat(editRouteRequest.getArrivalDate()).equals("Ok.")
                && checkTimeFormat(editRouteRequest.getDepartureTime()).equals("Ok.")
                && checkTimeFormat(editRouteRequest.getArrivalTime()).equals("Ok.")){

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            String sDepartureDateAndTime = editRouteRequest.getDepartureDate() + " " + editRouteRequest.getDepartureTime();
            Date departureDateAndTime = formatter.parse(sDepartureDateAndTime);

            String sArrivalDateAndTime = editRouteRequest.getArrivalDate() + " " + editRouteRequest.getArrivalTime();
            Date arrivalDateAndTime = formatter.parse(sArrivalDateAndTime);

            route.get().setDeparturePoint(editRouteRequest.getDeparturePoint());
            route.get().setDepartureDateAndTime(departureDateAndTime);
            route.get().setArrivalPoint(editRouteRequest.getArrivalPoint());
            route.get().setArrivalDateAndTime(arrivalDateAndTime);

            routeRepository.save(route.get());

            return new Response("Route successfully updated.", 200);
        }
        return new Response("Invalid date and time format.", 404);
    }

    public Response deleteRoute(Long id) {
        Optional<Route> route = routeRepository.findById(id);
        if (!route.isPresent()) return new Response("Route with Id=" + id + " does not exist.", 400);
        routeRepository.delete(route.get());
        return new Response("Route successfully deleted.", 200);
    }

    private String checkDateFormat(String date){
        Pattern p = Pattern.compile("\\d{1,2}/\\d{1,2}/\\d{4}");
        if(!p.matcher(date).matches()) return "Invalid date format.";
        return "Ok.";
    }

    private String checkTimeFormat(String time){
        Pattern p = Pattern.compile("\\d{1,2}:\\d{1,2}");
        if(!p.matcher(time).matches()) return "Invalid time format.";
        return "Ok.";
    }
}
