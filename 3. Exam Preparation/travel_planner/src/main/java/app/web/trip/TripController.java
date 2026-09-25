package app.web.trip;

import app.mapper.user.UserMapper;
import app.model.dto.trip.TripDto;
import app.model.dto.user.UserDto;
import app.service.trip.TripService;
import app.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/trips")
public class TripController
{
    private final TripService tripService;
    private final UserService userService;

    public TripController(TripService tripService, UserService userService)
    {
        this.tripService = tripService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllTrips()
    {
        List<TripDto> trips = tripService.getAllTrips();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trips");
        modelAndView.addObject("trips", trips);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getTripDetails(@PathVariable UUID id)
    {
        TripDto trip = tripService.getTripById(id);

        if(trip == null)
        {
            return new ModelAndView("redirect:/trips");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trip-details");
        modelAndView.addObject("trip", trip);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreateTripPage()
    {
        TripDto tripDto = TripDto.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trip-create");
        modelAndView.addObject("tripRequest", tripDto);

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createTrip(@Valid @ModelAttribute("tripRequest") TripDto tripDto, BindingResult bindingResult, HttpSession httpSession)
    {
        if(bindingResult.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("trip-create");
            return modelAndView;
        }

        UUID userId = (UUID) httpSession.getAttribute("user_id");

        UserDto creator = userService.getUserById(userId);
        if(creator == null)
        {
            return new ModelAndView("redirect:/login");
        }

        TripDto createdTrip = tripService.createTrip(tripDto, UserMapper.toEntity(creator));

        return new ModelAndView("redirect:/trips/" + createdTrip.getId());
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getEditTripPage(@PathVariable UUID id, HttpSession httpSession)
    {
        TripDto trip = tripService.getTripById(id);

        if(trip == null)
        {
            return new ModelAndView("redirect:/trips");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trip-edit");
        modelAndView.addObject("trip", trip);

        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateTrip(@PathVariable UUID id, @Valid @ModelAttribute("trip") TripDto tripDto, BindingResult bindingResult, HttpSession httpSession)
    {
        if(bindingResult.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("trip-edit");
            modelAndView.addObject("trip", tripDto);
            return modelAndView;
        }

        UUID userId = (UUID) httpSession.getAttribute("user_id");

        TripDto existingTrip = tripService.getTripById(id);
        if(!existingTrip.getCreator().getId().equals(userId))
        {
            return new ModelAndView("redirect:/trips/" + id);
        }

        tripDto.setId(id);
        TripDto updatedTrip = tripService.updateTrip(id, tripDto);

        return new ModelAndView("redirect:/trips/" + updatedTrip.getId());
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteTrip(@PathVariable UUID id, HttpSession httpSession)
    {
        TripDto trip = tripService.getTripById(id);

        if(trip == null)
        {
            return new ModelAndView("redirect:/trips");
        }

        // TODO: Unnecessary check SessionInterceptor already intercepts this
        //        UUID userId = (UUID) httpSession.getAttribute("user_id");
        //        if (userId == null) {
        //            return new ModelAndView("redirect:/login");
        //        }

        tripService.deleteTrip(id);

        return new ModelAndView("redirect:/trips");
    }
}

