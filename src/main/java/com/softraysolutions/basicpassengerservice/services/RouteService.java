package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.models.Company;
import com.softraysolutions.basicpassengerservice.models.Route;
import com.softraysolutions.basicpassengerservice.repositories.RouteRepository;
import com.softraysolutions.basicpassengerservice.requests.CompanyRequest;
import com.softraysolutions.basicpassengerservice.requests.RouteRequest;
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
